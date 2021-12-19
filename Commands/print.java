//доделана проверена
package Commands;
import java.util.Map;
public class print {
    public print(Map<String, String> params){
        try {
            String text = "";
            for (int i = 0; i != -1; i++){
                String key = "arg" + String.valueOf(i);
                if (params.containsKey(key)){
                    text += params.get(key) + " ";
                } else {
                    i = -2;
                }
            }
            System.out.print(text + "\n");
        } catch (Exception e) {
            System.out.println("printing error: " + e.getMessage());
        }
    }
}