package diseño_interfaces;


import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Informe_Tickets extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informe_Tickets frame = new Informe_Tickets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Informe_Tickets() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 325, 198);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFechaInicio = new JLabel("Fecha Desde:");
		lblFechaInicio.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblFechaInicio.setBounds(27, 36, 108, 22);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaHasta = new JLabel("Fecha Hasta:");
		lblFechaHasta.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblFechaHasta.setBounds(30, 79, 108, 22);
		contentPane.add(lblFechaHasta);
		
		textField = new JTextField();
		textField.setBounds(145, 37, 108, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 80, 108, 20);
		contentPane.add(textField_1);
	}
	
	public void abre_informe()
	{
		try
		{			

			// Compilar el informe generando fichero jasper
			JasperCompileManager.compileReportToFile("tickets.jrxml");
			System.out.println("Fichero tickets.jasper generado CORRECTAMENTE!");

			// Objeto para guardar parámetros necesarios para el informe
			HashMap<String,Object> parametros = new HashMap<String,Object>();
			
			// Parámetro que enviamos al Report
			parametros.put("parametro1", textField.getText());		
			parametros.put("parametro2", textField_1.getText());
	
			// Cargar el informe compilado
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("tickets.jasper");

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
			JasperExportManager.exportReportToPdfFile(print, "Tickets.pdf");

			// Abrir el fichero PDF generado
			File path = new File ("Tickets.pdf");
			Desktop.getDesktop().open(path);

			conexion.close();

		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}	
	}
}
