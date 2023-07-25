package commom;

import java.sql.*;
import java.util.*;

public class SQLServer {
    private static Connection conn1 = null;
    public static Connection connectDatabase() {
        try {
            // Connect method #1
            String dbURL1 = "jdbc:postgresql://172.31.17.11:5433/innofinv2";
            Properties parameters = new Properties();
            parameters.put("user", "postgres");
            parameters.put("password", "postgres");
            conn1 = DriverManager.getConnection(dbURL1, parameters);
            if (conn1 != null) {
                System.out.println("STATUS: CONNECTED TO DATA BASE");
                System.out.println("DATABASE_NAME: " + conn1.getCatalog());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn1;
    }
    public static List<HashMap<String, String>> executeQuery(String queryString) throws SQLException {
        System.out.println("Querying to db with command \""  + queryString + '"');
        Statement statement = conn1.createStatement();
        ResultSet result = statement.executeQuery(queryString);
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        List<HashMap<String, String>> listOfQueryRecords = new ArrayList<HashMap<String, String>>();
        while (result.next()) {
            Map<String, String> map = new HashMap<String, String>();

            for (int i = 1; i <= numberOfColumns; i++) {
                String cellValue = result.getString(i) != null ? result.getString(i) : "";
                map.put(metaData.getColumnName(i), cellValue);
            }
            listOfQueryRecords.add((HashMap<String, String>) map);
        }
        result.close();
        statement.close();

        return listOfQueryRecords;
    }
    private void closeConnection() throws SQLException {
        System.out.println("Closing database connection");
        if (conn1 != null && !conn1.isClosed()) {
            conn1.close();
        }
        conn1 = null;
    }

    public static void main(String[] args) throws SQLException {
        connectDatabase();
        SQLServer sqlServer = new SQLServer();
        List<HashMap<String, String>> data =  sqlServer.executeQuery("SELECT * FROM \"profile_v2\".\"collector\" LIMIT 1000 OFFSET 0");
        System.out.println(data);
    }
}
