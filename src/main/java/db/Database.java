package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database db = null;
    private Connection con;
    private Connection connect_to_db(){

        String url = "jdbc:mysql://127.0.0.1:3306/world";
        String user = "root";
        String pass = "12345";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.con = DriverManager.getConnection(url, user, pass);
            System.out.println("connection is "+this.con);
            return this.con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection()
    {
       if(db == null) {

           db = new Database();
           db.connect_to_db();

       }
       return  db.con;
    }

}
