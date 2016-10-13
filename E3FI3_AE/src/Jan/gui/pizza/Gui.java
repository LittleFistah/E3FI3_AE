package Jan.gui.pizza;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Gui implements ItemListener{
	// Hauptframe
	private JFrame wind;
	//Pizzagrößen + Gruppe
	private CheckboxGroup pizzaSize;
	private Checkbox pizzaSmall, pizzaMedium, pizzaLarge;
	// extra Beläge
	private Checkbox extraSalami, extraSchinken, extraPeperoni, extraPilze, extraOliven, extraCheese;
	//Preis
	private Label lblPreis;
	private double preis;
	private int size;
	private ArrayList<Checkbox> cbAL;

	public Gui() {
		init();
	}

	private void init() {
		wind = new JFrame("J-Pizza");
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setResizable(false);
		addGuiComponents(wind.getContentPane());
		wind.pack();
		wind.setVisible(true);
	}

	private void addGuiComponents(Container pane) {
		//Default Größe
		pane.setSize(640, 320);
		// Layout
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();	
		// Gruppe für Größe
		pizzaSize = new CheckboxGroup();
		
		pizzaSmall = new Checkbox("Klein", false, pizzaSize);
		pizzaSmall.addItemListener(this);
		gbc.insets = new Insets(20, 0, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 1;
		pane.add(pizzaSmall, gbc);

		pizzaMedium = new Checkbox("Mittel", true, pizzaSize);
		pizzaMedium.addItemListener(this);
		gbc.insets = new Insets(20, 10, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 5;
		gbc.gridy = 1;
		pane.add(pizzaMedium, gbc);

		pizzaLarge = new Checkbox("Groß", false, pizzaSize);
		pizzaLarge.addItemListener(this);
		gbc.insets = new Insets(20, 10, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 7;
		gbc.gridy = 1;
		pane.add(pizzaLarge, gbc);
		
		// ExtraBeläge
		extraSalami = new Checkbox("Salami", false);
		extraSalami.addItemListener(this);
		gbc.insets = new Insets(50, 5, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 3;
		pane.add(extraSalami, gbc);
		
		extraSchinken = new Checkbox("Schinken", false);
		extraSchinken.addItemListener(this);
		gbc.insets = new Insets(50, 10, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 3;
		pane.add(extraSchinken, gbc);
		
		extraPeperoni = new Checkbox("Peperoni", false);
		extraPeperoni.addItemListener(this);
		gbc.insets = new Insets(50, 10, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 5;
		gbc.gridy = 3;
		pane.add(extraPeperoni, gbc);
		
		extraPilze = new Checkbox("Pilze", false);
		extraPilze.addItemListener(this);
		gbc.insets = new Insets(50, 10, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 7;
		gbc.gridy = 3;
		pane.add(extraPilze, gbc);
		
		extraOliven = new Checkbox("Oliven", false);
		extraOliven.addItemListener(this);
		gbc.insets = new Insets(50, 10, 0, 5);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 9;
		gbc.gridy = 3;
		pane.add(extraOliven, gbc);
		
		extraCheese = new Checkbox("Käse", true);
		extraCheese.addItemListener(this);
		gbc.insets = new Insets(20, 5, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 5;
		pane.add(extraCheese, gbc);
		
		//Preis
		lblPreis = new Label("Preis: "+ preis+"€");
		gbc.insets = new Insets(50, 5, 0, 0);
		gbc.weightx = .5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 6;
		pane.add(lblPreis, gbc);
	}
	
	private void checkPreis(){
		if (pizzaSize.getSelectedCheckbox() == pizzaSmall) {
			size = 0;
			preis = 4;
		}else if (pizzaSize.getSelectedCheckbox() == pizzaMedium){
			size = 1;
			preis = 4.5;
		}else if(pizzaSize.getSelectedCheckbox() == pizzaLarge){
			size = 2;
			preis = 5;
		}
		cbAL = new ArrayList<Checkbox>();
		cbAL.add(extraSalami);
		cbAL.add(extraSchinken);
		cbAL.add(extraPeperoni);
		cbAL.add(extraPilze);
		cbAL.add(extraOliven);
		cbAL.add(extraCheese);
		for(Checkbox x : cbAL){
			if(x.getState()){
				switch (size) {
				case 0:
					preis += 0.5;
					break;
				case 1:
					preis += 0.75;					
					break;
				case 2:
					preis += 1;				
					break;
				default:
					System.out.println("Wenn man das lesen kann dann ist das nicht gut");
					break;
				}
			}
		}
		lblPreis.setText("Preis: "+preis+"€");
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		checkPreis();
	}
}
