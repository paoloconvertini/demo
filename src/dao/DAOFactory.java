package dao;

public abstract class DAOFactory {

    public static final int MYSQL = 0;

    public abstract PersonaDAO getPersonaDAO();

    public static DAOFactory getDAOFactory(int db) {
        switch (db) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
