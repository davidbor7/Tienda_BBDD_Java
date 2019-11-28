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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;


public class Nuevo_Ticket extends JFrame implements WindowListener
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCantidad;
	private JLabel lblNuevoArtculo;
	private JDialog dialogo1;
	private Panel panel1;
	private Panel panel2;
	private JLabel mensaje1;
	private JButton btn_dialogo_1;
	private JSeparator separator;
	private JLabel lblFechaTicket;
	private JLabel cantidadTotalTicket;
	private List lista;
	private JComboBox<String> combo_articulo;
	private JButton btnAgregarArticulo;
	private JComboBox<String> combo_cantidad;
	private JComboBox<String> combo_dia;
	private JComboBox<String> combo_mes;
	private JComboBox<String> combo_anyo;
	private ResultSet resultset1;
	private ResultSet resultset2;
	private double cantidad_total;
	private Conecta_BBDD base_datos = new Conecta_BBDD();
	private ArrayList<String> lista_de_articulos;
	private JLabel mensaje_aviso;
	boolean llave = false;
	private ArrayList<String> lista_de_articulos_en_jlist;
	private int cantidad_int;
	/**
	 * Create the frame.
	 */
	public Nuevo_Ticket()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 500, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 38, 468, 2);
		contentPane.add(separator);

		lblNuevoArtculo = new JLabel("NUEVO TICKET");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblNuevoArtculo.setBounds(197, 11, 121, 24);
		contentPane.add(lblNuevoArtculo);

		lblFechaTicket = new JLabel("Fecha Ticket:");
		lblFechaTicket.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblFechaTicket.setBounds(64, 77, 88, 24);
		contentPane.add(lblFechaTicket);

		lblCantidad = new JLabel("Precio Total: ");
		lblCantidad.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblCantidad.setBounds(46, 362, 88, 24);
		contentPane.add(lblCantidad);

		cantidadTotalTicket = new JLabel("0€");
		cantidadTotalTicket.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		cantidadTotalTicket.setBounds(135, 362, 192, 24);
		contentPane.add(cantidadTotalTicket);

		lista = new List();
		lista.setBounds(46, 192, 360, 148);
		contentPane.add(lista);

		combo_articulo = new JComboBox<String> ();
		combo_articulo.setBounds(107, 122, 121, 20);
		contentPane.add(combo_articulo);

		btnAgregarArticulo = new JButton("A\u00F1adir");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//mensaje_aviso.setText("");
				agrega_articulo_a_la_lista();

				/*
				System.out.println("-");
				System.out.println("-");
				for (int i = 0; i < lista_de_articulos.size(); i++) 
				{
					System.out.println(lista_de_articulos.get(i));
				}
				 */

			}
		});
		btnAgregarArticulo.setBounds(241, 153, 74, 23);
		contentPane.add(btnAgregarArticulo);

		JLabel label = new JLabel("/");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(212, 77, 13, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label_1.setBounds(313, 77, 13, 24);
		contentPane.add(label_1);

		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setBounds(179, 61, 18, 14);
		contentPane.add(lblDia);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(264, 61, 28, 14);
		contentPane.add(lblMes);

		JLabel lblAnyo = new JLabel("A\u00F1o");
		lblAnyo.setBounds(358, 61, 28, 14);
		contentPane.add(lblAnyo);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(true);
			}
		});
		btnGuardar.setBounds(377, 397, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nuevo_Ticket.this.dispose();
			}
		});
		btnCancelar.setBounds(280, 397, 89, 23);
		contentPane.add(btnCancelar);

		JButton btnEliminarArticulo = new JButton("Eliminar");
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				elimina_articulo_de_lista();


				/*
				mensaje_aviso.setText("");
				if (lista_de_articulos.contains(combo_articulo.getSelectedItem().toString()))
				{
					elimina_articulo_de_lista();
				}
				System.out.println("-");
				System.out.println("-");
				for (int i = 0; i < lista_de_articulos.size(); i++) 
				{
					System.out.println(lista_de_articulos.get(i));
				}
				 */
			}
		});
		btnEliminarArticulo.setBounds(325, 153, 81, 23);
		contentPane.add(btnEliminarArticulo);

		combo_cantidad = new JComboBox<String>();
		combo_cantidad.setBounds(107, 153, 60, 20);
		contentPane.add(combo_cantidad);

		JLabel lblArticulo = new JLabel("Art\u00EDculo");
		lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArticulo.setBounds(46, 121, 69, 19);
		contentPane.add(lblArticulo);

		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad_1.setBounds(35, 152, 69, 20);
		contentPane.add(lblCantidad_1);

		combo_dia = new JComboBox<String>();
		combo_dia.setBounds(161, 77, 45, 20);
		contentPane.add(combo_dia);

		combo_mes = new JComboBox<String>();
		combo_mes.setBounds(226, 79, 81, 20);
		contentPane.add(combo_mes);

		combo_anyo = new JComboBox<String>();
		combo_anyo.setBounds(325, 79, 81, 20);
		contentPane.add(combo_anyo);

		mensaje_aviso = new JLabel("");
		mensaje_aviso.setForeground(Color.RED);
		mensaje_aviso.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		mensaje_aviso.setBounds(107, 340, 290, 24);
		contentPane.add(mensaje_aviso);

		//------------------Dialog----------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("Ticket creado con éxito.");
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
		btn_dialogo_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				dialogo1.setVisible(false);
			}

		});

		//------------------------------------------------

		cantidad_total = 0.0;
		lista_de_articulos = new ArrayList<String>();
		lista_de_articulos_en_jlist = new ArrayList<String>();

		rellena_dias();
		rellena_meses();
		rellena_anyos();
		rellena_articulos();
		rellena_cantidad();

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

	public void rellena_dias()
	{	
		for (int i = 0; i < 31; i++)
		{
			combo_dia.addItem(""+(i+1));
		}	
	}
	public void rellena_meses()
	{
		String [] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

		for (int i = 0; i < 12; i++)
		{
			combo_mes.addItem(meses[i]);
		}

	}
	public void rellena_anyos()
	{

		for (int i = 1900; i < 2050; i++)
		{
			combo_anyo.addItem(""+i);
		}
		combo_anyo.setSelectedIndex(119);
	}

	public void rellena_cantidad()
	{	
		for (int i = 0; i < 20; i++)
		{
			combo_cantidad.addItem(""+(i+1));
		}
	}
	public void rellena_articulos()
	{
		resultset1 = Conecta_BBDD.obtener_objetos("SELECT descripcionArticulo FROM articulos ORDER BY 1;");

		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			while(resultset1.next())
			{
				combo_articulo.addItem(resultset1.getString("descripcionArticulo"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public double dame_precio_articulo(String articulo_seleccionado)
	{
		String articulo = articulo_seleccionado;
		double precio_articulo = 0.0;
		resultset2 = Conecta_BBDD.obtener_objetos("SELECT precioArticulo FROM articulos WHERE descripcionArticulo = '" + articulo + "';");	
		try
		{
			while(resultset2.next())
			{
				precio_articulo = resultset2.getDouble(1);
			}
		} catch (SQLException e)
		{
			System.out.println(e);
		}
		return precio_articulo;
	}

	public String devuelve_articulo_seleccionado_de_la_lista()
	{	
		String texto_lista = lista.getSelectedItem().toString();
		String [] descompone_string;
		descompone_string = texto_lista.split("  --  ");	
		return descompone_string[0];	
	}
	/*
	public void actualiza_lista()
	{
		for (int i = 0; i < lista_de_articulos_en_jlist.size(); i++) 
		{
			lista.add(lista_de_articulos_en_jlist.get(i)); //AÑADIMOS A LA LISTA LA DESCRIPCIÓN LA CANTIDAD Y EL PRECIO, TENIENDO EN CUENTA ESTE ÚLTIMO SEGÚN LA CANTIDAD				
		}
	}
	 */
	public void agrega_articulo_a_la_lista()
	{
		mensaje_aviso.setText("");

		//-----------AÑADE NOMBRE DE ARTÍCULO AL ARRAYLIST---------
		//
		//---------------------------------------------------------					

		//String cantidad = combo_cantidad.getSelectedItem().toString();
		//cantidad_int = Integer.valueOf(cantidad); //OBTENEMOS LA CANTIDAD DEL ARTÍCULO QUE ESTAMOS AÑADIENDO	


		//-------	AGREGA A LA LISTA EL ELEMENTO SELECCIONADO  -----------

		lista.add(combo_articulo.getSelectedItem().toString() + "  --  " + combo_cantidad.getSelectedItem().toString() + "  --  " + (String.format("%.2f", (dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int))+"€"));

		//-------	AGREGA AL ARRAYLIST EL ELEMENTO SELECCIONADO  -----------

		lista_de_articulos.add(combo_articulo.getSelectedItem().toString());




		//lista.add("Prueba");

		//----------AUMENTA TOTAL DE TICKET------------------------
		//cantidad_total += dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int ; //AUMENTA EL VALOR DEL TICKET CON EL PRECIO SELECCIONADO, TENIENDO EN CUENTA PRECIO Y CANTIDAD SELECIONADA DEL ARTÍCULO
		//cantidadTotalTicket.setText((String.format("%.2f", cantidad_total)+"€"));
		//---------------------------------------------------------

	}



	public void elimina_articulo_de_lista()
	{
		
		mensaje_aviso.setText("");

		int indice = 0;
		indice = lista.getSelectedIndex(); //RECOGEMOS EL INDICE DEL LA LÍNEA QUE TENEMOS SELECCIONADA EN EL LIST, YA QUE ESTÉ MÉTODO SI SE USA UNA VEZ, LUEGO YA NO DEVUELVE EL ÍNDICE QUE SE ENCUENTRA SELECCIONADO, POR LO QUE LO ALMACENAMOS PARA USARLO VARIAS VECES

		
		
		//-------	ELIMINA DEL ARRAYLIST EL ELEMENTO SELECCIONADO  -----------

		try
		{
			for (int i = 0; i < lista_de_articulos.size(); i++)
			{
				if(devuelve_articulo_seleccionado_de_la_lista().equals(lista_de_articulos.get(i).toString()))
				{
					System.out.println("Coincide");
					lista_de_articulos.remove(indice);
				}
			}
		}
		catch(NullPointerException e)
		{
			System.err.println("Error al eliminar el elemento del ArrayList.");
		}
		
		

		//-------	ELIMINA DE LA LISTA EL ELEMENTO SELECCIONADO  -----------
		try 
		{
			lista.remove(indice);

		} catch (ArrayIndexOutOfBoundsException e) {

			System.err.println("Error al eliminar el elemento seleccionado de la lista.");
			mensaje_aviso.setText("Selecciona el archivo que quieres eliminar");
		}

		
		
		
		System.out.println(indice);

		System.out.println("*****************");
		for (int i = 0; i < lista_de_articulos.size(); i++) 
		{
			System.out.println(lista_de_articulos.get(i));

		}
		System.out.println("*****************");
		
		
		
		/*

		/*

		//-------	ELIMINA DEL ARRAYLIST EL ELEMENTO SELECCIONADO  -----------

		for (int i = 0; i < lista_de_articulos.size(); i++) 
		{
			if((lista.getItem(indice)).equals(lista_de_articulos.get(i)))
			{
				lista_de_articulos.remove(i+1);
			}

		}

		System.out.println("*****************");
		for (int i = 0; i < lista_de_articulos.size(); i++) 
		{
			System.out.println(lista_de_articulos.get(i));

		}
		System.out.println("*****************");
		/*

		String texto_lista = lista.getSelectedItem().toString();
		String [] descompone_string;
		descompone_string = texto_lista.split("  --  ");	
		descompone_string[0];
		 */

		/*


		/*
		if(lista_de_articulos_en_jlist.size() == 0) 
		{
			System.out.println("No hay elementos");
		}
		else {
			for (int i = 0; i < lista_de_articulos_en_jlist.size(); i++) 
			{
				if(lista.getSelectedItem().equals(lista_de_articulos_en_jlist.get(i)))
				{
					lista_de_articulos_en_jlist.remove(i);
				}
			}
		}
		 */





		/*
		//---------------------------------------------------------	
		System.out.println(lista.getSelectedItem().toString());
		for (int i = 0; i < lista_de_articulos_en_jlist.size(); i++) 
		{
			System.out.println(lista_de_articulos_en_jlist.get(i));
		}
		for (int i = 0; i < lista_de_articulos_en_jlist.size(); i++) 
		{
			if(lista_de_articulos_en_jlist.get(i).equals(lista.getSelectedItem().toString()))
			{		
				lista.remove(lista.getSelectedItem());;
			}
		}
		actualiza_lista();
		//AÑADIMOS A LA LISTA LA DESCRIPCIÓN LA CANTIDAD Y EL PRECIO, TENIENDO EN CUENTA ESTE ÚLTIMO SEGÚN LA CANTIDAD		
		/*
			for (int i = 0; i < lista_de_articulos_en_jlist.size(); i++) 
			{
				if (lista_de_articulos_en_jlist.get(i).equals(lista.getSelectedItem().toString()))
				{
					lista_de_articulos.remove(i);
				}
			}
			String cantidad = combo_cantidad.getSelectedItem().toString();
			cantidad_int = Integer.valueOf(cantidad); //OBTENEMOS LA CANTIDAD DEL ARTÍCULO QUE ESTAMOS AÑADIENDO	
			lista_de_articulos_en_jlist.add(combo_articulo.getSelectedItem().toString() + "  --  " + combo_cantidad.getSelectedItem().toString() + "  --  " + (String.format("%.2f", (dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int))+"€"));
			actualiza_lista();
			//----------AUMENTA TOTAL DE TICKET------------------------
			cantidad_total += dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int ; //AUMENTA EL VALOR DEL TICKET CON EL PRECIO SELECCIONADO, TENIENDO EN CUENTA PRECIO Y CANTIDAD SELECIONADA DEL ARTÍCULO
			cantidadTotalTicket.setText((String.format("%.2f", cantidad_total)+"€"));
	}
	else 
	{
		//---------------------------------------------------------
		mensaje_aviso.setText("El artículo que desea eliminar no existe.");	
		actualiza_lista();
	}
		 */
	}
}