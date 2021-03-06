import grails.databinding.converters.ValueConverter
import com.torntrading.utils.DatabaseMessageSource
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import com.torntrading.portal.AuthenticationSuccessEventListener
beans = {
    localeResolver(SessionLocaleResolver) { 
        defaultLocale= new java.util.Locale("en","GB") 
  }
}
beans = {
    authenticationSuccessEventListener(AuthenticationSuccessEventListener)
}

// Place your Spring DSL code here
beans = {
    "defaultGrailsjava.lang.DoubleConverter"(DoubleValueConverter)
//    messageSource(DatabaseMessageSource)
}
class DoubleValueConverter implements ValueConverter {

    public LongValueConverter() {
    }

    boolean canConvert(value) {
        value instanceof Double
    }

    def convert(value) {
        //In my case returning the same value did the trick but you can define
        //custom code that takes care about comma and point delimiter...
        return value
    }

    Class<?> getTargetType() {
        return Double.class
    }
}