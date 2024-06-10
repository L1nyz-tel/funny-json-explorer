package org.example.factory;

import org.example.icon.Icon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.example.parser.JsonParser.readJson;

public class JsonIconFactory {
    public static Icon createIcon(String iconFamily) throws IOException {
        switch (iconFamily){
            case "poker":
                return new Icon('♢', '♤');
            case "custom":
                Map<String, Object> icons = readJson("config.json");
                return new Icon((String) icons.get("composite"), (String) icons.get("leaf"));
            default:
                return new Icon(' ', ' ');
        }
    }
}
