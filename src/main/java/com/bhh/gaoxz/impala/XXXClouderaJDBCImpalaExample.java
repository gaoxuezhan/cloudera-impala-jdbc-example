package com.bhh.gaoxz.impala;// java.sql packages are required

import java.sql.*;
// java.math packages are required
import java.math.*;

class XXXClouderaJDBCImpalaExample {
    // Define a string as the fully qualified class name
// (FQCN) of the desired JDBC driver
    static String JDBC_DRIVER = "com.cloudera.impala.jdbc3.Driver";
    // Define a string as the connection URL
    private static final String CONNECTION_URL = "jdbc:impala://192.168.1.1:21050";

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
// Define a plain query
        String query = "SELECT store_sales, store_cost FROM default.test LIMIT 20 ";
// Define a parameterized query Cloudera JDBC Driver for Impala | 11 Java Sample Code
        String prepQuery = "SELECT first_name, last_name, emp_id FROM default.emp where store_id = ?";
        try {
// Register the driver using the class name
            Class.forName(JDBC_DRIVER);
// Establish a connection using the connection
// URL
            con = DriverManager.getConnection(CONNECTION_URL);
// Create a Statement object for sending SQL
// statements to the database
            stmt = con.createStatement();
// Execute the SQL statement
            rs = stmt.executeQuery(query);
// Display a header line for output appearing in
// the Console View
            System.out.printf("%20s%20s\r\n", "STORE SALES", "STORE COST");
// Step through each row in the result set
// returned from the database
            while (rs.next()) {
// Retrieve values from the row where the
// cursor is currently positioned using
// column names
                BigDecimal StoreSales = rs.getBigDecimal("store_ sales");
                BigDecimal StoreCost = rs.getBigDecimal("store_ cost");
// Display values in columns 20 characters
// wide in the Console View using the
// Formatter 12 | Cloudera JDBC Driver for Impala Java Sample Code
                System.out.printf("%20s%20s\r\n", StoreSales.toString(), StoreCost.toString());
            }//Create a prepared statement
            PreparedStatement prep = con.prepareStatement
                    (prepQuery);
// Bind the query parameter with a value
            prep.setInt(1, 204);
// Execute the query
            prep.execute();
            rs = prep.getResultSet();
// Step through each row in the result set
// returned from the database
            while (rs.next()) {
// Retrieve values from the row where the
// cursor is currently positioned using
// column names
                String FirstName = rs.getString("first_name");
                String LastName = rs.getString("last_name");
                String EmployeeID = rs.getString("emp_id");
// Display values in columns 20 characters
// wide in the Console View using the
// Formatter
                System.out.printf("%20s%20s%20s\r\n", FirstName, LastName, EmployeeID);
            }
        } catch (SQLException se) {
// Handle errors encountered during interaction
// with the data source
        } catch (Exception e) {
// Handle other errors
        } finally {
// Perform clean up
            try {
                if (rs != null) {
//                    Cloudera JDBC Driver for Impala | 13 Java Sample Code
                    rs.close();
                }
            } catch (SQLException se1) {
// Log this
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
// Log this
            }
//            try {
////                if (prep != null) {
////                    prep.close();
////                }
//                System.out.println("asdf");
//            } catch (SQLException se3) {
//// Log this
//            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se4) {
// Log this
            } // End try
        } // End try
    } // End main
} // End XXXClouderaJDBCImpalaExample