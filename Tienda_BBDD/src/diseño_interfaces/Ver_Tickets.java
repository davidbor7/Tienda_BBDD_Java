package diseño_interfaces;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Ver_Tickets extends JFrame implements WindowListener, ItemListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private ResultSet resultset;
	private Conecta_BBDD base_datos ;
	private DefaultTableModel modelo;
	private DefaultTableCellRenderer tcr;
	private JComboBox<String>comboTicket;
	private ResultSet resulset_datos_ticket;
	private ResultSet descripcionArticulo;
	private JLabel label_Ticket;
	private JLabel label_Articulos;
	private JLabel label_Total;
	private int id_TicketSeleccionado;
	/**
	 * Create the frame.
	 */
	public Ver_Tickets()
	{
		
		base_datos = new Conecta_BBDD();
				
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);

		//ARRAY DE STRING CON LAS COLUMNAS DE LA TABLA
		String[] columnNames = {"Artículo","Cantidad"};

		//CREAMOS UN MODELO DE DATOS Y LE PASAMOS EL OBJETO QUE CONTIENE EL NOMBRE DE LAS COLUMNAS
		modelo = new DefaultTableModel(null, columnNames);

		//SE CREA LA TABLA Y SE LE PASA EL MODELO EN EL CONSTRUCTOR
		tabla = new JTable(modelo);

		//SE DEFINE LE TAMAÑO
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));

		//CREAMOS UN JSCROLLPANE Y LE AÑADIMOS UN JTABLE
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(37, 91, 423, 246);

		//AGREGAMOS EL SCROLL
		getContentPane().add(scrollPane);

		//HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);


		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 35, 468, 2);
		contentPane.add(separator);

		JLabel lblArtculos = new JLabel("TICKETS", SwingConstants.CENTER);
		lblArtculos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblArtculos.setBounds(199, 11, 90, 24);
		contentPane.add(lblArtculos);

		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Ver_Tickets.this.dispose();
			}
		});
		button.setBounds(371, 400, 89, 23);
		contentPane.add(button);

		comboTicket = new JComboBox<String>();
		comboTicket.setBounds(243, 48, 103, 20);
		contentPane.add(comboTicket);

		JLabel lblRefTicket = new JLabel("REF. TICKET:", SwingConstants.CENTER);
		lblRefTicket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRefTicket.setBounds(130, 48, 103, 19);
		contentPane.add(lblRefTicket);

		label_Ticket = new JLabel("Fecha Ticket:");
		label_Ticket.setForeground(Color.BLUE);
		label_Ticket.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Ticket.setBounds(37, 348, 217, 24);
		contentPane.add(label_Ticket);

		label_Articulos = new JLabel("N\u00FAmero de Art\u00EDculos: ");
		label_Articulos.setForeground(Color.BLUE);
		label_Articulos.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Articulos.setBounds(37, 373, 217, 24);
		contentPane.add(label_Articulos);

		label_Total = new JLabel("Total:");
		label_Total.setForeground(Color.BLUE);
		label_Total.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Total.setBounds(37, 397, 217, 24);
		contentPane.add(label_Total);

		this.setResizable(false);
		this.addWindowListener(this);
		this.setVisible(false);
		this.setLocationRelativeTo(null);

		comboTicket.addItem("Elegir uno...");
		rellena_tickets();
		comboTicket.addItemListener(this);
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

	public void rellena_tabla()
	{

		resultset =base_datos.obtener_objetos("SELECT * FROM contiene WHERE idTicketFK = "+ id_TicketSeleccionado + ";");

		try 
		{
			while(resultset.next())//USAMOS UN WHILE PARA RELLENAR LA TABLA
			{
				Object [] fila = new Object[2]; // HAY 3 COLUMNAS

				descripcionArticulo = base_datos.obtener_objetos("SELECT descripcionArticulo FROM articulos WHERE idArticulo="+ resultset.getInt(1) +";");
				descripcionArticulo.next();
				fila[0] = descripcionArticulo.getString(1); 
											
				fila[1] = resultset.getObject(3); 

				modelo.addRow(fila); // SE AÑADE AL MODELO LA FILA COMPLETA
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void rellena_tickets()
	{
		String sentencia = "SELECT * FROM tickets;";
		resulset_datos_ticket = base_datos.obtener_objetos(sentencia);

		try
		{
			while(resulset_datos_ticket.next())
			{

				comboTicket.addItem(resulset_datos_ticket.getString(1));

			}
		} catch (SQLException e)
		{
			System.out.println("Error al rellenar los tickets en el JComboBox.");
		}

	}

	

	public void itemStateChanged(ItemEvent ie)
	{

		if(ie.getStateChange()==1 && (!comboTicket.getSelectedItem().toString().equals("Elegir uno...")))  // LA PRIMERA CONDICIÓN SIRVE PARA SOLUCIONAR EL PROBLEMA DE QUE EL JCOMBOBOX SE LLAME DOS VECES CON EL ITEMLISTENER, CON LOS CHOICE NO OCURRE ESTE PROBLEMA
		{
			id_TicketSeleccionado = Integer.parseInt(comboTicket.getSelectedItem().toString());
			String sentencia = "SELECT * FROM tickets WHERE idTicket = " + id_TicketSeleccionado + ";";
			resulset_datos_ticket = base_datos.obtener_objetos(sentencia);

			try
			{
				resulset_datos_ticket.next();

				// INDICAMOS EL NUEVO FORMATO PARA LA FECHA
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String fechaTexto = formatter.format(resulset_datos_ticket.getDate(2));

				label_Ticket.setText("Fecha Ticket: "+fechaTexto);
				label_Articulos.setText("Num. Artículos: "+resulset_datos_ticket.getString(3));
				label_Total.setText("Total: "+resulset_datos_ticket.getString(4)+"€");

				
				modelo.setRowCount(0); //LIMPIAMOS LA TABLA
				rellena_tabla();

			} catch (SQLException e)
			{
				System.out.println("Error al obtener los datos del ticket seleccionado.");
			}
		}
	}	
}
