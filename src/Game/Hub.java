package Game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Item.Held;
import Item.Item;
import Item.Worn;
import Player.Player;

public class Hub extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Player player;
	private JScrollPane pane;
	private JPanel panel, statsPanel, bottomPanel, westPanel, eastPanel, centerPanel;
	private JTextPane console, xp, xpTill, lvl;
	private JTextField exp, bag, search;
	private JList invent, eqm, storage, database;
	private DefaultListModel inventory, equips;

	public static void main(String[] args) {
		Hub h = new Hub("Coobs' Quest™", new Player("Coobs", "123"));

	}

	public Hub(String title, Player plyr) {
		super(title);
		player = plyr;
		this.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(500, 450));
		setJMenuBar(createMenuBar());
		setContentPane(createContentPane());
		statsPanel = createStatsPanel(player.getName(), (int) player.getLevel(), player.getExperience(),
				player.getExpTillLevel());
		bottomPanel = createBottomPanel();
		westPanel = createWestPanel();
		eastPanel = createEastPanel();
		centerPanel = createCenterPanel();
		this.add(statsPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(westPanel, BorderLayout.WEST);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	public void updateLists() {
		inventory = new DefaultListModel();
		equips = new DefaultListModel();
		int index = 0;
		for (Item i : player.getBag().getContents()) {
			if (i != null) {
				inventory.addElement(index + ": " + i.getName() + " [" + GetClassName(i) + "]");
				index++;
			}
		}
		invent.setModel(inventory);
		if (player.getEquips().getHat() != null) {
			equips.addElement("Hat: " + player.getEquips().getHat().getName());

		} else {
			equips.addElement("Hat: Empty");
		}
		if (player.getEquips().getTop() != null) {
			equips.addElement("Top: " + player.getEquips().getTop().getName());

		} else {
			equips.addElement("Top: Empty");
		}
		if (player.getEquips().getBottom() != null) {
			equips.addElement("Bottom: " + player.getEquips().getBottom().getName());

		} else {
			equips.addElement("Bottom: Empty");
		}
		if (player.getEquips().getHold() != null) {
			equips.addElement("Hold: " + player.getEquips().getHold().getName());

		} else {
			equips.addElement("Hold: Empty");
		}
		invent.setModel(inventory);
		eqm.setModel(equips);
	}

	public JPanel createWestPanel() {
		int index = 0;
		JPanel westPanell = new JPanel(new BorderLayout());
		inventory = new DefaultListModel();
		JLabel in = new JLabel("Inventory");
		formatJLabel(in, new Font("Arial", Font.PLAIN, 12));

		for (Item i : player.getBag().getContents()) {
			if (i != null) {
				inventory.addElement(index + ": " + i.getName() + " [" + GetClassName(i) + "]");
				index++;
			}
		}
		invent = new JList(inventory);
		invent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		invent.setSelectedIndex(0);
		invent.setVisibleRowCount(10);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList<String> theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						if (player.getBag().getContents()[index] instanceof Worn
								| player.getBag().getContents()[index] instanceof Held) {
							player.equipItem(player.getBag().getContents()[index]);
							player.getBag().removeItem(index);
							updateHub();

						} else {
							printToConsole("You can't equip that.");
						}
					}
				}
			}
		};
		invent.addMouseListener(mouseListener);

		JScrollPane pane = new JScrollPane(invent);
		pane.setPreferredSize(new Dimension(200, 200));
		westPanell.add(in, BorderLayout.NORTH);
		westPanell.add(pane, BorderLayout.CENTER);

		return westPanell;

	}

	public JPanel createEastPanel() {
		JPanel eastPanell = new JPanel(new BorderLayout());
		equips = new DefaultListModel();
		JLabel eq = new JLabel("Equipment");
		formatJLabel(eq, new Font("Arial", Font.PLAIN, 12));
		if (player.getEquips().getHat() != null) {
			equips.addElement("Hat: " + player.getEquips().getHat().getName());

		} else {
			equips.addElement("Hat: Empty");
		}
		if (player.getEquips().getTop() != null) {
			equips.addElement("Top: " + player.getEquips().getTop().getName());

		} else {
			equips.addElement("Top: Empty");
		}
		if (player.getEquips().getBottom() != null) {
			equips.addElement("Bottom: " + player.getEquips().getBottom().getName());

		} else {
			equips.addElement("Bottom: Empty");
		}
		if (player.getEquips().getHold() != null) {
			equips.addElement("Hold: " + player.getEquips().getHold().getName());

		} else {
			equips.addElement("Hold: Empty");
		}

		eqm = new JList(equips);
		eqm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eqm.setSelectedIndex(0);
		eqm.setVisibleRowCount(4);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList<String> theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						if (index == 0) {
							if (player.getEquips().getHat() != null) {
								player.getBag().storeItem(player.getEquips().getHat());
								player.getEquips().setHat(null);
							}

							else {
								printToConsole("Slot is empty");
								player.getEquips().setHat(null);
							}

						} else if (index == 1) {
							if (player.getEquips().getTop() != null) {
								player.getBag().storeItem(player.getEquips().getTop());
								player.getEquips().setTop(null);
							}

							else {
								player.getEquips().setTop(null);
								printToConsole("Slot is empty");
							}

						} else if (index == 2) {
							if (player.getEquips().getBottom() != null) {
								player.getBag().storeItem(player.getEquips().getBottom());
								player.getEquips().setBottom(null);
							}

							else {
								player.getEquips().setHat(null);
								printToConsole("Slot is empty");
							}

						} else if (index == 3) {

							if (player.getEquips().getHold() != null) {
								player.getBag().storeItem(player.getEquips().getHold());
								player.getEquips().setHold(null);
							} else {
								player.getEquips().setHold(null);
								printToConsole("Slot is empty");
							}

						}
					}

				}
				updateHub();
			}

		};
		eqm.addMouseListener(mouseListener);

		JScrollPane pane = new JScrollPane(eqm);
		pane.setPreferredSize(new Dimension(200, 200));
		eastPanell.add(eq, BorderLayout.NORTH);
		eastPanell.add(pane, BorderLayout.CENTER);

		return eastPanell;
	}

	public JPanel createCenterPanel() {
		JPanel centerPanell = new JPanel(new GridLayout(0, 1));

		return centerPanell;
	}

	public JPanel createBottomPanel() {
		JPanel bottomPanell = new JPanel(new GridLayout(0, 1));
		JPanel functions = new JPanel(new GridLayout(0, 3, 75, 0));
		JPanel showConsole = new JPanel(new BorderLayout());
		console = new JTextPane();
		formatJTextPane(console, "Console", new Font("Courier New", Font.PLAIN, 12));
		showConsole.add(console, BorderLayout.SOUTH);
		exp = new JTextField("Xp", 1);
		bag = new JTextField("Console", 1);
		search = new JTextField("Id", 1);
		JLabel xp = new JLabel("Gain Exp (Alt-G)");
		JLabel bg = new JLabel("Console Test (Alt-C)");
		JLabel eq = new JLabel("Search Item (Alt-S)");
		formatJLabel(xp, new Font("Arial", Font.PLAIN, 12));
		formatJLabel(bg, new Font("Arial", Font.PLAIN, 12));
		formatJLabel(eq, new Font("Arial", Font.PLAIN, 12));

		functions.add(xp);
		functions.add(bg);
		functions.add(eq);
		functions.add(exp);
		functions.add(bag);
		functions.add(search);

		bottomPanell.add(functions);
		bottomPanell.add(showConsole);

		return bottomPanell;
	}

	public String lookupItem(int id) {
		System.out.println(player.getBag().getDatabase().toString());
		if (player.getBag().getDatabase().containsKey(id) == true) {
			return id + ": " + player.getBag().getDatabase().get(id).getName()+ " - " + player.getBag().getDatabase().get(id).getDescription() + " ["
					+ GetClassName(player.getBag().getDatabase().get(id)) + "]";
		} else {
			return "Invalid Item ID";
		}

	}

	public void formatJTextPane(JTextPane area, String set, Font font) {
		area.setText(set);
		area.setFont(font);
		area.setEditable(false);
		area.setOpaque(false);
		StyledDocument doc = area.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}

	public void formatJLabel(JLabel label, Font font) {
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(font);

	}

	public void printToConsole(String text) {
		console.setText(text);

	}

	public JPanel createStatsPanel(String name, int lv, double exp, double expLeft) {
		JPanel statsPanell = new JPanel(new GridLayout(0, 3));
		xp = new JTextPane();
		xpTill = new JTextPane();
		lvl = new JTextPane();
		formatJTextPane(xp, Integer.toString((int) exp), new Font("Arial", Font.PLAIN, 15));
		formatJTextPane(xpTill, Integer.toString((int) expLeft), new Font("Arial", Font.PLAIN, 15));
		formatJTextPane(lvl, "Level " + (int) lv, new Font("Arial", Font.PLAIN, 12));
		JLabel playerName = new JLabel(name);
		JLabel xpp = new JLabel("-Experience-");
		JLabel xpl = new JLabel("-Exp Till Level-");
		formatJLabel(playerName, new Font("Times New Roman", Font.BOLD, 18));
		formatJLabel(xpp, new Font("Arial", Font.BOLD, 10));
		formatJLabel(xpl, new Font("Arial", Font.BOLD, 10));
		statsPanell.add(xpp);
		statsPanell.add(playerName);
		statsPanell.add(xpl);
		statsPanell.add(xp);
		statsPanell.add(lvl);
		statsPanell.add(xpTill);
		statsPanell.setOpaque(true);

		return statsPanell;
	}

	public void updateHub() {
		formatJTextPane(xp, Integer.toString((int) player.getExperience()), new Font("Arial", Font.PLAIN, 15));
		formatJTextPane(xpTill, Integer.toString((int) player.getExpTillLevel()), new Font("Arial", Font.PLAIN, 15));
		formatJTextPane(lvl, "Level " + (int) player.getLevel(), new Font("Arial", Font.PLAIN, 12));
		updateLists();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Action event detected (" + source.getText() + ", instance of " + GetClassName(source) + ")";
		System.out.println(s);
		if (e.getActionCommand().equals("Open Player...")) {
//			open();

		} else if (e.getActionCommand().equals("Console Test")) {
			String con = bag.getText();
			printToConsole(con);
			updateHub();
		} else if (e.getActionCommand().equals("Gain Exp")) {
			int cl = (int) player.getLevel();
			String xp = exp.getText();
			try {
				int xpi = Integer.parseInt(xp);
				player.gainExp(xpi);
				printToConsole(player.getName() + " gained " + xpi + " experience");
				updateHub();
				if ((int) player.getLevel() > cl) {
					printToConsole(player.getName() + " gained " + xpi + " experience and leveled up to "
							+ (int) player.getLevel());
				}
			} catch (NumberFormatException n) {
				printToConsole("Invalid Input");

			}
		} else if (e.getActionCommand().equals("Bag Item")) {
			String bg = bag.getText();
			try {
				int xpi = Integer.parseInt(bg);
				player.gainExp(xpi);

			} catch (NumberFormatException n) {
				printToConsole("Invalid Input");
			}
			updateHub();
		} else if (e.getActionCommand().equals("Equip")) {
			String bg = bag.getText();

			updateHub();
		} else if (e.getActionCommand().equals("Reset")) {
			player = new Player("Coobs", "123");
			updateHub();
			console.setText("Player " + player.getName() + " reset");
		} else if (e.getActionCommand().equals("Search Item")) {
			String idd = search.getText();
			System.out.println(idd);
			try {
				Integer id = Integer.parseInt(idd);
				printToConsole(lookupItem(id));

			} catch (NumberFormatException n) {
				printToConsole("Invalid Input");
			}
		}

	}

	public Container createContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		panel = new JPanel();
		pane = new JScrollPane(panel);
		contentPane.add(pane, BorderLayout.CENTER);

		return contentPane;

	}

	protected String GetClassName(Object o) {
		String cn = o.getClass().getName();
		int dotIndex = cn.lastIndexOf(".");
		return cn.substring(dotIndex + 1);
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("Admin");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

//		menuItem = new JMenuItem("Open Player...");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
//		menuItem.addActionListener(this);
//		menu.add(menuItem);

		menuItem = new JMenuItem("Gain Exp");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);


		menuItem = new JMenuItem("Search Item");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Console Test");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Reset");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menuBar;
	}

}
