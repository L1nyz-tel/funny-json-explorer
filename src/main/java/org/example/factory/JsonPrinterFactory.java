package org.example.factory;

import org.example.printer.JsonPrinter;
import org.example.printer.RectanglePrinter;
import org.example.printer.TreePrinter;

public class JsonPrinterFactory {
    public static JsonPrinter createPrinter(String style) {
        switch (style) {
            case "tree":
                return new TreePrinter();
            case "rectangle":
                return new RectanglePrinter();
            default:
                return new TreePrinter();
        }
    }
}
