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

public class Informe_Articulos {

	public Informe_Articulos() 
	{

		abre_informe();

	}


	public void abre_informe()
	{
		try
		{			

			// Compilar el informe generando fichero jasper
			JasperCompileManager.compileReportToFile("articulos.jrxml");
			System.out.println("Fichero articulos.jasper generado CORRECTAMENTE!");

			// Objeto para guardar parámetros necesarios para el informe
			HashMap<String,Object> parametros = new HashMap<String,Object>();
			
	
			// Cargar el informe compilado
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("articulos.jasper");

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
			JasperExportManager.exportReportToPdfFile(print, "Articulos.pdf");

			// Abrir el fichero PDF generado
			File path = new File ("Articulos.pdf");
			Desktop.getDesktop().open(path);

			conexion.close();

		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}		
	}

}
