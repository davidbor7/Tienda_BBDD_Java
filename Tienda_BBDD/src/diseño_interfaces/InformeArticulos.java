package diseño_interfaces;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class InformeArticulos. This class generate items reports.
 * 
 * @author David Borrego Asencio
 * @since 11/01/2020
 * @version 1.0
 */
public class InformeArticulos {

	/**
	 * Instantiates a new informe articulos
	 */
	public InformeArticulos() 
	{

		abre_informe();

	}


	/**
	 * Open PDF Articulos
	 */
	public void abre_informe()
	{
		try
		{			

			// Compilar el informe generando fichero jasper
			JasperCompileManager.compileReportToFile("C:\\Tienda_BBDD\\articulos.jrxml");
			System.out.println("Fichero articulos.jasper generado CORRECTAMENTE!");

			// Objeto para guardar parámetros necesarios para el informe
			HashMap<String,Object> parametros = new HashMap<String,Object>();
			
	
			// Cargar el informe compilado
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("C:\\Tienda_BBDD\\articulos.jasper");

			// Conectar a la base de datos para sacar la información
			Class.forName("com.mysql.jdbc.Driver");
			String servidor = "jdbc:mysql://localhost:3306/tienda?useSSL=false";
			String usuarioDB = "root";
			String passwordDB = "Studium2019;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

			// Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

			/*
			// Mostrar el informe en JasperViewer
			JasperViewer.viewReport(print, false);
			 */

			// Para exportarlo a pdf
			JasperExportManager.exportReportToPdfFile(print, "C:\\Tienda_BBDD\\Articulos.pdf");

			// Abrir el fichero PDF generado
			File path = new File ("C:\\Tienda_BBDD\\Articulos.pdf");
			Desktop.getDesktop().open(path);

			conexion.close();

		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}		
	}

}
