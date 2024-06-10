package org.example.printer;

import org.example.icon.Icon;

import java.util.HashMap;
import java.util.Map;

public class RectanglePrinter implements JsonPrinter{
    private static int maxWidth = 0;
    private static int paddingLength = 3;
    private static Icon icon;
    @Override
    public void print(HashMap map, Icon icon) {
        this.icon = icon;
        calculateMaxWidth(map, 0);
        printRectangleMap(map, 0, "", true, false);
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
            boolean isLeaf = !(entry.getValue() instanceof Map);
            if (isFirst && i == 0) { // 第一行
                System.out.println("┌─" + (isLeaf ? icon.getLeaf() : icon.getComposite()) + line + "─".repeat(lineLength - line.length()) + "┐");
            } else if (i == map.size() - 1 && isLast) { // 最后一行
                System.out.println("└──" + "┴─".repeat(depth) + (isLeaf ? icon.getLeaf() : icon.getComposite()) + line + "─".repeat(lineLength - line.length()) + "┘");
            } else { // 其他行
                System.out.println(prefix + "├─" + (isLeaf ? icon.getLeaf() : icon.getComposite()) + line + "─".repeat(lineLength - line.length()) + "┤");
            }

            if (entry.getValue() instanceof Map) {
                printRectangleMap((Map<String, Object>) entry.getValue(), depth + 1, newPrefix, false, i == map.size() - 1 && (isLast || depth == 0));
            }
            i++;
        }
    }
}
