package ifce.polo.sippi.util;

import java.lang.reflect.Method;

import javax.persistence.Transient;

import ifce.polo.sippi.SippiApi;

public class EntityUtils
{
    public static final String BASE_PACKAGE_NAME = SippiApi.class.getPackage().getName();

/* --------------------------------------------------------------------------------------------- */

    public static final boolean isAttributeMethod(Method method)
    {
        return (method.getName().startsWith("get") || method.getName().startsWith("is")) &&
               (!method.isBridge()) &&
               (method.getDeclaringClass().getName().startsWith(BASE_PACKAGE_NAME)) &&
               (method.getDeclaredAnnotation(Transient.class) == null);
    }

/* --------------------------------------------------------------------------------------------- */

}
