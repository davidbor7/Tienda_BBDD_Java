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

public class Eliminar_Articulo extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNuevoArtculo;
	private JButton button;
	private JButton button_1;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;
	private JButton btn_dialogo_2;
	private ResultSet resultset1;
	private JComboBox<String> comboBox;
	private Conecta_BBDD base_datos = new Conecta_BBDD();
	/**
	 * Create the frame.
	 */
	public Eliminar_Articulo()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNuevoArtculo = new JLabel("ELIMINAR ART\u00CDCULO");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(176, 11, 150, 24);
		contentPane.add(lblNuevoArtculo);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(150, 63, 186, 20);
		contentPane.add(comboBox);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_Articulo.this.setVisible(false);
			}
		});
		button.setBounds(286, 397, 89, 23);
		contentPane.add(button);

		button_1 = new JButton("Eliminar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(true);
			}
		});
		button_1.setBounds(385, 397, 89, 23);
		contentPane.add(button_1);

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
		btn_dialogo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(false);
			}
		});
		btn_dialogo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sentencia_eliminar_podologo = "DELETE FROM articulos WHERE descripcionArticulo = '"+ comboBox.getSelectedItem().toString() + "';";
				System.out.println(sentencia_eliminar_podologo);
				Conecta_BBDD.agregar_objeto(sentencia_eliminar_podologo);
				dialogo1.setVisible(false);
				Eliminar_Articulo.this.dispose();
				new Eliminar_Articulo().setVisible(true);
			}
		});
		//------------------------------------------------

		//-------------RELLENA JCOMBOBOX DE ARTICULOS-------------
		comboBox.addItem("Elegir uno...");
		rellena_jcombobox_articulos();
		//--------------------------------------------------------

		this.setVisible(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void windowActivated(WindowEvent e)
	{}

	public void windowClosed(WindowEvent e)
	{
		this.dispose();
	}

	public void windowClosing(WindowEvent e)
	{}

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
}