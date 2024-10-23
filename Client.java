import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader input = null;
        PrintWriter output = null;
        BufferedReader consoleInput = null;

        try {
            // Connect to the server on localhost, port 12345
            socket = new Socket("localhost", 12345);
            System.out.println("Connected to the server");

            // Get input and output streams
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Read from the console and send messages to the server
            String userInput;
            while (true) {
                System.out.print("Enter message: ");
                userInput = consoleInput.readLine();
                output.println(userInput);

                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Read the server's response
                String response = input.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
                if (consoleInput != null) consoleInput.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
