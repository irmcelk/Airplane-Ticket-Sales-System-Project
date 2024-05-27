import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class project_connection {
	public static Connection getConnection() throws SQLException, IOException{
        String url, user, pass;
        url = "jdbc:postgresql://localhost:5432/systemAnalysis";
	    user = "postgres";
        pass = "5432";
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
