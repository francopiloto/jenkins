package ifce.polo.sippi.service;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ifce.polo.sippi.security.UserCredentials;

@Component
public class ContextProvider implements ApplicationContextAware
{
    private static ApplicationContext context;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

/* --------------------------------------------------------------------------------------------- */

    public static <T> Collection<T> getBeans(Class<T> type) {
        return context.getBeansOfType(type).values();
    }

/* --------------------------------------------------------------------------------------------- */

    public static UserCredentials getCredentials() {
        return (UserCredentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

/* --------------------------------------------------------------------------------------------- */

}
