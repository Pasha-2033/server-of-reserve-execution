package Connection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Connection {
    //создание объекта конструктора
    public Connection(){}
    public Connection(Socket socket){
        this.socket = socket;
    }
    public Connection(String name){
        this.name = name;
    }
    public Connection(Socket socket, String name){
        this.socket = socket;
        this.name = name;
    }
    //переменные обьекта
    public Socket socket = new Socket();
    public String name = "";
    //методы создания обьекта по определенным аргументам
    public static Connection createConnection(){
        return new Connection();
    }
    public static Connection createConnection(String name){
        return new Connection(name);
    }
    public static Connection createConnection(Socket socket){
        return new Connection(socket);
    }
    public static Connection createConnection(Socket socket, String name){
        return new Connection(socket, name);
    }
    public static Connection createConnection(ServerSocket serversocket) throws IOException{
        return new Connection(serversocket.accept());
    }
    public static Connection createConnection(ServerSocket serversocket, String name) throws IOException{
        return new Connection(serversocket.accept(), name);
    }
}