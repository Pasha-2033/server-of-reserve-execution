package MainFolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Var<value> {
    public Var(){}
    public Var(value value){
        this.value = value;
        try{
            this.instance = value.getClass();
        } catch (Exception e){
            this.instance = null;
        }
    }
    public Var(String name, value value){
        this.name = name;
        this.value = value;
        try{
            this.instance = value.getClass();
        } catch (Exception e){
            this.instance = null;
        }
    }
    private String name = "var" + Main.vars.size();
    private value value = null;
    private Class<?> instance = null;
    public final String getname(){
        return name;
    }
    public final value getvalue(){
        return value;
    }
    public final Class<?> getinstance(){
        return instance;
    }
    public final void setvalue(value value){
        this.value = value;
        try{
            this.instance = value.getClass();
        } catch (Exception e){
            this.instance = null;
        }
    }
    public static Var<?> getemptyvar(){
        //fix
        return new Var<>("empty" + Main.vars.size(), null);
    }
    public static final Var<?> getbyname(List<Var<?>> varlist, String name){
        for (Var<?> var : varlist) if (var.name.equals(name)) return var;
        return null;
    }
    public static final List<Var<?>> getallbyname(List<Var<?>> varlist, String name){
        List<Var<?>> vars = new ArrayList<Var<?>>(Collections.emptyList());
        for (Var<?> var : varlist) if (var.name.equals(name)) vars.add(var);
        if (vars.size() == 0) return null;
        return vars;
    }
}