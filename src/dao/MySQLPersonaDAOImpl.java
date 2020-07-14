package dao;

import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;
import model.Persona;
import search.PersonaSearch;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLPersonaDAOImpl implements PersonaDAO{
    public List<Persona> getPersonaList(PersonaSearch search) throws Exception {
        System.out.println("Actual DB call will occur......");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(" ");
            query.append("SELECT * FROM Persona ").append(" ");
            query.append(" WHERE 1 = 1 ").append(" ");
            Map<Integer, Object> parameters = new HashMap<>();
            query.append(getWhereQuery(parameters, search));
            // Get connection
//            connection = getConnection();
            connection = MySQLDAOFactory.createConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // Execute the query
            rs = statement.executeQuery();
            List<Persona> list = new ArrayList<>();

            int count = 0;
            skipFirstRows(rs, search);
            while (rs.next()) {
                Persona persona = buildModelFromResultSet(rs);
                list.add(persona);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method getElasticPostList");
            throw new SystemException(msg, ex);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
    }

    private StringBuilder getWhereQuery(Map<Integer, Object> parameters, PersonaSearch search) {
        StringBuilder whereClause = new StringBuilder();
        int p = 1;
        if (search.getName() != null) {
            whereClause.append("AND name = ? ");
            parameters.put(new Integer(p), search.getName());
            p++;
        }
        return whereClause;
    }

    private void addOrderClause(PersonaSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY elasticPostId ASC");
                break;
            case 2:
                query.append(" ORDER BY elasticPostId DESC");
                break;
            default:
                query.append(" ORDER BY elasticPostId DESC");
                break;
        }
    }


    protected void skipFirstRows(ResultSet rs, PersonaSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, PersonaSearch search) {
        return !(search.getPage() < 0 || count < search.getRowPerPage());
    }

    protected Persona buildModelFromResultSet(ResultSet rs) throws SQLException {
        Persona elasticPost = new Persona();
        elasticPost.setName(getParameterString(rs, "name"));
        return elasticPost;
    }


    private static String getParameterString(ResultSet rset, String column) throws SQLException {
        return rset.getString(column);
    }

    private void setParameterString(PreparedStatement preparedStatement, int parameterIndex, String value) throws SQLException {
        preparedStatement.setString(parameterIndex, value);
    }
}
