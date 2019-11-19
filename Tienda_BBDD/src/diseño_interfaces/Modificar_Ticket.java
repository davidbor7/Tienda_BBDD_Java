package diseño_interfaces;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Modificar_Ticket extends JFrame implements WindowListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblCantidad;
	private JLabel lblNuevoArtculo;
	private JTextField textField_1;
	private JTextField textField_2;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;

	/**
	 * Create the frame.
	 */
	public Modificar_Ticket()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);
		
		lblNuevoArtculo = new JLabel("MODIFICAR TICKET");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(179, 11, 141, 24);
		contentPane.add(lblNuevoArtculo);
		
		JLabel lblDescripcin = new JLabel("Fecha Ticket:");
		lblDescripcin.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblDescripcin.setBounds(106, 95, 88, 24);
		contentPane.add(lblDescripcin);

		textField = new JTextField();
		textField.setBounds(204, 95, 39, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(257, 95, 39, 24);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(308, 95, 73, 24);
		contentPane.add(textField_2);

		lblCantidad = new JLabel("Precio Total: ");
		lblCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblCantidad.setBounds(106, 362, 88, 24);
		contentPane.add(lblCantidad);
		
		JLabel lblXxx = new JLabel("XXX \u20AC");
		lblXxx.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblXxx.setBounds(204, 362, 45, 24);
		contentPane.add(lblXxx);

		List list = new List();
		list.setBounds(106, 197, 290, 120);
		contentPane.add(list);
		
		JComboBox<String>  comboBox = new JComboBox<String> ();
		comboBox.setBounds(106, 130, 154, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("A\u00F1adir Art\u00EDculo");
		btnNewButton.setBounds(275, 160, 121, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(247, 95, 13, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label_1.setBounds(298, 95, 13, 24);
		contentPane.add(label_1);
		
		JLabel lblDa = new JLabel("D\u00EDa");
		lblDa.setBounds(214, 79, 18, 14);
		contentPane.add(lblDa);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(268, 79, 28, 14);
		contentPane.add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(330, 79, 28, 14);
		contentPane.add(lblAo);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(377, 397, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Modificar_Ticket.this.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(280, 397, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnEliminarArtculo = new JButton("Eliminar Art\u00EDculo");
		btnEliminarArtculo.setBounds(105, 323, 130, 23);
		contentPane.add(btnEliminarArtculo);
		
		JLabel lblSeleccionarTicket = new JLabel("Seleccionar Ticket: ");
		lblSeleccionarTicket.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblSeleccionarTicket.setBounds(64, 51, 130, 24);
		contentPane.add(lblSeleccionarTicket);
		
		JComboBox<String> comboBox_1 = new JComboBox<String> ();
		comboBox_1.setBounds(227, 48, 154, 20);
		contentPane.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(106, 161, 63, 20);
		contentPane.add(comboBox_2);
		
		JLabel label_2 = new JLabel("Art\u00EDculo");
		label_2.setBounds(51, 133, 45, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Cantidad");
		label_3.setBounds(51, 164, 57, 14);
		contentPane.add(label_3);

		//------------------Dialog----------------------
				dialogo1 = new JDialog(this, "", true);
				mensaje1 = new JLabel("Ticket modificado con éxito.");
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
					}
				});
			   //------------------------------------------------
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public void windowActivated(WindowEvent e)
	{}

	public void windowClosed(WindowEvent e)
	{
		this.setVisible(false);	
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
}
