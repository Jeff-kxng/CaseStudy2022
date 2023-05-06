package Client;

public class ClientRun {
    public static void main(String[] args) throws Exception {
        ClientView view = new ClientView();
        ClientControl control = new ClientControl(view);
    }
}
