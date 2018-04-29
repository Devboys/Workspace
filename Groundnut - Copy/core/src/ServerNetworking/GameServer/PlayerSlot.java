package ServerNetworking.GameServer;

import ClientNetworking.GameClient.PlayerMovementOutputObject;

import java.net.InetAddress;

public class PlayerSlot {

    private PlayerMovementOutputObject movement;
    private int playerID;
    private InetAddress clientIP;


    public PlayerMovementOutputObject getMovement() {
        return movement;
    }

    public void setMovement(PlayerMovementOutputObject movement) {
        this.movement = movement;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public InetAddress getClientIP() {
        return clientIP;
    }

    public void setClientIP(InetAddress clientIP) {
        this.clientIP = clientIP;
    }
}
