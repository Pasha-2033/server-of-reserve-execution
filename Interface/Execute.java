package Interface;
import java.util.List;
import java.util.Map;
import MainFolder.Var;
public class Execute {
    public static void ExecuteCommand(String command, Map<String, String> params) {
        if (command == null) return;
        try {
            Class.forName("Commands." + command).getConstructor(Map.class).newInstance(params);
        } catch (ClassNotFoundException e) {
            System.out.println("command is not found");
        } catch (Exception e){
            System.out.println("executing error: " + e.getMessage());
        }
    }
    public static Class<?> ExecuteCommand(String command, List<Var<?>> params){
        if (command == null) return null;
        try {
            return (Class<?>) Class.forName("Commands." + command).getConstructor(List.class).newInstance(params);
        } catch (ClassNotFoundException e) {
            System.out.println("command is not found");
        } catch (Exception e){
            System.out.println("executing error: " + e.getMessage());
        }
        return null;
    }
}