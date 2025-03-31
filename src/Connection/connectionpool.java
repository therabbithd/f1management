package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class connectionpool {
    private String url;
    private String user;
    private String password;

    private ArrayList<Connection> conns = new ArrayList<Connection>();

    public connectionpool(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;

    }

    public Connection getConnection()throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection _conn = DriverManager.getConnection(this.url, this.user, this.password);
        this.conns.add(_conn);
        return _conn;
    }

    public void closeAll() throws SQLException{
        for(Connection conn : this.conns){
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        }
    }
    
}
