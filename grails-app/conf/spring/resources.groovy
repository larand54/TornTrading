import grails.databinding.converters.ValueConverter
// Place your Spring DSL code here
beans = {
    "defaultGrailsjava.lang.DoubleConverter"(DoubleValueConverter)
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