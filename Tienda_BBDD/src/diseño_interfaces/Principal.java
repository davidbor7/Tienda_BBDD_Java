package diseño_interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Principal extends JFrame implements ActionListener
{

	
	private static final long serialVersionUID = 1L;
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
	
	private Nuevo_Articulo nuevo_articulo;
	private Eliminar_Articulo eliminar_articulo = new Eliminar_Articulo();
	private Modificar_Articulo modificar_articulo;
	private Ver_Articulos ver_articulos = new Ver_Articulos();
	private Nuevo_Ticket nuevo_ticket = new Nuevo_Ticket();
	private Ver_Tickets ver_tickets = new Ver_Tickets();

	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnNewMenu = new JMenu("Art\u00EDculos");
	private JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Art\u00EDculo");
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("Eliminar Art\u00EDculo");
	private JMenuItem mntmNewMenuItem_2 = new JMenuItem("Modificar Art\u00EDculo");
	private JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ver Art\u00EDculos");
	private JMenu mnTickets = new JMenu("Tickets");
	private JMenuItem mntmNewMenuItem_4 = new JMenuItem("Nuevo Ticket");
	private JMenuItem mntmNewMenuItem_5 = new JMenuItem("Ver Tickets");
	private JMenu mnInformes = new JMenu("Informes");
	private JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe Artículos...");
	private JMenuItem mntmNewMenuItem_7 = new JMenuItem("Generar Informe Tickets...");

	/**
	 * Launch the application.
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


	@Override
	public void actionPerformed(ActionEvent ae)
	{


		if(ae.getSource().equals(mntmNewMenuItem))
		{	
			nuevo_articulo = new Nuevo_Articulo();
			nuevo_articulo.setVisible(true);

		}else
		{
			if(ae.getSource().equals(mntmNewMenuItem_1))
			{		
				eliminar_articulo = new Eliminar_Articulo();
				eliminar_articulo.setVisible(true);
			}else
			{
				if(ae.getSource().equals(mntmNewMenuItem_2))
				{		
					modificar_articulo = new Modificar_Articulo();
					modificar_articulo.setVisible(true);

				}else
				{
					if(ae.getSource().equals(mntmNewMenuItem_3))
					{			
						ver_articulos = new Ver_Articulos();
						ver_articulos.setVisible(true);
					}else
					{
						if(ae.getSource().equals(mntmNewMenuItem_4))
						{		
							nuevo_ticket = new Nuevo_Ticket();
							nuevo_ticket.setVisible(true);

						}else
						{
							if(ae.getSource().equals(mntmNewMenuItem_5))
							{
								ver_tickets = new Ver_Tickets();
								ver_tickets.setVisible(true);

							}else
							{
								if(ae.getSource().equals(mntmNewMenuItem_6))
								{		
									Informe_Articulos informe_Articulos = new Informe_Articulos();

								}else
								{
									if(ae.getSource().equals(mntmNewMenuItem_7))
									{
										Informe_Tickets informe_Tickets = new Informe_Tickets();
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
