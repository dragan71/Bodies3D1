package Bodies3D1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.*;


public class Bodies3D1start extends JFrame implements ActionListener, ChangeListener{

	double xAxis[] = new double[3];
	double yAxis[] = new double[3];
	double zAxis[] = new double[3];
	double temp[]={0,0,0};

	double Xt[]={0,0,0};
	double Yt[]={0,0,0};
	double Zt[]={0,0,0};

	//Arrays for defining states of body in layer
	boolean bodyFlags[] = new boolean[12];
	//bodyFlags[0] - Setting option for moving body
	//bodyFlags[1] - Setting option for rotate body
	//bodyFlags[2] - Drawing Coo System
	//bodyFlags[3] - Drawing Color on sides
	//bodyFlags[4] - Set Rendering
	//bodyFlags[5] - Setting border around body 100
	//bodyFlags[6] - Setting solid(true) or empty(false)
	//bodyFlags[7] - Drawing midle polygon
	//bodyFlags[8] - Edit shape mode
	//bodyFlags[9] - Draw Out Sphere
	//bodyFlags[10] - Draw In Sphere
	//bodyFlags[11] - Draw Normal Vectors

        double dblSize[] = new double[4];
	//dblSize[0] - default size value of a body
	//dblSize[1] - changeble size value of a body
	//dblSize[2] - number of points used to draw a body
	//dblSize[3] - iPoint selected in shape panel

        String strBody[] = new String[1];//Size of a body
	//strBody[0] - name of a body


	//Values for x,y,z coordinates of body points
        double bodyCorners[] = new double[24];
        double defaultBodyCorners[] = new double[24];

	//Vector u koji se smestaju tela koja se insertuju.
	//sluzi za podesavanje showing alata
	Vector vBody = new Vector();

	//Vector u koji se smestaju Button-i layer-a.
	Vector vLayerButton = new Vector();

	int iLayerPressed = 0;

	double tempX[] = new double[3];
	double tempY[] = new double[3];
	double tempZ[] = new double[3];

	Math m;
	Color col1;
	int num = 0;
	JFrame frame;
	boolean threadRunnerExist = false;

	//Pomocne promenljive, za Layere u drawing Area
	int newLayer = 0;
	int offset = 0;

	//Pomocne promenljive, rampe itd. za "Moving"
	boolean bmoveX = true;
	boolean bmoveY = true;
	boolean bmoveZ = true;
	boolean binterruptX = true;
	boolean binterruptY = true;
	boolean binterruptZ = true;
	int directionX = 1;
	int directionY = 1;
	int directionZ = 1;
	int speed = 100;
	int step = 10;

	JButton controlButton1 = new JButton("C1");
	JButton controlButton2 = new JButton("C2");


	JButton insertButton = new JButton();
	JButton resetButton = new JButton();
	JButton deleteLayerButton = new JButton();
	JButton printButton = new JButton();
        JButton editShapeButton = new JButton("Edit Shape");
        //JButton closeShapeButton = new JButton("Close Edit");

	JButton stopButton = new JButton("Stop Moving");

	JButton leftXMovButton = new JButton("<--X");
	JButton rightXMovButton = new JButton("X-->");
	JButton leftYMovButton = new JButton("<--Y");
	JButton rightYMovButton = new JButton("Y-->");
	JButton leftZMovButton = new JButton("<--Z");
	JButton rightZMovButton = new JButton("Z-->");


	//Buttons for move, rotate...
        JButton button1 = new JButton();
        JButton button2 = new JButton();

	//Main Panels which been used in methods
	JPanel eastPanel = new JPanel();
	JPanel centerPanel = new JPanel();

	//West area Panels
	JPanel westPanel = new JPanel();
	JPanel toolPanel1 = new JPanel();
	JPanel toolPanel10 = new JPanel();//west logo 1
	JPanel toolPanel11 = new JPanel();//Name of a Body
	JPanel toolPanel12 = new JPanel();//Edit Button
	JPanel toolPanel13 = new JPanel();//Move, Size, Shape
	JPanel toolPanel14 = new JPanel();//Checks and radio buttons
	JPanel shapePanel = new JPanel();

        JButton downButton = new JButton("");
        JButton upButton = new JButton("");
	Integer iPoint = new Integer(1);
	JLabel labelPoint;

	JTabbedPane tabTools23 = new JTabbedPane();
	JPanel stopButtonPanel = new JPanel();

	//Center area Panels
	JPanel drawingAreaPanel = new JPanel();
	JLayeredPane drawingLayerPanel = new JLayeredPane();


	JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 105);
	JSlider shapeXslider = new JSlider(JSlider.HORIZONTAL, -200, 200, 100);
	JSlider shapeYslider = new JSlider(JSlider.HORIZONTAL, -200, +200, 100);
	JSlider shapeZslider = new JSlider(JSlider.HORIZONTAL, -200, +200, 100);

        JFormattedTextField sizeText;
        JFormattedTextField shapeX;
        JFormattedTextField shapeY;
        JFormattedTextField shapeZ;


	JCheckBox edgesCheckButton = new JCheckBox("Edges", false);

	JCheckBox renderingButton = new JCheckBox("Set Antialiasing", false);
	JCheckBox cooCheckButton = new JCheckBox("show coo system", true);
	JCheckBox colorCheckButton = new JCheckBox("show color", true);
	JCheckBox midlePolygonButton = new JCheckBox("Midle Polygon", false);
	JCheckBox outSphereButton = new JCheckBox("circumscribed sphere", false);
	JCheckBox inSphereButton = new JCheckBox("Inscribed sphere", false);
	JCheckBox contureButton = new JCheckBox("Conture", false);



	JRadioButton radioButtonEmpty = new JRadioButton("Show empty body");
	JRadioButton radioButtonSolid = new JRadioButton("Show solid body", true);


	//getting font size
	JLabel label1 = new JLabel("Just for getting font");
	Font curFont8 = new Font(label1.getFont().getFontName(),
		label1.getFont().getStyle(),8);
	Font curFont10 = new Font(
	label1.getFont().getFontName(),label1.getFont().getStyle(),10);
	Font curFont12 = new Font(
	label1.getFont().getFontName(),label1.getFont().getStyle(),12);
	Font curFont14 = new Font(
	label1.getFont().getFontName(),label1.getFont().getStyle(),14);
	Font curFont18 = new Font(
	label1.getFont().getFontName(),label1.getFont().getStyle(),18);

	
	/*
	 //Icons
	ImageIcon imgIconRWD1 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/Back24.gif"));
	ImageIcon imgIconFWD1 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/Forward24.gif"));
	ImageIcon imgItranslate = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/translate.gif"));
	ImageIcon imgIconRotate = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/rotate.gif"));
	ImageIcon imgIconPrint = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/print.gif"));
	ImageIcon imgIconDelete = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/delete.gif"));
	ImageIcon imgIconReset = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/reset1.gif"));
	ImageIcon imgIconResetAll = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/reset2.gif"));
	ImageIcon imgIconLoad = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/load.gif"));
	ImageIcon imgIconInfo1 = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/info1.gif"));
	ImageIcon imgIconDrag = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/drag.gif"));

	ImageIcon imgPlatonic = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/platonic1.gif"));

	ImageIcon imgCube = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/cube24a.gif"));
	ImageIcon imgTetrahedron = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/tetrahedron24a.gif"));
	ImageIcon imgOctahedron = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/octahedron24a.gif"));
	ImageIcon imgDodecahedron = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/dodecahedron24a.gif"));
	ImageIcon imgIcosahedron = new ImageIcon(
	getClass().getResource("/Bodies3D1/images/icosahedron24a.gif"));

	 */
	
	//Icons
	ImageIcon imgIconRWD1 = new ImageIcon(
	getClass().getResource("/images/Back24.gif"));
	ImageIcon imgIconFWD1 = new ImageIcon(
	getClass().getResource("/images/Forward24.gif"));
	ImageIcon imgItranslate = new ImageIcon(
	getClass().getResource("/images/translate.gif"));
	ImageIcon imgIconRotate = new ImageIcon(
	getClass().getResource("/images/rotate.gif"));
	ImageIcon imgIconPrint = new ImageIcon(
	getClass().getResource("/images/print.gif"));
	ImageIcon imgIconDelete = new ImageIcon(
	getClass().getResource("/images/delete.gif"));
	ImageIcon imgIconReset = new ImageIcon(
	getClass().getResource("/images/reset1.gif"));
	ImageIcon imgIconResetAll = new ImageIcon(
	getClass().getResource("/images/reset2.gif"));
	ImageIcon imgIconLoad = new ImageIcon(
	getClass().getResource("/images/load.gif"));
	ImageIcon imgIconInfo1 = new ImageIcon(
	getClass().getResource("/images/info1.gif"));
	ImageIcon imgIconDrag = new ImageIcon(
	getClass().getResource("/images/drag.gif"));

	ImageIcon imgPlatonic = new ImageIcon(
	getClass().getResource("/images/platonic1.gif"));

	ImageIcon imgCube = new ImageIcon(
	getClass().getResource("/images/cube24a.gif"));
	ImageIcon imgTetrahedron = new ImageIcon(
	getClass().getResource("/images/tetrahedron24a.gif"));
	ImageIcon imgOctahedron = new ImageIcon(
	getClass().getResource("/images/octahedron24a.gif"));
	ImageIcon imgDodecahedron = new ImageIcon(
	getClass().getResource("/images/dodecahedron24a.gif"));
	ImageIcon imgIcosahedron = new ImageIcon(
	getClass().getResource("/images/icosahedron24a.gif"));
	
	
	
	
	
	
	Border raisedbevel;
	Boolean editmode;
	JLabel nameLabel = new JLabel();

	//XaboutDialog abf;
	AboutDialog abf1;
	PlatonicDialog ptf;


     public Bodies3D1start() {
	super("Bodies in 3D");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//setSize(750, 750);

	raisedbevel = BorderFactory.createRaisedBevelBorder();

	//abf = new XaboutDialog(null);
	abf1 = new AboutDialog(null);
	ptf = new PlatonicDialog(null);

	int inset = 20;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/8, screenSize.height/8);
        setBounds(inset, inset,
	screenSize.width  - inset*2,screenSize.height - inset*3);

 	FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
	col1 = printButton.getBackground();


	//Kreiranje menija
	JMenuBar menubar = new JMenuBar();
	JMenu menu1 = new JMenu("File");
	JMenu menu2 = new JMenu("Options");
	JMenu menu3 = new JMenu("Help");
	JMenuItem j1 = new JMenuItem("Exit");
	JMenuItem j21 = new JMenuItem("Insert 3D Body");
	JMenuItem j22 = new JMenuItem("Delete 3D Body");
	JMenuItem j23 = new JMenuItem("Clear all");
	JMenuItem j3 = new JMenuItem("Platonic Solids");
	JMenuItem j4 = new JMenuItem("About");
	JMenuItem j5 = new JMenuItem("Print");
        j1.setActionCommand("Exit");
        j1.setMnemonic(KeyEvent.VK_Q);
        j1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        j1.addActionListener(this);

        j21.setActionCommand("insert");
        j21.addActionListener(this);

        j22.setActionCommand("deleteLayer");
        j22.addActionListener(this);

        j23.setActionCommand("reset");
        j23.addActionListener(this);

        j3.setActionCommand("insertPlatonic");
        j3.addActionListener(this);

        j4.setActionCommand("about");
        j4.addActionListener(this);

        j5.setActionCommand("print");
        j5.addActionListener(this);


	menu1.add(j5);
	menu1.add(j1);
	menu2.add(j21);
	menu2.add(j22);
	menu2.add(j23);
	menu3.add(j3);
	menu3.add(j4);
	menubar.add(menu1);
	menubar.add(menu2);
	menubar.add(menu3);

	JButton dragButton = new JButton();
	dragButton.setIcon(imgIconDrag);

	//3D Bodies buttons and slider settings settings
        button1.setActionCommand("Move");
        button1.setToolTipText("Move body along paper");
        button1.addActionListener(this);
	button1.setIcon(imgItranslate);
	//button1.setText("Move");

        button2.setActionCommand("Rotate");
        button2.setToolTipText("Rotate body along paper");
        button2.addActionListener(this);
	button2.setIcon(imgIconRotate);
	//button2.setText("Rotate");
	button2.setBackground(Color.lightGray);


	//Buttons for Reset and Delete layers
        insertButton.setActionCommand("insert");
        insertButton.addActionListener(this);
        insertButton.setToolTipText("Insert 3D Body");
	insertButton.setIcon(imgIconLoad);

        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this);
        resetButton.setToolTipText("Clear all");
	resetButton.setIcon(imgIconResetAll);

        deleteLayerButton.setActionCommand("deleteLayer");
        deleteLayerButton.addActionListener(this);
        deleteLayerButton.setToolTipText("Delete selected layer");
	deleteLayerButton.setIcon(imgIconDelete);

        printButton.setActionCommand("print");
        printButton.addActionListener(this);
        printButton.setToolTipText("Print");
	printButton.setIcon(imgIconPrint);

	//Buttons for Control
        controlButton1.setActionCommand("control1");
        controlButton1.addActionListener(this);
        controlButton2.setActionCommand("control2");
        controlButton2.addActionListener(this);

	//Buttobs for showing


        //setMoveButton.setActionCommand("setMovePanel");
        //setMoveButton.addActionListener(this);
        //setStepButton.setActionCommand("setStepPanel");
        //setStepButton.addActionListener(this);


	//Center panel - Drawing Area

	drawingAreaPanel.setPreferredSize(new Dimension(620, 800));
	drawingAreaPanel.setBackground(Color.white);
	drawingLayerPanel.setPreferredSize(new Dimension(610, 790));
	drawingAreaPanel.add(drawingLayerPanel);


	//West panel components-------------------------------------------

	createtoolPanel11();

	//North Panel components ---------------------------------

	JPanel logoRazmak = new JPanel();
	logoRazmak.setPreferredSize(new Dimension(30, 80));
	//logoPanel1.setBorder(BorderFactory.createTitledBorder(""));


	//Slika Logo------------------------------------------
	//ImageIcon imgIconLogo2 = new ImageIcon(
	//getClass().getResource("/Bodies3D1/images/Logo2.gif"));
	
	ImageIcon imgIconLogo2 = new ImageIcon(
			getClass().getResource("/images/Logo2.gif"));

	JButton platonicButton = new JButton();
	platonicButton.setIcon(imgIconLogo2);
        platonicButton.setActionCommand("insertPlatonic");
        platonicButton.addActionListener(this);

	//ToolbarLogo-----------------------------
        JToolBar toolBarLogo = new JToolBar();
	toolBarLogo.setFloatable(false);
	toolBarLogo.add(platonicButton);

        editShapeButton.setActionCommand("editShape");
        editShapeButton.addActionListener(this);
	editShapeButton.setPreferredSize(new Dimension(130, 20));
	editmode = false;

	//3D Bodies buttons
	JButton info1Button = new JButton();
	info1Button.setIcon(imgIconInfo1);

	JButton cubeButton = new JButton();
	cubeButton.setIcon(imgCube);
        cubeButton.setToolTipText("Cube");
        cubeButton.setActionCommand("insertCube");
        cubeButton.addActionListener(this);

	JButton tetrahedronButton = new JButton();
	tetrahedronButton.setIcon(imgTetrahedron);
        tetrahedronButton.setToolTipText("Tetrahedron");
        tetrahedronButton.setActionCommand("insertTetrahedron");
        tetrahedronButton.addActionListener(this);

	JButton octahedronButton = new JButton();
	octahedronButton.setIcon(imgOctahedron);
        octahedronButton.setToolTipText("Octahedron");
        octahedronButton.setActionCommand("insertOctahedron");
        octahedronButton.addActionListener(this);

	JButton dodecahedronButton = new JButton();
	dodecahedronButton.setIcon(imgDodecahedron);
        dodecahedronButton.setToolTipText("Dodecahedron");
        dodecahedronButton.setActionCommand("insertDodecahedron");
        dodecahedronButton.addActionListener(this);

	JButton icosahedronButton = new JButton();
	icosahedronButton.setIcon(imgIcosahedron);
        icosahedronButton.setToolTipText("Icosahedron");
        icosahedronButton.setActionCommand("insertIcosahedron");
        icosahedronButton.addActionListener(this);


	//Toolbar1 - Insert, Delete, Print, Reset-------------------------------------
        JToolBar toolBar1 = new JToolBar("tools1");
	toolBar1.setFloatable(false);
	toolBar1.add(insertButton);
	toolBar1.add(deleteLayerButton);
	toolBar1.add(printButton);
	toolBar1.add(resetButton);

	//toolBar1.add(controlButton1);
	//toolBar1.add(controlButton2);

	//Toolbar2 - Platonic Solids -------------------------------------
        JToolBar toolBar2 = new JToolBar("tools2");
	toolBar2.setFloatable(false);

        toolBar2.add(info1Button);
        toolBar2.add(cubeButton);
        toolBar2.add(tetrahedronButton);
        toolBar2.add(octahedronButton);
        toolBar2.add(dodecahedronButton);
        toolBar2.add(icosahedronButton);
        //toolBar2.add(platonicButton);

	//Toolbar3 - MOve, Rotate ----------------------------------------
        JToolBar toolBar3 = new JToolBar("tools3");
	toolBar3.setFloatable(false);
        toolBar3.add(button2);
        toolBar3.add(button1);
        toolBar3.add(dragButton);

        JPanel northButtonPanel = new JPanel();
	northButtonPanel.setPreferredSize(new Dimension(440, 90));
	//northButtonPanel.setBorder(BorderFactory.createTitledBorder(""));
        //northButtonPanel.setBorder(BorderFactory.createEmptyBorder());

	northButtonPanel.setLayout(flow);
        northButtonPanel.add(toolBar2);
        northButtonPanel.add(toolBar3);
        northButtonPanel.add(toolBar1);

	//East Panel components ----------------------------------------------------

        edgesCheckButton.setActionCommand("showEdges");
        edgesCheckButton.addActionListener(this);
	edgesCheckButton.setFont(curFont12);

	JPanel northPanel = new JPanel();
	//JPanel westPanel = new JPanel();
	//JPanel eastPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel centerPanel = new JPanel();

	northPanel.setBorder(BorderFactory.createTitledBorder(""));
	//westPanel.setBorder(BorderFactory.createTitledBorder(""))
	eastPanel.setBorder(BorderFactory.createTitledBorder("Layers"));
	southPanel.setBorder(BorderFactory.createTitledBorder(""));
	centerPanel.setBorder(BorderFactory.createTitledBorder(""));

	northPanel.setLayout(flow);
	northPanel.setPreferredSize(new Dimension(700, 100));
	//northPanel.add(northP1);
	northPanel.add(toolBarLogo);
	northPanel.add(logoRazmak);
	northPanel.add(northButtonPanel);


	westPanel.setLayout(flow);
	westPanel.setPreferredSize(new Dimension(220, 570));
	westPanel.add(toolPanel1);
	//westPanel.add(toolPanel2);

	//eastPanel
	eastPanel.setPreferredSize(new Dimension(100, 500));
	BoxLayout vertical = new BoxLayout(eastPanel,BoxLayout.Y_AXIS);
	eastPanel.setLayout(vertical);

	//empty space with border (top, left, bottom, right)
        centerPanel.setBorder(
	BorderFactory.createEmptyBorder(10, 10, 10, 10));

	centerPanel.setBackground(Color.gray);
	centerPanel.setLayout(flow);
	centerPanel.add(drawingAreaPanel);

	JScrollPane northScrollPanel = new JScrollPane(northPanel);
	JScrollPane westScrollPanel = new JScrollPane(westPanel);
	JScrollPane eastScrollPanel = new JScrollPane(eastPanel);
	JScrollPane centerScrollPanel = new JScrollPane(centerPanel);

	//northScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	westScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	eastScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setLayout(new BorderLayout());

        add(southPanel, BorderLayout.SOUTH);
        add(westScrollPanel, BorderLayout.WEST);
        add(eastScrollPanel, BorderLayout.EAST);
        add(northScrollPanel, BorderLayout.NORTH);
        add(centerScrollPanel, BorderLayout.CENTER);


	setJMenuBar(menubar);
        loadFrameIcon();
	setVisible(true);

     }

       //Load the "desktop" icon
       private void loadFrameIcon() {
	    //ImageIcon imgIcon = new ImageIcon("/Bodies3D1/images/desk32.gif");
	    //ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Bodies3D1/images/desk32.gif"));
	    ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/desk32.gif"));
		      
            Image img = imgIcon.getImage();
            this.setIconImage(img);
	    //moze i bez "this" tj. samo setIconImage(img);
        }

	public void createtoolPanel11(){


	//TAB - Size ----------------------------------------------------
	   JPanel sizePanel = new JPanel();
	   JPanel sizeSliderPanel = new JPanel();
	   JPanel sizeTextPanel = new JPanel();
	   //JButton resetSizeButton = new JButton("Reset");
	   JButton resetSizeButton = new JButton(imgIconReset);


	   //resetSizeButton.setFont(curFont10);
	   resetSizeButton.setPreferredSize(new Dimension(40, 20));
           resetSizeButton.setActionCommand("resetSize");
           resetSizeButton.addActionListener(this);

	   //sizePanel.setPreferredSize(new Dimension(170, 135));
	   //sizePanel.setBorder(BorderFactory.createTitledBorder(""));

	   sizeSliderPanel.setPreferredSize(new Dimension(160, 50));
	   sizeTextPanel.setPreferredSize(new Dimension(160, 30));
	   //sizeTextPanel.setBorder(BorderFactory.createTitledBorder(""));

	   sizeSlider.setPreferredSize(new Dimension(150, 40));
	   sizeSlider.setMajorTickSpacing(100);
	   sizeSlider.setMinorTickSpacing(20);
	   sizeSlider.setPaintTicks(true);
	   sizeSlider.setPaintLabels(true);
	   sizeSlider.setFont(curFont10);
           sizeSlider.setToolTipText("Change size of a body");
           sizeSlider.addChangeListener(this);

           sizeText = new JFormattedTextField(NumberFormat.getNumberInstance());
           sizeText.setColumns(2);
	   sizeText.setFont(curFont12);
	   sizeText.setHorizontalAlignment(JTextField.CENTER);

           sizeText.setText(Integer.toString(sizeSlider.getValue()));
           sizeText.setActionCommand("sizeText");
           sizeText.addActionListener(this);


	   //JLabel sizeText1 = new JLabel("Change size");
	   //sizeText1.setFont(curFont10);
	   sizeTextPanel.add(new JLabel("Change size"));
	   sizeTextPanel.add(sizeText);
	   sizeTextPanel.add(resetSizeButton);

	   sizeSliderPanel.add(sizeSlider);

	   sizePanel.add(sizeTextPanel);
	   //sizePanel.add(resetSizeButton);
	   sizePanel.add(sizeSliderPanel);


	//TAB - Move ----------------------------------------------------
	   JPanel movePanel = new JPanel();
	   JPanel movingPanel = new JPanel();

	   movingPanel.setPreferredSize(new Dimension(160, 145));

	   leftXMovButton.setPreferredSize(new Dimension(57, 20));
	   rightXMovButton.setPreferredSize(new Dimension(57, 20));
	   leftYMovButton.setPreferredSize(new Dimension(57, 20));
	   rightYMovButton.setPreferredSize(new Dimension(57, 20));
	   leftZMovButton.setPreferredSize(new Dimension(57, 20));
	   rightZMovButton.setPreferredSize(new Dimension(57, 20));

           leftXMovButton.setActionCommand("XleftMove");
           leftXMovButton.addActionListener(this);
           rightXMovButton.setActionCommand("XrightMove");
           rightXMovButton.addActionListener(this);

           leftYMovButton.setActionCommand("YleftMove");
           leftYMovButton.addActionListener(this);
           rightYMovButton.setActionCommand("YrightMove");
           rightYMovButton.addActionListener(this);

           leftZMovButton.setActionCommand("ZleftMove");
           leftZMovButton.addActionListener(this);
           rightZMovButton.setActionCommand("ZrightMove");
           rightZMovButton.addActionListener(this);

	   movingPanel.add(leftXMovButton);
	   movingPanel.add(new JLabel(""));
	   movingPanel.add(rightXMovButton);
	   movingPanel.add(leftYMovButton);
	   movingPanel.add(new JLabel(""));
	   movingPanel.add(rightYMovButton);
	   movingPanel.add(leftZMovButton);
	   movingPanel.add(new JLabel(""));
	   movingPanel.add(rightZMovButton);

	   movePanel.add(movingPanel);


	//TAB - Step ----------------------------------------------------
	   JPanel stepPanel = new JPanel();
	   JPanel stepingPanel = new JPanel();

	   stepingPanel.setPreferredSize(new Dimension(160, 135));

	   JButton leftXButton = new JButton("<--X");
	   JButton rightXButton = new JButton("X-->");
	   JButton leftYButton = new JButton("<--Y");
	   JButton rightYButton = new JButton("Y-->");
	   JButton leftZButton = new JButton("<--Z");
	   JButton rightZButton = new JButton("Z-->");

	   leftXButton.setPreferredSize(new Dimension(57, 20));
	   rightXButton.setPreferredSize(new Dimension(57, 20));
	   leftYButton.setPreferredSize(new Dimension(57, 20));
	   rightYButton.setPreferredSize(new Dimension(57, 20));
	   leftZButton.setPreferredSize(new Dimension(57, 20));
	   rightZButton.setPreferredSize(new Dimension(57, 20));

           leftXButton.setActionCommand("XleftStep");
           leftXButton.addActionListener(this);
           rightXButton.setActionCommand("XrightStep");
           rightXButton.addActionListener(this);

           leftYButton.setActionCommand("YleftStep");
           leftYButton.addActionListener(this);
           rightYButton.setActionCommand("YrightStep");
           rightYButton.addActionListener(this);

           leftZButton.setActionCommand("ZleftStep");
           leftZButton.addActionListener(this);
           rightZButton.setActionCommand("ZrightStep");
           rightZButton.addActionListener(this);

	   stepingPanel.add(leftXButton);
	   stepingPanel.add(new JLabel(""));
	   stepingPanel.add(rightXButton);
	   stepingPanel.add(leftYButton);
	   stepingPanel.add(new JLabel(""));
	   stepingPanel.add(rightYButton);
	   stepingPanel.add(leftZButton);
	   stepingPanel.add(new JLabel(""));
	   stepingPanel.add(rightZButton);

	   stepPanel.add(stepingPanel);


	//Shape Panel ----------------------------------------------------
	   //JPanel shapePanel = new JPanel();//declared in Fields area
	   JPanel shapePanel1 = new JPanel();
	   JPanel shapePanel2 = new JPanel();


	   downButton.setIcon(imgIconRWD1);
           downButton.setActionCommand("downShape");
           downButton.addActionListener(this);

	   upButton.setIcon(imgIconFWD1);
           upButton.setActionCommand("upShape");
           upButton.addActionListener(this);

           JToolBar toolBar4 = new JToolBar("");
	   toolBar4.setFloatable(false);
           toolBar4.add(downButton);
           toolBar4.add(upButton);

	   labelPoint = new JLabel(iPoint.toString());
	   labelPoint.setFont(curFont18);

	   shapePanel1.setPreferredSize(new Dimension(160, 40));
	   shapePanel1.add(toolBar4);
	   shapePanel1.add(new JLabel("Point"));
	   shapePanel1.add(labelPoint);


           JButton resetPointButton = new JButton("Reset Point");
           resetPointButton.setActionCommand("resetPointShape");
           resetPointButton.addActionListener(this);
	   resetPointButton.setPreferredSize(new Dimension(120, 20));

           JButton resetBodyButton = new JButton("Reset Body");
           resetBodyButton.setActionCommand("resetBodyShape");
           resetBodyButton.addActionListener(this);
	   resetBodyButton.setPreferredSize(new Dimension(120, 20));

	   shapeXslider.setPreferredSize(new Dimension(100, 30));
	   shapeXslider.setMajorTickSpacing(200);
	   shapeXslider.setMinorTickSpacing(50);
	   shapeXslider.setPaintTicks(true);
	   //shapeXslider.setPaintLabels(true);
	   shapeXslider.setFont(curFont10);
           shapeXslider.addChangeListener(this);

	   shapeYslider.setPreferredSize(new Dimension(100, 30));
	   shapeYslider.setMajorTickSpacing(100);
	   shapeYslider.setMinorTickSpacing(50);
	   shapeYslider.setPaintTicks(true);
	   //shapeYslider.setPaintLabels(true);
	   shapeYslider.setFont(curFont10);
           shapeYslider.addChangeListener(this);

	   shapeZslider.setPreferredSize(new Dimension(100, 30));
	   shapeZslider.setMajorTickSpacing(100);
	   shapeZslider.setMinorTickSpacing(50);
	   shapeZslider.setPaintTicks(true);
	   //shapeZslider.setPaintLabels(true);
	   shapeZslider.setFont(curFont10);
           shapeZslider.addChangeListener(this);

           shapeX = new JFormattedTextField(NumberFormat.getNumberInstance());
           shapeX.setColumns(3);
           shapeX.setText(Integer.toString(shapeXslider.getValue()));
           shapeX.setActionCommand("shapeX");
           shapeX.addActionListener(this);

           shapeY = new JFormattedTextField(NumberFormat.getNumberInstance());
           shapeY.setColumns(3);
           shapeY.setText(Integer.toString(shapeYslider.getValue()));
           shapeY.setActionCommand("shapeY");
           shapeY.addActionListener(this);

           shapeZ = new JFormattedTextField(NumberFormat.getNumberInstance());
           shapeZ.setColumns(3);
           shapeZ.setText(Integer.toString(shapeZslider.getValue()));
           shapeZ.setActionCommand("shapeZ");
           shapeZ.addActionListener(this);

	   shapePanel2.setPreferredSize(new Dimension(160, 160));
	   shapePanel2.add(new JLabel("X"));
	   shapePanel2.add(shapeXslider);
	   shapePanel2.add(shapeX);
	   shapePanel2.add(new JLabel("Y"));
	   shapePanel2.add(shapeYslider);
	   shapePanel2.add(shapeY);
	   shapePanel2.add(new JLabel("Z"));
	   shapePanel2.add(shapeZslider);
	   shapePanel2.add(shapeZ);
	   shapePanel2.add(resetPointButton);
	   shapePanel2.add(resetBodyButton);

	   shapePanel.setPreferredSize(new Dimension(170, 250));
	   shapePanel.setBorder(BorderFactory.createTitledBorder("Change shape of a body"));
	   shapePanel.add(shapePanel1);
	   shapePanel.add(shapePanel2);


	   //TAB ---------------------------------------------------------

	   tabTools23.setPreferredSize(new Dimension(170, 165));
           tabTools23.addChangeListener(this);
	   tabTools23.addTab("Size", sizePanel);
	   tabTools23.addTab("Rotation", movePanel);
	   tabTools23.addTab("Step", stepPanel);

	   //toolPanel10 ---------------------------------------------------------

	   toolPanel10.setPreferredSize(new Dimension(180, 500));
	   //toolPanel10.setBorder(BorderFactory.createTitledBorder(""));

	   JPanel dlgPanel1 = new JPanel();
	   dlgPanel1.setPreferredSize(new Dimension(160, 40));
	   //dlgPanel1.setBorder(BorderFactory.createTitledBorder(""));


	   JLabel dlgL1 = new JLabel("Platonic Solids");
	   dlgL1.setFont(curFont18);
	   dlgPanel1.add(dlgL1);


	   JPanel dlgPanel2 = new JPanel();
	   dlgPanel2.setPreferredSize(new Dimension(170, 260));
	   //dlgPanel2.setBorder(BorderFactory.createTitledBorder(""));

	   dlgPanel2.add(new JLabel(
		"<html>Click on icon and insert body<br>"
		+ "                <br>"
		+ "You can:<br>"
		+ "Rotate (by draging mouse)<br>"
		+ "Move (by draging mouse)<br>"
		+ "change size<br>"
		+ "change shape<br>"
		+ "show empty bodies<br>"
		+ "set constant rotation<br>"
		+ "insert many layers of body<br>"
		+ "choose between many layers<br>"
		+ "show Inscribed sphere<br>"
		+ "show Circumscribed sphere<br>"
		+ "show coo system inside body<br>"
		+ "print</html>"));


	   toolPanel10.add(dlgPanel1);
	   toolPanel10.add(dlgPanel2);
	   //toolPanel10.setAlignmentX(Component.CENTER_ALIGNMENT);

	   //toolPanel11 ---------------------------------------------------------
	   toolPanel11.setPreferredSize(new Dimension(180, 40));
	   //toolPanel11.setBorder(BorderFactory.createTitledBorder(""));
	   toolPanel11.setBorder(raisedbevel);


	   //toolPanel12 ---------------------------------------------------------
	   toolPanel12.setPreferredSize(new Dimension(180, 30));
	   //toolPanel12.setBorder(BorderFactory.createTitledBorder(""));
	   toolPanel12.add(editShapeButton);

	   //toolPanel13  ---------------------------------------------------------
	   stopButtonPanel.setPreferredSize(new Dimension(160, 30));

           stopButton.setActionCommand("stopMoving");
           stopButton.addActionListener(this);
	   stopButton.setPreferredSize(new Dimension(125, 20));
           stopButton.setEnabled(false);

	   stopButtonPanel.add(stopButton);

	   toolPanel13.setPreferredSize(new Dimension(180, 180));
	   //toolPanel13.setBorder(BorderFactory.createTitledBorder(""));
	   toolPanel13.setBorder(raisedbevel);
	   toolPanel13.add(stopButtonPanel);
	   toolPanel13.add(tabTools23);


	   //toolPanel14 ---------------------------------------------------------

           renderingButton.setActionCommand("setRendering");
           renderingButton.addActionListener(this);
	   renderingButton.setFont(curFont12);

           cooCheckButton.setActionCommand("showCooSystem");
           cooCheckButton.addActionListener(this);
	   cooCheckButton.setFont(curFont12);

           colorCheckButton.setActionCommand("showColor");
           colorCheckButton.addActionListener(this);;
	   colorCheckButton.setFont(curFont12);

           midlePolygonButton.setActionCommand("showMidlePolygon");
           midlePolygonButton.addActionListener(this);
	   midlePolygonButton.setFont(curFont12);

           outSphereButton.setActionCommand("showOutSphere");
           outSphereButton.addActionListener(this);
	   outSphereButton.setFont(curFont12);

           inSphereButton.setActionCommand("showInSphere");
           inSphereButton.addActionListener(this);
	   inSphereButton.setFont(curFont12);

           contureButton.setActionCommand("showConture");
           contureButton.addActionListener(this);
	   contureButton.setFont(curFont12);

           radioButtonSolid.setActionCommand("settingSolid");
           radioButtonSolid.addActionListener(this);
	   radioButtonSolid.setFont(curFont12);

           radioButtonEmpty.setActionCommand("settingEmpty");
           radioButtonEmpty.addActionListener(this);;
	   radioButtonEmpty.setFont(curFont12);


 	   FlowLayout flow1 = new FlowLayout(FlowLayout.LEFT);

           ButtonGroup group = new ButtonGroup();

	   toolPanel14.setPreferredSize(new Dimension(180, 270));
	   //toolPanel14.setBorder(BorderFactory.createTitledBorder(""));
	   toolPanel14.setBorder(raisedbevel);
	   toolPanel14.setLayout(flow1);

	   toolPanel14.add(renderingButton);
	   toolPanel14.add(cooCheckButton);
	   toolPanel14.add(radioButtonSolid);
	   toolPanel14.add(new JLabel("           "));
	   toolPanel14.add(new JLabel("       "));
	   toolPanel14.add(colorCheckButton);
	   toolPanel14.add(new JLabel("            "));
	   toolPanel14.add(new JLabel("       "));
	   toolPanel14.add(contureButton);
	   toolPanel14.add(radioButtonEmpty);
	   toolPanel14.add(new JLabel("            "));
	   toolPanel14.add(new JLabel("       "));
	   toolPanel14.add(midlePolygonButton);
	   toolPanel14.add(outSphereButton);
	   toolPanel14.add(inSphereButton);


           group.add(radioButtonEmpty);
           group.add(radioButtonSolid);


	  //toolPanel1 ---------------------------------------------------------

	  toolPanel1.setPreferredSize(new Dimension(210, 550));
	  //toolPanel1.setBorder(BorderFactory.createTitledBorder(""));
	  toolPanel1.setBorder(raisedbevel);
	  toolPanel1.add(toolPanel10);//only west logo


	}


    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();

        if ("Exit".equals(cmd)) { //exit
            System.exit(0);
        } else if ("Move".equals(cmd)) { // second button clicked
		moveBody();
        } else if ("Rotate".equals(cmd)) { // third button clicked
		rotateBody();

	//Steping
        } else if ("XleftStep".equals(cmd)) { // X axe left step
	   	directionX = 1;
	   	stepXaxe();
	   	drawingLayerPanel.repaint();
        } else if ("XrightStep".equals(cmd)) { // X axe right step
	   	directionX = -1;
	   	stepXaxe();
	   	drawingLayerPanel.repaint();
        } else if ("YleftStep".equals(cmd)) { // Y axe left step
	   	directionY = -1;
	   	stepYaxe();
	   	drawingLayerPanel.repaint();
        } else if ("YrightStep".equals(cmd)) { // Y axe right step
	   	directionY = 1;
	   	stepYaxe();
	   	drawingLayerPanel.repaint();
        } else if ("ZleftStep".equals(cmd)) { // Z axe left step
	   	directionZ = 1;
	   	stepZaxe();
	   	drawingLayerPanel.repaint();
        } else if ("ZrightStep".equals(cmd)) { // Z axe right step
	   	directionZ = -1;
	  	stepZaxe();
	   	drawingLayerPanel.repaint();

	//Moving
        } else if ("XleftMove".equals(cmd)) { // X axe left move
		XleftMove();
                stopButton.setEnabled(true);
        } else if ("XrightMove".equals(cmd)) { // X axe right move
		XrightMove();
                stopButton.setEnabled(true);
        } else if ("YleftMove".equals(cmd)) { // Y axe left move
		YleftMove();
                stopButton.setEnabled(true);
        } else if ("YrightMove".equals(cmd)) { // Y axe right move
		YrightMove();
                stopButton.setEnabled(true);
        } else if ("ZleftMove".equals(cmd)) { // Z axe left move
		ZleftMove();
                stopButton.setEnabled(true);
        } else if ("ZrightMove".equals(cmd)) { // Z axe right move
		ZrightMove();
                stopButton.setEnabled(true);

        } else if ("stopMoving".equals(cmd)) { // Stop Moving
		stopMoving();

	//Inserting 3D Body into drawing area

        } else if ("insert".equals(cmd)) {
		insert();
        } else if ("insertPlatonic".equals(cmd)) { // Insert Cube
		//insertPlatonic();
	   	ptf.setVisible(true);

        } else if ("insertCube".equals(cmd)) { // Insert Cube
		insertCube();
        } else if ("insertTetrahedron".equals(cmd)) { // Insert Tetrahedron
		insertTetrahedron();
        } else if ("insertOctahedron".equals(cmd)) { // Insert Octahedron
		insertOctahedron();
        } else if ("insertDodecahedron".equals(cmd)) { // Insert Octahedron
		insertDodecahedron();
        } else if ("insertIcosahedron".equals(cmd)) { // Insert Octahedron
		insertIcosahedron();

	//Reset, Delete...
        } else if ("reset".equals(cmd)) {

	    if (newLayer > 0) {
		String spom1 = new String("      Do you really want to clear all");
		int response = JOptionPane.showOptionDialog(
            	   null                      // Center in window.
          	, spom1        		     // Message
          	, "Warning  "                // Title in titlebar
          	, JOptionPane.YES_NO_OPTION  // Option type
          	, JOptionPane.PLAIN_MESSAGE  // messageType
          	, null                       // Icon (none)
          	, null                       // Button text as above.
          	, "None of your business"    // Default button's label
          	);

	    	if (response == 0) {
			reset();
	    	}
	    }

        } else if ("deleteLayer".equals(cmd)) {
		deleteLayer();

	//Showing edges, coo system, color...
        } else if ("showEdges".equals(cmd)) {
		showEdges();
        } else if ("setRendering".equals(cmd)) {
		setRendering();
        } else if ("showCooSystem".equals(cmd)) {
		showCooSystem();
        } else if ("showColor".equals(cmd)) {
		showColor();
        } else if ("settingSolid".equals(cmd)) {
		settingSolid();
        } else if ("settingEmpty".equals(cmd)) {
		settingEmpty();
        } else if ("showMidlePolygon".equals(cmd)) {
		showMidlePolygon();
        } else if ("showOutSphere".equals(cmd)) {
		showOutSphere();
        } else if ("showInSphere".equals(cmd)) {
		showInSphere();
        } else if ("showConture".equals(cmd)) {
		showConture();

	//Text area for size text
        } else if ("sizeText".equals(cmd)) {
            int pom2 = ((Number)sizeText.getValue()).intValue();
	    if (pom2 >= 100) {
		  pom2 = 100;
       	    }

	    sizeText.setValue(pom2);
	    sizeSlider.setValue(pom2);


	//Text area for shape text (X, Y, Z)
        } else if ("shapeX".equals(cmd)) {
            int pom2 = ((Number)shapeX.getValue()).intValue();
	    if (pom2 >= 200) {
		  pom2 = 200;
		  shapeX.setValue(pom2);
	          shapeXslider.setValue(pom2);
       	    } else if (pom2 <= -200) {
		  pom2 = -200;
		shapeX.setValue(pom2);
	        shapeXslider.setValue(pom2);

       	    } else {
		shapeX.setValue(pom2);
	        shapeXslider.setValue(pom2);
	    }

        } else if ("shapeY".equals(cmd)) {
            int pom2 = ((Number)shapeY.getValue()).intValue();
	    if (pom2 > 200) {
		  pom2 = 200;
		  shapeY.setValue(pom2);
	          shapeYslider.setValue(pom2);
       	    } else if (pom2 <= -200) {
		  pom2 = -200;
		shapeY.setValue(pom2);
	        shapeYslider.setValue(pom2);
       	    } else {
		shapeY.setValue(pom2);
	        shapeYslider.setValue(pom2);
	    }

        } else if ("shapeZ".equals(cmd)) {
            int pom2 = ((Number)shapeZ.getValue()).intValue();
	    if (pom2 > 200) {
		  pom2 = 200;
		  shapeZ.setValue(pom2);
	          shapeZslider.setValue(pom2);
       	    } else if (pom2 <= -200) {
		  pom2 = -200;
		shapeZ.setValue(pom2);
	        shapeZslider.setValue(pom2);
       	    } else {
		shapeZ.setValue(pom2);
	        shapeZslider.setValue(pom2);
	    }



	//Edit shape of a body
        } else if ("editShape".equals(cmd)) {
		editShape();

	//Close Edit shape of a body
        } else if ("closeShape".equals(cmd)) {
		closeShape();

	//up shape - select point
        } else if ("upShape".equals(cmd)) {
		upShape();

	//down shape - select point
        } else if ("downShape".equals(cmd)) {
		downShape();

	//Reset shape of a body point
        } else if ("resetPointShape".equals(cmd)) {
		resetPointShape();

	//Reset shape of a Body
        } else if ("resetBodyShape".equals(cmd)) {
		resetBodyShape();

	//Reset Size of a Body
        } else if ("resetSize".equals(cmd)) {
	    sizeSlider.setValue((int)(dblSize[0]));

	//About
        } else if ("about".equals(cmd)) {
	   	//AboutDialog abf1 = new AboutDialog(null);
	   	abf1.setVisible(true);

	//Print
        } else if ("print".equals(cmd)) {
	   	PrintUtilities.printComponent(drawingLayerPanel);

	//control
        } else if ("control1".equals(cmd)) {
		control1();

        } else if ("control2".equals(cmd)) {
		control2();



      }


	  //Layer Buttons
	  String sLayerName;
          for(int i=0; i < vLayerButton.size() + 1; i++){

	     sLayerName = "Layer " + i;
             if (sLayerName.equals(cmd)) {
		  selectLayer(i);
       	     }

          }

    }


    //Change a state of a Sliders
    public void stateChanged(ChangeEvent e) {

	Object prim1 = new Object();
	prim1 = e.getSource();

	if (sizeSlider == prim1) {

	    sizeText.setText(Integer.toString(sizeSlider.getValue()));

	    dblSize[1] = sizeSlider.getValue();
	    drawingLayerPanel.repaint();

	} else if (shapeXslider == prim1) {

	    shapeX.setText(Integer.toString(shapeXslider.getValue()));
	    bodyCorners[3*iPoint - 3] = (double)shapeXslider.getValue()/100;
	    drawingLayerPanel.repaint();


	} else if (shapeYslider == prim1) {

	    shapeY.setText(Integer.toString(shapeYslider.getValue()));
	    bodyCorners[3*iPoint - 2] = (double)shapeYslider.getValue()/100;
	    drawingLayerPanel.repaint();

	} else if (shapeZslider == prim1) {

	    shapeZ.setText(Integer.toString(shapeZslider.getValue()));
	    bodyCorners[3*iPoint - 1] = (double)shapeZslider.getValue()/100;
	    drawingLayerPanel.repaint();


	} else if (tabTools23 == prim1) {

	   int index = tabTools23.getSelectedIndex();
	   if (index == 0) {
	   	tabTools23.setPreferredSize(new Dimension(170, 120));
	   	toolPanel13.setPreferredSize(new Dimension(180, 180));
	   	toolPanel1.setPreferredSize(new Dimension(210, 550));
	   } else if (index == 1) {
	   	tabTools23.setPreferredSize(new Dimension(170, 120));
	   	toolPanel13.setPreferredSize(new Dimension(180, 180));
	   	toolPanel1.setPreferredSize(new Dimension(210, 550));
	   } else if (index == 2) {
	   	tabTools23.setPreferredSize(new Dimension(170, 120));
	   	toolPanel13.setPreferredSize(new Dimension(180, 180));
	   	toolPanel1.setPreferredSize(new Dimension(210, 550));
	   }

	}

    }


     public void control1(){

/*
	System.out.println("--------------");
	System.out.println("iPoint = " + iPoint);
	System.out.println("defx = " + defaultBodyCorners[3*iPoint-3]);
	System.out.println("bodyx = " + bodyCorners[3*iPoint-3]);
*/

	System.out.println("--------------");
	System.out.println("Threads = " + Thread.activeCount());

     }


     public void control2(){

	   XbodyPanel xbody1 = new XbodyPanel();
	   vBody.add(xbody1);
           xbody1.settingBodyMidPoint(150 + offset, 150 + offset);
	   drawingLayerPanel.add(xbody1, new Integer(newLayer));
	   insertLayer();

	   	System.out.println("aaaaa");

     }


     public void editShape(){

	EditPanel ep = new EditPanel();
	drawingLayerPanel.add(ep, new Integer(99));

        //editShapeButton.setEnabled(false);
	editShapeButton.setActionCommand("closeShape");
	editShapeButton.setText("close Edit Shape");

	editmode = true;
        //closeShapeButton.setEnabled(true);
	bodyFlags[8] = true;//Edit shape mode

	stopMoving();
        stopButton.setEnabled(false);
	toolPanel13.removeAll();
	toolPanel13.add(shapePanel);

	toolPanel13.setPreferredSize(new Dimension(180, 270));
	toolPanel1.setPreferredSize(new Dimension(210, 640));
	westPanel.setPreferredSize(new Dimension(220, 660));
	toolPanel13.updateUI();
	toolPanel1.updateUI();

	setSlidersShape();

     }


     public void closeShape(){


        //editShapeButton.setEnabled(true);
	editShapeButton.setActionCommand("editShape");
	editShapeButton.setText("Edit Shape");

	editmode = false;
        //closeShapeButton.setEnabled(false);
	bodyFlags[8] = false;//Edit shape mode

	drawingLayerPanel.setLayer(
	(drawingLayerPanel.getComponentsInLayer(99))[0],199);

	drawingLayerPanel.remove(0);
	drawingLayerPanel.validate();
	drawingLayerPanel.repaint();

	toolPanel13.removeAll();
	toolPanel13.add(stopButtonPanel);
	toolPanel13.add(tabTools23);

	toolPanel13.setPreferredSize(new Dimension(180, 180));
	toolPanel1.setPreferredSize(new Dimension(210, 550));
	westPanel.setPreferredSize(new Dimension(220, 570));
	toolPanel13.updateUI();
	toolPanel1.updateUI();

     }

     public void upShape(){

	iPoint++;
	if (iPoint > (int)(dblSize[2])) {
	    iPoint = 1;
	}
	labelPoint.setText(iPoint.toString());
	dblSize[3] = iPoint;
	setSlidersShape();

     }



     public void downShape(){

	iPoint--;
	if (iPoint < 1) {
	    iPoint = (int)(dblSize[2]);
	}
	labelPoint.setText(iPoint.toString());
	dblSize[3] = iPoint;
	setSlidersShape();

     }


     public void setSlidersShape(){

          for(int i=0; i < dblSize[2] + 1; i++){
             if (i == iPoint) {
	          shapeXslider.setValue((int)(bodyCorners[3*i-3]*100));
	          shapeYslider.setValue((int)(bodyCorners[3*i-2]*100));
	          shapeZslider.setValue((int)(bodyCorners[3*i-1]*100));
       	     }
          }

     }


     public void resetPointShape(){

	bodyCorners[3*iPoint-3] = defaultBodyCorners[3*iPoint-3];
	bodyCorners[3*iPoint-2] = defaultBodyCorners[3*iPoint-2];
	bodyCorners[3*iPoint-1] = defaultBodyCorners[3*iPoint-1];

	shapeXslider.setValue((int)(bodyCorners[3*iPoint-3]*100));
	shapeYslider.setValue((int)(bodyCorners[3*iPoint-2]*100));
	shapeZslider.setValue((int)(bodyCorners[3*iPoint-1]*100));

	drawingLayerPanel.repaint();

     }


     public void resetBodyShape(){

          for(int i=0; i < 3*dblSize[2]; i++){
	     bodyCorners[i] = defaultBodyCorners[i];
       	  }
	drawingLayerPanel.repaint();

     }


     public void insert(){

	Object[] possibilities = {
		"Cube", "Tetrahedron", "Octahedron", "Dodecahedron", "Icosahedron"};
	String s = (String)JOptionPane.showInputDialog(
		frame,
                "Chose 3D Body",
		"Insert 3D Body",
		JOptionPane.PLAIN_MESSAGE,
		null,
		possibilities,
		"cube");

	//If a string was returned, say so.
	if ((s != null) && (s.length() > 0)) {

	    if (s == "Cube") {
		insertCube();
	    } else if (s == "Tetrahedron") {
		insertTetrahedron();
	    } else if (s == "Octahedron") {
		insertOctahedron();
	    } else if (s == "Dodecahedron") {
		insertDodecahedron();
	    } else if (s == "Icosahedron") {
		insertIcosahedron();
	    }

	    return;
	}

     }


     public void insertPlatonic(){


	System.out.println("Pressed insertPlatonic button");


     }

     public void insertCube(){
	   CubePanel cube1 = new CubePanel();
	   vBody.add(cube1);
           cube1.settingBodyMidPoint(150 + offset, 150 + offset);
	   drawingLayerPanel.add(cube1, new Integer(newLayer));
	   insertLayer();
     }

     public void insertTetrahedron(){
	   TetrahedronPanel tetrah1 = new TetrahedronPanel();
	   vBody.add(tetrah1);
           tetrah1.settingBodyMidPoint(150 + offset, 150 + offset);
	   drawingLayerPanel.add(tetrah1, new Integer(newLayer));
	   insertLayer();
     }

     public void insertOctahedron(){
	   OctahedronPanel octahed1 = new OctahedronPanel();
	   vBody.add(octahed1);
           octahed1.settingBodyMidPoint(150 + offset, 150 + offset);
	   drawingLayerPanel.add(octahed1, new Integer(newLayer));
	   insertLayer();
     }

     public void insertDodecahedron(){
	   DodecahedronPanel dodec1 = new DodecahedronPanel();
	   vBody.add(dodec1);
           dodec1.settingBodyMidPoint(150 + offset, 150 + offset);
	   drawingLayerPanel.add(dodec1, new Integer(newLayer));
	   insertLayer();
     }

     public void insertIcosahedron(){
	   IcosahedronPanel icosa1 = new IcosahedronPanel();
	   vBody.add(icosa1);
           icosa1.settingBodyMidPoint(170 + offset, 170 + offset);
	   drawingLayerPanel.add(icosa1, new Integer(newLayer));
	   insertLayer();
     }


     public void insertLayer(){

	if (newLayer == 0) {
	   eastPanel.add(edgesCheckButton);
           //editShapeButton.setEnabled(true);
	   editmode = false;
           //closeShapeButton.setEnabled(false);
	}

	if (newLayer > 15) {
	    eastPanel.setPreferredSize(new Dimension(100, 900));
            eastPanel.updateUI();
	} else {
	    eastPanel.setPreferredSize(new Dimension(100, 500));
            eastPanel.updateUI();
	}

	newLayer+= 1;
	offset+= 20;

	//Setting another layer button in east panel
	String layername = "Layer " + (newLayer);
	vLayerButton.add(new JButton(layername));
        eastPanel.add((JButton)(vLayerButton.get(newLayer - 1)));
        eastPanel.validate();
        eastPanel.repaint();

        ((JButton)(vLayerButton.get(newLayer - 1))).setActionCommand(layername);
        ((JButton)(vLayerButton.get(newLayer - 1))).addActionListener(this);

	selectLayer(newLayer);

	toolPanel1.removeAll();
	toolPanel1.add(toolPanel11);
	toolPanel1.add(toolPanel12);
	toolPanel1.add(toolPanel13);
	toolPanel1.add(toolPanel14);
	toolPanel1.validate();
	toolPanel1.repaint();

	drawingLayerPanel.validate();
	drawingLayerPanel.repaint();

     }


     public void selectLayer(int k){

	bodyFlags[5] = false;

	//Getting back Layer which have been used on it's place
	if (drawingLayerPanel.getComponentCountInLayer(100) != 0) {
	   drawingLayerPanel.setLayer(
	   (drawingLayerPanel.getComponentsInLayer(100))[0],iLayerPressed);
	   ((JButton)(vLayerButton.get(iLayerPressed))).setBackground(col1);

	}

	//Setting Layer which been chosen on place 100
	if (drawingLayerPanel.getComponentCountInLayer(k - 1) != 0) {
	   drawingLayerPanel.setLayer(
	   (drawingLayerPanel.getComponentsInLayer(k - 1))[0],100);
	   iLayerPressed = k - 1;
	   setShowingAndChecks(iLayerPressed);
	   ((JButton)(vLayerButton.get(iLayerPressed))).setBackground(Color.lightGray);
	}

     }



     public void setShowingAndChecks(Integer pom1){

	bodyFlags[8] = false;//Edit shape mode

	//Podesavanje showing alatki za najvisi layer
	bodyFlags = ((BodyPanel)(vBody.get(pom1))).bodyFlags1;
	dblSize = ((BodyPanel)(vBody.get(pom1))).dblSize1;
	strBody = ((BodyPanel)(vBody.get(pom1))).strBody;
	xAxis =   ((BodyPanel)(vBody.get(pom1))).eX;
	yAxis =   ((BodyPanel)(vBody.get(pom1))).eY;
	zAxis =   ((BodyPanel)(vBody.get(pom1))).eZ;

	bodyCorners = ((BodyPanel)(vBody.get(pom1))).bodyCorners1;
	defaultBodyCorners = ((BodyPanel)(vBody.get(pom1))).defaultBodyCorners1;

	sizeSlider.setValue((int)(dblSize[1]));
	iPoint = 1;
	dblSize[3] = iPoint;
	labelPoint.setText(iPoint.toString());
	setSlidersShape();


	toolPanel11.removeAll();
	nameLabel.setText(strBody[0]);
	nameLabel.setFont(curFont18);
	toolPanel11.add(nameLabel);
	toolPanel11.validate();
	toolPanel11.repaint();


	   if (editmode){
		bodyFlags[8] = true;//Edit shape mode
	   } else {
		bodyFlags[8] = false;//Edit shape mode
	   }


	   if (bodyFlags[4] == true){
		renderingButton.setSelected(true);
	   } else {
	        renderingButton.setSelected(false);
	   }

	   if (bodyFlags[2] == true){
		cooCheckButton.setSelected(true);
	   } else {
	        cooCheckButton.setSelected(false);
	   }

	   if (bodyFlags[3] == true){
	        colorCheckButton.setSelected(true);
	   } else {
	        colorCheckButton.setSelected(false);
	   }


	   if (bodyFlags[6] == true){
	        radioButtonSolid.setSelected(true);
		settingSolid();
	   } else {
	        radioButtonEmpty.setSelected(true);
		settingEmpty();
	   }

	   if (bodyFlags[7] == true){
		midlePolygonButton.setSelected(true);
	   } else {
	        midlePolygonButton.setSelected(false);
	   }


	   if (bodyFlags[9] == true){
		outSphereButton.setSelected(true);
	   } else {
	        outSphereButton.setSelected(false);
	   }

	   if (bodyFlags[10] == true){
		inSphereButton.setSelected(true);
	   } else {
	        inSphereButton.setSelected(false);
	   }

	   if (bodyFlags[11] == true){
		contureButton.setSelected(true);
	   } else {
	        contureButton.setSelected(false);
	   }


	   if (button1.isSelected()) {
	   	bodyFlags[0] = true;
	   	bodyFlags[1] = false;
           }

	   if (button2.isSelected()) {
	   	bodyFlags[0] = false;
	   	bodyFlags[1] = true;
           }


	   if (edgesCheckButton.isSelected()) {
	   	bodyFlags[5] = true;
	   } else {
	        bodyFlags[5] = (false);
	   }

     }



     public void moveBody(){

           	//CubePanel.settingMoveTool();
	        bodyFlags[0] = true;//Select option for moving Body
	        bodyFlags[1] = false;//Lock options for rotate Body

		button1.setBackground(Color.lightGray);
		button2.setBackground(col1);

		button1.setSelected(true);
		button2.setSelected(false);


	   drawingLayerPanel.repaint();
     }


     public void rotateBody(){

	        bodyFlags[0] = false;//Lock option for moving Body
	        bodyFlags[1] = true;//Select option for rotate Body

		button1.setBackground(col1);
		button2.setBackground(Color.lightGray);

		button1.setSelected(false);
		button2.setSelected(true);

	   drawingLayerPanel.repaint();
     }


     public void setRendering(){

	   if (renderingButton.isSelected()){
	        bodyFlags[4] = true;//Draw with Rendering
	   } else {
	        bodyFlags[4] = false;//Do not draw with Rendering
	   }

	   drawingLayerPanel.repaint();
     }


     public void showCooSystem(){

	   if (cooCheckButton.isSelected()){
	        bodyFlags[2] = true;//Draw Coo Sysytem
	   } else {
	        bodyFlags[2] = false;//Do not draw Coo System
	   }

	   drawingLayerPanel.repaint();
     }

     public void showColor(){

	   if (colorCheckButton.isSelected()){
	        bodyFlags[3] = true;//Draw Color
	   } else {
	        bodyFlags[3] = false;//Do not draw Color
	   }

	   drawingLayerPanel.repaint();
     }


     public void showEdges(){

	   if (edgesCheckButton.isSelected()){
	        bodyFlags[5] = true;//Draw edges around body 100
	   } else {
	        bodyFlags[5] = false;//Do not draw edges around body 100
	   }

	   drawingLayerPanel.repaint();
     }



     public void settingSolid(){

	bodyFlags[6] = true;
	colorCheckButton.setEnabled(true);
	drawingLayerPanel.repaint();

     }

     public void settingEmpty(){

	bodyFlags[6] = false;
	colorCheckButton.setEnabled(false);
	drawingLayerPanel.repaint();

     }


     public void showMidlePolygon(){

	   if (midlePolygonButton.isSelected()){
	        bodyFlags[7] = true;//Draw Midle Plygon
	   } else {
	        bodyFlags[7] = false;//Do not draw Midle Polygon
	   }

	   drawingLayerPanel.repaint();
     }

     public void showOutSphere(){

	   if (outSphereButton.isSelected()){
	        bodyFlags[9] = true;//Draw Out Sphere
	   } else {
	        bodyFlags[9] = false;//Do not draw Out Sphere
	   }

	   drawingLayerPanel.repaint();
     }

     public void showInSphere(){

	   if (inSphereButton.isSelected()){
	        bodyFlags[10] = true;//Draw Out Sphere
	   } else {
	        bodyFlags[10] = false;//Do not draw Out Sphere
	   }

	   drawingLayerPanel.repaint();
     }

     public void showConture(){

	   if (contureButton.isSelected()){
	        bodyFlags[11] = true;//Draw conture
	   } else {
	        bodyFlags[11] = false;//Do not draw conture
	   }

	   drawingLayerPanel.repaint();
     }


     public void deleteLayer(){

           if (newLayer > 1) {

	   	drawingLayerPanel.remove(0);
	   	drawingLayerPanel.repaint();
	   	vBody.set(iLayerPressed, null);
	        eastPanel.remove((JButton)(vLayerButton.get(iLayerPressed)));
		vLayerButton.set(iLayerPressed, null);

		for (int i = newLayer; i > 0; i--) {
		   if (vBody.get(i - 1) != null) {
	  		setShowingAndChecks(i - 1);
			selectLayer(i);
		        iLayerPressed = i - 1;
			break;
		   } else {
	   		vBody.remove(i - 1);
			vLayerButton.remove(i - 1);
	   		newLayer-= 1;
	   		offset-= 20;
		   }
		}

                eastPanel.repaint();

           } else {

           	drawingLayerPanel.removeAll();
	   	drawingLayerPanel.repaint();
           	eastPanel.removeAll();
	   	eastPanel.repaint();
        	//editShapeButton.setEnabled(false);
		editmode = false;
        	//closeShapeButton.setEnabled(false);
		stopMoving();

	   	newLayer = 0;
	   	offset = 10;

	        vBody.removeAllElements();
		vLayerButton.removeAllElements();

	        bodyFlags[2] = true;
	        bodyFlags[3] = true;
	        bodyFlags[7] = false;

	       cooCheckButton.setSelected(true);
	       colorCheckButton.setSelected(true);

	       sizeSlider.setValue(105);
                stopButton.setEnabled(false);
		toolPanel1.setPreferredSize(new Dimension(210, 550));
		toolPanel1.updateUI();
		tabTools23.setSelectedIndex(0);
		toolPanel1.removeAll();
	        toolPanel1.add(toolPanel10);
		toolPanel1.validate();
		toolPanel1.repaint();

           }

     }


     public void reset() {

	   xAxis[0] = 1;
	   xAxis[1] = 0;
	   xAxis[2] = 0;

	   yAxis[0] = 0;
	   yAxis[1] = 1;
	   yAxis[2] = 0;

	   zAxis[0] = 0;
	   zAxis[1] = 0;
	   zAxis[2] = 1;

	   vBody.removeAllElements();
	   vLayerButton.removeAllElements();

	   drawingLayerPanel.repaint();
	   drawingLayerPanel.removeAll();

	   eastPanel.removeAll();
           eastPanel.repaint();
           //editShapeButton.setEnabled(false);
	   editmode = false;
           //closeShapeButton.setEnabled(false);
	   stopMoving();

	   newLayer = 0;
	   offset = 0;

	   sizeSlider.setValue(105);

           stopButton.setEnabled(false);
	   toolPanel1.setPreferredSize(new Dimension(210, 550));
	   toolPanel1.updateUI();
	   tabTools23.setSelectedIndex(0);
	   toolPanel1.removeAll();
	   toolPanel1.add(toolPanel10);
	   toolPanel1.validate();
	   toolPanel1.repaint();

     }


     public void stopMoving(){

	binterruptX = true;
	binterruptY = true;
	binterruptZ = true;

	bmoveX = true;
	bmoveY = true;
	bmoveZ = true;

	leftXMovButton.setBackground(col1);
	rightXMovButton.setBackground(col1);
	leftYMovButton.setBackground(col1);
	rightYMovButton.setBackground(col1);
	leftZMovButton.setBackground(col1);
	rightZMovButton.setBackground(col1);

        stopButton.setEnabled(false);

	threadRunnerExist = false;

     }


     public void XleftMove(){

	   directionX = 1;

	   if (bmoveX) {

		//Set body to move
		binterruptX = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}	 	
	  	leftXMovButton.setBackground(Color.lightGray);
		rightXMovButton.setBackground(col1);
		bmoveX = false;

	    } else {

		//Stop moving
		leftXMovButton.setBackground(col1);
		rightXMovButton.setBackground(col1);
		binterruptX = true;
		bmoveX = true;

	    }

     }

     public void XrightMove(){

	   directionX = -1;

	   if (bmoveX) {
		binterruptX = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}
	  	rightXMovButton.setBackground(Color.lightGray);
		leftXMovButton.setBackground(col1);
		bmoveX = false;
	    } else {
		rightXMovButton.setBackground(col1);
		leftXMovButton.setBackground(col1);
		binterruptX = true;
		bmoveX = true;
	    }
     }


     public void YleftMove(){

	   directionY = -1;

	   if (bmoveY) {

		binterruptY = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}
	  	leftYMovButton.setBackground(Color.lightGray);
		rightYMovButton.setBackground(col1);
		bmoveY = false;
	    } else {
		leftYMovButton.setBackground(col1);
		rightYMovButton.setBackground(col1);
		binterruptY = true;
		bmoveY = true;
	    }
     }


     public void YrightMove(){

	   directionY = 1;

	   if (bmoveY) {

		binterruptY = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}
	  	rightYMovButton.setBackground(Color.lightGray);
		leftYMovButton.setBackground(col1);
		bmoveY = false;

	    } else {

		leftYMovButton.setBackground(col1);
		rightYMovButton.setBackground(col1);
		binterruptY = true;
		bmoveY = true;
	    }
     }


     public void ZleftMove(){

	   directionZ = 1;

	   if (bmoveZ) {

		binterruptZ = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}
	  	leftZMovButton.setBackground(Color.lightGray);
		rightZMovButton.setBackground(col1);
		bmoveZ = false;

	    } else {

		leftZMovButton.setBackground(col1);
		rightZMovButton.setBackground(col1);
		binterruptZ = true;
		bmoveZ = true;
	    }
     }


     public void ZrightMove(){

	   directionZ = -1;

	   if (bmoveZ) {

		binterruptZ = false;
	        if (!threadRunnerExist){(new Thread(new MoveXRunnable())).start();}
	  	rightZMovButton.setBackground(Color.lightGray);
		leftZMovButton.setBackground(col1);
		bmoveZ = false;

	    } else {

		leftZMovButton.setBackground(col1);
		rightZMovButton.setBackground(col1);
		binterruptZ = true;
		bmoveZ = true;
	    }
     }


     public class MoveXRunnable implements Runnable {
	public void run() {

	   threadRunnerExist = true;

	   while (!binterruptX || !binterruptY || !binterruptZ) {
		pause(speed);
	   	if (!binterruptX) {stepXaxe();}
	   	if (!binterruptY) {stepYaxe();}
	   	if (!binterruptZ) {stepZaxe();}
	   	drawingLayerPanel.repaint();
	   }

	   threadRunnerExist = false;

	}
     }


    private void pause(int time) {
	try { Thread.sleep(time); }
	catch(InterruptedException e) { dbi(""+e); }				
    }

    private void dbi(String s) {
	//if(debug) System.out.println(s);
    }


     public void stepXaxe() {

		tempX[0] = xAxis[0];
		tempX[1] = yAxis[0];
		tempX[2] = zAxis[0];
		tempY[0] = xAxis[1];
		tempY[1] = yAxis[1];
		tempY[2] = zAxis[1];
		tempZ[0] = xAxis[2];
		tempZ[1] = yAxis[2];
		tempZ[2] = zAxis[2];

		copyVec(tempZ,0,temp,0);
		scalMult(temp,0,directionX*step*0.016);
		addVec(temp,0,tempY,0);
		vecProd(tempX,0,tempY,0,tempZ,0);
		normalize(tempZ,0);
		normalize(tempY,0);

		xAxis[0] = tempX[0];
		yAxis[0] = tempX[1];
		zAxis[0] = tempX[2];
		xAxis[1] = tempY[0];
		yAxis[1] = tempY[1];
		zAxis[1] = tempY[2];
		xAxis[2] = tempZ[0];
		yAxis[2] = tempZ[1];
		zAxis[2] = tempZ[2];
     }

     public void stepYaxe() {

		tempX[0] = xAxis[0];
		tempX[1] = yAxis[0];
		tempX[2] = zAxis[0];
		tempY[0] = xAxis[1];
		tempY[1] = yAxis[1];
		tempY[2] = zAxis[1];
		tempZ[0] = xAxis[2];
		tempZ[1] = yAxis[2];
		tempZ[2] = zAxis[2];

		copyVec(tempX,0,temp,0);
		scalMult(temp,0,directionY*step*0.016);
		addVec(temp,0,tempZ,0);
		vecProd(tempY,0,tempZ,0,tempX,0);
		normalize(tempX,0);
		normalize(tempZ,0);

		xAxis[0] = tempX[0];
		yAxis[0] = tempX[1];
		zAxis[0] = tempX[2];
		xAxis[1] = tempY[0];
		yAxis[1] = tempY[1];
		zAxis[1] = tempY[2];
		xAxis[2] = tempZ[0];
		yAxis[2] = tempZ[1];
		zAxis[2] = tempZ[2];
     }


     public void stepZaxe() {

		tempX[0] = xAxis[0];
		tempX[1] = yAxis[0];
		tempX[2] = zAxis[0];
		tempY[0] = xAxis[1];
		tempY[1] = yAxis[1];
		tempY[2] = zAxis[1];
		tempZ[0] = xAxis[2];
		tempZ[1] = yAxis[2];
		tempZ[2] = zAxis[2];

		copyVec(tempY,0,temp,0);
		scalMult(temp,0,directionZ*step*0.016);
		addVec(temp,0,tempX,0);
		vecProd(tempZ,0,tempX,0,tempY,0);
		normalize(tempY,0);
		normalize(tempX,0);

		xAxis[0] = tempX[0];
		yAxis[0] = tempX[1];
		zAxis[0] = tempX[2];
		xAxis[1] = tempY[0];
		yAxis[1] = tempY[1];
		zAxis[1] = tempY[2];
		xAxis[2] = tempZ[0];
		yAxis[2] = tempZ[1];
		zAxis[2] = tempZ[2];
     }


	public double scal3(double v1,double v2)
	{
	    return m.sqrt(1 - v1*v1 - v2*v2);
	}


