package MainFolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Connection.Connections;
import Interface.CommandListener;
public class Main {
    public static void main(String[] args){
        Сonnections = new Connections();
        Сondition = new SereverCondition();
        Сommandlistener = new CommandListener();
        vars = new ArrayList<Var<?>>(Collections.emptyList());
    }
    public static Connections Сonnections;
    public static SereverCondition Сondition;
    public static CommandListener Сommandlistener;
    public static List<Var<?>> vars;
}