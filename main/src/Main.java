import util.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        /* Instantiate the ConnectionFactory class(Singleton pattern)
         * and print the connection info.
         * printConnectionInfo() method is not static,
         * so we need to create an instance of the class to call it
         */
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connectionFactory.printConnectionInfo();

    }
}
