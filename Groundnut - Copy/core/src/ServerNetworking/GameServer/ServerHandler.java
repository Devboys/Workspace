package ServerNetworking.GameServer;

import java.net.InetAddress;
import java.util.ArrayList;

public class ServerHandler {

    public static final int gamePort = 24000;
    public static final int multicastPort = 24002;
    public static final int maxPlayerCount = 4;
    public static final int clientTickRate = 2000; // 1 = 1ms
    public static final int serverTickRate = 1000;
    public static final String serverIp = "127.0.0.1";
    public static final String group = "230.0.0.0";

    private static ArrayList<PlayerSlot> clientSlots = new ArrayList<PlayerSlot>(maxPlayerCount);
    private static int connectedPlayers = 0;

    public static int checkPlayer(InetAddress IP) {

        if (!(clientSlots.size() >= maxPlayerCount)) {
            if (clientSlots.isEmpty()) {
                PlayerSlot pSlot = new PlayerSlot();
                pSlot.setClientIP(IP);
                clientSlots.add(pSlot);
                System.out.println(IP.toString() + " is player 0");
                return 0; //Player #1
            }
            else if (connectedPlayers < maxPlayerCount) {
                for (int i = 0; i < connectedPlayers; i++) {
                    if (!IP.equals(clientSlots.get(i))) {

                        PlayerSlot pSlot = new PlayerSlot();
                        pSlot.setClientIP(IP);
                        clientSlots.add(pSlot);

                        System.out.println(IP.toString() + " is player " + i);
                        return i;
                    }
                }
            }
        }

        for (int i = 0; i < maxPlayerCount; i++){
            if(IP.equals(clientSlots.get(i))){
                return i;
            }
        }
        System.out.println("This shouldn't happen.");
        return -1;
    }

    public static int getConnectedPlayers() { return connectedPlayers; }
    public static ArrayList<PlayerSlot> getClientSlots() {
        return clientSlots;
    }

}