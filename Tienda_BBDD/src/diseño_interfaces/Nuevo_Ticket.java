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
import javax.swing.SwingConstants;
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
	private ResultSet resultset_idTicket;
	long idTicket;
	private double cantidad_total;
	private Conecta_BBDD base_datos;
	private ArrayList<String> lista_de_articulos;
	private ArrayList<Integer> lista_de_cantidades_de_articulos;
	private JLabel mensaje_aviso;
	boolean llave = false;
	private int cantidad_int;
	private JLabel label_idTicket;
	/**
	 * Create the frame.
	 */
	public Nuevo_Ticket()
	{

		base_datos = new Conecta_BBDD();

		lista_de_cantidades_de_articulos = new ArrayList<Integer>();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Nuevo_Articulo.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setBounds(100, 100, 512, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(28, 38, 468, 2);
		contentPane.add(separator);

		lblNuevoArtculo = new JLabel("NUEVO TICKET");
		lblNuevoArtculo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblNuevoArtculo.setBounds(204, 11, 125, 24);
		contentPane.add(lblNuevoArtculo);

		lblFechaTicket = new JLabel("Fecha Ticket:");
		lblFechaTicket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaTicket.setBounds(28, 60, 88, 24);
		contentPane.add(lblFechaTicket);

		lblCantidad = new JLabel("Precio Total: ", SwingConstants.CENTER);
		lblCantidad.setForeground(Color.BLUE);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setBounds(42, 397, 125, 24);
		contentPane.add(lblCantidad);

		cantidadTotalTicket = new JLabel("0€", SwingConstants.LEFT);
		cantidadTotalTicket.setForeground(Color.BLUE);
		cantidadTotalTicket.setFont(new Font("Tahoma", Font.BOLD, 15));
		cantidadTotalTicket.setBounds(157, 397, 135, 24);
		contentPane.add(cantidadTotalTicket);

		lista = new List();
		lista.setBounds(53, 210, 414, 162);
		contentPane.add(lista);

		combo_articulo = new JComboBox<String> ();
		combo_articulo.setBounds(124, 112, 168, 20);
		contentPane.add(combo_articulo);

		btnAgregarArticulo = new JButton("A\u00F1adir");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{		
				agrega_articulo_a_la_lista();
			}
		});
		btnAgregarArticulo.setBounds(290, 181, 86, 23);
		contentPane.add(btnAgregarArticulo);

		JLabel label = new JLabel("/");
		label.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label.setBounds(176, 60, 13, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		label_1.setBounds(297, 60, 13, 24);
		contentPane.add(label_1);

		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setBounds(143, 44, 18, 14);
		contentPane.add(lblDia);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(228, 44, 28, 14);
		contentPane.add(lblMes);

		JLabel lblAnyo = new JLabel("A\u00F1o");
		lblAnyo.setBounds(328, 44, 28, 14);
		contentPane.add(lblAnyo);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mensaje_aviso.setText(""); //LIMPIA EL MENSAJE
				if(lista_de_articulos.size() > 0) //COMPROBAMOS QUE HAYA ALGÚN ARTÍCULO EN LA LISTA
				{
					//----------- AGREGAR EL TICKET A LA BBDD  ---------------
					try
					{
						String sentencia_agregar_ticket = "INSERT INTO tickets (fechaTicket, articulosTicket, totalTicket) VALUES ('"+ combo_anyo.getSelectedItem().toString() + "-"+ devuelve_mes(combo_mes.getSelectedItem().toString()) + "-" + combo_dia.getSelectedItem().toString() +"', "+ lista_de_articulos.size() + ", " + (float)cantidad_total + ");";
						if (base_datos.agregar_fecha_ticket(sentencia_agregar_ticket) == true)//AGREGA EL TICKET A LA BBDD SI LA FECHA ES CORRECTA
						{
							//----------- OBTIENE EL ID DE TODOS LOS OJETOS DE LA LISTA LA CANTIDAD Y LOS AGREGA AL TICKET EN LA BD ---------------
							for (int i = 0; i < lista_de_articulos.size(); i++)
							{
								System.out.println("SELECT idArticulo FROM articulos WHERE descripcionArticulo = '"+ lista_de_articulos.get(i) +"';"); //OBTIENE ID DEL ARTICULO
								ResultSet resultset_insertaArticulos = base_datos.obtener_objetos("SELECT idArticulo FROM articulos WHERE descripcionArticulo = '"+ lista_de_articulos.get(i) +"';");

								try
								{
									resultset_insertaArticulos.first();
									System.out.println("El id del articulo es: " + resultset_insertaArticulos.getInt(1) +" Y la cantidad de dicho artículo es: " + lista_de_cantidades_de_articulos.get(i) );

									//----------- AGREGA LOS ARTICULOS AL TICKET CORRESPONDIENTE  ---------------		
									String sentencia_agregar_articulos_al_ticket = "INSERT INTO contiene ( idArticuloFK, idTicketFK, cantidadArticulo) VALUES (" + resultset_insertaArticulos.getInt(1) +", " + idTicket +", " + lista_de_cantidades_de_articulos.get(i) + ");";							
									base_datos.agregar_objeto(sentencia_agregar_articulos_al_ticket);		
									base_datos.agregar_objeto("UPDATE articulos SET stockArticulo = stockArticulo - " + lista_de_cantidades_de_articulos.get(i) + " WHERE idArticulo = " + resultset_insertaArticulos.getInt(1) + ";"); //BAJA EL STOCK DEL ARTÍCULO

								} catch (SQLException e2)
								{
									mensaje_aviso.setText("Error al agregar los artículos al ticket en la BBDD.");
									e2.printStackTrace();
								}
								
							}
							dialogo1.setVisible(true);
						}
						else
						{
							mensaje_aviso.setText("La fecha elegida no es correcta.");
						}

					} catch (Exception e1)
					{
						mensaje_aviso.setText("Introduzca una fecha válida para el ticket.");
						e1.printStackTrace();
					}			
				}	
				else
				{
					mensaje_aviso.setText("Debe introducir al menos un artículo en el ticket.");
				}

			}
		});
		btnGuardar.setBounds(386, 400, 81, 23);
		contentPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Nuevo_Ticket.this.dispose();
			}
		});
		btnCancelar.setBounds(290, 400, 88, 23);
		contentPane.add(btnCancelar);

		JButton btnEliminarArticulo = new JButton("Eliminar");
		btnEliminarArticulo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				elimina_articulo_de_lista();
			}
		});
		btnEliminarArticulo.setBounds(386, 181, 81, 23);
		contentPane.add(btnEliminarArticulo);

		combo_cantidad = new JComboBox<String>();
		combo_cantidad.setBounds(126, 143, 60, 20);
		contentPane.add(combo_cantidad);

		JLabel lblArticulo = new JLabel("Art\u00EDculo:");
		lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArticulo.setBounds(63, 111, 69, 19);
		contentPane.add(lblArticulo);

		JLabel lblCantidad_1 = new JLabel("Cantidad:");
		lblCantidad_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad_1.setBounds(54, 142, 69, 20);
		contentPane.add(lblCantidad_1);

		combo_dia = new JComboBox<String>();
		combo_dia.setBounds(126, 62, 45, 22);
		contentPane.add(combo_dia);

		combo_mes = new JComboBox<String>();
		combo_mes.setBounds(190, 62, 102, 22);
		contentPane.add(combo_mes);

		combo_anyo = new JComboBox<String>();
		combo_anyo.setBounds(308, 62, 81, 22);
		contentPane.add(combo_anyo);

		mensaje_aviso = new JLabel("", SwingConstants.CENTER);
		mensaje_aviso.setForeground(Color.RED);
		mensaje_aviso.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		mensaje_aviso.setBounds(64, 372, 391, 24);
		contentPane.add(mensaje_aviso);

		label_idTicket = new JLabel("", SwingConstants.LEFT);
		label_idTicket.setForeground(Color.RED);
		label_idTicket.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 12));
		label_idTicket.setBounds(28, 11, 139, 24);
		contentPane.add(label_idTicket);

		//------------------Dialog----------------------
		dialogo1 = new JDialog(this, "", true);
		mensaje1 = new JLabel("Ticket creado con éxito.");
		dialogo1.addWindowListener(new WindowListener() 
		{

			public void windowActivated(WindowEvent e)
			{}
			public void windowClosed(WindowEvent e)
			{}
			public void windowClosing(WindowEvent e)
			{
				dialogo1.setVisible(false);
				base_datos.cierra_conexion();
				Nuevo_Ticket.this.dispose();
				
				
			}
			public void windowDeactivated(WindowEvent e)
			{}
			public void windowDeiconified(WindowEvent e)
			{}
			public void windowIconified(WindowEvent e)
			{}
			public void windowOpened(WindowEvent e)
			{}
		});

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
				base_datos.cierra_conexion();
				Nuevo_Ticket.this.dispose();

			}

		});

		//------------------------------------------------

		cantidad_total = 0.0;
		lista_de_articulos = new ArrayList<String>();


		idTicket = dame_idTicke();
		label_idTicket.setText("REF. TICKET: " + idTicket);

		JLabel lblListaCompra = new JLabel("--LISTA DE ART\u00CDCULOS--", SwingConstants.CENTER);
		lblListaCompra.setForeground(Color.GRAY);
		lblListaCompra.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 12));
		lblListaCompra.setBounds(124, 190, 139, 24);
		contentPane.add(lblListaCompra);

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
	{}

	public void windowClosing(WindowEvent e)
	{
		base_datos.cierra_conexion();
		this.dispose();	
	}

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
		resultset1 = base_datos.obtener_objetos("SELECT descripcionArticulo FROM articulos ORDER BY 1;");

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
		resultset2 = base_datos.obtener_objetos("SELECT precioArticulo FROM articulos WHERE descripcionArticulo = '" + articulo + "';");	
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

	public void agrega_articulo_a_la_lista()
	{

		mensaje_aviso.setText("");
		cantidad_int = Integer.valueOf(combo_cantidad.getSelectedItem().toString());

		if(lista_de_articulos.contains(combo_articulo.getSelectedItem().toString()))
		{
			mensaje_aviso.setText("El artículo que está agregando ya existe.");
		}
		else
		{
			//-------	AGREGA A LA LISTA EL ELEMENTO SELECCIONADO  -----------
			lista.add(combo_articulo.getSelectedItem().toString() + "  --  " + combo_cantidad.getSelectedItem().toString() + "  --  " + (String.format("%.2f", (dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int))+"€"));

			//-------	AGREGA AL ARRAYLIST EL ELEMENTO SELECCIONADO  -----------
			lista_de_articulos.add(combo_articulo.getSelectedItem().toString());

			//-------	AGREGA AL ARRAYLIST LA CANTIDAD DEL ELEMENTO  -----------
			lista_de_cantidades_de_articulos.add(cantidad_int);

			//----------AUMENTA TOTAL DE TICKET------------------------
			cantidad_total += dame_precio_articulo(combo_articulo.getSelectedItem().toString()) * cantidad_int ; //AUMENTA EL VALOR DEL TICKET CON EL PRECIO SELECCIONADO, TENIENDO EN CUENTA PRECIO Y CANTIDAD SELECIONADA DEL ARTÍCULO
			cantidadTotalTicket.setText((String.format("%.2f", cantidad_total)+"€")); 
			//---------------------------------------------------------
		}

	}

	public void elimina_articulo_de_lista()
	{

		try
		{
			mensaje_aviso.setText("");
			int indice = 0;
			indice = lista.getSelectedIndex(); //RECOGEMOS EL INDICE DEL LA LÍNEA QUE TENEMOS SELECCIONADA EN EL LIST, YA QUE ESTÉ MÉTODO SI SE USA UNA VEZ, LUEGO YA NO DEVUELVE EL ÍNDICE QUE SE ENCUENTRA SELECCIONADO, POR LO QUE LO ALMACENAMOS PARA USARLO VARIAS VECES


			//-------	DISMINUYE TICKET  -----------
			disminuye_ticket(indice);


			//-------	ELIMINA DEL ARRAYLIST EL ELEMENTO SELECCIONADO  -----------
			try
			{
				for (int i = 0; i < lista_de_articulos.size(); i++)
				{
					if(devuelve_articulo_seleccionado_de_la_lista().equals(lista_de_articulos.get(i).toString()))
					{
						lista_de_articulos.remove(indice);
						lista_de_cantidades_de_articulos.remove(indice); // ELIMINA DEL ARRAYLIST LA CANTIDAD DEL ARTÍCULO SELECCIONADO
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

		} catch (Exception e)
		{
			mensaje_aviso.setText("Elemento a eliminar no seleccionado.");
		}
	}


	public int devuelve_mes(String mes)
	{

		String mes_del_anyo = mes;
		switch (mes_del_anyo)
		{
		case "Enero":
			return 1;
		case "Febrero":
			return 2;
		case "Marzo":
			return 3;
		case "Abril":
			return 4;
		case "Mayo":
			return 5;
		case "Junio":
			return 6;
		case "Julio":
			return 7;
		case "Agosto":
			return 8;
		case "Septiembre":
			return 9;
		case "Octubre":
			return 10;
		case "Noviembre":
			return 11;
		case "Diciembre":
			return 12;
		default:
			return 0;
		}
	}

	public void disminuye_ticket(int linea_seleccionada)
	{
		int indice = linea_seleccionada;

		String array = lista.getItem(indice);
		String[] string_cantidad_con_simbolo = array.split("  --  ");
		String string_cantidad_sin_simbolo = string_cantidad_con_simbolo[2];
		string_cantidad_sin_simbolo = string_cantidad_sin_simbolo.substring(0, string_cantidad_sin_simbolo.length()-1);
		String cantidad_con_punto = string_cantidad_sin_simbolo.replaceAll(",",".");

		double cantidad_double = Double.parseDouble(cantidad_con_punto);

		cantidad_total -= cantidad_double;

		if(cantidad_total<0)
		{
			cantidad_total = 0;
		}

		cantidadTotalTicket.setText((String.format("%.2f", cantidad_total)+"€"));

	}

	public long dame_idTicke() {

		resultset_idTicket = base_datos.obtener_objetos("SELECT idTicket FROM tickets ORDER BY idTicket DESC;");
		long id_nuevo_ticket = 0;

		try
		{
			resultset_idTicket.first();
			id_nuevo_ticket = resultset_idTicket.getInt(1);


		} catch (SQLException e)
		{
			System.out.println("Error al obtener el id del ticket de la base de datos.");
		}


		return id_nuevo_ticket+1;

	}
}