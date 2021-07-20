import java.sql.*;

public class Client {



    // COnstructeur privé
    private Client(){}

    // Holder
    private static class ClientHolder
    {
        // Instance unique non pré-initialisée
        private final static Client clients = new Client();
    }

    // Point d'accès pour l'instance unique du Singleton
    public static Client getInstance(){
        return ClientHolder.clients;
    }

    public static void displayClients() throws SQLException {
        System.out.println("----- READ -----");

        String query = "SELECT * FROM `user` WHERE 1";

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/picom?" + "user=root&password=");


            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            // rs will be scrollable, will not show changes made by others,
            // and will be updatable

            rs = stmt.executeQuery(query);

            if (stmt.execute(query)){
                rs = stmt.getResultSet();

                while (rs.next()){
                    int user_id = rs.getInt(1);
                    String user_firstname = rs.getString(2);
                    String user_lastname = rs.getString(3);
                    String user_email = rs.getString(4);
                    String user_password = rs.getString(5);
                    String user_telephone = rs.getString(6);
                    Boolean user_newsletter = rs.getBoolean(7);
                    int id_city_user = rs.getInt(8);
                    String user_adress_number = rs.getString(9);
                    String user_adress_street = rs.getString(10);

                    System.out.println(user_id + " | " + user_firstname + " | " + user_lastname + " | " + user_email +
                            " | " + user_password + " | " + user_telephone + " | " + user_newsletter + " | " + id_city_user +
                            " | " + user_adress_number + " | " + user_adress_street + " | "
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        finally{
            // it is a good idea to release
            // resources in a finally {} block
            // in reverse order of their creation
            // if they are no longer needed

            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
        conn.close();
    }

    public static void createClient(){
        System.out.println("----- CREATE -----");
        final String QUERY = "SELECT * FROM `user` WHERE 1";
        final String UPDATE_QUERY =
                "INSERT INTO `user` (`user_id`, `user_firstname`, `user_lastname`, `user_email`, `user_password`, `user_telephone`, `user_newsletter`, `id_city_user`, `user_adress_number`, `user_adress_street`, `user_society_name`, `user_society_siret`, `user_status_admin`, `user_civilite_monsieur`) " +
                "VALUES (NULL, 'Mwamba', 'Keren', 'keren.mwamba@icloud.com', 'KerenMwamba2001', '0685566945', '1', '5', '123', 'avenue Pierre Brossol', 'Jonas&Co', '10', '1', '1')";
        final String DB_URL = "jdbc:mysql://localhost/picom?";
        final String USER = "root";
        final String PASS = "";

        // Ouvrir une connexion
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            // Voir si ça retourne un vrai résultat Set ou pas
            Boolean ret = stmt.execute(UPDATE_QUERY);
            System.out.println("Return value is :" + ret.toString());

            // Sélectionner & afficher tous les users
            ResultSet rs = stmt.executeQuery(QUERY);

            // Extraire les données du Resultset
            while(rs.next()){

                int user_id = rs.getInt(1);
                String user_firstname = rs.getString(2);
                String user_lastname = rs.getString(3);
                String user_email = rs.getString(4);
                String user_password = rs.getString(5);
                String user_telephone = rs.getString(6);
                Boolean user_newsletter = rs.getBoolean(7);
                int id_city_user = rs.getInt(8);
                String user_adress_number = rs.getString(9);
                String user_adress_street = rs.getString(10);

                // Affichons par colonne
                System.out.println(user_id + " | " + user_firstname + " | " + user_lastname + " | " + user_email +
                        " | " + user_password + " | " + user_telephone + " | " + user_newsletter + " | " + id_city_user +
                        " | " + user_adress_number + " | " + user_adress_street + " | "
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void updateClient(){

        System.out.println("----- UPDATE ----");
        final String DB_URL = "jdbc:mysql://localhost/picom?";
        final String USER = "root";
        final String PASS = "";

        final String QUERY =  "SELECT * FROM `user` WHERE 1";
        //Selon la requête : ici update le booléen de la newsletter (true or false)
        final String UPDATE_QUERY = "UPDATE `user` SET `user_newsletter` = '1' WHERE `user`.`user_id` = 2";
        // Ouvrir une connexion
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            // Voir si ça retourne un vrai résultat Set ou pas
            Boolean ret = stmt.execute(UPDATE_QUERY);
            System.out.println("Return value is :" + ret.toString());

            // Update la newsletter de l'user_id 3
            int rows = stmt.executeUpdate(UPDATE_QUERY);
            System.out.println("Rows impacted : " + rows);

            // Sélectionner & afficher tous les users
            ResultSet rs = stmt.executeQuery(QUERY);

            // Extraire les données du Resultset
            while(rs.next()){

                int user_id = rs.getInt(1);
                String user_firstname = rs.getString(2);
                String user_lastname = rs.getString(3);
                String user_email = rs.getString(4);
                String user_password = rs.getString(5);
                String user_telephone = rs.getString(6);
                Boolean user_newsletter = rs.getBoolean(7);
                int id_city_user = rs.getInt(8);
                String user_adress_number = rs.getString(9);
                String user_adress_street = rs.getString(10);

                // Affichons par colonne
                System.out.println(user_id + " | " + user_firstname + " | " + user_lastname + " | " + user_email +
                        " | " + user_password + " | " + user_telephone + " | " + user_newsletter + " | " + id_city_user +
                        " | " + user_adress_number + " | " + user_adress_street + " | "
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteClient(){

        final String QUERY = "SELECT * FROM `user` WHERE 1";

        System.out.println("----- DELETE ----");
        final String DB_URL = "jdbc:mysql://localhost/picom?";
        final String USER = "root";
        final String PASS = "";

        //Selon la requête : ici update le booléen de la newsletter (true or false)
        final String UPDATE_QUERY = "DELETE FROM `user` WHERE `user`.`user_id` = 17 ";
        // Ouvrir une connexion
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            // Voir si ça retourne un vrai résultat Set ou pas
            Boolean ret = stmt.execute(UPDATE_QUERY);
            System.out.println("Return value is :" + ret.toString());

            // Update la newsletter de l'user_id 3
            int rows = stmt.executeUpdate(UPDATE_QUERY);
            System.out.println("Rows impacted : " + rows);

            // Sélectionner & afficher tous les users
            ResultSet rs = stmt.executeQuery(QUERY);

            // Extraire les données du Resultset
            while(rs.next()){

                int user_id = rs.getInt(1);
                String user_firstname = rs.getString(2);
                String user_lastname = rs.getString(3);
                String user_email = rs.getString(4);
                String user_password = rs.getString(5);
                String user_telephone = rs.getString(6);
                Boolean user_newsletter = rs.getBoolean(7);
                int id_city_user = rs.getInt(8);
                String user_adress_number = rs.getString(9);
                String user_adress_street = rs.getString(10);

                // Affichons par colonne
                System.out.println(user_id + " | " + user_firstname + " | " + user_lastname + " | " + user_email +
                        " | " + user_password + " | " + user_telephone + " | " + user_newsletter + " | " + id_city_user +
                        " | " + user_adress_number + " | " + user_adress_street + " | "
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
