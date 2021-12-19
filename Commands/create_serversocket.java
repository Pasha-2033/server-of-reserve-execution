//доделана непроверена
package Commands;
import java.net.ServerSocket;
import java.util.Map;
import MainFolder.Main;
public class create_serversocket {
    public create_serversocket(Map<String, String> params){
        try {
            if (params.size() == 0){
                Main.Сonnections.serversockets.add(new ServerSocket());
                return;
            } else if (params.containsKey("port")){
                if (params.containsKey("backlog")){
                    if (params.containsKey("address")){
                        Main.Сonnections.serversockets.add(new ServerSocket(Integer.parseInt(params.get("port")), Integer.parseInt(params.get("backlog")), Main.Сonnections.addresses.get(Integer.parseInt(params.get("address")))));
                        return;
                    } else {
                        Main.Сonnections.serversockets.add(new ServerSocket(Integer.parseInt(params.get("port")), Integer.parseInt(params.get("backlog"))));
                        return;
                    }
                } else {
                    Main.Сonnections.serversockets.add(new ServerSocket(Integer.parseInt(params.get("port"))));
                    return;
                }
            } else {
                System.out.println("serversocket creation error: creation requires <port> <backlog(optionaly)> <inetaddress object(optionaly, but backlog must be given)>");
            }
        } catch (Exception e) {
            System.out.println("serversocket creation error: " + e.getMessage());
        }
    }
}