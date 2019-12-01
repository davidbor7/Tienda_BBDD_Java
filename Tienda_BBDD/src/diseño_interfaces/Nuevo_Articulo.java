package diseño_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Nuevo_Articulo extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textfieldDescripcion;
	private JTextField textfieldPrecio;
	private JLabel labelPrecio;
	private JLabel labelMoneda;
	private JLabel labelCantidad;
	private JTextField textfieldCantidad;
	private JLabel labelNuevoArticulo;
	private JButton buttonCancelar;
	private JButton buttonGuardar;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;
	private JSeparator separator;
	private Conecta_BBDD base_datos;

	/**
	 * Create the frame.
	 */
	public Nuevo_Articulo()
	{
		base_datos = new Conecta_BBDD();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelDescripcion = new JLabel("Descripci\u00F3n:");
		labelDescripcion.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelDescripcion.setBounds(114, 120, 88, 24);
		contentPane.add(labelDescripcion);

		textfieldDescripcion = new JTextField();
		textfieldDescripcion.setBounds(242, 120, 153, 24);
		contentPane.add(textfieldDescripcion);
		textfieldDescripcion.setColumns(10);

		textfieldPrecio = new JTextField();
		textfieldPrecio.setColumns(10);
		textfieldPrecio.setBounds(244, 181, 72, 24);
		contentPane.add(textfieldPrecio);

		labelPrecio = new JLabel("Precio:");
		labelPrecio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelPrecio.setBounds(149, 181, 53, 24);
		contentPane.add(labelPrecio);

		labelMoneda = new JLabel("\u20AC");
		labelMoneda.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelMoneda.setBounds(323, 181, 25, 24);
		contentPane.add(labelMoneda);

		labelCantidad = new JLabel("Cantidad:");
		labelCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelCantidad.setBounds(132, 247, 63, 24);
		contentPane.add(labelCantidad);

		textfieldCantidad = new JTextField();
		textfieldCantidad.setColumns(10);
		textfieldCantidad.setBounds(244, 247, 72, 24);
		contentPane.add(textfieldCantidad);

		labelNuevoArticulo = new JLabel("NUEVO ART\u00CDCULO");
		labelNuevoArticulo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		labelNuevoArticulo.setBounds(183, 11, 146, 24);
		contentPane.add(labelNuevoArticulo);

		separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				base_datos.cierra_conexion();
				Nuevo_Articulo.this.setVisible(false);
				Nuevo_Articulo.this.dispose();
				
			}
		});
		buttonCancelar.setBounds(288, 397, 89, 23);
		contentPane.add(buttonCancelar);

		buttonGuardar = new JButton("Guardar");
		buttonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sentencia_nuevo_articulo = "INSERT INTO articulos (descripcionArticulo, precioArticulo, stockArticulo) VALUES ('" + textfieldDescripcion.getText() + "', " + comprueba_cantidad(textfieldPrecio.getText()) + ", " + textfieldCantidad.getText() + ");"; 
				System.out.println(sentencia_nuevo_articulo);
				base_datos.agregar_objeto(sentencia_nuevo_articulo);
				
				dialogo1.setVisible(true);
			}
		});
		buttonGuardar.setBounds(385, 397, 89, 23);
		contentPane.add(buttonGuardar);

		//------------------Dialog----------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("Artículo creado con éxito.");
		dialogo1.getContentPane().setLayout(new BorderLayout());
		dialogo1.setSize(500, 470);
		dialogo1.setResizable(false);
		dialogo1.setLocationRelativeTo(null);
		dialogo1.addWindowListener(this);
		panel1 = new Panel();
		panel2 = new Panel();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel1.add(mensaje1);
		btn_dialogo_1 = new JButton("Aceptar");
		panel2.add(btn_dialogo_1);	
		dialogo1.getContentPane().add(BorderLayout.NORTH, panel1);
		dialogo1.getContentPane().add(BorderLayout.CENTER, panel2);
		btn_dialogo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				base_datos.cierra_conexion();
				Nuevo_Articulo.this.dispose();
				new Nuevo_Articulo().setVisible(true);
			}
		});
		//------------------------------------------------
		this.addWindowListener(this);
		this.setVisible(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public float comprueba_cantidad(String precio)
	{
		String precio_articulo = precio;
		char[] array_caracteres = precio_articulo.toCharArray();
		String precio_factorizado = "";
		String string_precio_dos_decimales = "";
		boolean llave = true;

		for (int i = 0; i < array_caracteres.length; i++)
		{
			if (array_caracteres[i] == ',')
			{
				precio_factorizado += ".";
			}
			else
			{
				precio_factorizado += array_caracteres[i];
			}
		}

		for (int i = 0; i < precio_factorizado.length(); i++)
		{
			if(llave)
			{
				if(precio_factorizado.charAt(i) == '.')
				{
					string_precio_dos_decimales += precio_factorizado.charAt(i);
					string_precio_dos_decimales += precio_factorizado.charAt(i+1);
					string_precio_dos_decimales += precio_factorizado.charAt(i+2); //ESTO PUEDE DAR PROBLEMAS SI EL NUMERO INTRODUCIDO POR EL USUARIO TIENE MENOS DE DOS DECIMALES, CORREGIR EN FUTURAS VERSIONES
					llave = false;
				}
				else
				{
					string_precio_dos_decimales += precio_factorizado.charAt(i);

				}
			}
		} 
		return Float.valueOf(string_precio_dos_decimales);
	}

	public void windowActivated(WindowEvent e)
	{}

	public void windowClosed(WindowEvent e)
	{}

	public void windowClosing(WindowEvent e)
	{
		base_datos.cierra_conexion();
		this.dispose();
	}

	public void windowDeactivated(WindowEvent e)
	{}

	public void windowDeiconified(WindowEvent e)
	{}

	public void windowIconified(WindowEvent e)
	{}

	public void windowOpened(WindowEvent e)
	{}
}
