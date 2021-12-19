package Interface;
//import java.util.Map;
public class CommandListener {
    public CommandListener(){
        thread.start();
    }
    public CLThread thread = new CLThread();
    public class CLThread extends Thread {
        public void run(){
            //Object[] commandline = Parser_probny.ParseCommandLine(new String[]{System.console().readLine()});
            //if (commandline != null) Execute.ExecuteCommand((String) commandline[0], (Map<String, String>) commandline[1]);
            Parser.readexecutecommand(System.console().readLine());
            this.run();
        }
    }
}