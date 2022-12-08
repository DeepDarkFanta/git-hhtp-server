import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;

public class Server {

    private int port;

    private String directory;

    public Server (int port, String directory)
    {
        this.port = port;
        this.directory = directory;
    }

    void start()
    {
        try (var server = new ServerSocket(this.port)){
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
            while (true)
            {
                var socket = server.accept();
                var thread = new Handler(socket, this.directory);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args)
    {
        System.out.println("Ok, server is ready");
        var port = Integer.parseInt("8080");
        var directory = "./files";
        new Server(port, directory).start();
    }
}
