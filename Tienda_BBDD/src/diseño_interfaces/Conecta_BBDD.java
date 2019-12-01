package diseño_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  Conecta_BBDD
{
	private String driver = "com.mysql.jdbc.Driver";
	private String url ="jdbc:mysql://localhost:3306/tienda?autoReconnect=true&useSSL=false";
	private String login = "root";
	private String password = "Studium2019;";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;

	
	//Prueba 
	
	
	
	
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
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
	}

	public ResultSet obtener_objetos(String sentencia)
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
	
	public boolean agregar_fecha_ticket(String sentencia)
	{
		try
		{
			statement=connection.createStatement();
			statement.executeUpdate(sentencia);		
			return true;
			
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public void cierra_conexion()
	{
		try
		{
			statement.close();
			connection.close();	
		}
		catch(SQLException e)
		{
			System.out.println("Error al cerrar la conexión");	
		}
	}
}
