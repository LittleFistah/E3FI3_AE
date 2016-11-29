package Jan.map_bsp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

	JLabel jlger, jleng;
	JTextField jtger, jteng;
	JButton jbsearch;
	HashMap<String, String> dictionary = new HashMap<>();

	public Gui() {
		init();
	}

	private void init() {
		readDictionary();
		super.setTitle("Wörterbuch");
		super.setBounds(10, 10, 600, 150);
		super.setLayout(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setVisible(true);

		jleng = new JLabel("Englisch:");
		jleng.setBounds(10, 10, 100, 20);
		super.add(jleng);

		jlger = new JLabel("Deutsch:");
		jlger.setBounds(10, 50, 100, 20);
		super.add(jlger);

		jteng = new JTextField();
		jteng.setBounds(110, 10, 350, 20);
		super.add(jteng);

		jtger = new JTextField();
		jtger.setBounds(110, 50, 350, 20);
		super.add(jtger);

		jbsearch = new JButton("Übersetzen");
		jbsearch.setHorizontalAlignment(SwingConstants.CENTER);
		jbsearch.setBounds(110, 80, 350, 20);
		jbsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtger.setText(crawler(jteng.getText()));
			}
		});
		super.add(jbsearch);
	}

	private void readDictionary() {
		String line;
		String[] values;
		try {
			Scanner sc = new Scanner(new File("C:/Arbeit/Schule/Java/Dictionary/DeutschEnglisch.txt"));
			while (sc.hasNext()) {
				line = sc.nextLine();
				if (line.startsWith("#")) {

				} else {
					values = line.split("\\t");
					if (dictionary.containsKey(values[0])) {
						values[1] = values[1] + ", " + dictionary.get(values[0]);
						dictionary.put(values[0], values[1]);
					}else{
						dictionary.put(values [0], values[1]);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String crawler(String key) {
		if (dictionary.containsKey(key)) {
			return dictionary.get(key);
		} else
			return "Keine Übersetzung gefunden";
	}

}
