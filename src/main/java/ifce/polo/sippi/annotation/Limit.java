package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
public @interface Limit
{
    int min() default 0;
    int max() default Integer.MAX_VALUE;
}
