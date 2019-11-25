package diseño_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conecta_BBDD
{
	String driver = "com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/policlinica?autoReconnect=true&useSSL=false";
	String login = "administrativo";
	String password = "studium2019;";
	String sentencia = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public Conecta_BBDD()
	{
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
	}

	public void agregar_objeto(String sentencia)
	{
		try
		{
			statement=connection.createStatement();
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
	}

	public Object obtener_objetos(String sentencia)
	{
		try
		{
			statement=connection.createStatement();
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
}
