package ServerNetworking.GameServer;

import ClientNetworking.GameClient.PlayerMovementOutputObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;


public class ServerInputThread extends Thread {

    //Data
    private final int BUFFER_LENGTH = 256;
    private byte[] buffer = new byte[BUFFER_LENGTH];

    //Socket
    private DatagramSocket udpSocket;
    private DatagramPacket dgram;
    private ArrayList<InetAddress> playerIPs = ServerHandler.getClientSlots();
    private int serverPort;

    public ServerInputThread(){
        try {
            //Socket
            serverPort = ServerHandler.gamePort;
            dgram = new DatagramPacket(buffer, buffer.length);
            udpSocket = new DatagramSocket(serverPort, InetAddress.getByName("0.0.0.0"));
            System.out.println("SERVER Created socket on LOCALHOST port: " + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("SERVER Waiting for datagram to be received...");
            try {
                udpSocket.receive(dgram);
                System.out.println("SERVER Datagram received");
                byte[] data = dgram.getData();
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);

                try{
                    PlayerMovementOutputObject serverInput = (PlayerMovementOutputObject) ois.readObject();
                    int clientIndex = ServerHandler.checkPlayer(dgram.getAddress());

                } catch(Exception e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error in ServerInputThread");
            }
        }
    }

    public void handleInput(PlayerMovementOutputObject input, int clientIndex){
        ServerHandler.getClientSlots().get(clientIndex).setMovement(input);
    }
}