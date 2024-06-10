package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"oranges\": {\n" +
                "    \"mandarin\": {\n" +
                "      \"clementine\": null,\n" +
                "      \"tangerine\": \"cheap & juicy!\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"apples\": {\n" +
                "    \"gala\": null,\n" +
                "    \"pink lady\": null\n" +
                "  }\n" +
                "}";
//        System.out.println(json);
        Map<String, Object> result = parseJson(json);
        result.forEach((key, value) ->
                System.out.println(key + ": " + value));
    }

    private static Map<String, Object> parseJson(String json) {
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            return parseObject(json.substring(1, json.length() - 1));
        }
        return new HashMap<>();
    }

    private static Map<String, Object> parseObject(String json) {
        Map<String, Object> map = new HashMap<>();
//        json = json.substring(1, json.length() - 1).trim(); // Remove the curly braces

        String key = null;
        StringBuilder value = new StringBuilder();
        int braces = 0;
        boolean inQuotes = false;
        int isNull = 0;

        for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                    if (!inQuotes) {
                        braces++;
                    }
                    value.append(c);
                    break;
                case '}':
                    if (!inQuotes) {
                        braces--;
                    }
                    value.append(c);
                    if (braces == 0 && key != null) { // End of nested object
                        String valueString = value.toString().trim();

                        if(valueString.startsWith("{") && valueString.endsWith("}")) {
                            map.put(key, parseObject(valueString.substring(1, valueString.length() - 1)));
                        } else {
                            if(isNull == 4){
                                map.put(key, null);
                            } else {
                                map.put(key, valueString);
                            }
                        }
                        key = null;
                        value = new StringBuilder();
                    }
                    break;
                case '\"':
                    inQuotes = !inQuotes;
                    if (braces != 0) {
                        value.append(c);
                    }
                    break;
                case ':':
                    if (!inQuotes && braces == 0 && key == null) {
                        key = value.toString().trim().replace("\"", "").replace("'", "");
                        value = new StringBuilder();
                    } else {
                        value.append(c);
                    }
                    break;
                case ',':
                    if (!inQuotes && braces == 0) {
                        if (value.toString().trim().equals("null")) {
                            map.put(key, null);
                        } else if (!value.toString().isEmpty()) {
                            map.put(key, value.toString().trim());
                        }
                        key = null;
                        value = new StringBuilder();
                    } else {
                        value.append(c);
                    }
                    break;
                default:
                    if(inQuotes || c == 'n' || c == 'u' || c == 'l') {
                        value.append(c);
                    }
            }
        }

        if (key != null && !value.toString().isEmpty()) {
            if (value.toString().trim().equals("null")) {
                map.put(key, null);
            } else {
                map.put(key, value.toString().trim().replace("\"", "").replace("'", ""));
            }
        }

        return map;
    }
}
