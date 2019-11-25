package diseño_interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ver_Articulos extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNuevoArtculo;
	private JTable table;
	private JButton btnVolver;


	/**
	 * Create the frame.
	 */
	public Ver_Articulos()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNuevoArtculo = new JLabel("ARTÍCULOS");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(214, 11, 89, 24);
		contentPane.add(lblNuevoArtculo);

		table = new JTable();
		table.setBounds(130, 177, 173, -81);
		contentPane.add(table);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		List list = new List();
		list.setBounds(98, 73, 297, 299);
		contentPane.add(list);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ver_Articulos.this.setVisible(false);
			}
		});
		btnVolver.setBounds(389, 397, 89, 23);
		contentPane.add(btnVolver);

		this.setVisible(false);
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