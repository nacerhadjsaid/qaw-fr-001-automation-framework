package org.qaway.utility;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectDB {
    MongoDatabase mongoDatabase = null;
    public Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Connection connectToMySql(){
        Properties prop = Utility.loadProperties();
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String username = Utility.decode(prop.getProperty("MYSQLJDBC.username"));
        String password = Utility.decode(prop.getProperty("MYSQLJDBC.password"));
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("database is connected");
        return connection;
    }

    public MongoDatabase connectToMongoDB(String dbName) {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase(dbName);
        System.out.println("Database Connected");
        return mongoDatabase;
    }

    public List<String> getTableColumnData(String query, String columnName) {
        List<String> list = new ArrayList<>();
        try {
            statement = connectToMySql().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                list.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
