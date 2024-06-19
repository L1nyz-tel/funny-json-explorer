package org.example.context;

import org.example.icon.Icon;
import org.example.printer.JsonPrinter;

import java.util.HashMap;

public class Context {
    JsonPrinter strategy;

    public void setStrategy(JsonPrinter jsonPrinter) {
        this.strategy = jsonPrinter;
    }

    public void execute(HashMap map, Icon icon) {
        strategy.print(map, icon);
    }
}
