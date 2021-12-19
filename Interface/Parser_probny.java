package Interface;
import java.util.LinkedHashMap;
import java.util.Map;
public class Parser_probny {
    public static Object[] ParseCommandLine(String[] args) {
        String[] commandline = ParseCommand(args);
        if (commandline == null) return null;
        if (commandline.length == 1) return new Object[]{commandline[0], null};
        Map<String, String> params = ParseArgument(new String[]{commandline[1]});
        String command = commandline[0];
        return new Object[]{command, params};
    }
    public static String[] ParseCommand(String[] args) {
        if (args.length > 0){
            if (args[0].length() == 0) return null;
            return args[0].split(" ", 2);
        } else {
            return null;
        }
    }
    public static Map<String, String> ParseArgument(String[] args) {
        Map<String, String> parameters = new LinkedHashMap<>();
        int i  = 0;
        String[] parts = args[0].split(" ");  
        for (String arg : parts) {
            String[] keyval = arg.split("=");
            String key;
            String value;
            if (keyval.length > 1){
                key = keyval[0];
                value = keyval[1];
                if (key.startsWith("--")) {
                    key = key.substring(2);
                } else {
                    key = key.substring(1);
                }
                parameters.put(key, value);
            } else if (keyval.length == 1){
                key = "arg" + String.valueOf(i);
                value = keyval[0];
                parameters.put(key, value);
            }
            i++;
        }
        return parameters;
    }
}
