package am;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;

public class EncprytionGUI {
	
	private String type = "Encrpyt";
	private Assembler assemble = new Assembler();
	
	public void showGUI(){
		
		JFrame frame = new JFrame("BedRock");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		JButton button = new JButton(type);
		
		JCheckBox box = new JCheckBox("Encrpyt");
		JCheckBox box2 = new JCheckBox("Decrpyt");
		box.setSelected(true);
		box.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					box2.setSelected(false);
					type = "Encrpyt";
					button.setText(type);
				}else if(e.getStateChange() == ItemEvent.DESELECTED){
					if(!box2.isSelected())
					box.setSelected(true);
				}
			}
		});
		
		box2.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					box.setSelected(false);
					type = "Decrpyt";
					button.setText(type);
					frame.pack();
				}else if(e.getStateChange() == ItemEvent.DESELECTED){
					if(!box.isSelected())
						box2.setSelected(true);
				}
			}
		});
		
		JPanel topPanel = new JPanel(new FlowLayout());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.ipadx = 300;
		cons.gridy = 0;
		cons.gridx = 0;
		
		topPanel.add(box);
		topPanel.add(box2);
		frame.add(topPanel, cons);
		
		JLabel label = new JLabel("Type Below To Encrpyt", JLabel.CENTER);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.ipadx = 300;
		cons.gridy = 1;
		cons.gridx = 0;
		cons.insets = new Insets(10, 0, 10, 0);
		frame.add(label, cons);
		
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(0, 10, 0, 10);
		cons.ipadx = 300;
		cons.ipady = 200;
		cons.gridx = 0;
		cons.gridy = 2;
		frame.add(scroll, cons);
		
		
		JPanel bottomPanel= new JPanel(new FlowLayout());
		
		JLabel keyLabel = new JLabel("Key: ");
		bottomPanel.add(keyLabel);
		
		JTextField keyText = new JTextField(" ", 10);
		
		bottomPanel.add(keyText);
		bottomPanel.add(button);

		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridy = 3;
		cons.gridx = 0;
		cons.insets = new Insets(10, 0, 0, 0);
		cons.ipady = 20;
		frame.add(bottomPanel, cons);
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(button.getText().equals("Encrpyt")){
					String text = textArea.getText();
					String key = keyText.getText().trim();
					text = assemble.Encrypt(key, text);
					textArea.setText(text);
					box.setSelected(false);
					box2.setSelected(true);
					button.setText("Decrpyt");
				}else if(button.getText().equals("Decrpyt")){
					String text = textArea.getText();
					String key = keyText.getText().trim();
					try{
						text = assemble.Decrypt(key, text);
						textArea.setText(text);
						box.setSelected(true);
						box2.setSelected(false);
						button.setText("Encrpyt");
					}catch(NumberFormatException error){
						keyText.setText("Wrong Key");
					}
				}
				
			}
			
		});
		
		JPanel bottomPan = new JPanel(new GridLayout(1, 2));
		JButton fileImport = new JButton("Import");
		bottomPan.add(fileImport);
		JButton fileExport = new JButton("Export");
		bottomPan.add(fileExport);
		
		cons.gridy = 4;
		cons.gridx = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		frame.add(bottomPan, cons);
		
		JFileChooser filec = new JFileChooser();
		fileImport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = filec.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = filec.getSelectedFile();
					String fileStr = "";
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line = null;
						while((line = reader.readLine()) != null){
							fileStr += line;
						}
						reader.close();
						textArea.setText(fileStr);
						
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
					}
				}
				
			}
		});
		
		fileExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String userHomeFolder = System.getProperty("user.home");
				File file = new File(userHomeFolder + "/Desktop/Encrypt.txt");
				if(file.exists()){
					file.delete();
				}
					try {
						file.createNewFile();
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(textArea.getText());
						writer.close();
					} catch (IOException e1) {
					}
				
			}
			
		});
		
		URL iconUrl = getClass().getResource("/image/bedrock.png");
		frame.setIconImage(new ImageIcon(iconUrl).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
