package booktransaction;

/**
 * Created by Prabhath on 9/27/2016.
 */
import java.sql.*;

public class DBConn1 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/library_mgt_system";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    public DBConn1() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int checkBook1(int person_id) {
        int availability = 0;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql;
            stmt = conn.createStatement();
            sql = "SELECT * FROM book_1 where student_id = '"+person_id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next()) {
                availability = 1;
            } else {
                while(rs.next()){
                    //Retrieve by column name
                    int student_id  = rs.getInt("student_id");
                    int book_id = rs.getInt("book_id");
                    String given_date = rs.getString("given_date");
                    String submit_date = rs.getString("submit_date");
                    availability = rs.getInt("availability");
                }
            }
           rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return availability;
    }
    public int checkBook2(int person_id) {
        int availability = 0;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            stmt = conn.createStatement();
            sql = "SELECT * FROM book_2 where student_id = '"+person_id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next()) {
                availability = 1;
            } else {
                while(rs.next()){
                    //Retrieve by column name
                    int student_id  = rs.getInt("student_id");
                    int book_id = rs.getInt("book_id");
                    String given_date = rs.getString("given_date");
                    String submit_date = rs.getString("submit_date");
                    availability = rs.getInt("availability");
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availability;
    }

    public boolean checkLateSubmissions(int person_id) {
        boolean availability = false;
        ResultSet rs = null;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            stmt = conn.createStatement();

            sql = "SELECT * FROM late_submissions where person_id = '"+person_id+"'";
            rs = stmt.executeQuery(sql);
            if(!rs.next()) {
                availability = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return availability;
    }

    public boolean checkSuitabilityBook1(int book_id) {
        boolean availability = true;
        ResultSet rs1 = null;
        int book_availability = 1;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql1;
            stmt = conn.createStatement();

            sql1 = "SELECT * FROM book_1 where book_id = '"+book_id+"'";
            rs1 = stmt.executeQuery(sql1);
            if(!rs1.next()) {
                availability = true;
            } else {
                while(rs1.next()){
                    //Retrieve by column name
                    book_availability = rs1.getInt("availability");
                }
                if(book_availability == 0) {
                    availability = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return availability;
    }

    public boolean checkSuitabilityBook2(int book_id) {
        boolean availability = true;
        ResultSet rs2 = null;
        int book_availability = 1;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql2;
            stmt = conn.createStatement();

            sql2 = "SELECT * FROM book_2 where book_id = '"+book_id+"'";
            rs2 = stmt.executeQuery(sql2);
            if(!rs2.next()) {
                availability = true;
            } else {
                while(rs2.next()){
                    //Retrieve by column name
                    book_availability = rs2.getInt("availability");
                }
                if(book_availability == 0) {
                    availability = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs2.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return availability;
    }

    public boolean submitBook1(int person_id, int book_id) {
        boolean submitted = true;
        ResultSet rs1 = null;
        int book_availability = 0;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql1,sql2;
            stmt = conn.createStatement();

            sql1 = "SELECT availability FROM book_1 where book_id = '"+book_id+"' AND student_id = '"+person_id+"'";
            rs1 = stmt.executeQuery(sql1);
            if(!rs1.next()) {
                submitted = false;
            } else {
                while(rs1.next()){
                    //Retrieve by column name
                    book_availability = rs1.getInt("availability");
                }
                if(book_availability == 0) {
                    sql2 = "UPDATE book_1 SET availability = 1 where book_id = '"+book_id+"' AND student_id = '"+person_id+"'";
                    stmt.executeUpdate(sql2);
                    submitted = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return submitted;
    }

    public boolean submitBook2(int person_id, int book_id) {
        boolean submitted = true;
        ResultSet rs2 = null;
        int book_availability = 0;
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql2,sql3;
            stmt = conn.createStatement();

            sql2 = "SELECT availability FROM book_2 where book_id = '"+book_id+"' AND student_id = '"+person_id+"'";
            rs2 = stmt.executeQuery(sql2);
            if(!rs2.next()) {
                submitted = false;
            } else {
                while(rs2.next()){
                    //Retrieve by column name
                    book_availability = rs2.getInt("availability");
                }
                if(book_availability == 0) {
                    sql3 = "UPDATE book_2 SET availability = 1 where book_id = '"+book_id+"' AND student_id = '"+person_id+"'";
                    stmt.executeUpdate(sql3);
                    submitted = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs2.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return submitted;
    }
}
