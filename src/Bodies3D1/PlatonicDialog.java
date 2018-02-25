package Bodies3D1;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;


public class PlatonicDialog extends JDialog implements ActionListener{
	//JLabel labelSlika;
	JButton okButton = new JButton("OK");


     public PlatonicDialog (JFrame parent) {
	super(parent, "Info: Platonic Solids", true);
	//setDefaultCloseOperation(PlatonicDialog.DISPOSE_ON_CLOSE);
	setSize(430, 520);
	//setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/4, screenSize.height/4);


	//ImageIcon imgIconP = new ImageIcon(
	//getClass().getResource("/Bodies3D1/images/platonic1.gif"));

	createGUI();

	//labelSlika = new JLabel();
	//labelSlika.setIcon(imgIconP);

	
        okButton.addActionListener(this);


	JPanel platonicPanel = new JPanel();

	//platonicPanel.setPreferredSize(new Dimension(100, 50));
	//platonicPanel.setBorder(BorderFactory.createTitledBorder("aaa"));

 
	//add(platonicPanel);


	//pack();
	//setVisible(true);

     }


     public void createGUI(){


    	 /*
    	  ImageIcon imgIcon2 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/cube48a.gif"));

	ImageIcon imgIcon3 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/tetrahedron48a.gif"));

	ImageIcon imgIcon4 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/octahedron48a.gif"));

	ImageIcon imgIcon5 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/dodecahedron48a.gif"));

	ImageIcon imgIcon6 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/icosahedron48a.gif"));

    	  */
	


    		ImageIcon imgIcon2 = new ImageIcon(
    		getClass().getResource("/images/cube48a.gif"));

    		ImageIcon imgIcon3 = new ImageIcon(
    		getClass().getResource("/images/tetrahedron48a.gif"));

    		ImageIcon imgIcon4 = new ImageIcon(
    		getClass().getResource("/images/octahedron48a.gif"));

    		ImageIcon imgIcon5 = new ImageIcon(
    		getClass().getResource("/images/dodecahedron48a.gif"));

    		ImageIcon imgIcon6 = new ImageIcon(
    		getClass().getResource("/images/icosahedron48a.gif"));

    		
	JLabel iconLabel21 = new JLabel(imgIcon2);
	JLabel iconLabel31 = new JLabel(imgIcon3);
	JLabel iconLabel41 = new JLabel(imgIcon4);
	JLabel iconLabel51 = new JLabel(imgIcon5);
	JLabel iconLabel61 = new JLabel(imgIcon6);

	JPanel platonicPanel = new JPanel();
	JPanel line1 = new JPanel();
	JPanel line2 = new JPanel();
	JPanel line3 = new JPanel();
	JPanel line4 = new JPanel();
	JPanel line5 = new JPanel();
	JPanel line6 = new JPanel();
	JPanel line7 = new JPanel();
	JPanel line8 = new JPanel();

	JPanel line21 = new JPanel();
	JPanel line31 = new JPanel();
	JPanel line41 = new JPanel();
	JPanel line51 = new JPanel();
	JPanel line61 = new JPanel();

	JPanel line22 = new JPanel();
	JPanel line32 = new JPanel();
	JPanel line42 = new JPanel();
	JPanel line52 = new JPanel();
	JPanel line62 = new JPanel();

        JLabel PlatonicLabel = new JLabel(
		"<html>PLATONIC SOLID is a convex polyhedron that is regular, in the sense <br>"
		+ "of a regular polygon. Specifically, the faces of a Platonic solid are <br>"
		+ "congruent regular polygons, with the same number of faces meeting <br>"
		+ "at each vertex; thus, all its edges are congruent, as are its vertices <br>"
		+ "and angles. There are precisely five Platonic solids (shown below):</html>");


        JLabel cubeLabel = new JLabel(
		"<html>CUBE<br>"
		+ "is a three-dimensional solid object bounded by<br>"
		+ "six square faces with three meeting at each vertex.</html>");

        JLabel tetrahedronLabel = new JLabel(
		"<html>TETRAHEDRON<br>"
		+ "is composed of four triangular faces,<br>"
		+ "three of which meet at each vertex.</html>");

        JLabel octahedronLabel = new JLabel(
		"<html>OCTAHEDRON<br>"
		+ "is composed of eight equilateral triangles,<br>"
		+ "four of which meet at each vertex.</html>");

        JLabel dodecahedronLabel = new JLabel(
		"<html>DODECAHEDRON<br>"
		+ "is composed of 12 regular pentagonal faces<br>"
		+ "with three meeting at each vertex.</html>");

        JLabel icosahedronLabel = new JLabel(
		"<html>ICOSAHEDRON<br>"
		+ "is composed of 20 identical equilateral triangular<br>"
		+ "faces, 30 edges and 12 vertices.</html>");



	//line1.setBorder(BorderFactory.createTitledBorder(""));
	line1.setPreferredSize(new Dimension(400, 100));
	line1.setLayout(new FlowLayout(FlowLayout.CENTER));
	line1.add(PlatonicLabel);

	//line21.setBorder(BorderFactory.createTitledBorder(""));
	line21.setPreferredSize(new Dimension(50, 60));
	line21.setLayout(new FlowLayout(FlowLayout.CENTER));
	line21.add(iconLabel21);

	//line31.setBorder(BorderFactory.createTitledBorder(""));
	line31.setPreferredSize(new Dimension(50, 60));
	line31.setLayout(new FlowLayout(FlowLayout.CENTER));
	line31.add(iconLabel31);

	//line41.setBorder(BorderFactory.createTitledBorder(""));
	line41.setPreferredSize(new Dimension(50, 60));
	line41.setLayout(new FlowLayout(FlowLayout.CENTER));
	line41.add(iconLabel41);


	//line51.setBorder(BorderFactory.createTitledBorder(""));
	line51.setPreferredSize(new Dimension(50, 60));
	line51.setLayout(new FlowLayout(FlowLayout.CENTER));
	line51.add(iconLabel51);


	//line61.setBorder(BorderFactory.createTitledBorder(""));
	line61.setPreferredSize(new Dimension(50, 60));
	line61.setLayout(new FlowLayout(FlowLayout.CENTER));
	line61.add(iconLabel61);


	//line22.setBorder(BorderFactory.createTitledBorder(""));
	line22.setPreferredSize(new Dimension(300, 55));
	line22.setLayout(new FlowLayout(FlowLayout.LEFT));
	line22.add(cubeLabel);

	//line32.setBorder(BorderFactory.createTitledBorder(""));
	line32.setPreferredSize(new Dimension(300, 55));
	line32.setLayout(new FlowLayout(FlowLayout.LEFT));
	line32.add(tetrahedronLabel);

	//line42.setBorder(BorderFactory.createTitledBorder(""));
	line42.setPreferredSize(new Dimension(300, 55));
	line42.setLayout(new FlowLayout(FlowLayout.LEFT));
	line42.add(octahedronLabel);

	//line52.setBorder(BorderFactory.createTitledBorder(""));
	line52.setPreferredSize(new Dimension(300, 55));
	line52.setLayout(new FlowLayout(FlowLayout.LEFT));
	line52.add(dodecahedronLabel);

	//line62.setBorder(BorderFactory.createTitledBorder(""));
	line62.setPreferredSize(new Dimension(300, 55));
	line62.setLayout(new FlowLayout(FlowLayout.LEFT));
	line62.add(icosahedronLabel);


	//line2.setBorder(BorderFactory.createTitledBorder(""));
	line2.setPreferredSize(new Dimension(370, 60));
	//line2.setLayout(new FlowLayout(FlowLayout.LEFT));
	line2.add(line21);
	line2.add(line22);


	//line3.setBorder(BorderFactory.createTitledBorder(""));
	line3.setPreferredSize(new Dimension(370, 60));
	line3.add(line31);
	line3.add(line32);

	//line4.setBorder(BorderFactory.createTitledBorder(""));
	line4.setPreferredSize(new Dimension(370, 60));
	line4.add(line41);
	line4.add(line42);

	//line5.setBorder(BorderFactory.createTitledBorder(""));
	line5.setPreferredSize(new Dimension(370, 60));
	line5.add(line51);
	line5.add(line52);

	//line6.setBorder(BorderFactory.createTitledBorder(""));
	line6.setPreferredSize(new Dimension(370, 60));
	line6.add(line61);
	line6.add(line62);

	//line7.setBorder(BorderFactory.createTitledBorder(""));
	line7.setPreferredSize(new Dimension(370, 1));

	//line8.setBorder(BorderFactory.createTitledBorder(""));
	line8.setPreferredSize(new Dimension(370, 50));
	line8.add(okButton);

	platonicPanel.add(line1);
	platonicPanel.add(line2);
	platonicPanel.add(line3);
	platonicPanel.add(line4);
	platonicPanel.add(line5);
	platonicPanel.add(line6);
	platonicPanel.add(line7);
	platonicPanel.add(line8);
 
	add(platonicPanel);
 


     }
	

    public void actionPerformed(ActionEvent evt) {
	  setVisible(false);

     	}   

}