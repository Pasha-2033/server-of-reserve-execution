//доделана проверена
package Commands;
import java.util.List;
import java.util.Map;
import MainFolder.Var;
public class exit {
    public exit(Map<String, String> params){
        if (params == null){
            System.exit(0);
            return;
        }
        if (params.containsKey("code")){
            System.exit(Integer.parseInt(params.get("code")));
        } else {
            System.exit(0);
        }
    }
    public exit(List<Var<?>> params){
        if (params == null){
            System.exit(0);
            return;
        }
        Var<?> v = Var.getbyname(params, "code");
        if (v != null){
            System.exit(Integer.parseInt((String) v.getvalue()));
        } else {
            System.exit(0);
        }
    }
}