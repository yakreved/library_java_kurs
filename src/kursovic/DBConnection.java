
package kursovic;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class DBConnection 
{
    private static MysqlDataSource dataSource;
    public static Connection conn;
    public static MysqlDataSource DataSource()
    {
        if(dataSource != null)
            return dataSource;
        connect();
        return dataSource;
    }
    
    public static void connect()
    {
        dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("test");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setServerName("localhost");
    }
    
    public static ResultSet executeQuery(String query)
    {
        try {
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
