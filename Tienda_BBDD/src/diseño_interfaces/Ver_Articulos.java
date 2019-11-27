package diseño_interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
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

public class Ver_Articulos extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private ResultSet resultset;
	private Conecta_BBDD base_datos = new Conecta_BBDD();
	private DefaultTableModel modelo;
	private DefaultTableCellRenderer tcr;
	/**
	 * Create the frame.
	 */
	public Ver_Articulos()
	{
        setBounds(100, 100, 500, 470);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane); 
        contentPane.setLayout(null);
        
        //ARRAY DE STRING CON LAS COLUMNAS DE LA TABLA
        String[] columnNames = {"Descripción","Precio","Stock"};

        //CREAMOS UN MODELO DE DATOS Y LE PASAMOS EL OBJETO QUE CONTIENE EL NOMBRE DE LAS COLUMNAS
        modelo = new DefaultTableModel(null, columnNames);
        
        //SE CREA LA TABLA Y SE LE PASA EL MODELO EN EL CONSTRUCTOR
        tabla = new JTable(modelo);
        
        //SE DEFINE LE TAMAÑO
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
        
        //CREAMOS UN JSCROLLPANE Y LE AÑADIMOS UN JTABLE
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(37, 68, 407, 311);
        
        //AGREGAMOS EL SCROLL
        getContentPane().add(scrollPane);
        
        //HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.DARK_GRAY);
        separator.setBounds(10, 35, 468, 2);
        contentPane.add(separator);
        
        JLabel lblArtculos = new JLabel("ART\u00CDCULOS");
        lblArtculos.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
        lblArtculos.setBounds(206, 11, 90, 24);
        contentPane.add(lblArtculos);
        
        JButton button = new JButton("Volver");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		Ver_Articulos.this.dispose();
        	}
        });
        button.setBounds(355, 397, 89, 23);
        contentPane.add(button);
        
        rellena_tabla();
        
        this.setResizable(false);
        this.addWindowListener(this);
		this.setVisible(false);
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

	public void rellena_tabla()
	{

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