import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PiComMain {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println(Client.getInstance());
        Client.getInstance().displayClients();
        Client.getInstance().updateClient();
        Client.getInstance().createClient();
        Client.getInstance().deleteClient();

    }
}