// Various vector manipulation functions

	public double scalProd(double v1[],int ix1,double v2[],int ix2)
	{
		return v1[ix1]*v2[ix2]+v1[ix1+1]*v2[ix2+1]+v1[ix1+2]*v2[ix2+2];
	}

	public double vNorm(double v[],int ix)
	{
		return m.sqrt(v[ix]*v[ix]+v[ix+1]*v[ix+1]+v[ix+2]*v[ix+2]);
	}

	public double cosAng(double v1[],int ix1,double v2[],int ix2)
	{
		return scalProd(v1,ix1,v2,ix2)/(vNorm(v1,ix1)*vNorm(v2,ix2));
	}

	public void normalize(double v[], int ix)
	{
		double t=vNorm(v,ix);
		v[ix]=v[ix]/t;
		v[ix+1]=v[ix+1]/t;
		v[ix+2]=v[ix+2]/t;
	}

	public void scalMult(double v[], int ix,double a)
	{
		v[ix]=v[ix]*a;
		v[ix+1]=v[ix+1]*a;
		v[ix+2]=v[ix+2]*a;
	}

	public void addVec(double v1[], int ix1,double v2[],int ix2)
	{
		v2[ix2]+=v1[ix1];
		v2[ix2+1]+=v1[ix1+1];
		v2[ix2+2]+=v1[ix1+2];
	}

	public void subVec(double v1[], int ix1,double v2[],int ix2)
	{
		v2[ix2]-=v1[ix1];
		v2[ix2+1]-=v1[ix1+1];
		v2[ix2+2]-=v1[ix1+2];
	}

	public void copyVec(double v1[], int ix1,double v2[],int ix2)
	{
		v2[ix2]=v1[ix1];
		v2[ix2+1]=v1[ix1+1];
		v2[ix2+2]=v1[ix1+2];
	}

	public void vecProd(double v1[],int ix1,double v2[],int ix2,
				double v3[],int ix3)
	{
		v3[ix3]=v1[ix1+1]*v2[ix2+2]-v1[ix1+2]*v2[ix2+1];
		v3[ix3+1]=v1[ix1+2]*v2[ix2]-v1[ix1]*v2[ix2+2];
		v3[ix3+2]=v1[ix1]*v2[ix2+1]-v1[ix1+1]*v2[ix2];
	}


    public static void main (String[] args) {
	Bodies3D1start b3d = new Bodies3D1start();

    }

}