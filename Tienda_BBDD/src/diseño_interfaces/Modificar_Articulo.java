package diseño_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class Modificar_Articulo extends JFrame implements WindowListener, ItemListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Conecta_BBDD base_datos;
	private JPanel contentPane;
	private JTextField textfieldDescripcion;
	private JTextField textfieldPrecio;
	private JTextField textfieldCantidad;
	private JLabel labelPrecio;
	private JLabel label;
	private JLabel labelCantidad;
	private JLabel labelModificarArticulo;
	private JButton buttonCancelar;
	private JButton buttonGuardar;
	private JLabel labelSeleccionarArticulo;
	private JComboBox<String> comboArticulo;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;
	private ResultSet resultset1;
	private ResultSet resultset2;
	private String nombre_articulo;
	/**
	 * Create the frame.
	 */
	public Modificar_Articulo()
	{
		base_datos = new Conecta_BBDD();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelDescripcin = new JLabel("Descripci\u00F3n:");
		labelDescripcin.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelDescripcin.setBounds(121, 160, 88, 24);
		contentPane.add(labelDescripcin);

		textfieldDescripcion = new JTextField();
		textfieldDescripcion.setBounds(251, 160, 153, 24);
		contentPane.add(textfieldDescripcion);
		textfieldDescripcion.setColumns(10);

		textfieldPrecio = new JTextField();
		textfieldPrecio.setColumns(10);
		textfieldPrecio.setBounds(252, 211, 72, 24);
		contentPane.add(textfieldPrecio);

		labelPrecio = new JLabel("Precio:");
		labelPrecio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelPrecio.setBounds(154, 211, 53, 24);
		contentPane.add(labelPrecio);

		label = new JLabel("\u20AC");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(328, 211, 25, 24);
		contentPane.add(label);

		labelCantidad = new JLabel("Cantidad:");
		labelCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelCantidad.setBounds(140, 260, 63, 24);
		contentPane.add(labelCantidad);

		textfieldCantidad = new JTextField();
		textfieldCantidad.setColumns(10);
		textfieldCantidad.setBounds(251, 260, 73, 24);
		contentPane.add(textfieldCantidad);

		labelModificarArticulo = new JLabel("MODIFICAR ART\u00CDCULO");
		labelModificarArticulo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		labelModificarArticulo.setBounds(169, 11, 170, 24);
		contentPane.add(labelModificarArticulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Modificar_Articulo.this.setVisible(false);
				Modificar_Articulo.this.dispose();
			}
		});
		buttonCancelar.setBounds(292, 397, 89, 23);
		contentPane.add(buttonCancelar);

		buttonGuardar = new JButton("Guardar");
		buttonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!comboArticulo.getSelectedItem().equals("Elegir uno..."))
				{
					String sentencia_modificar_podologo = "UPDATE articulos SET descripcionArticulo = '" + textfieldDescripcion.getText() + "', precioArticulo = " + comprueba_cantidad(textfieldPrecio.getText()) + ", stockArticulo = " + textfieldCantidad.getText() + " WHERE descripcionArticulo = '"+ nombre_articulo +"';";
					System.out.println(sentencia_modificar_podologo);
					base_datos.agregar_objeto(sentencia_modificar_podologo);
					dialogo1.setVisible(true);
				}	
			}
		});
		buttonGuardar.setBounds(389, 397, 89, 23);
		contentPane.add(buttonGuardar);

		labelSeleccionarArticulo = new JLabel("Seleccionar Art\u00EDculo: ");
		labelSeleccionarArticulo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		labelSeleccionarArticulo.setBounds(66, 110, 153, 24);
		contentPane.add(labelSeleccionarArticulo);

		comboArticulo = new JComboBox<String>();
		comboArticulo.setBounds(250, 112, 154, 20);
		contentPane.add(comboArticulo);

		//------------------Dialog------------------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("Artículo modificado con éxito.");
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
			public void actionPerformed(ActionEvent e) {
				
				base_datos.cierra_conexion();
				dialogo1.setVisible(false);
				Modificar_Articulo.this.dispose();
				new Modificar_Articulo().setVisible(true);
			}
		});
		//--------------------------------------------------------
		
		//-------------RELLENA JCOMBOBOX DE ARTICULOS-------------
		comboArticulo.addItem("Elegir uno...");
		rellena_jcombobox_articulos();
		//--------------------------------------------------------

		comboArticulo.addItemListener( this);
		this.addWindowListener(this);
		this.setResizable(false);
		this.setVisible(false);
		this.setLocationRelativeTo(null);
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

	public void rellena_jcombobox_articulos()
	{
		resultset1 = base_datos.obtener_objetos("SELECT descripcionArticulo FROM articulos ORDER BY 1;");
		
		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			while(resultset1.next())
			{
				comboArticulo.addItem(resultset1.getString("descripcionArticulo"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void rellena_todos_los_campos()
	{

		try //USAMOS UN WHILE PARA RELLENAR LOS CAMPOS CON LOS DATOS DEL RESULSET
		{
			while(resultset2.next())
			{
				textfieldDescripcion.setText(resultset2.getString("descripcionArticulo"));
				textfieldPrecio.setText(resultset2.getString("precioArticulo"));
				textfieldCantidad.setText(resultset2.getString("stockArticulo"));

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}		
	
	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange()==1 && (!comboArticulo.getSelectedItem().equals("Elegir uno...")))  // LA PRIMERA CONDICIÓN SIRVE PARA SOLUCIONAR EL PROBLEMA DE QUE EL JCOMBOBOX SE LLAME DOS VECES CON EL ITEMLISTENER, CON LOS CHOICE NO OCURRE ESTE PROBLEMA
		{
			nombre_articulo = (String)ie.getItem();
			System.out.println("SELECT * FROM articulos WHERE descripcionArticulo = '" + nombre_articulo +"';");
			resultset2 = base_datos.obtener_objetos("SELECT * FROM articulos WHERE descripcionArticulo = '" + (String)ie.getItem() +"';"); //ACTUALIZA EL RESULTSET
			rellena_todos_los_campos();
		}else
		{
			textfieldDescripcion.setText("");
			textfieldPrecio.setText("");
			textfieldCantidad.setText("");
		}
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
}