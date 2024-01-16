package dao.generic;

import configuration.ConnectionDB;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericCrudOperation<T> {

    public T save(T entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String tableName = getTableName();
            String sql = "INSERT INTO " + tableName + " VALUES (?)";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            setObjectParameters(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources(connection, statement);
        }
        return entity;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> result = new ArrayList<>();
        try {
            String tableName = getTableName();
            String sql = "SELECT * FROM " + tableName;
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = getObjectFromResultSet(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return result;
    }

    private void setObjectParameters(PreparedStatement statement, T entity) throws SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                statement.setObject(parameterIndex++, field.get(entity));
            } catch (IllegalAccessException e) {
                throw new SQLException("Error setting parameters for PreparedStatement", e);
            }
        }
    }

    abstract String getTableName();

    abstract T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    private void closeResources(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            closeResources(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
