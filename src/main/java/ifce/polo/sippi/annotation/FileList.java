package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ifce.polo.sippi.service.validation.FileListValidator;

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = FileListValidator.class)
public @interface FileList
{
    boolean required() default true;
    int maxfiles() default 5;
    String[] accept() default {"pdf"};
    long filesize() default 5242880;

    String message() default "filelist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
