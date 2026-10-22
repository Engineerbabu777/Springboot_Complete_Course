import java.sql.*;

public class TransactionsDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Database Connected :)");

            conn.setAutoCommit(false);

            // INSERT INTO ORDER ITEM
            int orderId1 = insertOrder(conn, 101, "Alice01", 2000.0);
            int orderId2 = insertOrder(conn, 102, "Bob01", 4440.0);

            // INSERT INTO ORDER ITEM
            insertItem(conn, orderId1, "Laptop", 1, 2000.0);
            insertItem(conn, orderId2, "Mac", 1, 12000.0);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static int insertOrder(Connection conn, int customerId, String customerName, Double price){
        String sql = "INSERT INTO orders(user_id, customer_name, total_amount) " + "VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, customerId);
            pstmt.setString(2, customerName);
            pstmt.setDouble(3, price);

            int rows = pstmt.executeUpdate();
            System.out.println("INSERTED into order: "+rows);

            try(ResultSet rs = pstmt.getGeneratedKeys()){

                if(rs.next()){
                    int orderId = rs.getInt(1);
                    System.out.println("ORDER ID: "+ orderId);
                }else{
                    throw new SQLException("Order ID not generated");
                }

            }
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertItem(Connection conn, int orderId, String productName, int quantity, Double price){
        String sql = "INSERT INTO order_items(order_id, product_name, price, quantity) VALUES(?, ?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, orderId);
            pstmt.setString(2, productName);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, quantity);

            int rows = pstmt.executeUpdate();

            System.out.println("Inserted into OrderItems: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
