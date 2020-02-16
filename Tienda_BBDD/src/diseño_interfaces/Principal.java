package diseño_interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Principal.
 * @author David Borrego Asencio
 * @since 11/01/2020
 * @version 1.0
 */
public class Principal extends JFrame implements ActionListener
{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane = new JPanel() {
		
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			Dimension tamanio = getSize();
			ImageIcon imagenFondo = new ImageIcon(getClass().getResource("SHOP.png"));
			g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
			setOpaque(false);	
		}
	};
	
	/** The nuevo articulo. */
	private NuevoArticulo nuevo_articulo;
	
	/** The eliminar articulo. */
	private EliminarArticulo eliminar_articulo = new EliminarArticulo();
	
	/** The modificar articulo. */
	private ModificarArticulo modificar_articulo;
	
	/** The ver articulos. */
	private VerArticulos ver_articulos = new VerArticulos();
	
	/** The nuevo ticket. */
	private NuevoTicket nuevo_ticket = new NuevoTicket();
	
	/** The ver tickets. */
	private VerTickets ver_tickets = new VerTickets();

	
	/** The menu bar. */
	private JMenuBar menuBar = new JMenuBar();
	
	/** The mn new menu. */
	private JMenu mnNewMenu = new JMenu("Art\u00EDculos");
	
	/** The mntm new menu item. */
	private JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Art\u00EDculo");
	
	/** The mntm new menu item 1. */
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("Eliminar Art\u00EDculo");
	
	/** The mntm new menu item 2. */
	private JMenuItem mntmNewMenuItem_2 = new JMenuItem("Modificar Art\u00EDculo");
	
	/** The mntm new menu item 3. */
	private JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ver Art\u00EDculos");
	
	/** The mn tickets. */
	private JMenu mnTickets = new JMenu("Tickets");
	
	/** The mntm new menu item 4. */
	private JMenuItem mntmNewMenuItem_4 = new JMenuItem("Nuevo Ticket");
	
	/** The mntm new menu item 5. */
	private JMenuItem mntmNewMenuItem_5 = new JMenuItem("Ver Tickets");
	
	/** The mn informes. */
	private JMenu mnInformes = new JMenu("Informes");
	
	/** The mntm new menu item 6. */
	private JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe Artículos...");
	
	/** The mntm new menu item 7. */
	private JMenuItem mntmNewMenuItem_7 = new JMenuItem("Generar Informe Tickets...");

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/dise\u00F1o_interfaces/SHOP.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 471);

		this.setTitle("TIENDA");
		setJMenuBar(menuBar);
		menuBar.add(mnNewMenu);
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem_2);
		mnNewMenu.add(mntmNewMenuItem_3);
		menuBar.add(mnTickets);
		mnTickets.add(mntmNewMenuItem_4);
		mnTickets.add(mntmNewMenuItem_5);
		menuBar.add(mnInformes);
		mnInformes.add(mntmNewMenuItem_6);
		mnInformes.add(mntmNewMenuItem_7);


		mntmNewMenuItem.addActionListener(this);
		mntmNewMenuItem_1.addActionListener(this);
		mntmNewMenuItem_2.addActionListener(this);
		mntmNewMenuItem_3.addActionListener(this);
		mntmNewMenuItem_4.addActionListener(this);
		mntmNewMenuItem_5.addActionListener(this);
		mntmNewMenuItem_6.addActionListener(this);
		mntmNewMenuItem_7.addActionListener(this);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
	}


	/**
	 * Action performed.
	 *
	 * @param ae the ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{


		if(ae.getSource().equals(mntmNewMenuItem))
		{	
			nuevo_articulo = new NuevoArticulo();
			nuevo_articulo.setVisible(true);

		}else
		{
			if(ae.getSource().equals(mntmNewMenuItem_1))
			{		
				eliminar_articulo = new EliminarArticulo();
				eliminar_articulo.setVisible(true);
			}else
			{
				if(ae.getSource().equals(mntmNewMenuItem_2))
				{		
					modificar_articulo = new ModificarArticulo();
					modificar_articulo.setVisible(true);

				}else
				{
					if(ae.getSource().equals(mntmNewMenuItem_3))
					{			
						ver_articulos = new VerArticulos();
						ver_articulos.setVisible(true);
					}else
					{
						if(ae.getSource().equals(mntmNewMenuItem_4))
						{		
							nuevo_ticket = new NuevoTicket();
							nuevo_ticket.setVisible(true);

						}else
						{
							if(ae.getSource().equals(mntmNewMenuItem_5))
							{
								ver_tickets = new VerTickets();
								ver_tickets.setVisible(true);

							}else
							{
								if(ae.getSource().equals(mntmNewMenuItem_6))
								{			
									InformeArticulos informe_Articulos = new InformeArticulos();

								}else
								{
									if(ae.getSource().equals(mntmNewMenuItem_7))
									{
										InformeTickets informe_Tickets = new InformeTickets();
										informe_Tickets.setVisible(true);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
