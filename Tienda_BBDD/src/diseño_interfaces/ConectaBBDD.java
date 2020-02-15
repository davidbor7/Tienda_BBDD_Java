package diseño_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The Class ConectaBBDD.
 * @author David Borrego Asencio
 * @since 11/01/2020
 * @version 1.0
 */
public class  ConectaBBDD
{
	
	/** The driver. */
	private String driver = "com.mysql.jdbc.Driver";
	
	/** The url. */
	private String url ="jdbc:mysql://localhost:3306/tienda?autoReconnect=true&useSSL=false";
	
	/** The login. */
	private String login = "root";
	
	/** The password. */
	private String password = "Studium2019;";
	
	/** The connection. */
	private static Connection connection = null;
	
	/** The statement. */
	private static Statement statement = null;
	
	/** The resultset. */
	private static ResultSet rs = null;

	
	/**
	 * Instantiates a new conecta BBDD.
	 */
	public ConectaBBDD()
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

	/**
	 * Add Object.
	 *
	 * @param sentencia the query
	 */
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

	/**
	 * Get Objects
	 *
	 * @param sentencia the query
	 * @return the result set
	 */
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
	
	/**
	 * Add ticket date
	 *
	 * @param sentencia the query
	 * @return true, if successful
	 */
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
	
	/**
	 * Close connection with database.
	 */
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
