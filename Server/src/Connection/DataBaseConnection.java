/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DataBaseConnection {

    private static DataBaseConnection instance;
    private Connection connection;

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    private DataBaseConnection() {

    }

//    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=Login;encrypt=true;trustServerCertificate=true";
//    String user = "sa";
//    String password = "sa";
    public void ConnectToDatabase() throws SQLException {
        String server = "127.0.0.1";
        String port = "3306";
        String database = "account";
//        String url = "jdbc:mysql://localhost\\SQLEXPRESS:1433;databaseName=Account;encrypt=true;trustServerCertificate=true";
        String userName = "root";
        String password = "abc@123";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
