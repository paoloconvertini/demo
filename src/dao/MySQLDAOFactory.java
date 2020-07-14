package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/test";
    public static final String DBUSER = "root";
    public static final String DBPW = "root";

    public static Connection createConnection(){
        Connection connection = null;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPW);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public PersonaDAO getPersonaDAO() {
        return new MySQLPersonaDAOImpl();
    }
}
