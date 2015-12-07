package Main;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicUserInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	String path = "";
	String path2 = "";
	ExcelHandler excelHandler = new ExcelHandler();
	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					GraphicUserInterface window = new GraphicUserInterface();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Image img = new ImageIcon(this.getClass().getResource("/owl2.jpg"))
				.getImage();
		frame = new JFrame();
		frame.setBackground(new Color(0, 153, 255));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setIconImage(img);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 13));
		tabbedPane.setBackground(Color.WHITE);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ewidencja", null, panel, null);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lblISBN_1 = new JLabel("Kod ISBN:");
		lblISBN_1.setForeground(new Color(0, 102, 255));
		sl_panel.putConstraint(SpringLayout.WEST, lblISBN_1, 10,
				SpringLayout.WEST, panel);
		lblISBN_1.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel.add(lblISBN_1);

		JLabel lblINFO = new JLabel("INFORMACJE:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblINFO, 0,
				SpringLayout.NORTH, lblISBN_1);
		lblINFO.setForeground(new Color(0, 102, 255));
		lblINFO.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel.add(lblINFO);

		JLabel lblWyszukaj = new JLabel("Wyszukaj:");
		sl_panel.putConstraint(SpringLayout.WEST, lblWyszukaj, 0, SpringLayout.WEST, lblISBN_1);
		lblWyszukaj.setForeground(new Color(0, 102, 255));
		lblWyszukaj
				.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel.add(lblWyszukaj);

		JButton btnZnajd = new JButton("Znajd\u017A\r\n");

		
		btnZnajd.setBackground(SystemColor.control);
		btnZnajd.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 15));
		//ImageIcon im = new ImageIcon(getClass().getResource("/Calculator.png"));
		//btnZnajd.setIcon(im);
		panel.add(btnZnajd);

		JButton btnSprawdz = new JButton("Sprawd\u017A");
		sl_panel.putConstraint(SpringLayout.NORTH, btnSprawdz, 103, SpringLayout.NORTH, panel);
		btnSprawdz.setForeground(Color.BLACK);
		btnSprawdz.setBackground(SystemColor.control);
		btnSprawdz
				.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));

		panel.add(btnSprawdz);

		JButton btnDodaj = new JButton("Dodaj");
		sl_panel.putConstraint(SpringLayout.WEST, btnZnajd, -32, SpringLayout.WEST, btnDodaj);
		sl_panel.putConstraint(SpringLayout.WEST, btnDodaj, 236, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnSprawdz, -15, SpringLayout.WEST, btnDodaj);
		btnDodaj.setBackground(new Color(255, 255, 255));
		btnDodaj.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));
		panel.add(btnDodaj);

		final JTextField textISBN_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textISBN_1, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnDodaj, 6, SpringLayout.SOUTH, textISBN_1);
		sl_panel.putConstraint(SpringLayout.NORTH, lblWyszukaj, 67, SpringLayout.SOUTH, textISBN_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblISBN_1, -6, SpringLayout.NORTH, textISBN_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, textISBN_1, -6, SpringLayout.NORTH, btnSprawdz);
		textISBN_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textISBN_1.setBorder(new LineBorder(new Color(0, 102, 204)));
		textISBN_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textISBN_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textISBN_1.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel.add(textISBN_1);

		JLabel lblSOWA = new JLabel("");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSOWA, 476, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblSOWA, 0, SpringLayout.WEST, lblISBN_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSOWA, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblSOWA, -46,
				SpringLayout.EAST, btnSprawdz);

		lblSOWA.setIcon(new ImageIcon(img));
		panel.add(lblSOWA);

		final JTextField textWyszukaj = new JTextField();
		sl_panel.putConstraint(SpringLayout.SOUTH, btnZnajd, 33, SpringLayout.SOUTH, textWyszukaj);
		sl_panel.putConstraint(SpringLayout.WEST, textWyszukaj, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnZnajd, 6, SpringLayout.SOUTH, textWyszukaj);
		sl_panel.putConstraint(SpringLayout.NORTH, textWyszukaj, 6, SpringLayout.SOUTH, lblWyszukaj);
		textWyszukaj.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textWyszukaj.setBorder(new LineBorder(new Color(0, 102, 204)));
		textWyszukaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textWyszukaj.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textWyszukaj.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel.add(textWyszukaj);

		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.EAST, textISBN_1, -322, SpringLayout.WEST, scrollPane_1);
		sl_panel.putConstraint(SpringLayout.EAST, btnDodaj, -322, SpringLayout.WEST, scrollPane_1);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane_1, 627, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textWyszukaj, -322, SpringLayout.WEST, scrollPane_1);
		sl_panel.putConstraint(SpringLayout.EAST, btnZnajd, -322, SpringLayout.WEST, scrollPane_1);
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane_1, 6, SpringLayout.SOUTH, lblINFO);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane_1, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane_1, -7, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblINFO, 0, SpringLayout.WEST, scrollPane_1);
		scrollPane_1.setBorder(new LineBorder(new Color(0, 102, 204)));
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				scrollPane_1.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel.add(scrollPane_1);

		final JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		sl_panel.putConstraint(SpringLayout.NORTH, textArea, 243,SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textArea, -346,SpringLayout.EAST, panel);
		textArea.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.WEST, textArea, 16,SpringLayout.EAST, lblSOWA);
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea, 0,SpringLayout.SOUTH, lblSOWA);

		btnSprawdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excelHandler.clearArrayListBook();
				textArea.setText(null);
				try {
					
					String str = excelHandler.arrayInString(textISBN_1.getText(), path);
					textArea.setText(str);
					textISBN_1.setText(null);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});

	

		btnZnajd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				excelHandler.clearArrayListBook();

				try {
					String str = excelHandler.arrayInString(textWyszukaj.getText(), path);
					textArea.setText(str);
					textWyszukaj.setText(null);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception ea) {
					ea.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});
		

		textISBN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excelHandler.clearArrayListBook();
				textArea.setText(null);
				try {
					
					String str = excelHandler.arrayInString(textISBN_1.getText(), path);
					textArea.setText(str);
					textISBN_1.setText(null);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});

		textWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				excelHandler.clearArrayListBook();

				try {
					String str = excelHandler.arrayInString(textWyszukaj.getText(), path);
					textArea.setText(str);
					textWyszukaj.setText(null);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception ea) {
					ea.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});

		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excelHandler.clearArrayListBook();
				textArea.setText(null);
				try {
					
					String str = excelHandler.arrayInString(textISBN_1.getText(), path);
					textArea.setText(str);
					textISBN_1.setText(null);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				try {
					excelHandler.addBook(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(51, 153, 255));
		menuBar.setBackground(new Color(0, 153, 255));
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Plik");
		menuBar.add(mnFile);
		Image img3 = new ImageIcon(this.getClass().getResource("/open.jpg"))
				.getImage();
		JMenu mnOtworz = new JMenu("Otw\u00F3rz...");
		mnFile.add(mnOtworz);
		mnOtworz.setIcon(new ImageIcon(img3));
		Image img2 = new ImageIcon(this.getClass().getResource("/excel.jpg"))
				.getImage();

		JMenuItem mntmArkuszEwidencyjny = new JMenuItem("Arkusz ewidencyjny");
		mntmArkuszEwidencyjny.setIcon(new ImageIcon(img2));
		mntmArkuszEwidencyjny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					path = f.getAbsolutePath();
				} catch (NullPointerException ex) {
					ex.printStackTrace();
				}

			}
		});

		mnOtworz.add(mntmArkuszEwidencyjny);
		JMenuItem mntmArkuszRejestru = new JMenuItem("Arkusz rejestru");
		mnOtworz.add(mntmArkuszRejestru);
		mntmArkuszRejestru.setIcon(new ImageIcon(img2));
		mntmArkuszRejestru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					path2 = f.getAbsolutePath();
				} catch (NullPointerException ex) {
					ex.printStackTrace();
				}

			}
		});

		JMenu mnNarzdzia = new JMenu("Narz\u0119dzia");
		menuBar.add(mnNarzdzia);

		
		// Okno wycennika 
		JMenuItem mntmWycennik = new JMenuItem("Wycennik");
		mntmWycennik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame calc = new JFrame();
				calc.setBounds(100, 100, 580, 361);
				// calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				calc.setVisible(true);
				calc.setResizable(false);
				JPanel panel_calc = new JPanel();
				panel_calc.setBackground(new Color(154, 205, 50));
				calc.getContentPane().add(panel_calc, BorderLayout.CENTER);
				SpringLayout sl_panel_calc = new SpringLayout();
				panel_calc.setLayout(sl_panel_calc);

				final JTextArea textArea_calc = new JTextArea();
				sl_panel_calc.putConstraint(SpringLayout.WEST, textArea_calc,
						60, SpringLayout.WEST, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.SOUTH, textArea_calc,
						-107, SpringLayout.SOUTH, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.EAST, textArea_calc,
						-57, SpringLayout.EAST, panel_calc);
				textArea_calc.setEditable(false);
				textArea_calc.setFont(new Font("Microsoft YaHei UI Light",
						Font.PLAIN, 16));
				panel_calc.add(textArea_calc);

				final JTextField textField_1_calc = new JTextField();

				sl_panel_calc.putConstraint(SpringLayout.WEST,
						textField_1_calc, 111, SpringLayout.WEST, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.SOUTH,
						textField_1_calc, -24, SpringLayout.SOUTH, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.EAST,
						textField_1_calc, -107, SpringLayout.EAST, panel_calc);
				textField_1_calc.setFont(new Font(
						"Microsoft JhengHei UI Light", Font.PLAIN, 16));
				panel_calc.add(textField_1_calc);
				textField_1_calc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textArea_calc.setText(null);
						Double price = Double.parseDouble(textField_1_calc
								.getText());
						textField_1_calc.setText(null);
						String price_percent = "75% - " + price * 75 / 100
								+ "z� (Stan Bdb- oraz 2 i 3 LO rozszerzenia)\n"
								+ "80% - " + price * 80 / 100
								+ "z� (specjalistyczne)\n" + "85% - " + price
								* 85 / 100 + "z� (nowe)\n" + "70% - " + price
								* 70 / 100 + "z� (pozosta�e)\n";

						textArea_calc.setText(price_percent);
					}
				});

				JLabel Wycena_calc = new JLabel("Wycena:");
				sl_panel_calc.putConstraint(SpringLayout.WEST, Wycena_calc, 60,
						SpringLayout.WEST, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.SOUTH, Wycena_calc,
						-283, SpringLayout.SOUTH, panel_calc);
				sl_panel_calc.putConstraint(SpringLayout.NORTH, textArea_calc,
						6, SpringLayout.SOUTH, Wycena_calc);
				Wycena_calc.setForeground(new Color(255, 255, 255));
				Wycena_calc.setBackground(new Color(255, 255, 255));
				Wycena_calc.setFont(new Font("Microsoft YaHei UI Light",
						Font.BOLD, 20));
				panel_calc.add(Wycena_calc);

				JLabel lblCena = new JLabel("Cena:");
				sl_panel_calc.putConstraint(SpringLayout.WEST, lblCena, 0,
						SpringLayout.WEST, textField_1_calc);
				sl_panel_calc.putConstraint(SpringLayout.SOUTH, lblCena, -6,
						SpringLayout.NORTH, textField_1_calc);
				lblCena.setForeground(Color.WHITE);
				lblCena.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD,
						20));
				lblCena.setBackground(Color.WHITE);
				panel_calc.add(lblCena);

			}

		});
		mnNarzdzia.add(mntmWycennik);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Uzupe\u0142nianie", null, panel_2, null);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		JLabel label = new JLabel("Kod ISBN:");
		label.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(0, 102, 255));
		label.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(label);

		final JTextField textISBN_3 = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.WEST, textISBN_3, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textISBN_3, -631, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, textISBN_3);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, label, -6, SpringLayout.NORTH, textISBN_3);
		textISBN_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textISBN_3.setBorder(new LineBorder(new Color(0, 102, 204)));
		textISBN_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textISBN_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textISBN_3.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textISBN_3);

		final JTextField textPrice = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.EAST, textPrice, -369, SpringLayout.EAST, panel_2);
		textPrice.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textPrice.setBorder(new LineBorder(new Color(0, 102, 204)));
		textPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textPrice.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textPrice.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textPrice);

		JLabel label_1 = new JLabel("Cena:");
		sl_panel_2.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, textPrice);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, label_1, -6, SpringLayout.NORTH, textPrice);
		label_1.setForeground(new Color(0, 102, 255));
		label_1.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(label_1);

		final JTextField textTitle = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.WEST, textTitle, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textTitle, -765, SpringLayout.EAST, panel_2);
		textTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textTitle.setBorder(new LineBorder(new Color(0, 102, 204)));
		textTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textTitle.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textTitle);

		JLabel lblTytu = new JLabel("Tytu\u0142:");
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblTytu, -401, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, textTitle, 6, SpringLayout.SOUTH, lblTytu);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblTytu, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textISBN_3, -27, SpringLayout.NORTH, lblTytu);
		lblTytu.setForeground(new Color(0, 102, 255));
		lblTytu.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblTytu);

		JLabel lblAutorzy = new JLabel("Autor(zy):");
		sl_panel_2.putConstraint(SpringLayout.WEST, lblAutorzy, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblAutorzy, -319, SpringLayout.SOUTH, panel_2);
		lblAutorzy.setForeground(new Color(0, 102, 255));
		lblAutorzy.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblAutorzy);

		final JTextField textAuthors = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textAuthors, 6, SpringLayout.SOUTH, lblAutorzy);
		sl_panel_2.putConstraint(SpringLayout.WEST, textAuthors, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textAuthors, -765, SpringLayout.EAST, panel_2);
		textAuthors.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textAuthors.setBorder(new LineBorder(new Color(0, 102, 204)));
		textAuthors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textAuthors.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textAuthors.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textAuthors);

		JLabel lblIlo = new JLabel("Ilo\u015B\u0107:");
		sl_panel_2.putConstraint(SpringLayout.WEST, lblIlo, 44, SpringLayout.WEST, panel_2);
		lblIlo.setForeground(new Color(0, 102, 255));
		lblIlo.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblIlo);

		final JTextField textAmount = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textPrice, 0, SpringLayout.NORTH, textAmount);
		sl_panel_2.putConstraint(SpringLayout.NORTH, textAmount, 415, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, textAmount, 44, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textAmount, -990, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblIlo, -6, SpringLayout.NORTH, textAmount);
		textAmount.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textAmount.setBorder(new LineBorder(new Color(0, 102, 204)));
		textAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textAmount.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textAmount.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textAmount);

		JLabel lblWydawnictwo = new JLabel("Wydawnictwo:");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblWydawnictwo, 0, SpringLayout.NORTH, lblAutorzy);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblWydawnictwo, 0, SpringLayout.WEST, textPrice);
		lblWydawnictwo.setForeground(new Color(0, 102, 255));
		lblWydawnictwo.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblWydawnictwo);

		final JTextField textPublisher = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textPublisher, 0, SpringLayout.NORTH, textAuthors);
		sl_panel_2.putConstraint(SpringLayout.WEST, textPublisher, 0, SpringLayout.WEST, textPrice);
		sl_panel_2.putConstraint(SpringLayout.EAST, textPublisher, -141,
				SpringLayout.EAST, panel_2);
		textPublisher.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textPublisher.setBorder(new LineBorder(new Color(0, 102, 204)));
		textPublisher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textPublisher.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textPublisher.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textPublisher);

		final JTextField textLevel = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textLevel, 0, SpringLayout.NORTH, textISBN_3);
		sl_panel_2.putConstraint(SpringLayout.WEST, textLevel, 0, SpringLayout.WEST, textPrice);
		sl_panel_2.putConstraint(SpringLayout.EAST, textLevel, -43, SpringLayout.EAST, panel_2);
		textLevel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textLevel.setBorder(new LineBorder(new Color(0, 102, 204)));
		textLevel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textLevel.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textLevel.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textLevel);

		JLabel lblPoziom = new JLabel("Poziom zaawansowania:");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblPoziom, 0, SpringLayout.NORTH, label);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblPoziom, 0, SpringLayout.WEST, textPrice);
		lblPoziom.setForeground(new Color(0, 102, 255));
		lblPoziom.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblPoziom);

		final JTextField textSchool = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textSchool, 0, SpringLayout.NORTH, textTitle);
		sl_panel_2.putConstraint(SpringLayout.WEST, textSchool, 0, SpringLayout.WEST, textPrice);
		sl_panel_2.putConstraint(SpringLayout.EAST, textSchool, -97,
				SpringLayout.EAST, panel_2);
		textSchool.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textSchool.setBorder(new LineBorder(new Color(0, 102, 204)));
		textSchool.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textSchool.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				textSchool.setBorder(new LineBorder(new Color(0, 102, 204)));
			}
		});
		panel_2.add(textSchool);

		JLabel lblSzkoa = new JLabel("Szko\u0142a:");
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblSzkoa, 0, SpringLayout.NORTH, lblTytu);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblSzkoa, 0, SpringLayout.WEST, textPrice);
		lblSzkoa.setForeground(new Color(0, 102, 255));
		lblSzkoa.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
		panel_2.add(lblSzkoa);

	
		final JLabel lblComm = new JLabel("");
		lblComm.setForeground(new Color(0, 102, 255));
		sl_panel_2.putConstraint(SpringLayout.WEST, textPrice, 378, SpringLayout.EAST, lblComm);
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblComm, 54, SpringLayout.SOUTH, textAuthors);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblComm, 15, SpringLayout.EAST, textAmount);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, lblComm, -197, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblComm, 271, SpringLayout.WEST, panel_2);
		lblComm.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 15));
		panel_2.add(lblComm);

		JButton btnUzupelnij = new JButton("Wprowad\u017A");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnUzupelnij, 829, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnUzupelnij, -10,
				SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnUzupelnij, -43, SpringLayout.EAST, panel_2);
		btnUzupelnij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				{

					try {
						String message = excelHandler.fillInfo(textTitle.getText(),
								textLevel.getText(),
								textAuthors.getText(), textPublisher.getText(),
								textAmount.getText(), textPrice.getText(),
								textISBN_3.getText(), textSchool.getText(),
								path);
						
						textTitle.setText(null);
						textLevel.setText(null);
						textAuthors.setText(null);
						textPublisher.setText(null);
						textAmount.setText(null);
						textPrice.setText(null);
						textISBN_3.setText(null);
						textSchool.setText(null);
						lblComm.setText(null);
						JOptionPane.showMessageDialog(null, message);
					} catch (FileNotFoundException e1) {
						JOptionPane
								.showMessageDialog(null,
										"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
					} catch (IllegalFormatException e2) {
						JOptionPane
								.showMessageDialog(null,
										"Brakuje danych lub s� wprowadzone niepoprawnie!");
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (NumberFormatException e3) {
						JOptionPane
								.showMessageDialog(null,
										"Brakuje danych lub s� wprowadzone niepoprawnie!");
					}

				}

			}
		});
		btnUzupelnij.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN,
				14));
		btnUzupelnij.setBackground(SystemColor.control);
		panel_2.add(btnUzupelnij);

		JButton btnSprawdz2 = new JButton("Sprawd\u017A ISBN");
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnSprawdz2, -6, SpringLayout.NORTH, btnUzupelnij);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnSprawdz2, -43, SpringLayout.EAST, panel_2);
		btnSprawdz2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excelHandler.clearArrayListBook();

				try {
					excelHandler.searchBook(textISBN_3.getText(), path);
					if (!excelHandler.getArrayListBook().isEmpty()) {
						
						textSchool.setText(excelHandler.getArrayListBook().get(0)
								.getSchool());
						textPrice.setText(String.valueOf(excelHandler
								.getArrayListBook().get(0).getPrice()));
						textAuthors.setText(excelHandler.getArrayListBook().get(0)
								.getAuthors());
						textAmount.setText(null);
						textPublisher.setText(excelHandler.getArrayListBook().get(0)
								.getPublisher());
						textLevel.setText(excelHandler.getArrayListBook().get(0)
								.getLevel());
						textTitle.setText(excelHandler.getArrayListBook().get(0)
								.getTitle());
						lblComm.setText("Ile chcesz doda� ?");
					} else
						JOptionPane.showMessageDialog(null,
								"Brak danych o zadanym kryterium");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane
							.showMessageDialog(null,
									"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				excelHandler.clearArrayListBook();
			}
		});
		btnSprawdz2
				.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));
		btnSprawdz2.setBackground(SystemColor.control);
		panel_2.add(btnSprawdz2);
		
		JLabel label_2 = new JLabel("");
		sl_panel_2.putConstraint(SpringLayout.NORTH, label_2, -167, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, label_2, 10, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, label_2, -10, SpringLayout.SOUTH, panel_2);
		label_2.setIcon(new ImageIcon(img));
		panel_2.add(label_2);
		
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(255, 255, 255));
				tabbedPane.addTab("Sprzeda\u017C", null, panel_1, null);
				SpringLayout sl_panel_1 = new SpringLayout();
				panel_1.setLayout(sl_panel_1);
				
						final JTextField textISBN_2 = new JTextField();
						sl_panel_1.putConstraint(SpringLayout.WEST, textISBN_2, 10, SpringLayout.WEST, panel_1);
						
								textISBN_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
								textISBN_2.setBorder(new LineBorder(new Color(0, 102, 204)));
								textISBN_2.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent arg0) {
										textISBN_2.setBorder(new LineBorder(new Color(0, 0, 0)));
									}
									@Override
									public void mouseExited(MouseEvent arg0) {
										textISBN_2.setBorder(new LineBorder(new Color(0, 102, 204)));
									}
								});
								panel_1.add(textISBN_2);
								
										JLabel lblISBN_2 = new JLabel("Kod ISBN:");
										sl_panel_1.putConstraint(SpringLayout.WEST, lblISBN_2, 10, SpringLayout.WEST, panel_1);
										sl_panel_1.putConstraint(SpringLayout.SOUTH, lblISBN_2, -6, SpringLayout.NORTH, textISBN_2);
										lblISBN_2.setForeground(new Color(0, 102, 255));
										lblISBN_2.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
										panel_1.add(lblISBN_2);
														
																JLabel lblSOWA_2 = new JLabel("");
																sl_panel_1.putConstraint(SpringLayout.NORTH, lblSOWA_2, -167,
																		SpringLayout.SOUTH, panel_1);
																sl_panel_1.putConstraint(SpringLayout.WEST, lblSOWA_2, 10, SpringLayout.WEST, panel_1);
																sl_panel_1.putConstraint(SpringLayout.SOUTH, lblSOWA_2, -10,
																		SpringLayout.SOUTH, panel_1);
																lblSOWA_2.setIcon(new ImageIcon(img));
																panel_1.add(lblSOWA_2);
																
																		JButton btnOk = new JButton("OK");
																		sl_panel_1.putConstraint(SpringLayout.NORTH, btnOk, 160, SpringLayout.NORTH, panel_1);
																		sl_panel_1.putConstraint(SpringLayout.SOUTH, textISBN_2, -8, SpringLayout.NORTH, btnOk);
																		sl_panel_1.putConstraint(SpringLayout.WEST, btnOk, 358, SpringLayout.WEST, panel_1);
																		sl_panel_1.putConstraint(SpringLayout.EAST, btnOk, 0, SpringLayout.EAST, textISBN_2);
																		btnOk.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));
																		btnOk.setBackground(SystemColor.control);
																		panel_1.add(btnOk);
																		
																				final JButton btnPOTWIERDZ = new JButton("POTWIERD\u0179");
																				sl_panel_1.putConstraint(SpringLayout.NORTH, btnPOTWIERDZ, 592, SpringLayout.NORTH, panel_1);
																				sl_panel_1.putConstraint(SpringLayout.WEST, btnPOTWIERDZ, -127,
																						SpringLayout.EAST, panel_1);
																				sl_panel_1.putConstraint(SpringLayout.SOUTH, btnPOTWIERDZ, -20, SpringLayout.SOUTH, panel_1);
																				
																						btnPOTWIERDZ.setEnabled(false);
																						sl_panel_1.putConstraint(SpringLayout.EAST, btnPOTWIERDZ, -10,
																								SpringLayout.EAST, panel_1);
																						btnPOTWIERDZ.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN,
																								14));
																						btnPOTWIERDZ.setBackground(SystemColor.control);
																						panel_1.add(btnPOTWIERDZ);
																						
																								JLabel lblParagon = new JLabel("Informacje:");
																								sl_panel_1.putConstraint(SpringLayout.NORTH, lblParagon, 0, SpringLayout.NORTH, lblISBN_2);
																								lblParagon.setForeground(new Color(0, 102, 255));
																								lblParagon.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 20));
																								panel_1.add(lblParagon);
																								
																										JButton btnSUMA = new JButton("Podsumuj");
																										sl_panel_1.putConstraint(SpringLayout.SOUTH, btnSUMA, -6, SpringLayout.NORTH, btnPOTWIERDZ);
																										sl_panel_1.putConstraint(SpringLayout.EAST, btnSUMA, 0, SpringLayout.EAST, btnPOTWIERDZ);
																										btnSUMA.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));
																										btnSUMA.setBackground(SystemColor.control);
																										panel_1.add(btnSUMA);
																										
																												JButton btnAnuluj = new JButton("Anuluj");
																												sl_panel_1.putConstraint(SpringLayout.NORTH, btnAnuluj, 0, SpringLayout.NORTH, btnSUMA);
																												sl_panel_1.putConstraint(SpringLayout.EAST, btnAnuluj, -6, SpringLayout.WEST, btnSUMA);
																												btnAnuluj.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 14));
																												btnAnuluj.setBackground(SystemColor.control);
																												panel_1.add(btnAnuluj);
																												
																														JScrollPane scrollPane = new JScrollPane();
																														sl_panel_1.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblParagon);
																														sl_panel_1.putConstraint(SpringLayout.SOUTH, scrollPane, -19, SpringLayout.NORTH, btnSUMA);
																														sl_panel_1.putConstraint(SpringLayout.EAST, lblSOWA_2, -373, SpringLayout.WEST, scrollPane);
																														sl_panel_1.putConstraint(SpringLayout.WEST, lblParagon, 0, SpringLayout.WEST, scrollPane);
																														sl_panel_1.putConstraint(SpringLayout.EAST, textISBN_2, -103, SpringLayout.WEST, scrollPane);
																														sl_panel_1.putConstraint(SpringLayout.WEST, scrollPane, 551, SpringLayout.WEST, panel_1);
																														sl_panel_1.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, panel_1);
																														scrollPane.setBorder(new LineBorder(new Color(0, 102, 204)));
																														scrollPane.addMouseListener(new MouseAdapter() {
																															@Override
																															public void mouseEntered(MouseEvent arg0) {
																																scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
																															}
																															@Override
																															public void mouseExited(MouseEvent arg0) {
																																scrollPane.setBorder(new LineBorder(new Color(0, 102, 204)));
																															}
																														});
																														panel_1.add(scrollPane);
																														
																																final JTextArea textArea_1 = new JTextArea();
																																scrollPane.setViewportView(textArea_1);
																																sl_panel_1.putConstraint(SpringLayout.NORTH, textArea_1, 189,
																																		SpringLayout.NORTH, textISBN_2);
																																sl_panel_1.putConstraint(SpringLayout.WEST, textArea_1, 79,
																																		SpringLayout.EAST, lblSOWA_2);
																																sl_panel_1.putConstraint(SpringLayout.SOUTH, textArea_1, -37,
																																		SpringLayout.SOUTH, panel_1);
																																textArea_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
																																textArea_1.setEditable(false);
																																textArea_1.setBackground(Color.WHITE);
																																sl_panel_1.putConstraint(SpringLayout.EAST, textArea_1, -340,
																																		SpringLayout.EAST, btnPOTWIERDZ);
																																
																																		btnOk.addActionListener(new ActionListener() {
																																			public void actionPerformed(ActionEvent arg0) {
																																
																																				try {
																																					String ISBN = textISBN_2.getText();
																																					excelHandler.searchBookOnlyByISBN(ISBN, path);
																																					if (excelHandler.getChanges()) {
																																						textArea_1.append(excelHandler.printReceipt(ISBN, path));
																																					} else JOptionPane.showMessageDialog(null,
																																										"Brak takiego ISBN w bazie lub �le podany.\nDodaj najpierw ksi��k� do arkusza ewidencyjnego!");
																																					textISBN_2.setText(null);
																																				
																																				} catch (FileNotFoundException ex) {
																																
																																					JOptionPane
																																							.showMessageDialog(null,
																																									"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
																																					ex.printStackTrace();
																																
																																				} catch (NumberFormatException e) {
																																					e.printStackTrace();
																																					textISBN_2.setText(null);
																																					JOptionPane
																																							.showMessageDialog(null,
																																									"B��d!\n\nSprawd� czy wszystkie dane zosta�y podane poprawnie !");
																																				} catch (Exception e) {
																																					e.printStackTrace();
																																				}
																																
																																			}
																																		});
																																		
																																				btnSUMA.addActionListener(new ActionListener() {
																																					public void actionPerformed(ActionEvent arg0) {
																																						
																																						if (!excelHandler.getArrayListBook().isEmpty())
																																							btnPOTWIERDZ.setEnabled(true);
																																					}
																																		
																																				});
																																				
																																						btnAnuluj.addActionListener(new ActionListener() {
																																							public void actionPerformed(ActionEvent arg0) {
																																				
																																								textArea_1.setText(null);
																																								btnPOTWIERDZ.setEnabled(false);
																																								excelHandler.clearArrayListBook();
																																								
																																								JOptionPane.showMessageDialog(null, "Anulowano.");
																																				
																																							}
																																						});
																																						btnPOTWIERDZ.addActionListener(new ActionListener() {
																																							public void actionPerformed(ActionEvent arg0) {
																																								try {
																																									String message = excelHandler.confirmSell(path, path2);
																																									textArea_1.setText(null);
																																									btnPOTWIERDZ.setEnabled(false);
																																									JOptionPane.showMessageDialog(null,message);

																																								} catch (FileNotFoundException ex) {

																																									JOptionPane.showMessageDialog(null,"Brak pliku �r�d�owego lub plik jest otwarty w innym programie!");
																																									ex.printStackTrace();

																																								} catch (IndexOutOfBoundsException a) {
																																									JOptionPane.showMessageDialog(null,"Do pola kodu ISBN wprowadzano b��dne dane np. tytu�y ksi��ek!");
																																									textArea_1.setText(null);
																																									excelHandler.clearArrayListBook();
																																									//prices.clear();
																																									btnPOTWIERDZ.setEnabled(false);
																																									a.printStackTrace();
																																								} catch (IOException e) {

																																									e.printStackTrace();
																																								}

																																							}
																																						});

		frame.setBounds(100, 100, 1100, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}