package Interface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import MainFolder.Main;
import MainFolder.Var;
public class Parser {
    public static final void readexecutecommand(String commandline){
        List<String> commands = parsecommandline(commandline);
        if (commands == null || commands.size() == 0) return;
        execute(commands.get(0), commands);
    }
    public static final List<String> parsecommandline(String commandline){
        if (!iscorrectcomand(commandline)) return null;
        List<String> splitedcommandline = new ArrayList<String>(Collections.emptyList());
        List<String> result = new ArrayList<String>(Collections.emptyList());
        splitedcommandline = splitcommandline(commandline);
        result = uniocommandline(splitedcommandline);
        //--------------------------------------
        /*for (String str : result){
            System.out.println(str); 
        }*/
        //--------------------------------------
        return result;
    }
    private static List<String> splitcommandline(String commandline){
        String[] data = commandline.split("\\$");
        if (data.length == 1) return new ArrayList<String>(Arrays.asList(commandline));
        List<String> result = new ArrayList<String>(Collections.emptyList());
        String buffer = "";
        for (int i = 1; i < data.length; i++){
            if (data[i - 1].split("")[data[i - 1].length() - 1].equals("\\") || !data[i].split("")[0].equals("(")){
                if (buffer.length() == 0){
                    buffer += data[i - 1] + "$" + data[i];
                } else {
                    buffer += "$" + data[i];
                }
                if (i + 1 == data.length) result.add("$" + buffer);
            } else {
                if (i == 1) result.add(data[0]);
                if (buffer.length() != 0) result.add("$" + buffer);
                buffer = "";
                if (!data[i].split("")[data[i].length() - 1].equals("\\") || i + 1 == data.length){
                    if (i + 1 < data.length){
                        if (data[i + 1].split("")[0].equals("(")) result.add("$" + data[i]);
                    } else {
                        result.add("$" + data[i]);
                    }
                }
            }
        }
        return result;
    }
    private static List<String> uniocommandline(List<String> splitedcommandline){
        if (splitedcommandline.size() == 0) return null;
        List<CommandHelper> commands = lvlspliting(splitedcommandline);
        List<String> commandlines = new ArrayList<String>(Collections.emptyList());
        for (int i = 0; i < commands.size(); i++){
            int minbufferlvl = commands.get(i).lvl;
            if (isfirst(commands, i)){
                String buffer = "";
                for (int ii = i; ii < commands.size(); ii++){
                    if (commands.get(ii).lvl >= minbufferlvl){
                        buffer += commands.get(ii).code;
                        if (ii + 1 == commands.size()) {
                            if (!buffer.equals(")") && buffer != "") commandlines.add(buffer);
                        }
                    } else {
                        if (!buffer.equals(")") && buffer != "") commandlines.add(buffer);
                        break;
                    }
                }
            }
        }
        return commandlines;
    }
    private static List<CommandHelper> lvlspliting(List<String> splitedcommandline){
        List<CommandHelper> commands = new ArrayList<CommandHelper>(Collections.emptyList());
        int lvl = 0;
        for (String str : splitedcommandline){
            if (str.contains(")")){
                for (String substr : splitlvlcommand(str)){
                    commands.add(new Parser.CommandHelper(substr, lvl));
                    lvl--;
                }
                if (commands.get(commands.size() - 1).code.endsWith(")")) {
                    commands.add(new Parser.CommandHelper("", lvl));
                } else {
                    lvl++;
                }
            } else {
                commands.add(new Parser.CommandHelper(str, lvl));
            }
            lvl++;
        }
        commands.add(new Parser.CommandHelper("", lvl));
        return commands;
    }
    private static List<String> splitlvlcommand(String command){
        if (command.length() == 0) return new ArrayList<String>(Collections.emptyList());
        if (command.length() == 1) return new ArrayList<String>(Arrays.asList(command));
        List<String> result = new ArrayList<String>(Collections.emptyList());
        int start = 0;
        char[] c = command.toCharArray();
        for (int end = 1; end < c.length; end++){
            if (c[end] == ')' && c[end - 1] != '\\'){
                result.add(command.substring(start, end + 1));
                start = end + 1;
            } else {
                if (end + 1 == c.length) result.add(command.substring(start, end + 1));
            }
        }
        return result;
    }
    private static String findinnercommand(String command, String argname, List<String> allcommands){
        String tmp = command.substring(command.indexOf(argname) + argname.length() + 1);
        if (tmp.length() == 0) return null;
        List<String> sizecommands = new ArrayList<String>(Collections.emptyList());
        if (allcommands.isEmpty()) return null;
        sizecommands.add(allcommands.get(0));
        for (int i = 1; i < allcommands.size(); i++){
            for (int ii = 0; ii < sizecommands.size(); ii++){
                if (sizecommands.get(ii).length() > allcommands.get(i).length()){
                    sizecommands.add(ii, allcommands.get(i));
                    break;
                }
            }
        }
        for (String s : sizecommands){
            if (tmp.startsWith(s)) return s.substring(2, s.length() - 1);
        }
        return null;
    }
    private static String[] parsecommand(String command){
        if (command.startsWith("$(")) command = command.substring(2, command.length() - 2);
        return command.split(" ", 2);
    }
    private static List<Var<?>> parsearg(String args, List<String> allcommands) {
        List<Var<?>> parameters = new ArrayList<Var<?>>(Collections.emptyList());
        String[] parts = args.split(" ");
        int i  = 0;  
        for (String arg : parts) {
            String[] keyval = arg.split("=");
            if (keyval.length > 1){
                String key = keyval[0];
                String value = keyval[1];
                if (value.startsWith("$(")){
                    value = findinnercommand(args, key, allcommands);
                    if (value == null){
                        System.out.println("Parser error: argument (" + key + ") doesn`t have existing command");
                        return new ArrayList<Var<?>>(Collections.emptyList());
                    }
                    if (value == ""){
                        System.out.println("Parser error: argument (" + key + ") doesn`t have empty command");
                        return new ArrayList<Var<?>>(Collections.emptyList());
                    }
                    parameters.add(new Var<>(key, execute(value, allcommands).getvalue()));
                } else if (value.startsWith("$")){
                    Var<?> v = Var.getbyname(Main.vars, value.substring(1));
                    if (v != null){
                        parameters.add(Var.getbyname(Main.vars, value.substring(1)));
                    } else {
                        System.out.println("Parser error: var (" + value.substring(1) + ") is not found");
                        return new ArrayList<Var<?>>(Collections.emptyList());
                    }
                } else {
                    Var<?> v = Var.getbyname(parameters, key);
                    if (v != null){
                        parameters.set(parameters.indexOf(v), new Var<>(key, value));
                    } else {
                        parameters.add(new Var<>(key, value));
                    }
                }
            } else {
                Var<?> v = Var.getbyname(parameters, "arg" + i);
                if (v != null){
                    parameters.set(parameters.indexOf(v), new Var<>("arg" + i, keyval[0]));
                } else {
                    parameters.add(new Var<>("arg" + i, keyval[0]));
                }
                i++;
            }
        }
        return parameters;
    }
    private static int countclosingbrackets(String line){
        line = line.replace("\\)", "");
        return line.length() - line.replace(")", "").length();
    }
    private static int countopeningbrackets(String line){
        line = line.replace("\\$(", "");
        return line.length() - line.replace("$(", "$").length();
    }
    private static boolean iscorrectcomand(String commandline){
        if (!commandline.replace("\\$(", "").contains("$(")) return true;
        return countclosingbrackets(commandline) == countopeningbrackets(commandline);
    }
    private static boolean isfirst(List<CommandHelper> list, int index){
        if (index < 1) return true;
        for (int i = index - 1; i > -1; i--){
            if (list.get(i).lvl < list.get(index).lvl){
                return true;
            } else if (list.get(i).lvl == list.get(index).lvl){
                return false;
            }
        }
        return false;
    }
    public static final Var<?> execute(String command, List<String> allcommands){
        if (command == null){
            System.out.println("Warning: name of the command took null value");
            return Var.getemptyvar();
        }
        if (command == ""){
            System.out.println("Warning: name of the command took empty value");
            return Var.getemptyvar();
        }
        String[] commandargs = parsecommand(command);
        if (commandargs.length == 1) return new Var<>(commandargs[0], Execute.ExecuteCommand2(commandargs[0], new ArrayList<Var<?>>(Collections.emptyList())));
        return new Var<>(commandargs[0], Execute.ExecuteCommand2(commandargs[0], parsearg(commandargs[1], allcommands)).getvalue());
    }
    public static class CommandHelper {
        public CommandHelper(String code, int lvl){
            this.code = code;
            this.lvl = lvl;
        }
        public String code;
        public int lvl;
    }
}