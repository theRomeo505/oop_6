package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool connectionPool = new ConnectionPool();

    private final String url = "jdbc:postgresql://localhost:5432/bank";
    private final String user = "postgres";
    private final String password = "131313";
    private final int MAX_CONNECTIONS = 8;

    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        }

        connections = new LinkedBlockingQueue<Connection>(MAX_CONNECTIONS);

        try {
            for(int i = 0; i < MAX_CONNECTIONS; ++i) {
                connections.put(DriverManager.getConnection(url,user,password));
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public Connection getConnection() throws InterruptedException, SQLException {
        Connection connection = connections.take();
        if (connection.isClosed()) {
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        connections.put(connection);
    }
}