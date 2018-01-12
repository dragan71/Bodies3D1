package Bodies3D1;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.text.*;


public class AboutDialog extends JDialog implements ActionListener{

	JPanel mainPanel = new JPanel();

	//getting font size
	//JLabel label1 = new JLabel("Just for getting font");
	//Font curFont16 = new Font(
	//label1.getFont().getFontName(),label1.getFont().getStyle(),16);


     public AboutDialog (JFrame parent) {
	super(parent, "About Creator", true);
	//setDefaultCloseOperation(AboutDialog.DISPOSE_ON_CLOSE);
	setSize(350, 210);
	setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/4, screenSize.height/4);

	
	Logo1Panel logo1 = new Logo1Panel();

        JLabel textLabel1 = new JLabel(
		"<html>Developed by<br>"
		+ "Dragan Mihajlovic<br>"
		+ "www.dragan-galaxy.com<br>"
		+ "Email: dragan037@yahoo.com</html>");

	JButton okButton = new JButton("OK");
        okButton.addActionListener(this);

	JPanel linePanel1 = new JPanel();
	JPanel linePanel11 = new JPanel();
	JPanel linePanel12 = new JPanel();
	JPanel linePanel13 = new JPanel();
	JPanel linePanel2 = new JPanel();


	//linePanel1.setBorder(BorderFactory.createTitledBorder(""));
	//linePanel11.setBorder(BorderFactory.createTitledBorder(""));
	//linePanel12.setBorder(BorderFactory.createTitledBorder(""));
	//linePanel13.setBorder(BorderFactory.createTitledBorder(""));
	//linePanel2.setBorder(BorderFactory.createTitledBorder(""));

	linePanel1.setPreferredSize(new Dimension(180, 160));
	linePanel11.setPreferredSize(new Dimension(180, 80));
	linePanel12.setPreferredSize(new Dimension(150, 10));
	linePanel13.setPreferredSize(new Dimension(150, 40));
	linePanel2.setPreferredSize(new Dimension(120, 150));


	linePanel11.add(textLabel1);
	linePanel13.add(okButton);
	linePanel1.add(linePanel11);
	linePanel1.add(linePanel12);
	linePanel1.add(linePanel13);
	linePanel2.add(logo1);
	mainPanel.add(linePanel1);
	mainPanel.add(linePanel2);
	add(mainPanel);

	//pack();
	//setVisible(true);

     }
	

    public void actionPerformed(ActionEvent evt) {
	  setVisible(false);
    }   

}