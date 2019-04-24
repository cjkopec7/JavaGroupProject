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
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
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
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Player.Player;

public class Hub extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Player player;
	private JScrollPane pane;
	private JPanel panel, statsPanel, bottomPanel, westPanel, eastPanel, centerPanel;
	private JTextPane console, xp, xpTill, lvl;
	private JTextField exp, bag, equip;
	private JList invent, storage, database;

	public static void main(String[] args) {
		Hub h = new Hub("Coobs' Quest�", new Player("Coobs", "123"));

	}

	public Hub(String title, Player plyr) {
		super(title);
		player = plyr;
		this.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(700, 450));
		setJMenuBar(createMenuBar());
		setContentPane(createContentPane());
		statsPanel = createStatsPanel(player.getName(), (int) player.getLevel(), player.getExperience(),
				player.getExpTillLevel());
		bottomPanel = createBottomPanel();
		this.add(statsPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
//		this.add(westPanel, BorderLayout.WEST);
//		this.add(eastPanel, BorderLayout.EAST);
//		this.add(centerPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	public JPanel createBottomPanel() {
		JPanel bottomPanell = new JPanel(new GridLayout(0, 1));
		JPanel functions = new JPanel(new GridLayout(0, 3, 150, 0));
		JPanel showConsole = new JPanel(new BorderLayout());
		console = new JTextPane();
		formatJTextPane(console, "Console", new Font("Courier New", Font.PLAIN, 12));
		showConsole.add(console, BorderLayout.CENTER);
		exp = new JTextField("Xp", 1);
		bag = new JTextField("Id", 1);
		equip = new JTextField("Id", 1);
		JLabel xp = new JLabel("Gain Exp (Alt-G)");
		JLabel bg = new JLabel("Bag Item (Alt-B)");
		JLabel eq = new JLabel("Equip Item (Alt-E)");
		formatJLabel(xp, new Font("Arial", Font.PLAIN, 12));
		formatJLabel(bg, new Font("Arial", Font.PLAIN, 12));
		formatJLabel(eq, new Font("Arial", Font.PLAIN, 12));

		functions.add(xp);
		functions.add(bg);
		functions.add(eq);
		functions.add(exp);
		functions.add(bag);
		functions.add(equip);

		bottomPanell.add(functions);
		bottomPanell.add(showConsole);

		return bottomPanell;
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Action event detected (" + source.getText() + ", instance of " + GetClassName(source) + ")";
		System.out.println(s);
		if (e.getActionCommand().equals("Open Player...")) {
//			open();

		} else if (e.getActionCommand().equals("Console")) {
			printToConsole("Test");
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
					printToConsole(player.getName() + " gained " + xpi + " experience and leveled up to " + (int) player.getLevel());
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
			try {
				int xpi = Integer.parseInt(bg);
				player.gainExp(xpi);
				
			} catch (NumberFormatException n) {
				printToConsole("Invalid Input");
			}
			updateHub();
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

		menuItem = new JMenuItem("Open Player...");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);


		menuItem = new JMenuItem("Gain Exp");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Bag Item");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Equip Item");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Console Test");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menuBar;
	}

}
