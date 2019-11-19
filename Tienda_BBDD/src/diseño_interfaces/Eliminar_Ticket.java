package diseño_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Eliminar_Ticket extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNuevoArtculo;
	private JLabel label;
	private JComboBox<String>  comboBox;
	private JButton btnEliminar;
	private JButton btnEliminar_1;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;
	private JButton btn_dialogo_2;

	/**
	 * Create the frame.
	 */
	public Eliminar_Ticket()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNuevoArtculo = new JLabel("ELIMINAR TICKET");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(185, 11, 150, 24);
		contentPane.add(lblNuevoArtculo);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		label = new JLabel("Seleccionar Ticket: ");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(79, 66, 130, 24);
		contentPane.add(label);

		comboBox = new JComboBox<String> ();
		comboBox.setBounds(242, 68, 154, 20);
		contentPane.add(comboBox);

		btnEliminar = new JButton("Cancelar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_Ticket.this.setVisible(false);
			}
		});
		btnEliminar.setBounds(280, 397, 89, 23);
		contentPane.add(btnEliminar);

		btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(true);
			}
		});
		btnEliminar_1.setBounds(379, 397, 89, 23);
		contentPane.add(btnEliminar_1);

		//------------------Dialog----------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("¿Estás seguro que quieres eliminar este ticket?");
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
		dialogo1.add(BorderLayout.NORTH, panel1);
		dialogo1.add(BorderLayout.CENTER, panel2);
		btn_dialogo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(false);
			}
		});
		btn_dialogo_2.addActionListener(new ActionListener() {
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
