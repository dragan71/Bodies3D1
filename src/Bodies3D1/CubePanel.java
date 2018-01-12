package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class CubePanel extends BodyPanel {


	//Coo System fields ---------------------------------------------
	// 3D cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.5,0, 0,0,1.5, -1.5,0,0, 0,-1.5,0, 0,0,-1.5};

	// 2D cordinates (x,y,-x,-y)
	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners


	//Cube fields-----------------------------------------------------------
	//Cube 3D cordinates
	//final double cubeCorners[]=
	//{-1,-1,-1, 1,-1,-1, 1,1,-1, -1,1,-1, -1,-1,1, 1,-1,1, 1,1,1, -1,1,1};


	//Cube 2D cordinates
	double cube2D[]; //double 2D coordinates for cube corners
	int rectX[],rectY[];//integer 2D coordinates for cube corners

	//Cube Normal vectors
	final double normalVectors[]={0,0,1, 0,0,-1, 0,-1,0, 1,0,0, 0,1,0, -1,0,0};
	final int sides[]={4,5,6,7,3,2,1,0,0,1,5,4,1,2,6,5,2,3,7,6,0,4,7,3};
	
	Graphics2D offGraphics;


    public CubePanel() {

	// coo system - Projected co-ordinates
	newCoo2D=new double[12]; 
	line=new int[12];

	//3D coordinates for Cube
	defaultBodyCorners1[0]= -1; 	bodyCorners1[0]= -1;
	defaultBodyCorners1[1]= -1; 	bodyCorners1[1]= -1;
	defaultBodyCorners1[2]= -1; 	bodyCorners1[2]= -1;

	defaultBodyCorners1[3]=  1; 	bodyCorners1[3]=  1;
	defaultBodyCorners1[4]= -1; 	bodyCorners1[4]= -1;
	defaultBodyCorners1[5]= -1; 	bodyCorners1[5]= -1;

	defaultBodyCorners1[6]=  1; 	bodyCorners1[6]=  1;
	defaultBodyCorners1[7]=  1; 	bodyCorners1[7]=  1;
	defaultBodyCorners1[8]= -1; 	bodyCorners1[8]= -1;

	defaultBodyCorners1[9]=  -1; 	bodyCorners1[9]=  -1;
	defaultBodyCorners1[10]=  1; 	bodyCorners1[10]=  1;
	defaultBodyCorners1[11]= -1; 	bodyCorners1[11]= -1;

	defaultBodyCorners1[12]= -1; 	bodyCorners1[12]= -1;
	defaultBodyCorners1[13]= -1; 	bodyCorners1[13]= -1;
	defaultBodyCorners1[14]=  1; 	bodyCorners1[14]=  1;

	defaultBodyCorners1[15]=  1; 	bodyCorners1[15]=  1;
	defaultBodyCorners1[16]= -1; 	bodyCorners1[16]= -1;
	defaultBodyCorners1[17]=  1; 	bodyCorners1[17]=  1;

	defaultBodyCorners1[18]=  1; 	bodyCorners1[18]=  1;
	defaultBodyCorners1[19]=  1; 	bodyCorners1[19]=  1;
	defaultBodyCorners1[20]=  1; 	bodyCorners1[20]=  1;

	defaultBodyCorners1[21]= -1; 	bodyCorners1[21]= -1;
	defaultBodyCorners1[22]=  1; 	bodyCorners1[22]=  1;
	defaultBodyCorners1[23]=  1; 	bodyCorners1[23]=  1;


	// cube - projected co-ordinates
	cube2D=new double[16];
	rectX=new int[4];
	rectY=new int[4];

	//number of points used to draw a body
	dblSize1[2] = 8;

	//name of a body
	strBody[0] = "CUBE";

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


	//Cube: Project 3D co-ordinates into 2D
	for (i=0;i<8;i++) {
	    cube2D[i*2]=(origin.x+dblSize1[1]*scalProd(bbodyCorners,i*3,beX,0));
	    cube2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bbodyCorners,i*3,beY,0));
	}


	    //Draw solid Cube
	    if (bodyFlags1[6]) {
	      for (i=0;i<6;i++){
		    
		if (scalProd(beZ,0,normalVectors,3*i)>0.001) { // Face towards us? Draw it.
 
		    for (j=0;j<4;j++) { // Find corner co-ordinates 	
			rectX[j]=(int)cube2D[2*sides[i*4+j]];
			rectY[j]=(int)cube2D[2*sides[i*4+j]+1];
		    }

		    //Drawing color or not
		    if (bodyFlags1[3]) {
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }

		    offGraphics.fillPolygon(rectX,rectY,4);
		    offGraphics.setPaint(Color.black);
		    offGraphics.drawPolygon(rectX,rectY,4);
		}
	      }

	    
	    } else {//Draw empty cube
	      for (i=0;i<6;i++){
		    
		for (j=0;j<4;j++){ // Find corner co-ordinates 
		    rectX[j]=(int)cube2D[2*sides[i*4+j]];
		    rectY[j]=(int)cube2D[2*sides[i*4+j]+1];
		}

		//offGraphics.setPaint(cDEV[i]);
		offGraphics.drawPolygon(rectX,rectY,4);
	      }

		//Draw Midle Polygon
		if (bodyFlags1[7]) {

		    rectX[0]=(int)cube2D[2];
		    rectY[0]=(int)cube2D[3];

		    rectX[1]=(int)cube2D[4];
		    rectY[1]=(int)cube2D[5];

		    rectX[2]=(int)cube2D[14];
		    rectY[2]=(int)cube2D[15];

		    rectX[3]=(int)cube2D[8];
		    rectY[3]=(int)cube2D[9];

		    offGraphics.drawPolygon(rectX,rectY,4);

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
	    int pomX = (int)(cube2D[pom1]);
	    int pomY = (int)(cube2D[pom2]);

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

	//Drawing In Sphere
	if (bodyFlags1[10]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - dblSize1[1],
	      origin.y - dblSize1[1],
	      2*dblSize1[1], 
	      2*dblSize1[1]));
	}

	//Draw Conture
	if (bodyFlags1[11]) {
	      for (i=0;i<6;i++){
		    
		for (j=0;j<4;j++){ // Find corner co-ordinates 
		    rectX[j]=(int)cube2D[2*sides[i*4+j]];
		    rectY[j]=(int)cube2D[2*sides[i*4+j]+1];
		}

		//offGraphics.setPaint(cDEV[i]);
		offGraphics.drawPolygon(rectX,rectY,4);
	      }
	}


    }//End of fixBlock

}//End of CubePanel
