//доделана проверена
package Commands;
import java.util.List;
import MainFolder.Var;
public class exit {
    public exit(){}
    public Var<?> execute(List<Var<?>> params){
        if (params == null){
            System.exit(0);
        }
        for (Var<?> v : params){
            System.out.println(v.getname() + " " + v.getvalue());
        }
        Var<?> v = Var.getbyname(params, "code");
        if (v != null){
            System.exit(Integer.parseInt((String) v.getvalue()));
        } else {
            System.exit(0);
        }
        return null;
    }
}