package Server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ServerControl  {
    private List<User> users = new ArrayList<>();
    private ServerView view;
    private int serverPort;
    //private Connection con;
    private ServerSocket myServer;
    private Socket clientSocket;

    public ServerControl(ServerView view) {
        this.view = view;
        users.add(new User("hello", "world"));
        try{
            openServer(6543);
            listening();
            //Tạo input stream, nối tới Socket
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));

            //Tạo outputStream, nối tới socket
            DataOutputStream outToClient =
                    new DataOutputStream(clientSocket.getOutputStream());

            while(true){
                String user = inFromClient.readLine();
                System.out.println(user);
                boolean result = checkUser(new User(user.split(" ")[0], user.split(" ")[1]));
                System.out.print(result);
                if (result) outToClient.writeBytes("1\n");
                else outToClient.writeBytes("0\n");
            }
        }catch (Exception e){
            System.out.print(e.getMessage());
        }


    }

    public void openServer(int port) throws Exception {
        myServer = new ServerSocket(port);
    }

    //public void getDBConection(){}

    public void listening() throws Exception{
        clientSocket = myServer.accept();
    }

    public boolean checkUser(User user) {
        for (User u : users) {
            if (u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}

