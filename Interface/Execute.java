package Interface;
import java.util.List;
import MainFolder.Var;
public class Execute {
    public static Class<?> ExecuteCommand(String command, List<Var<?>> params){
        if (command == null) return null;
        try {
            Class.forName("Commands." + command).getConstructor(List.class).newInstance(params);
        } catch (ClassNotFoundException e) {
            System.out.println("Executing error: command (" + command + ") is not found");
        } catch (Exception e){
            System.out.println("Executing error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static Var<?> ExecuteCommand2(String command, List<Var<?>> params){
        if (command == null || command == "") return null;
        try {
            Class<?> clazz = Class.forName("Commands." + command);
            return (Var<?>) clazz.getDeclaredMethod("execute", List.class).invoke(clazz.getConstructor().newInstance(), params);
        } catch (ClassNotFoundException e) {
            System.out.println("Executing error: command (" + command + ") is not found");
        } catch (Exception e){
            System.out.println("Executing error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}