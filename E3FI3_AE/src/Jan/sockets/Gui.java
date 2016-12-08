package Jan.sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton jbTime, jbQuote, jbEcho;
	private JTextPane jtOutput;
	private JScrollPane jsOutput;
	private String toEcho;
	private Socket connector;

	public Gui() {
		init();
	}

	private void init() {
		super.setSize(600, 320);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setVisible(true);
		super.setLayout(null);

		jtOutput = new JTextPane();
		jtOutput.setEditable(true);

		jsOutput = new JScrollPane(jtOutput);
		jsOutput.setBounds(10, 40, 570, 250);

		super.add(jsOutput);

		jbTime = new JButton("Daytime");
		jbTime.setBounds(10, 10, 186, 20);
		jbTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connect(13);

			}
		});

		super.add(jbTime);

		jbQuote = new JButton("Zitat");
		jbQuote.setBounds(206, 10, 186, 20);
		jbQuote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connect(17);

			}
		});

		super.add(jbQuote);

		jbEcho = new JButton("Echo");
		jbEcho.setBounds(402, 10, 186, 20);
		jbEcho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				toEcho = JOptionPane.showInputDialog("Bitte Nachricht eingeben");
				connect(7);
			}
		});

		super.add(jbEcho);
	}

	private void connect(int port) {
		try {
			connector = new Socket("localhost", port);
			connector.setSoTimeout(5000);

			String line;
			String output = "Server antwortet: \n\t";

			if (port == 7) {
				PrintWriter bw = new PrintWriter(new OutputStreamWriter(connector.getOutputStream()));

				bw.println(toEcho);
				bw.flush();

				BufferedReader br1 = new BufferedReader(new InputStreamReader(connector.getInputStream()));
				output += br1.readLine();
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(connector.getInputStream()));
				while ((line = br.readLine()) != null) {
					output += line + "\n\t";
				}
			}

			jtOutput.setText(output);
			connector.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
