package Connection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Connections {
    public Connections(){}
    public List<Connection> connections = new ArrayList<Connection>(Collections.emptyList());
    public List<ServerSocket> serversockets = new ArrayList<ServerSocket>(Collections.emptyList());
    public List<Socket> sockets = new ArrayList<Socket>(Collections.emptyList());
    public List<InetAddress> addresses = new ArrayList<InetAddress>(Collections.emptyList());
}