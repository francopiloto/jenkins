package ifce.polo.sippi.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalBindingInitializer
{

/* --------------------------------------------------------------------------------------------- */

    /**
     * Using null values instead of empty strings on form submit.
     */
    @InitBinder
    public void allowNullDataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEditor());
    }

/* --------------------------------------------------------------------------------------------- */

    private static class StringEditor extends StringTrimmerEditor
    {
        public StringEditor() {
            super(true);
        }

        @Override
        public void setAsText(String text) {
            super.setAsText(text != null ? text.replaceAll("\r\n", "\n") : null);
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
