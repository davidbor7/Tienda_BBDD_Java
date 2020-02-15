package diseño_interfaces;


import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * The Class InformeTickets. This class generate ticket reports.
 */
public class InformeTickets extends JFrame
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field. */
	private JTextField textField;
	
	/** The text field 1. */
	private JTextField textField_1;	

	/**
	 * Create the frame
	 */
	public InformeTickets() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoArticulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 325, 198);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFechaInicio = new JLabel("Fecha Desde:");
		lblFechaInicio.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblFechaInicio.setBounds(55, 21, 108, 22);
		contentPane.add(lblFechaInicio);

		JLabel lblFechaHasta = new JLabel("Fecha Hasta:");
		lblFechaHasta.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblFechaHasta.setBounds(58, 64, 108, 22);
		contentPane.add(lblFechaHasta);

		textField = new JTextField();
		textField.setBounds(173, 22, 80, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 65, 80, 20);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Generar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				abre_informe();
			}
		});
		btnNewButton.setBounds(110, 125, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblFormatDdmmaaaa = new JLabel("Date Format: DD/MM/YYYY");
		lblFormatDdmmaaaa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblFormatDdmmaaaa.setBounds(88, 100, 147, 14);
		contentPane.add(lblFormatDdmmaaaa);
	}

	/**
	 * Open PDF Tickets
	 */
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
			parametros.put("fechaDesde", cambiarFormatoFecha(textField.getText()));		
			parametros.put("fechaHasta", cambiarFormatoFecha(textField_1.getText()));

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
			JOptionPane.showMessageDialog(this, "Error en el formato de fecha.");
			textField.setText("");
			textField_1.setText("");
			System.out.println("Error: " + e.toString());
		}	
	}

	/**
	 * Change European date format to SQL format
	 *
	 * @param String the string in European format
	 * @return the String in SQL format
	 */
	public String cambiarFormatoFecha(String fecha)	
	{
		String fechaEuropa = fecha;
		String fechaSQL = "";	

		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-4);
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-3);
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-2);
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-1);
		fechaSQL = fechaSQL + "/";
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-7);
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-6);
		fechaSQL = fechaSQL + "/";
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-10);
		fechaSQL = fechaSQL + fechaEuropa.charAt(fechaEuropa.length()-9);

		return fechaSQL;	
	}
}