package ifce.polo.sippi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ifce.polo.sippi.service.converter.MultipartFileConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MultipartFileConverter());
    }

/* --------------------------------------------------------------------------------------------- */

}
