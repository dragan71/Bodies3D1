package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class CubesegmentPanel extends BodyPanel {


	//Coo System fields -----------------------------------------
	//coo cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.5,0, 0,0,1.5, -1.5,0,0, 0,-1.5,0, 0,0,-1.5};

	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners


	//Cube segment fields----------------------------------------
	//Cube segment 3D cordinates
	//final double cubesegCorners[]=
	//{-1,-1,1, 1,-1,1, -1,-1,-1, -1,1,1,};

	//Cube segment 2D cordinates
	double newCubeseg2D[]; //double 2D coordinates for cube corners
	int triangleX[],triangleY[];//integer 2D coordinates for tetrahedron corners

	//Cube segment Normal vectors
	final double sideVec1[]={0,-1,0, 1,1,-1, -1,0,0, 0,0,1}; // Normal vectors
	final int sides1[]={0,2,4, 2,4,6, 4,6,0, 6,0,2};



	Graphics2D offGraphics;


    public CubesegmentPanel() {

	// coo system - Projected co-ordinates
	newCoo2D=new double[12]; 
	line=new int[12];

	defaultBodyCorners1[0]= -1; 	bodyCorners1[0]= -1;
	defaultBodyCorners1[1]= -1; 	bodyCorners1[1]= -1;
	defaultBodyCorners1[2]=  1; 	bodyCorners1[2]=  1;

	defaultBodyCorners1[3]=  1; 	bodyCorners1[3]=  1;
	defaultBodyCorners1[4]= -1; 	bodyCorners1[4]= -1;
	defaultBodyCorners1[5]=  1; 	bodyCorners1[5]=  1;

	defaultBodyCorners1[6]= -1; 	bodyCorners1[6]= -1;
	defaultBodyCorners1[7]= -1; 	bodyCorners1[7]= -1;
	defaultBodyCorners1[8]= -1; 	bodyCorners1[8]= -1;

	defaultBodyCorners1[9]=  -1; 	bodyCorners1[9]=  -1;
	defaultBodyCorners1[10]=  1; 	bodyCorners1[10]=  1;
	defaultBodyCorners1[11]=  1; 	bodyCorners1[11]=  1;


	// Cube segment - Projected co-ordinates
	newCubeseg2D=new double[8]; 
	triangleX=new int[3];
	triangleY=new int[3];

	//number of points used to draw a body
	dblSize1[2] = 4;

    }


    public void paintComponent(Graphics g) {

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
			     double bcooCorners[], double bcubesegCorners[])
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


	//Cube segment: Project 3D co-ordinates into 2D
	for (i=0;i<4;i++) {

	    newCubeseg2D[i*2]=(origin.x+dblSize1[1]*scalProd(bcubesegCorners,i*3,beX,0));
	    newCubeseg2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bcubesegCorners,i*3,beY,0));

	}


	//Drawing Cube segment
	if (bodyFlags1[6]) {
		
	    for (i=0;i<4;i++) {
		    
		if (scalProd(beZ,0,sideVec1,3*i)>0.001) { // Face towards us? Draw it.
			
		    for (j=0;j<3;j++) {// Find corner co-ordinates 	
			triangleX[j]=(int)newCubeseg2D[sides1[i*3+j]];
			triangleY[j]=(int)newCubeseg2D[sides1[i*3+j]+1];
		    }

		    //Drawing color or not
		    if (bodyFlags1[3]) {
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }
				
		    offGraphics.fillPolygon(triangleX,triangleY,3);
		    offGraphics.setPaint(Color.black);									    		    offGraphics.drawPolygon(triangleX,triangleY,3);

		}

	    }

	//Draw empty cube segment
	} else {
		 
	    for (i=0;i<4;i++) {
		     
		for (j=0;j<3;j++) {// Find corner co-ordinates 
		    triangleX[j]=(int)newCubeseg2D[sides1[i*3+j]];
		    triangleY[j]=(int)newCubeseg2D[sides1[i*3+j]+1];
		}

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(triangleX,triangleY,3);

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
	    int pomX = (int)(newCubeseg2D[pom1]);
	    int pomY = (int)(newCubeseg2D[pom2]);

	    offGraphics.drawOval(pomX-13,pomY-13,26,26);

	}

	//Drawing Out Sphere
	if (bodyFlags1[9]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - (Math.sqrt(3))*dblSize1[1],
	      origin.y - (Math.sqrt(3))*dblSize1[1],
	      (2*Math.sqrt(3))*dblSize1[1], 
	      (2*Math.sqrt(3))*dblSize1[1]));
	}


    }//End of fixBlock

}//End of CubesegmentPanel

