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
	private Conecta_BBDD base_datos = new Conecta_BBDD();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblPrecio;
	private JLabel label;
	private JLabel lblCantidad;
	private JLabel lblNuevoArtculo;
	private JButton button;
	private JButton button_1;
	private JLabel lblSeleccionarArtculo;
	private JComboBox<String> comboBox;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblDescripcin.setBounds(121, 160, 88, 24);
		contentPane.add(lblDescripcin);

		textField = new JTextField();
		textField.setBounds(251, 160, 153, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(252, 211, 72, 24);
		contentPane.add(textField_1);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblPrecio.setBounds(154, 211, 53, 24);
		contentPane.add(lblPrecio);

		label = new JLabel("\u20AC");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(328, 211, 25, 24);
		contentPane.add(label);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblCantidad.setBounds(140, 260, 63, 24);
		contentPane.add(lblCantidad);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(251, 260, 73, 24);
		contentPane.add(textField_2);

		lblNuevoArtculo = new JLabel("MODIFICAR ART\u00CDCULO");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(169, 11, 150, 24);
		contentPane.add(lblNuevoArtculo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Articulo.this.setVisible(false);
				Modificar_Articulo.this.dispose();
			}
		});
		button.setBounds(292, 397, 89, 23);
		contentPane.add(button);

		button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!comboBox.getSelectedItem().equals("Elegir uno..."))
				{
					String sentencia_modificar_podologo = "UPDATE articulos SET descripcionArticulo = '" + textField.getText() + "', precioArticulo = " + textField_1.getText() + ", stockArticulo = " + textField_2.getText() + " WHERE descripcionArticulo = '"+ nombre_articulo +"';";
					System.out.println(sentencia_modificar_podologo);
					Conecta_BBDD.agregar_objeto(sentencia_modificar_podologo);
					dialogo1.setVisible(true);
				}	
			}
		});
		button_1.setBounds(389, 397, 89, 23);
		contentPane.add(button_1);

		lblSeleccionarArtculo = new JLabel("Seleccionar Art\u00EDculo: ");
		lblSeleccionarArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblSeleccionarArtculo.setBounds(66, 110, 153, 24);
		contentPane.add(lblSeleccionarArtculo);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(250, 112, 154, 20);
		contentPane.add(comboBox);

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
				dialogo1.setVisible(false);
				Modificar_Articulo.this.dispose();
				new Modificar_Articulo().setVisible(true);
			}
		});
		//--------------------------------------------------------
		
		//-------------RELLENA JCOMBOBOX DE ARTICULOS-------------
		comboBox.addItem("Elegir uno...");
		rellena_jcombobox_articulos();
		//--------------------------------------------------------

		comboBox.addItemListener( this);
		this.addWindowListener(this);
		this.setVisible(false);
		this.setLocationRelativeTo(null);
	}

	public void windowActivated(WindowEvent e)
	{}

	public void windowClosed(WindowEvent e)
	{}

	public void windowClosing(WindowEvent e)
	{
		this.setVisible(false);	
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
		resultset1 = Conecta_BBDD.obtener_objetos("SELECT descripcionArticulo FROM articulos ORDER BY 1;");

		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			while(resultset1.next())
			{
				comboBox.addItem(resultset1.getString("descripcionArticulo"));
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
				textField.setText(resultset2.getString("descripcionArticulo"));
				textField_1.setText(resultset2.getString("precioArticulo"));
				textField_2.setText(resultset2.getString("stockArticulo"));

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}		
	
	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		
		if(ie.getStateChange()==1 && (!comboBox.getSelectedItem().equals("Elegir uno...")))  // LA PRIMERA CONDICIÓN SIRVE PARA SOLUCIONAR EL PROBLEMA DE QUE EL JCOMBOBOX SE LLAME DOS VECES CON EL ITEMLISTENER, CON LOS CHOICE NO OCURRE ESTE PROBLEMA
		{
			nombre_articulo = (String)ie.getItem();
			System.out.println("SELECT * FROM articulos WHERE descripcionArticulo = '" + nombre_articulo +"';");
			resultset2 = Conecta_BBDD.obtener_objetos("SELECT * FROM articulos WHERE descripcionArticulo = '" + (String)ie.getItem() +"';"); //ACTUALIZA EL RESULTSET
			rellena_todos_los_campos();
		}else
		{
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
		}
	}	
}