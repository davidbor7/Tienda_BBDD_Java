package diseño_interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Ver_Articulos extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNuevoArtculo;
	private JButton btnVolver;
	private ResultSet resultset;
	private Conecta_BBDD base_datos = new Conecta_BBDD();
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
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

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ver_Articulos.this.setVisible(false);
			}
		});
		btnVolver.setBounds(389, 397, 89, 23);
		contentPane.add(btnVolver);
/*
		table = new JTable(modelo);	
		table.setBounds(33, 64, 418, 309);
		contentPane.add(table);
		
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);

		//AÑADIMOS LAS COLUMNAS AL JTABLE
		modelo.addColumn("Descripción Artículo");
		modelo.addColumn("Precio");
		modelo.addColumn("Stock");

		//HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
		tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
		
		*/
		rellena_tabla();
		

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

	public void rellena_tabla()
	{
		Object [] encabezado = {"Descripción Artículo", "Precio", "Stock"};
		modelo.addRow(encabezado);

		resultset = Conecta_BBDD.obtener_objetos("SELECT * FROM articulos ORDER BY 2;");

		try 
		{
			while(resultset.next())//USAMOS UN WHILE PARA RELLENAR LA TABLA
			{
				Object [] fila = new Object[3]; // HAY 3 COLUMNAS

				// SE RELLENA CADA POSICIÓN DEL ARRAY CON UNA DE LAS COLUMNAS DE LA TABLA EN LA BASE DE DATOS
				for (int i = 0; i < 3; i++)
				{
					fila[i] = resultset.getObject(i+2); // EL PRIMER INDICE EN EL RS ES EL 1, NO EL 0, POR ESO SE SUMA 1.
				}
				modelo.addRow(fila); // SE AÑADE AL MODELO LA FILA COMPLETA
			}
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}