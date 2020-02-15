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


/**
 * The Class ModificarArticulo. This class modify items.
 * @author David Borrego Asencio
 * @since 11/01/2020
 * @version 1.0
 */
public class ModificarArticulo extends JFrame implements WindowListener, ItemListener
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The base datos. */
	private ConectaBBDD base_datos;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The textfield descripcion. */
	private JTextField textfieldDescripcion;
	
	/** The textfield precio. */
	private JTextField textfieldPrecio;
	
	/** The textfield cantidad. */
	private JTextField textfieldCantidad;
	
	/** The label precio. */
	private JLabel labelPrecio;
	
	/** The label. */
	private JLabel label;
	
	/** The label cantidad. */
	private JLabel labelCantidad;
	
	/** The label modificar articulo. */
	private JLabel labelModificarArticulo;
	
	/** The button cancelar. */
	private JButton buttonCancelar;
	
	/** The button guardar. */
	private JButton buttonGuardar;
	
	/** The label seleccionar articulo. */
	private JLabel labelSeleccionarArticulo;
	
	/** The combo articulo. */
	private JComboBox<String> comboArticulo;
	
	/** The dialogo 1. */
	private JDialog dialogo1;
	
	/** The panel 1. */
	private Panel panel1;
	
	/** The panel 2. */
	private Panel panel2;
	
	/** The mensaje 1. */
	private JLabel mensaje1;
	
	/** The btn dialogo 1. */
	private JButton btn_dialogo_1;
	
	/** The resultset 1. */
	private ResultSet resultset1;
	
	/** The resultset 2. */
	private ResultSet resultset2;
	
	/** The nombre articulo. */
	private String nombre_articulo;
	/**
	 * Create the frame.
	 */
	public ModificarArticulo()
	{
		base_datos = new ConectaBBDD();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoArticulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
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
				ModificarArticulo.this.setVisible(false);
				ModificarArticulo.this.dispose();
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
				ModificarArticulo.this.dispose();
				new ModificarArticulo().setVisible(true);
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

	/**
	 * Window activated.
	 *
	 * @param e the e
	 */
	public void windowActivated(WindowEvent e)
	{}

	/**
	 * Window closed.
	 *
	 * @param e the e
	 */
	public void windowClosed(WindowEvent e)
	{}

	/**
	 * Window closing.
	 *
	 * @param e the e
	 */
	public void windowClosing(WindowEvent e)
	{
		base_datos.cierra_conexion();
		this.dispose();
	}

	/**
	 * Window deactivated.
	 *
	 * @param e the e
	 */
	public void windowDeactivated(WindowEvent e)
	{}

	/**
	 * Window deiconified.
	 *
	 * @param e the e
	 */
	public void windowDeiconified(WindowEvent e)
	{}

	/**
	 * Window iconified.
	 *
	 * @param e the e
	 */
	public void windowIconified(WindowEvent e)
	{}

	/**
	 * Window opened.
	 *
	 * @param e the e
	 */
	public void windowOpened(WindowEvent e)
	{}

	/**
	 * Fill JComboBox of items
	 */
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

	/**
	 * Fill JComboBox of items properties
	 */
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
	
	/**
	 * Item state changed.
	 *
	 * @param ie the ie
	 */
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
	
	/**
	 * Check quantity
	 *
	 * @param precio the price
	 * @return the float
	 */
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