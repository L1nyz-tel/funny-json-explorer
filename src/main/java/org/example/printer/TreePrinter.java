package org.example.printer;

import org.example.icon.Icon;

import java.util.HashMap;
import java.util.Map;

public class TreePrinter implements JsonPrinter {
    private static Icon icon;
    @Override
    public void print(HashMap map, Icon icon) {
        this.icon = icon;
        printTreeMap(map, "");
    }

    private static void printTreeMap(Map<String, Object> map, String indent) {
        int i = 0;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            boolean isLast = i == map.size() - 1;
            i += 1;
            if (entry.getValue() instanceof Map) {
                // Print the key
                if (isLast) {
                    System.out.println(indent + "└─" + icon.getComposite() +entry.getKey());
                } else {
                    System.out.println(indent + "├─" + icon.getComposite() + entry.getKey());
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
                    System.out.println(indent + "└─" + icon.getLeaf() + entry.getKey() + (entry.getValue() != null ? ": " + entry.getValue() : ""));
                } else {
                    System.out.println(indent + "├─" + icon.getLeaf() + entry.getKey() + (entry.getValue() != null ? ": " + entry.getValue() : ""));
                }
            }
        }
    }
}
