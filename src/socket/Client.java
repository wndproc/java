package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static final int SERVER_PORT = 5555;
    private static final String ADDRESS = "127.0.0.1";

    public static void main(String[] args) {

        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            System.out.println(
                    "Any of you heard of a socket with IP address " + ADDRESS + " and port " + SERVER_PORT + "?");
            Socket socket = new Socket(ipAddress, SERVER_PORT);
            System.out.println("Yes! I just got hold of the program.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(
                    "Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();

            while (true) {
                String line = keyboard.readLine();
                System.out.println("Sending this line to the server...");
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                System.out.println("The server was very polite. It sent me this : " + line);
                System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}