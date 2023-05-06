package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {

    private JTextField username;
    private JPasswordField password;
    private JLabel usernamelbl;
    private JLabel passwordlbl;
    private JButton loginButton;

    public ClientView(){
        // Username label and text field
        usernamelbl = new JLabel("Username:");
        usernamelbl.setBounds(5, 10, 100, 50);
        username = new JTextField();
        username.setBounds(70, 20, 200, 25);

        // Password label and password field
        passwordlbl = new JLabel("Password: ");
        passwordlbl.setBounds(275, 10, 100, 50);
        password = new JPasswordField();
        password.setBounds(340, 20, 200, 25);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(545, 20, 82, 27);

        add(usernamelbl);
        add(username);
        add(passwordlbl);
        add(password);
        add(loginButton);

        setTitle("Login MVC");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 100);
        setLayout(null);
        setVisible(true);
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return new String(password.getPassword());
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public void addLoginListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
}
