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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class EliminarArticulo. 
 * @author David Borrego Asencio
 * @since 11/01/2020
 * @version 1.0
 */
public class EliminarArticulo extends JFrame implements WindowListener
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The label eliminar articulo. */
	private JLabel labelEliminarArticulo;
	
	/** The button cancelar. */
	private JButton buttonCancelar;
	
	/** The button eliminar. */
	private JButton buttonEliminar;
	
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
	
	/** The btn dialogo 2. */
	private JButton btn_dialogo_2;
	
	/** The resultset 1. */
	private ResultSet resultset1;
	
	/** The combo articulo. */
	private JComboBox<String> comboArticulo;
	
	/** The base datos. */
	private ConectaBBDD base_datos;
	
	/** The label. */
	private JLabel label;
	/**
	 * Create the frame.
	 */
	public EliminarArticulo()
	{
		base_datos = new ConectaBBDD();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoArticulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		labelEliminarArticulo = new JLabel("ELIMINAR ART\u00CDCULO");
		labelEliminarArticulo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		labelEliminarArticulo.setBounds(164, 11, 176, 24);
		contentPane.add(labelEliminarArticulo);

		comboArticulo = new JComboBox<String>();
		comboArticulo.setBounds(150, 63, 186, 20);
		contentPane.add(comboArticulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				base_datos.cierra_conexion();
				EliminarArticulo.this.dispose();
				//new EliminarArticulo();
			}
		});
		buttonCancelar.setBounds(286, 397, 89, 23);
		contentPane.add(buttonCancelar);

		buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{			
				if(comboArticulo.getSelectedItem().toString().equals("Elegir uno..."))
				{			
					label.setText("Seleccione un artículo a eliminar.");
				}
				else
				{
					dialogo1.setVisible(true);
				}			
			}
		});
		buttonEliminar.setBounds(385, 397, 89, 23);
		contentPane.add(buttonEliminar);

		label = new JLabel("", SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		label.setBounds(10, 94, 468, 24);
		contentPane.add(label);

		//------------------Dialog----------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("¿Estás seguro que quieres eliminar este artículo?");
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
		btn_dialogo_1 = new JButton("Cancelar");
		btn_dialogo_2 = new JButton("Aceptar");
		panel2.add(btn_dialogo_1);
		panel2.add(btn_dialogo_2);
		dialogo1.getContentPane().add(BorderLayout.NORTH, panel1);
		dialogo1.getContentPane().add(BorderLayout.CENTER, panel2);
		btn_dialogo_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dialogo1.setVisible(false);
			}
		});
		btn_dialogo_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					//---------- ELIMINAR ARTÍCULO DE TODOS LOS TICKETS EN DONDE ES FK ---------------

					ResultSet idArticulo = base_datos.obtener_objetos("SELECT idArticulo FROM articulos WHERE descripcionArticulo = '"+ comboArticulo.getSelectedItem().toString() + "';");
					idArticulo.next();
					base_datos.agregar_objeto("DELETE FROM contiene WHERE idArticuloFK = '" + idArticulo.getInt(1) + "';");
	

					//---------- ELIMINAR ARTÍCULO ---------------
					String sentencia_eliminar_articulo = "DELETE FROM articulos WHERE descripcionArticulo = '"+ comboArticulo.getSelectedItem().toString() + "';";
					System.out.println(sentencia_eliminar_articulo);
					base_datos.agregar_objeto(sentencia_eliminar_articulo);
					
					
					base_datos.cierra_conexion();
					dialogo1.setVisible(false);
					EliminarArticulo.this.dispose();
					//new EliminarArticulo().setVisible(true);

				} catch (SQLException e1)
				{
					System.out.println(e1);
					label.setText("Error al eliminar el artículo");
				}		
			}
		});
		//------------------------------------------------

		//-------------RELLENA JCOMBOBOX DE ARTICULOS-------------
		comboArticulo.addItem("Elegir uno...");
		rellena_jcombobox_articulos();
		//--------------------------------------------------------

		this.setVisible(false);
		this.setResizable(false);
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
	{
		base_datos.cierra_conexion();
		this.dispose();
	}

	/**
	 * Window closing.
	 *
	 * @param e the e
	 */
	public void windowClosing(WindowEvent e)
	{}

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
	 * Fill JComboBox of items.
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
}