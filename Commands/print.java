//доделана проверена
package Commands;
import java.util.List;
import MainFolder.Var;
public class print {
    public print(){}
    public Var<?> execute(List<Var<?>> params){
        if (params == null){
            System.out.println("Printing error: arguments can`t be null");
            return Var.getemptyvar();
        }
        if (Var.getbyname(params, "arg0") == null){
            System.out.println("Printing error: arguments should have values (starts from argN, where N = ℕ)");
            return Var.getemptyvar();
        }
        String text = "";
        for (int i = 0; true; i++){
            String key = "arg" + String.valueOf(i);
            Var<?> v = Var.getbyname(params, key);
            if (v != null){
                try {
                    text += v.getvalue() + " ";
                } catch (Exception e){
                    System.out.println("Printing error: arguments should have instance of String");
                    return Var.getemptyvar();
                }
            } else {
                break;
            }
        }
        System.out.println(text);
        return Var.getemptyvar();
    }
}