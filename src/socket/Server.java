package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5555;

    public static void main(String[] ar) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept();

            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String line = in.readUTF();
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                out.writeUTF(line);
                out.flush();
                System.out.println("Waiting for the next line...");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

