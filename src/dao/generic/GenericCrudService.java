package dao.generic;

import configuration.ConnectionDB;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class  GenericCrudService<T> {

    public  T save(T entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        T result = null;
        try{
            String sql = STR."insert into \{getTableName()}"+"values (?)";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1,setObject(entity));
            statement.executeUpdate();
            result = entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null ;
        List<T> result = new ArrayList<>();
        try{
            String sql = STR."select * from \{getTableName()}";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                result.add(getResultset(getResultset((T) resultSet)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }
   abstract String getTableName();
   abstract T getResultset(T entity);
   abstract T setObject(T entity);
}