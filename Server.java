import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader input = null;
        PrintWriter output = null;

        try {
            // Create a server socket listening on port 12345
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for a client...");

            // Accept an incoming connection from a client
            socket = serverSocket.accept();
            System.out.println("Client connected");

            // Get input and output streams
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Read messages from the client and respond
            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Client says: " + message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                output.println("Server echoes: " + message); // Send a response back to the client
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
