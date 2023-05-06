package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerControl control;

    public TCPServer(ServerControl control) {
        this.control = control;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected...");

                new Thread(new ClientHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket socket) {
            this.socket = socket;

            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                User user = (User) inputStream.readObject();
                //boolean result = control.checkLogin(user);
                //outputStream.writeBoolean(result);
                outputStream.flush();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
