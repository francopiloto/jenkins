package ifce.polo.sippi.service.validation;

import static ifce.polo.sippi.util.EntityUtils.BASE_PACKAGE_NAME;
import static ifce.polo.sippi.util.StringUtils.capitalCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.annotation.AsyncValidation;
import ifce.polo.sippi.annotation.CNPJ;
import ifce.polo.sippi.annotation.CPF;
import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.annotation.FileList;
import ifce.polo.sippi.annotation.Flag;
import ifce.polo.sippi.annotation.ItemList;
import ifce.polo.sippi.annotation.Lattes;
import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.annotation.Name;
import ifce.polo.sippi.annotation.Password;
import ifce.polo.sippi.annotation.PhoneNumber;
import ifce.polo.sippi.annotation.Required;
import ifce.polo.sippi.annotation.URL;
import ifce.polo.sippi.annotation.UpperCase;
import ifce.polo.sippi.annotation.Username;

public class ConstraintMapper
{
    private final Class<?> target;
    private final String[] include;
    private final ObjectMapper mapper;

/* --------------------------------------------------------------------------------------------- */

    public ConstraintMapper(ObjectMapper mapper, Object target, String[] include)
    {
        this.mapper = mapper;
        this.target = target instanceof Class<?> ? (Class<?>)target : target.getClass();
        this.include = include;
    }

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode execute() {
        return include != null ? parseSelectedFields() : parseFields(target);
    }

/* --------------------------------------------------------------------------------------------- */

    private ObjectNode parseSelectedFields()
    {
        StringBuilder sb = new StringBuilder("get");
        ObjectNode rules = mapper.createObjectNode();

        for (String name : include)
        {
            Method method = null;
            Class<?> target = this.target;

            String[] tokens = name.split("[.]");

            for (String token : tokens)
            {
                if (method != null) {
                    target = getReturnType(method);
                }

                sb.setLength(3);
                capitalCase(token, sb);

                try {
                    method = target.getMethod(sb.toString());
                }
                catch (Exception e)
                {
                    method = null;
                    break;
                }
            }

            if (method != null) {
                parseField(method, rules);
            }
        }

        return rules;
    }

/* --------------------------------------------------------------------------------------------- */

    private ObjectNode parseFields(Class<?> target)
    {
        ObjectNode rules = mapper.createObjectNode();

        for (Method method : target.getMethods())
        {
            String methodName = method.getName();

            if (!method.isBridge() &&
                !methodName.startsWith("getId") &&
                methodName.startsWith("get") &&
                method.getDeclaringClass().getName().startsWith(BASE_PACKAGE_NAME) &&
                method.getAnnotation(Transient.class) == null)
            {
                parseField(method, rules);
            }
        }

        return rules;
    }

/* --------------------------------------------------------------------------------------------- */

    private void parseField(Method method, ObjectNode rules)
    {
        Annotation[] annotations = method.getDeclaredAnnotations();

        if (annotations.length == 0) {
            return;
        }

        String methodName = method.getName();

        ObjectNode rule = method.getAnnotation(Valid.class) == null ? parseAnnotations(annotations, null)
                : parseFields(getReturnType(method));

        if (rule.size() > 0)
        {
            String fieldName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);

            if (rule.has("required") && rule.has("minlength") && rule.get("minlength").asInt() == 1) {
                rule.remove("minlength");
            }

            if (rule.has("async"))
            {
                rule.remove("async");
                rule.put("async", fieldName);
            }

            rules.set(fieldName, rule);
        }
    }

/* --------------------------------------------------------------------------------------------- */

    private ObjectNode parseAnnotations(Annotation[] annotations, ObjectNode rule)
    {
        if (rule == null) {
            rule = mapper.createObjectNode();
        }

        for (Annotation annotation : annotations)
        {
            if (annotation instanceof NotNull || annotation instanceof Required) {
                rule.put("required", true);
            }
            else if (annotation instanceof Size)
            {
                Size size = (Size) annotation;
                setSize(size.min(), size.max(), rule);
            }
            else if (annotation instanceof Mask) {
                setMask((Mask) annotation, rule);
            }
            else if (annotation instanceof AsyncValidation) {
                rule.put("async", true);
            }
            else if (annotation instanceof Min) {
                rule.put("min", ((Min)annotation).value());
            }
            else if (annotation instanceof Flag) {
                rule.put(((Flag) annotation).value(), true);
            }
            else if (annotation instanceof UpperCase) {
                rule.put("case", "upper");
            }
            else if (annotation instanceof ItemList) {
                setItemList((ItemList) annotation, rule);
            }
            else if (annotation instanceof Email || annotation instanceof PhoneNumber || annotation instanceof Name
                    || annotation instanceof CPF || annotation instanceof CNPJ || annotation instanceof Username
                    || annotation instanceof Password || annotation instanceof URL || annotation instanceof Lattes) {
                parseAnnotations(annotation.annotationType().getDeclaredAnnotations(), rule);
            }
            else if (annotation instanceof FileList) {
                setFileList((FileList) annotation, rule);
            }
        }

        return rule;
    }

/* --------------------------------------------------------------------------------------------- */

    private void setMask(Mask mask, ObjectNode rule)
    {
        ObjectNode node = mapper.createObjectNode();

        rule.set("mask", node);
        node.put("pattern", mask.value());

        if (mask.regex()) {
            node.put("regex", true);
        }

        if (mask.reverse()) {
            node.put("reverse", true);
        }
    }

/* --------------------------------------------------------------------------------------------- */

    private static void setSize(int min, int max, ObjectNode rule)
    {
        if (min > 0) {
            rule.put("minlength", min);
        }

        if (max > 0) {
            rule.put("maxlength", max);
        }
    }

/* --------------------------------------------------------------------------------------------- */

    private static void setItemList(ItemList list, ObjectNode rule)
    {
        setSize(list.min(), list.max(), rule);

        if (list.min() > 0) {
            rule.put("required", true);
        }
    }

/* --------------------------------------------------------------------------------------------- */

    private static void setFileList(FileList file, ObjectNode rule)
    {
        if (file.required()) {
            rule.put("required", true);
        }

        String[] accept = file.accept();

        if (accept != null && accept.length > 0)
        {
            ArrayNode array = rule.putArray("accept");

            for (String type : accept) {
                array.add(type);
            }
        }

        if (file.filesize() > 0) {
            rule.put("filesize", file.filesize());
        }

        if (file.maxfiles() > 0) {
            rule.put("maxfiles", file.maxfiles());
        }
    }

/* --------------------------------------------------------------------------------------------- */

    private static Class<?> getReturnType(Method method)
    {
        Class<?> type = method.getReturnType();

        if (type.equals(List.class)) {
            return (Class<?>) (((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0]);
        }

        return type;
    }

/* --------------------------------------------------------------------------------------------- */

}
