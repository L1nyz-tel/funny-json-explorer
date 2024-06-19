package org.example;

import org.example.context.Context;
import org.example.factory.JsonIconFactory;
import org.example.factory.JsonPrinterFactory;
import org.example.icon.Icon;
import org.example.printer.JsonPrinter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.example.parser.JsonParser.readJson;

/**
 * Hello world!
 *
 */
public class FunnyJsonExplorer
{
    private static JsonPrinter jsonPrinter;
    private static Icon icon;
    public static void main( String[] args )
    {
        Map<String, String> params = new HashMap<>();
        // 解析命令行参数
        for (int i = 0; i < args.length; i++) {
            if ("-f".equals(args[i]) && i + 1 < args.length) {
                params.put("file", args[i + 1]);
            } else if ("-s".equals(args[i]) && i + 1 < args.length) {
                params.put("style", args[i + 1]);
            } else if ("-i".equals(args[i]) && i + 1 < args.length) {
                params.put("icon", args[i + 1]);
            }
        }

        // 读取和解析 JSON 文件
        if (params.containsKey("file")) {
            String filePath = params.get("file");
            try {
                HashMap jsonMap = (HashMap) readJson(filePath);
                icon = JsonIconFactory.createIcon(params.getOrDefault("icon", "default"));

                Context context = new Context();
                context.setStrategy(JsonPrinterFactory.createPrinter(params.getOrDefault("style", "default")));
                context.execute(jsonMap, icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No JSON file provided!");
        }
    }

}
