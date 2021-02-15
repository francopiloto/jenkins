package ifce.polo.sippi.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import ifce.polo.sippi.model.Arquivo;

public class MultipartFileConverter implements Converter<MultipartFile, Arquivo>
{

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Arquivo convert(MultipartFile source)
    {
        try
        {
            if (source.getOriginalFilename() == null || source.getOriginalFilename().isEmpty()) {
                return null;
            }

            Arquivo arquivo = new Arquivo();
            arquivo.setNome(source.getOriginalFilename());
            arquivo.setDados(source.getBytes());

            return arquivo;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
