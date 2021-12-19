//доделана непроверена
package Commands;
import java.util.Map;
import Connection.Connection;
import MainFolder.Main;
public class create_connection {
    public create_connection(Map<String, String> params){
        try {
            if (params.containsKey("socket")){
                if (params.containsKey("name")){
                    Main.Сonnections.connections.add(Connection.createConnection(Main.Сonnections.sockets.get(Integer.parseInt(params.get("socket"))), params.get("name")));
                } else {
                    Main.Сonnections.connections.add(Connection.createConnection(Main.Сonnections.sockets.get(Integer.parseInt(params.get("socket")))));
                }
            } else if (params.containsKey("serversocket")){
                if (params.containsKey("name")){
                    Main.Сonnections.connections.add(Connection.createConnection(Main.Сonnections.serversockets.get(Integer.parseInt(params.get("serversocket"))), params.get("name")));
                } else {
                    Main.Сonnections.connections.add(Connection.createConnection(Main.Сonnections.serversockets.get(Integer.parseInt(params.get("serversocket")))));
                }
            } else {
                System.out.println("connetion error: connection requires arguments <socket object> <name (optionaly)> or <serversocket object> <name (optionaly)>");
            }
        } catch (Exception e) {
            System.out.println("connetion error: " + e.getMessage());
        }
    }
}