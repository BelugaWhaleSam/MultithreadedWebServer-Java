
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        try {
            ServerSocket socket = new ServerSocket(port);
            // 10 seconds timeout
            socket.setSoTimeout(10000);
            while (true) {
                System.out.println("Server is listening on port: " + port);
                // will run till block, until someone accepts the connection
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from: " + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}