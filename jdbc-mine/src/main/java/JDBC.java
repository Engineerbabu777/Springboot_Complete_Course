import java.sql.*;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            System.out.println("Connected to Database");

            selectStudents(connection);
            insertStudent(connection, "awais2","awaismumtaz62@gmail.com");
            selectStudents(connection);
            updateStudent(connection, "zain2", "zain@zain12.com", 1);
            deleteStudent(connection,  1);
            selectStudents(connection);

        } catch(SQLException e){
            e.printStackTrace();
        }




    }

    private static void insertStudent(Connection conn, String name, String email){
        String sql = "INSERT INTO STUDENTS (name, email) VALUES('" + name + "','" + email + "')";

        try (Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("INSERTED: "+rows);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    private static void selectStudents(Connection conn){
        String sql = "SELECT * FROM STUDENTS";
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Student fetched.");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println(id+ " : "+name+" : "+email);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void updateStudent(Connection conn, String name, String email, int id){
//        String sql = "UPDATE STUDENTS SET name = '" + name + "', email = '" + email + "' WHERE id = " + id;

        String sql = "UPDATE STUDENTS SET name = ?, email = ? WHERE id =?";

        try (PreparedStatement pstmp = conn.prepareStatement(sql)){
            pstmp.setString(1, name);
            pstmp.setString(2, email);
            pstmp.setInt(3, id);

            pstmp.executeUpdate();

            System.out.println("Student updated.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection conn, int id){
        String sql = "DELETE FROM STUDENTS WHERE id ="+id;

        try(Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



/*
* public static void main(String[] args) {
         Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                System.out.println("COnnection close");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
*
* */