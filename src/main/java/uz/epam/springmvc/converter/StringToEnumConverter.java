package uz.epam.springmvc.converter;

import org.springframework.core.convert.converter.Converter;
import uz.epam.springmvc.bean.Gender;

public class StringToEnumConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String s) {
       if (s.equalsIgnoreCase("male")) {
           return Gender.MALE;
       }else if (s.equalsIgnoreCase("female")){
           return Gender.FEMALE;
       }else {
           return Gender.OTHER;
       }
    }
}
