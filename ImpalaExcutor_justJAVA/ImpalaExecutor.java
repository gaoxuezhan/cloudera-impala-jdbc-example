import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ImpalaExecutor {

	public static void main(String[] args) throws IOException {

		String sql = "select * from gaoxz.aaa";
		String hostImapala = "jdbc:impala://192.168.119.204:21050";
		String jdbcDriverName = "com.cloudera.impala.jdbc41.Driver";

		InputStream config = ImpalaExecutor.class.getClassLoader().getResourceAsStream("config.ini");
		Properties properties = new Properties();
		properties.load(config);

        hostImapala = properties.getProperty("host");
        sql = properties.getProperty("sql");


		System.out.println("Host: \n<" + hostImapala + ">");
		System.out.println("Sql: \n<" + sql + ">");
		System.out.println("Result: \n*********************************************************");

		Connection connection = null;

		try {

			Class.forName(jdbcDriverName);
			connection = DriverManager.getConnection(hostImapala);
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData m = rs.getMetaData();

			int columnCount = m.getColumnCount();

			//1.HEAD
			for(int i=1 ; i <= columnCount ; i++)
			{
				System.out.print(m.getColumnName(i));
				if (i != columnCount){
					System.out.print(",");
				}
			}

			System.out.println();

			//2.DATA
			while(rs.next())
			{
				for(int i = 1 ; i <= columnCount ; i++)
				{
					System.out.print(rs.getString(i));
					if (i != columnCount){
						System.out.print(",");
					}
				}
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// Nothing
			}
		}
	}
}
