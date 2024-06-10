package print;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static int maxWidth = 0;
    private static int paddingLength = 3;

    public static void main(String[] args) {
        Map<String, Object> data = new LinkedHashMap<>();
        Map<String, Object> oranges = new LinkedHashMap<>();
        Map<String, Object> mandarin = new LinkedHashMap<>();
        mandarin.put("clementine", null);
        mandarin.put("tangerine", "cheap & juicy!");
        oranges.put("mandarin", mandarin);

        Map<String, Object> apples = new LinkedHashMap<>();
        apples.put("gala", null);
        apples.put("pink lady", null);

        data.put("oranges", oranges);
        data.put("apples", apples);

        printTreeMap(data,"");
//        calculateMaxWidth(data, 0);
//        printRectangleMap(data, 0, "", true, false);
    }

    // 计算最大宽度以用于格式化
    private static void calculateMaxWidth(Map<String, Object> map, int depth) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();

            if (entry.getValue() instanceof Map) {
                maxWidth = Math.max(maxWidth, depth * 3 + entry.getKey().length() + paddingLength);
                calculateMaxWidth((Map<String, Object>) entry.getValue(), depth + 1);
            } else if (entry.getValue() != null) {
                String valueStr = entry.getKey() + ": " + entry.getValue();
                maxWidth = Math.max(maxWidth, depth * 3 + valueStr.length() + paddingLength);
            }
        }
    }

    // 格式化并打印 Map
    private static void printRectangleMap(Map<String, Object> map, int depth, String prefix, boolean isFirst, boolean isLast) {
        String newPrefix = prefix + "│  ";
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            int lineLength = maxWidth - depth * 3;
            String line = key + (entry.getValue() instanceof Map ? "" : (entry.getValue() == null ? "" : ": " + entry.getValue())) + " ";

            if (isFirst && i == 0) { // 第一行
                System.out.println("┌─ " + line + "─".repeat(lineLength - line.length()) + "┐");
            } else if (i == map.size() - 1 && isLast) { // 最后一行
                System.out.println("└──" + "┴─".repeat(depth) + " " + line + "─".repeat(lineLength - line.length()) + "┘");
            } else { // 其他行
                System.out.println(prefix + "├─ " + line + "─".repeat(lineLength - line.length()) + "┤");
            }

            if (entry.getValue() instanceof Map) {
                printRectangleMap((Map<String, Object>) entry.getValue(), depth + 1, newPrefix, false, i == map.size() - 1 && (isLast || depth == 0));
            }
            i++;
        }
    }

    private static void printTreeMap(Map<String, Object> map, String indent) {
        boolean isFirst = true;
        int i = 0;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            boolean isLast = i == map.size() - 1;
            i += 1;
            if (entry.getValue() instanceof Map) {
                // Print the key
                if (isLast) {
                    System.out.println(indent + "└─ " + entry.getKey());
                    isFirst = false;
                } else {
                    System.out.println(indent + "├─ " + entry.getKey());
                }
                // Recursively print the nested map
                if(indent.startsWith("│")){
                    printTreeMap((Map<String, Object>) entry.getValue(), indent + "  ");
                } else if (!isLast){
                    printTreeMap((Map<String, Object>) entry.getValue(), "│" + indent + "  ");
                } else {
                    printTreeMap((Map<String, Object>) entry.getValue(), indent + "  ");
                }
            } else {
                // Print the key-value pair
                if (isLast) {
                    System.out.println(indent + "└─ " + entry.getKey() + (entry.getValue() != null ? ": " + entry.getValue() : ""));
                    isFirst = false;
                } else {
                    System.out.println(indent + "├─ " + entry.getKey() + (entry.getValue() != null ? ": " + entry.getValue() : ""));
                }
            }
        }
    }
}
