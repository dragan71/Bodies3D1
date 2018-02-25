package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class IcosahedronPanel extends BodyPanel {


	//Coo System fields ---------------------------------------------
	// 3D cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.5,0, 0,0,1.5, -1.5,0,0, 0,-1.5,0, 0,0,-1.5};

	// 2D cordinates (x,y,-x,-y)
	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners


	//Dodecahedron fields-----------------------------------------------------------
	double f1 = ((double)1 + Math.sqrt(5))/(double)2;//koordinate
	//double f2 = (double)1/f1;//koordinate

	double a1 = (double)2;//duzina osnove
	double Ru = Math.sqrt((double)10+(double)2*Math.sqrt(5))*a1/(double)4;//poluprecnik opisanog kruga
	double Ri = Math.sqrt(3)*(Math.sqrt(5)+(double)3)*a1/(double)12;//poluprecnik upisanog kruga
	double Rm = (Math.sqrt(5)+(double)1)*a1/(double)4;//poluprecnik srednjeg kruga


	double yprim = Math.sqrt(f1*f1-Ri*Ri)*Ri/f1;//2 koordinata normale
	double xprim = Math.sqrt(Ri*Ri - yprim*yprim);//1 koordinata normale


	//Dodecahedron 2D cordinates
	double icosahedron2D[];//double 2D coordinates for Dodecahedron corners
	int rectX[],rectY[];//integer 2D coordinates for Cube corners
	int rectX3[],rectY3[];//integer 2D coordinates for Icosahedron corners

	//Dodecahedron Normal vectors
	final double normalVectors[]={0,yprim,xprim, 0,-yprim,xprim, 0,yprim,-xprim, 0,-yprim,-xprim,
				      xprim,0,yprim, xprim,0,-yprim, -xprim,0,yprim, -xprim,0,-yprim,
				      -yprim,-xprim,0, yprim,-xprim,0, -yprim,xprim,0, yprim,xprim,0,
				      1,1,1, 1,-1,1, 1,1,-1, 1,-1,-1, -1,1,1, -1,-1,1, -1,1,-1, -1,-1,-1};

	final int sides1[]={0,2,22, 0,2,16, 4,6,20, 4,6,18, 
			    12,14,2, 12,14,4, 8,10,0, 8,10,6,
			    16,18,10, 16,18,12, 20,22,8, 20,22,14, 
			    2,14,22, 2,12,16, 4,20,14, 4,18,12,
			    0,22,8, 0,16,10, 6,20,8, 6,18,10};
	

	Graphics2D offGraphics;


    public IcosahedronPanel() {

	// coo system - Projected co-ordinates
	newCoo2D=new double[12]; 
	line=new int[12];

	//3D coordinates for Icosahedron
	defaultBodyCorners1[0]= -1; 	bodyCorners1[0]= -1;
	defaultBodyCorners1[1]=  0; 	bodyCorners1[1]=  0;
	defaultBodyCorners1[2]= f1; 	bodyCorners1[2]= f1;

	defaultBodyCorners1[3]=  1; 	bodyCorners1[3]=  1;
	defaultBodyCorners1[4]=  0; 	bodyCorners1[4]=  0;
	defaultBodyCorners1[5]= f1; 	bodyCorners1[5]= f1;

	defaultBodyCorners1[6]=  1; 	bodyCorners1[6]=  1;
	defaultBodyCorners1[7]=  0; 	bodyCorners1[7]=  0;
	defaultBodyCorners1[8]=-f1; 	bodyCorners1[8]=-f1;

	defaultBodyCorners1[9]=  -1; 	bodyCorners1[9]=  -1;
	defaultBodyCorners1[10]=  0; 	bodyCorners1[10]=  0;
	defaultBodyCorners1[11]=-f1; 	bodyCorners1[11]=-f1;


	defaultBodyCorners1[12]=-f1; 	bodyCorners1[12]=-f1;
	defaultBodyCorners1[13]=  1; 	bodyCorners1[13]=  1;
	defaultBodyCorners1[14]=  0; 	bodyCorners1[14]=  0;

	defaultBodyCorners1[15]=-f1; 	bodyCorners1[15]=-f1;
	defaultBodyCorners1[16]= -1; 	bodyCorners1[16]= -1;
	defaultBodyCorners1[17]=  0; 	bodyCorners1[17]=  0;

	defaultBodyCorners1[18]= f1; 	bodyCorners1[18]= f1;
	defaultBodyCorners1[19]= -1; 	bodyCorners1[19]= -1;
	defaultBodyCorners1[20]=  0; 	bodyCorners1[20]=  0;

	defaultBodyCorners1[21]= f1; 	bodyCorners1[21]= f1;
	defaultBodyCorners1[22]=  1; 	bodyCorners1[22]=  1;
	defaultBodyCorners1[23]=  0; 	bodyCorners1[23]=  0;


	defaultBodyCorners1[24]=  0; 	bodyCorners1[24]=  0;
	defaultBodyCorners1[25]=-f1; 	bodyCorners1[25]=-f1;
	defaultBodyCorners1[26]=  1; 	bodyCorners1[26]=  1;

	defaultBodyCorners1[27]=  0; 	bodyCorners1[27]=  0;
	defaultBodyCorners1[28]=-f1; 	bodyCorners1[28]=-f1;
	defaultBodyCorners1[29]= -1; 	bodyCorners1[29]= -1;

	defaultBodyCorners1[30]=  0; 	bodyCorners1[30]=  0;
	defaultBodyCorners1[31]= f1; 	bodyCorners1[31]= f1;
	defaultBodyCorners1[32]= -1; 	bodyCorners1[32]= -1;

	defaultBodyCorners1[33]=  0; 	bodyCorners1[33]=  0;
	defaultBodyCorners1[34]= f1; 	bodyCorners1[34]= f1;
	defaultBodyCorners1[35]=  1; 	bodyCorners1[35]=  1;


	//Dodecahedron - projected co-ordinates
	icosahedron2D=new double[24];
	rectX=new int[4];
	rectY=new int[4];
	rectX3=new int[3];
	rectY3=new int[3];

	//number of points used to draw a body
	dblSize1[2] = 12;

	//name of a body
	strBody[0] = "ICOSAHEDRON";

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


	//Icosahedron: Project 3D co-ordinates into 2D
	for (i=0;i<12;i++) {
	    icosahedron2D[i*2]=(origin.x+dblSize1[1]*scalProd(bbodyCorners,i*3,beX,0));
	    icosahedron2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bbodyCorners,i*3,beY,0));
	}



	    //Draw Solid Icosahedron
	    if (bodyFlags1[6]) {

	      for (i=0;i<20;i++){

		if (scalProd(beZ,0,normalVectors,3*i)>0.001) { // Face towards us? Draw it.
 
		   for (j=0;j<3;j++) {// Find corner co-ordinates 
		      rectX3[j]=(int)icosahedron2D[sides1[i*3+j]];
		      rectY3[j]=(int)icosahedron2D[sides1[i*3+j]+1];
		   }

		    //Drawing color or not
		    if (bodyFlags1[3]) {
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }

		    offGraphics.fillPolygon(rectX3,rectY3,3);
		    offGraphics.setPaint(Color.black);
		    offGraphics.drawPolygon(rectX3,rectY3,3);
		}
	      }

	    
	    } else {//Draw empty Icosahedron
	       for (i=0;i<20;i++) {
		     
		   for (j=0;j<3;j++) {// Find corner co-ordinates 
		      rectX3[j]=(int)icosahedron2D[sides1[i*3+j]];
		      rectY3[j]=(int)icosahedron2D[sides1[i*3+j]+1];
		   }

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX3,rectY3,3);

	       }

		//Draw Midle Polygon
		if (bodyFlags1[7]) {

		    rectX[0]=(int)icosahedron2D[2];
		    rectY[0]=(int)icosahedron2D[3];

		    rectX[1]=(int)icosahedron2D[4];
		    rectY[1]=(int)icosahedron2D[5];

		    rectX[2]=(int)icosahedron2D[14];
		    rectY[2]=(int)icosahedron2D[15];

		    rectX[3]=(int)icosahedron2D[8];
		    rectY[3]=(int)icosahedron2D[9];

		    offGraphics.drawPolygon(rectX,rectY,4);

		}

	    }


/*
	//Draw circle around all points
	for (i=1;i<21;i++){

	    int pom1 = (int)(2*dblSize1[3] - 2);
	    int pom2 = (int)(2*dblSize1[3] - 1);
	    int pomX = (int)(icosahedron2D[2*i-2]);
	    int pomY = (int)(icosahedron2D[2*i-1]);

	    offGraphics.drawOval(pomX-5,pomY-5,10,10);

	}
*/

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
	    int pomX = (int)(icosahedron2D[pom1]);
	    int pomY = (int)(icosahedron2D[pom2]);

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

	//Draw Conture
	if (bodyFlags1[11]) {
	       for (i=0;i<20;i++) {
		     
		   for (j=0;j<3;j++) {// Find corner co-ordinates 
		      rectX3[j]=(int)icosahedron2D[sides1[i*3+j]];
		      rectY3[j]=(int)icosahedron2D[sides1[i*3+j]+1];
		   }

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX3,rectY3,3);

	       }
	}


    }//End of fixBlock

}//End of IcosahedronPanel
