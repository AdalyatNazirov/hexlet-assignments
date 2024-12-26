package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        Class<?> clazz = object.getClass();
        List<String> result = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (field.getDeclaredAnnotation(NotNull.class) != null && value == null) {
                    result.add(field.getName());
                }
            } catch (IllegalAccessException e) {
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Class<?> clazz = object.getClass();
        Map<String, List<String>> result = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                var notNull = field.getDeclaredAnnotation(NotNull.class);
                if (notNull != null && value == null) {
                    result.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                            .add("Field cannot be null");
                }

                var minLength = field.getDeclaredAnnotation(MinLength.class);
                if (minLength != null && value instanceof String && ((String) value).length() < minLength.minLength()) {
                    result.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                            .add("Field should be at list " + minLength.minLength() + " in length");
                }

            } catch (IllegalAccessException e) {
            }
        }

        return result;
    }
}
// END
