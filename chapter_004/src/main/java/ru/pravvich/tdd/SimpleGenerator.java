package ru.pravvich.tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replace key in string on value from HashMap.
 */
class SimpleGenerator implements Template {

    /**
     * Pattern for determines key.
     */
    private final Pattern pattern = Pattern.compile("\\$\\{\\w*?\\}");

    /**
     * Method for replace key on value.
     *
     * @param text text for processing.
     * @param data - map with set key-value.
     * @return new string with replacement keys on values.
     * @throws KeyNotFoundException - if key not found.
     */
    @Override
    public String generate(String text, Object data) throws KeyNotFoundException {
        Map<String, String> map = (Map<String, String>) data;
        String result = text;
        Matcher matcher = this.pattern.matcher(result);

        while (matcher.find()) {
            String equal = map.get(matcher.group()); // получаем значение по ключу
            this.keyExist(equal); // кидаем исключение если нет ключа
            result = matcher.replaceFirst(equal); // заменяем ключ на значение
            matcher.reset(result); // перезаписываем в matcher обновленный вариант
        }

        return result;
    }

    /**
     * Method throws exception if key not found.
     *
     * @param value for validate on null.
     * @throws KeyNotFoundException if value equal null.
     */
    private void keyExist(String value) throws KeyNotFoundException {
        if (value == null)
            throw new KeyNotFoundException("Key not found.");
    }
}
