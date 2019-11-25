package diseño_interfaces;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Nuevo_Articulo extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPrecio;
	private JLabel label;
	private JLabel lblCantidad;
	private JTextField textField_2;
	private JLabel lblNuevoArtculo;
	private JButton button;
	private JButton button_1;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;


	/**
	 * Create the frame.
	 */
	public Nuevo_Articulo()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblDescripcin.setBounds(114, 120, 88, 24);
		contentPane.add(lblDescripcin);

		textField = new JTextField();
		textField.setBounds(244, 120, 153, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(244, 181, 72, 24);
		contentPane.add(textField_1);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblPrecio.setBounds(149, 181, 53, 24);
		contentPane.add(lblPrecio);

		label = new JLabel("\u20AC");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(323, 181, 25, 24);
		contentPane.add(label);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblCantidad.setBounds(132, 247, 63, 24);
		contentPane.add(lblCantidad);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(247, 247, 72, 24);
		contentPane.add(textField_2);

		lblNuevoArtculo = new JLabel("NUEVO ART\u00CDCULO");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(183, 11, 121, 24);
		contentPane.add(lblNuevoArtculo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nuevo_Articulo.this.setVisible(false);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.requestFocus();
			}
		});
		button.setBounds(288, 397, 89, 23);
		contentPane.add(button);

		button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(true);
			}
		});
		button_1.setBounds(385, 397, 89, 23);
		contentPane.add(button_1);

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
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(false);
			}
		});
		//------------------------------------------------
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
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField.requestFocus();	
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
