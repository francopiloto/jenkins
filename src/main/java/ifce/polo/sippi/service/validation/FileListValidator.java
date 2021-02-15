package ifce.polo.sippi.service.validation;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ifce.polo.sippi.annotation.FileList;
import ifce.polo.sippi.model.Arquivo;

public class FileListValidator implements ConstraintValidator<FileList, Collection<Arquivo>>
{
    private FileList annotation;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(FileList annotation) {
        this.annotation = annotation;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isValid(Collection<Arquivo> arquivos, ConstraintValidatorContext context)
    {
        context.disableDefaultConstraintViolation();

        if (arquivos == null || arquivos.isEmpty())
        {
            if (annotation.required())
            {
                context.buildConstraintViolationWithTemplate("required").addConstraintViolation();
                return false;
            }

            return true;
        }

        if (annotation.maxfiles() > 0 && arquivos.size() > annotation.maxfiles())
        {
            context.buildConstraintViolationWithTemplate("maxfiles").addConstraintViolation();
            return false;
        }

        for (Arquivo arquivo : arquivos)
        {
            if (annotation.filesize() > 0 && arquivo.size() > annotation.filesize())
            {
                context.buildConstraintViolationWithTemplate("filesize").addConstraintViolation();
                return false;
            }

            if (!accept(arquivo.getNome()))
            {
                context.buildConstraintViolationWithTemplate("accept").addConstraintViolation();
                return false;
            }
        }

        return true;
    }

/* --------------------------------------------------------------------------------------------- */

    private boolean accept(String filename)
    {
        filename = filename.toLowerCase();

        for (String type : annotation.accept())
        {
            if (filename.endsWith(type.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

/* --------------------------------------------------------------------------------------------- */

}
