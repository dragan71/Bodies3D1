package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class TetrahedronPanel extends BodyPanel {


	//Coo System fields ---------------------------------------------
	// 3D cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.5,0, 0,0,1.5, -1.5,0,0, 0,-1.5,0, 0,0,-1.5};

	// 2D cordinates (x,y,-x,-y)
	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners


	//Tetrahedron fields-----------------------------------------------------------
	double a = 2;
	double sRadius = a/Math.sqrt(24);
	double lRadius = 3*sRadius;
	double height13 = a/(2*Math.sqrt(3));
	double height23 = 2*a/(2*Math.sqrt(3));

	//tetrahedron 3D cordinates
	//final double tetrahCorners[]=
	//{-a/2,-sRadius,height13,  a/2,-sRadius,height13,  0,-sRadius,-height23,  0,lRadius,0,
	//0,-sRadius,height13, 0,-sRadius,0};

	//tetrahedron 2D cordinates
	double tetrahedron2D[]; //double 2D coordinates for tetrahedron corners
	int rectX[],rectY[];//integer 2D coordinates for tetrahedron corners

	final double normalVectors[]=
	{0,-1,0, 1,sRadius,-height13, -1,sRadius,-height13,  0,sRadius,height23,};

	final int sides1[]={0,2,4, 2,4,6, 4,6,0, 6,0,2};


	Graphics2D offGraphics;


    public TetrahedronPanel() {
	
	// coo system - Projected co-ordinates
	newCoo2D=new double[12];
	line=new int[12];

	//3D coordinates for tetrahedron
	defaultBodyCorners1[0]= -a/2; 		bodyCorners1[0]= -a/2;
	defaultBodyCorners1[1]= -sRadius; 	bodyCorners1[1]= -sRadius;
	defaultBodyCorners1[2]= height13; 	bodyCorners1[2]= height13;

	defaultBodyCorners1[3]=  a/2; 		bodyCorners1[3]=  a/2;
	defaultBodyCorners1[4]= -sRadius; 	bodyCorners1[4]= -sRadius;
	defaultBodyCorners1[5]= height13; 	bodyCorners1[5]= height13;

	defaultBodyCorners1[6]=  0; 		bodyCorners1[6]=  0;
	defaultBodyCorners1[7]=  -sRadius; 	bodyCorners1[7]=  -sRadius;
	defaultBodyCorners1[8]= -height23; 	bodyCorners1[8]= -height23;

	defaultBodyCorners1[9]=  0; 		bodyCorners1[9]=  0;
	defaultBodyCorners1[10]=  lRadius; 	bodyCorners1[10]=  lRadius;
	defaultBodyCorners1[11]= 0; 		bodyCorners1[11]= 0;

	defaultBodyCorners1[12]= 0; 		bodyCorners1[12]= 0;
	defaultBodyCorners1[13]= -sRadius; 	bodyCorners1[13]= -sRadius;
	defaultBodyCorners1[14]=  height13; 	bodyCorners1[14]=  height13;

	defaultBodyCorners1[15]=  0; 		bodyCorners1[15]=  0;
	defaultBodyCorners1[16]= -sRadius; 	bodyCorners1[16]= -sRadius;
	defaultBodyCorners1[17]=  0; 		bodyCorners1[17]=  0;


	// Tetrahedron - Projected co-ordinates
	tetrahedron2D=new double[12];
	rectX=new int[3];
	rectY=new int[3];

	//number of points used to draw a body
	dblSize1[2] = 4;

	//name of a body
	strBody[0] = "TETRAHEDRON";

    }



    public void paintComponent(Graphics g)	{

	offGraphics = (Graphics2D)g;
	setOpaque(false);//ovim se obezbedjuje da je pozadina providna
        setBorder(BorderFactory.createEmptyBorder());

	if (bodyFlags1[4]) {//Set Rendering
            offGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
	}

	fixBlock(eZ,eX,eY,cooCorners,bodyCorners1);

    }


    public void fixBlock(double beZ[],double beX[],double beY[],
			     double bcooCorners[], double bbodyCorners[])
    {

	//Coo-system: Project 3D co-ordinates into 2D
	for (i=0;i<6;i++) { 
		
	    //double values
	    newCoo2D[i*2]=(origin.x+dblSize1[1]*scalProd(bcooCorners,i*3,beX,0));
	    newCoo2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bcooCorners,i*3,beY,0));
			
	    //integer values
	    line[i*2]=(int)newCoo2D[i*2];
	    line[i*2+1]=(int)newCoo2D[i*2+1];
	}

	//Tetrahedron: Project 3D co-ordinates into 2D
	for (i=0;i<6;i++) {
		
	    tetrahedron2D[i*2]=(origin.x+dblSize1[1]*scalProd(bbodyCorners,i*3,beX,0));
	    tetrahedron2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bbodyCorners,i*3,beY,0));
	}


	//Drawing Tetrahedron
	if (bodyFlags1[6]) {//Draw solid tetrahedron
		
	    for (i=0;i<4;i++) {
		    
		if (scalProd(beZ,0,normalVectors,3*i)>0.001) {// Face towards us? Draw it. 
			
		    for (j=0;j<3;j++) {// Find corner co-ordinates 
				
			rectX[j]=(int)tetrahedron2D[sides1[i*3+j]];
			rectY[j]=(int)tetrahedron2D[sides1[i*3+j]+1];

		    }

		    if (bodyFlags1[3]) {//Drawing color or not
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }
					
		    offGraphics.fillPolygon(rectX,rectY,3);
		    offGraphics.setPaint(Color.black);										    		    offGraphics.drawPolygon(rectX,rectY,3);

		}

	    }

	//Draw empty tetrahedron
	} else	 {

	    for (i=0;i<4;i++) {
		     
		for (j=0;j<3;j++) {// Find corner co-ordinates 

		    rectX[j]=(int)tetrahedron2D[sides1[i*3+j]];
		    rectY[j]=(int)tetrahedron2D[sides1[i*3+j]+1];

		}

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX,rectY,3);
	    }


		//Draw Midle Polygon
		if (bodyFlags1[7]) {

		    rectX[0]=(int)tetrahedron2D[4];
		    rectY[0]=(int)tetrahedron2D[5];

		    rectX[1]=(int)tetrahedron2D[6];
		    rectY[1]=(int)tetrahedron2D[7];

		    rectX[2]=(int)tetrahedron2D[8];
		    rectY[2]=(int)tetrahedron2D[9];

		    offGraphics.setPaint(Color.black);
		    offGraphics.drawPolygon(rectX,rectY,3);

		}

	}


	//Draw Coo System
	if (bodyFlags1[2]) {
		
	    for (i=0;i<3;i++) {
		offGraphics.setPaint(Color.black);
		offGraphics.drawLine(origin.x,origin.y,line[2*i],line[2*i+1]);
	    }

	    for (i=3;i<6;i++) {
		offGraphics.setPaint(Color.black);
		offGraphics.drawLine(origin.x,origin.y,line[2*i],line[2*i+1]);
	    }

	}


	//Drawing edges around or not
	if (bodyFlags1[5]) {

	    offGraphics.drawRect(
		origin.x - (int)(1.6*dblSize1[1]),
		origin.y - (int)(1.6*dblSize1[1]),
		(int)(3.2*dblSize1[1]),
		(int)(3.2*dblSize1[1]));

	}

	//Edit mode - drawing circles around points
	if (bodyFlags1[8]) {

	    int pom1 = (int)(2*dblSize1[3] - 2);
	    int pom2 = (int)(2*dblSize1[3] - 1);
	    int pomX = (int)(tetrahedron2D[pom1]);
	    int pomY = (int)(tetrahedron2D[pom2]);

	    offGraphics.drawOval(pomX-13,pomY-13,26,26);

	}

	//Drawing Out Sphere
	if (bodyFlags1[9]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - lRadius*dblSize1[1],
	      origin.y - lRadius*dblSize1[1],
	      2*lRadius*dblSize1[1], 
	      2*lRadius*dblSize1[1]));
	}

	//Drawing In Sphere
	if (bodyFlags1[10]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - sRadius*dblSize1[1],
	      origin.y - sRadius*dblSize1[1],
	      2*sRadius*dblSize1[1], 
	      2*sRadius*dblSize1[1]));
	}

	//Draw Conture
	if (bodyFlags1[11]) {
	    for (i=0;i<4;i++) {
		     
		for (j=0;j<3;j++) {// Find corner co-ordinates 

		    rectX[j]=(int)tetrahedron2D[sides1[i*3+j]];
		    rectY[j]=(int)tetrahedron2D[sides1[i*3+j]+1];

		}

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX,rectY,3);
	    }
	}


    }//End of fixBlock

}//End of TetrahedronPanel
