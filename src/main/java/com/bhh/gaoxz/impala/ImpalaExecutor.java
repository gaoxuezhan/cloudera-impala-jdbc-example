package com.bhh.gaoxz.impala;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ImpalaExecutor {
	
	private static final String CONNECTION_URL_PROPERTY = "connection.url";
	private static final String JDBC_DRIVER_NAME_PROPERTY = "jdbc.driver.class.name";

	private static String connectionUrl;
	private static String jdbcDriverName;

        private static void loadConfiguration() throws IOException {
                InputStream input = null;
                try {
                        String filename = ImpalaExecutor.class.getSimpleName() + ".conf";
                        input = ImpalaExecutor.class.getClassLoader().getResourceAsStream(filename);
                        Properties prop = new Properties();
                        prop.load(input);
        
                        connectionUrl = prop.getProperty(CONNECTION_URL_PROPERTY);
                        jdbcDriverName = prop.getProperty(JDBC_DRIVER_NAME_PROPERTY);
                } finally {
                        try {
                                if (input != null)
                                        input.close();
                        } catch (IOException e) {
                                // nothing to do
                        }
                }
        }

	public static void main(String[] args) throws IOException {

//                if (args.length != 1) {
//                        System.out.println("Syntax: ClouderaImpalaJdbcExample \"<SQL_query>\"");
//                        System.exit(1);
//                }
//                String sqlStatement = args[0];

		String sqlStatement = "select * from gaoxz.aaa";
		loadConfiguration();

		System.out.println("\n=============================================");
		System.out.println("Cloudera Impala JDBC Example");
		System.out.println("Using Connection URL: " + connectionUrl);
		System.out.println("Running Query: " + sqlStatement);

		Connection con = null;

		try {

			Class.forName(jdbcDriverName);

			con = DriverManager.getConnection(connectionUrl);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sqlStatement);

			ResultSetMetaData m = rs.getMetaData();

			int columns = m.getColumnCount();

			for(int i=1 ; i<=columns ; i++)
			{
				System.out.print(m.getColumnName(i));
				System.out.print("\t\t");
			}

			System.out.println();

			while(rs.next())
			{
				for(int i = 1 ; i <= columns ; i++)
				{
					System.out.print(rs.getString(i));
					System.out.print("\t\t");
				}
				System.out.println();
			}

//
//			System.out.println("\n== Begin Query Results ======================");
//
//			// print the results to the console
//			while (rs.next()) {
//				// the example query returns one String column
//                System.out.println(rs.toString());
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
//				System.out.println(rs.getString(4));
//				System.out.println(rs.getString(5));
//			}
//
//			System.out.println("== End Query Results =======================\n\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// swallow
			}
		}
	}
}
