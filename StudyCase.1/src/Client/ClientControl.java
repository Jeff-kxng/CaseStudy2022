package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientControl {

    private ClientView view;
    private User model;

    public ClientControl(ClientView view){
        this.view = view;

        view.addLoginListener(new LoginListener());
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            User user = new User(username, password);

            try{
                Socket clientSocket = new Socket("127.0.0.1", 6543);

                //Tạo OutputStream nối với Socket
                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());

                //Tạo inputStream nối với Socket
                BufferedReader inFromServer =
                        new BufferedReader(new
                                InputStreamReader(clientSocket.getInputStream()));

                //Gửi chuỗi ký tự tới Server thông qua outputStream đã nối với Socket (ở trên)
                outToServer.writeBytes( view.getUsername() + " " + view.getPassword() + '\n');
                //outToServer.writeBytes("hello\n");

                //Đọc tin từ Server thông qua InputSteam đã nối với socket
                String result = inFromServer.readLine();
                clientSocket.close();
                if(result == "1"){
                    view.showMessage("Login successfully");
                } else {
                    view.showMessage("Invalid username and/or password");
                }

            }catch (Exception ex){
                view.showMessage(ex.getMessage());
            }
        }
    }
}
