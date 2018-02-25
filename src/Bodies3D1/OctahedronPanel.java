package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class OctahedronPanel extends BodyPanel {


	//Coo System fields ---------------------------------------------
	// 3D cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.7,0, 0,0,1.5, -1.5,0,0, 0,-1.7,0, 0,0,-1.5};

	// 2D cordinates (x,y,-x,-y)
	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners
	int normals2D[];    //integer 2D coordinates for Normal Vectors

	//Octahedron fields-----------------------------------------------------------
	double h = Math.sqrt(2);
	double xprim = (double)2/(double)3;
	double yprim = h/3;

	double a1 = (double)2;//duzina osnove
	double Ru = (Math.sqrt(2)/(double)2)*a1;//poluprecnik opisanog kruga
	double Ri = (Math.sqrt(6)/(double)6)*a1;//poluprecnik upisanog kruga

	//Octahedron 2D cordinates
	double octahedron2D[]; //double 2D coordinates for cube corners
	int triangleX[],triangleY[];//integer 2D coordinates for Octahedron corners

	//Octahedron Normal vectors

	final double sideVec1[]= // Normal vectors
	{0,yprim,xprim, xprim,yprim,0, 0,yprim,-xprim, -xprim,yprim,0,
	 0,-yprim,xprim, xprim,-yprim,0, 0,-yprim,-xprim, -xprim,-yprim,0};


	final int sides1[]={0,2,8, 2,4,8, 4,6,8, 6,0,8, 0,2,10, 2,4,10, 4,6,10, 6,0,10};

	
	Graphics2D offGraphics;


    public OctahedronPanel() {

	//Coo system - Projected co-ordinates
	newCoo2D=new double[12]; 
	line=new int[12];
	normals2D=new int[16];

	defaultBodyCorners1[0]= -1; 	bodyCorners1[0]= -1;
	defaultBodyCorners1[1]=  0; 	bodyCorners1[1]=  0;
	defaultBodyCorners1[2]=  1; 	bodyCorners1[2]=  1;

	defaultBodyCorners1[3]=  1; 	bodyCorners1[3]=  1;
	defaultBodyCorners1[4]=  0; 	bodyCorners1[4]=  0;
	defaultBodyCorners1[5]=  1; 	bodyCorners1[5]=  1;

	defaultBodyCorners1[6]=  1; 	bodyCorners1[6]=  1;
	defaultBodyCorners1[7]=  0; 	bodyCorners1[7]=  0;
	defaultBodyCorners1[8]= -1; 	bodyCorners1[8]= -1;

	defaultBodyCorners1[9]=  -1; 	bodyCorners1[9]=  -1;
	defaultBodyCorners1[10]=  0; 	bodyCorners1[10]=  0;
	defaultBodyCorners1[11]= -1; 	bodyCorners1[11]= -1;

	defaultBodyCorners1[12]=  0; 	bodyCorners1[12]=  0;
	defaultBodyCorners1[13]=  h; 	bodyCorners1[13]=  h;
	defaultBodyCorners1[14]=  0; 	bodyCorners1[14]=  0;

	defaultBodyCorners1[15]=  0; 	bodyCorners1[15]=  0;
	defaultBodyCorners1[16]= -h; 	bodyCorners1[16]= -h;
	defaultBodyCorners1[17]=  0; 	bodyCorners1[17]=  0;



	//Octahedron - projected co-ordinates
	octahedron2D=new double[12];
	triangleX=new int[3];
	triangleY=new int[3];

	//number of points used to draw a body
	dblSize1[2] = 6;

	//name of a body
	strBody[0] = "OCTAHEDRON";

    }


    public void paintComponent(Graphics g)	{

	offGraphics = (Graphics2D)g;
	setOpaque(false);//ovim se obezbedjuje da je pozadina providna
        setBorder(BorderFactory.createEmptyBorder());

	if (bodyFlags1[4]) {//Set Rendering
            offGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
	}

	fixBlock(eZ,eX,eY,cooCorners,bodyCorners1, sideVec1);

    }


    public void fixBlock(double beZ[],double beX[],double beY[],
			     double bcooCorners[], double bbodyCorners[], double bsideVec1[])
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


	//Octahedron: Project 3D co-ordinates into 2D
	for (i=0;i<6;i++) {
	    octahedron2D[i*2]=(origin.x+dblSize1[1]*scalProd(bbodyCorners,i*3,beX,0));
	    octahedron2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bbodyCorners,i*3,beY,0));
	}




	//Normal Vectors: Project 3D co-ordinates into 2D
	for (i=0;i<8;i++) {
	    normals2D[i*2]=(int)(origin.x+dblSize1[1]*scalProd(bsideVec1,i*3,beX,0));
	    normals2D[i*2+1]=(int)(origin.y-dblSize1[1]*scalProd(bsideVec1,i*3,beY,0));
	}


	//Drawing Octahedron
	if (bodyFlags1[6]) {//Draw solid Octahedron
	//if (false) {//Draw solid Octahedron

	
	    for (i=0;i<8;i++) {
		    
		if (scalProd(beZ,0,sideVec1,3*i)>0.001) {// Face towards us? Draw it. 
			
		    for (j=0;j<3;j++) {// Find corner co-ordinates 
				
			triangleX[j]=(int)octahedron2D[sides1[i*3+j]];
			triangleY[j]=(int)octahedron2D[sides1[i*3+j]+1];

		    }

		    if (bodyFlags1[3]) {//Drawing color or not
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }
					
		    offGraphics.fillPolygon(triangleX,triangleY,3);
		    offGraphics.setPaint(Color.black);
		    offGraphics.drawPolygon(triangleX,triangleY,3);

		}

	    }



	//Draw empty Octahedron
	} else	 {

	    for (i=0;i<8;i++) {
		     
		for (j=0;j<3;j++) {// Find corner co-ordinates 
		    triangleX[j]=(int)octahedron2D[sides1[i*3+j]];
		    triangleY[j]=(int)octahedron2D[sides1[i*3+j]+1];
		}

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(triangleX,triangleY,3);

	    }


	    //Draw Midle Polygon
	    if (bodyFlags1[7]) {


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
	    int pomX = (int)(octahedron2D[pom1]);
	    int pomY = (int)(octahedron2D[pom2]);

	    offGraphics.drawOval(pomX-13,pomY-13,26,26);

	}

	//Drawing Out Sphere
	if (bodyFlags1[9]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - Ru*dblSize1[1],
	      origin.y - Ru*dblSize1[1],
	      2*Ru*dblSize1[1], 
	      2*Ru*dblSize1[1]));
	}


	//Drawing In Sphere
	if (bodyFlags1[10]) {
	   offGraphics.draw(new Ellipse2D.Double(
	      origin.x - Ri*dblSize1[1],
	      origin.y - Ri*dblSize1[1],
	      2*Ri*dblSize1[1], 
	      2*Ri*dblSize1[1]));
	}


	//Draw contures
	if (bodyFlags1[11]) {
	    for (i=0;i<8;i++) { 
		for (j=0;j<3;j++) {// Find corner co-ordinates 
		    triangleX[j]=(int)octahedron2D[sides1[i*3+j]];
		    triangleY[j]=(int)octahedron2D[sides1[i*3+j]+1];
		}
		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(triangleX,triangleY,3);
	    }

	}


    }//End of fixBlock

}//End of OctahedronPanel
