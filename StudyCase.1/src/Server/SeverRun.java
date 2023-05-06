package Server;

public class SeverRun {
    public static void main(String[] args) {
        ServerView view = new ServerView();
        ServerControl control = new ServerControl(view);
        TCPServer server = new TCPServer(control);
        server.start();
    }
}
