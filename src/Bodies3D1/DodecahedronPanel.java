package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class DodecahedronPanel extends BodyPanel {


	//Coo System fields ---------------------------------------------
	// 3D cordinates (x,y,z,-x,-y,-z)
	final double cooCorners[]=
	{1.5,0,0, 0,1.5,0, 0,0,1.5, -1.5,0,0, 0,-1.5,0, 0,0,-1.5};

	// 2D cordinates (x,y,-x,-y)
	double newCoo2D[];  //double 2D coordinates for coo system corners
	int line[];	    //integer 2D coordinates for coo system corners


	//Dodecahedron fields-----------------------------------------------------------
	double f1 = ((double)1 + Math.sqrt(5))/(double)2;//koordinate
	double f2 = (double)1/f1;//koordinate

	double radian54 = Math.toRadians(54);//konvertovanje stepena u radijane
	double radian36 = Math.toRadians(36);//konvertovanje stepena u radijane

	double a1 = (double)1/Math.sin(radian54);//duzina osnove
	double Ru = (Math.sqrt(15)+Math.sqrt(3))*a1/(double)4;//poluprecnik opisanog kruga
	double Ri = Math.sqrt(((double)110*Math.sqrt(5)+250))*a1/(double)20;//poluprecnik upisanog kruga
	double Rm = (Math.sqrt(5)+(double)3)*a1/(double)4;//poluprecnik srednjeg kruga
	double t1 = (Math.sin(radian54)/Math.sin(radian36))*a1/(double)2;//donji deo visine petougla
	double h1 = ((double)1/Math.sin(radian36))*a1/(double)2;//gornji deo visine petougla

	double yprim = t1*Ri/Rm;//2 koordinata normale
	double xprim = Math.sqrt(Ri*Ri - yprim*yprim);//1 koordinata normale

	//Dodecahedron 2D cordinates
	double dodecahedron2D[];//double 2D coordinates for Dodecahedron corners
	int rectX[],rectY[];//integer 2D coordinates for Cube corners
	int rectX5[],rectY5[];//integer 2D coordinates for Dodecahedron corners

	//Dodecahedron Normal vectors
	final double normalVectors[]={-yprim,0,xprim, yprim,0,xprim, -yprim,0,-xprim, yprim,0,-xprim,
				 xprim,yprim,0, xprim,-yprim,0, -xprim,yprim,0, -xprim,-yprim,0,
				0,-xprim,yprim, 0,-xprim,-yprim, 0,xprim,yprim, 0,xprim,-yprim};

	final int sides1[]={18,22,14,34,8, 18,22,12,38,10, 16,20,6,32,0, 16,20,4,36,2,
			    36,38,12,30,4, 36,38,10,28,2, 32,34,14,26,6, 32,34,8,24,0,
			    24,28,10,18,8, 24,28,2,16,0, 26,30,12,22,14, 26,30,4,20,6};
	

	Graphics2D offGraphics;


    public DodecahedronPanel() {

	// coo system - Projected co-ordinates
	newCoo2D=new double[12]; 
	line=new int[12];

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


	defaultBodyCorners1[24]=  0; 	bodyCorners1[24]=  0;
	defaultBodyCorners1[25]=-f2; 	bodyCorners1[25]=-f2;
	defaultBodyCorners1[26]=-f1; 	bodyCorners1[26]=-f1;

	defaultBodyCorners1[27]=  0; 	bodyCorners1[27]=  0;
	defaultBodyCorners1[28]=-f2; 	bodyCorners1[28]=-f2;
	defaultBodyCorners1[29]= f1; 	bodyCorners1[29]= f1;

	defaultBodyCorners1[30]=  0; 	bodyCorners1[30]=  0;
	defaultBodyCorners1[31]= f2; 	bodyCorners1[31]= f2;
	defaultBodyCorners1[32]=-f1; 	bodyCorners1[32]=-f1;

	defaultBodyCorners1[33]=  0; 	bodyCorners1[33]=  0;
	defaultBodyCorners1[34]= f2; 	bodyCorners1[34]= f2;
	defaultBodyCorners1[35]= f1; 	bodyCorners1[35]= f1;


	defaultBodyCorners1[36]=-f2; 	bodyCorners1[36]=-f2;
	defaultBodyCorners1[37]=-f1; 	bodyCorners1[37]=-f1;
	defaultBodyCorners1[38]=  0; 	bodyCorners1[38]=  0;

	defaultBodyCorners1[39]=-f2; 	bodyCorners1[39]=-f2;
	defaultBodyCorners1[40]= f1; 	bodyCorners1[40]= f1;
	defaultBodyCorners1[41]=  0; 	bodyCorners1[41]=  0;

	defaultBodyCorners1[42]= f2; 	bodyCorners1[42]= f2;
	defaultBodyCorners1[43]=-f1; 	bodyCorners1[43]=-f1;
	defaultBodyCorners1[44]=  0; 	bodyCorners1[44]=  0;

	defaultBodyCorners1[45]= f2; 	bodyCorners1[45]= f2;
	defaultBodyCorners1[46]= f1; 	bodyCorners1[46]= f1;
	defaultBodyCorners1[47]=  0; 	bodyCorners1[47]=  0;


	defaultBodyCorners1[48]=-f1; 	bodyCorners1[48]=-f1;
	defaultBodyCorners1[49]=  0; 	bodyCorners1[49]=  0;
	defaultBodyCorners1[50]=-f2; 	bodyCorners1[50]=-f2;

	defaultBodyCorners1[51]=-f1; 	bodyCorners1[51]=-f1;
	defaultBodyCorners1[52]=  0; 	bodyCorners1[52]=  0;
	defaultBodyCorners1[53]= f2; 	bodyCorners1[53]= f2;

	defaultBodyCorners1[54]= f1; 	bodyCorners1[54]= f1;
	defaultBodyCorners1[55]=  0; 	bodyCorners1[55]=  0;
	defaultBodyCorners1[56]=-f2; 	bodyCorners1[56]=-f2;

	defaultBodyCorners1[57]= f1; 	bodyCorners1[57]= f1;
	defaultBodyCorners1[58]=  0; 	bodyCorners1[58]=  0;
	defaultBodyCorners1[59]= f2; 	bodyCorners1[59]= f2;


	//Dodecahedron - projected co-ordinates
	dodecahedron2D=new double[40];
	rectX=new int[4];
	rectY=new int[4];
	rectX5=new int[5];
	rectY5=new int[5];

	//number of points used to draw a body
	dblSize1[2] = 20;

	//name of a body
	strBody[0] = "DODECAHEDRON";

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


	//Dodecahedron: Project 3D co-ordinates into 2D
	for (i=0;i<20;i++) {
	    dodecahedron2D[i*2]=(origin.x+dblSize1[1]*scalProd(bbodyCorners,i*3,beX,0));
	    dodecahedron2D[i*2+1]=(origin.y-dblSize1[1]*scalProd(bbodyCorners,i*3,beY,0));
	}



	    //Draw Solid Dodecahedron
	    if (bodyFlags1[6]) {

	      for (i=0;i<12;i++){

		if (scalProd(beZ,0,normalVectors,3*i)>0.001) { // Face towards us? Draw it.
 
		   for (j=0;j<5;j++) {// Find corner co-ordinates 
		      rectX5[j]=(int)dodecahedron2D[sides1[i*5+j]];
		      rectY5[j]=(int)dodecahedron2D[sides1[i*5+j]+1];
		   }

		    //Drawing color or not
		    if (bodyFlags1[3]) {
			offGraphics.setPaint(cDEV[i]);
		    } else {
			offGraphics.setPaint(Color.white);
		    }

		    offGraphics.fillPolygon(rectX5,rectY5,5);
		    offGraphics.setPaint(Color.black);
		    offGraphics.drawPolygon(rectX5,rectY5,5);
		}
	      }

	    
	    } else {//Draw empty Dodecahedron
	       for (i=0;i<12;i++) {
		     
		   for (j=0;j<5;j++) {// Find corner co-ordinates 
		      rectX5[j]=(int)dodecahedron2D[sides1[i*5+j]];
		      rectY5[j]=(int)dodecahedron2D[sides1[i*5+j]+1];
		   }

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX5,rectY5,5);

	       }

		//Draw Midle Polygon
		if (bodyFlags1[7]) {

		    rectX[0]=(int)dodecahedron2D[2];
		    rectY[0]=(int)dodecahedron2D[3];

		    rectX[1]=(int)dodecahedron2D[4];
		    rectY[1]=(int)dodecahedron2D[5];

		    rectX[2]=(int)dodecahedron2D[14];
		    rectY[2]=(int)dodecahedron2D[15];

		    rectX[3]=(int)dodecahedron2D[8];
		    rectY[3]=(int)dodecahedron2D[9];

		    offGraphics.drawPolygon(rectX,rectY,4);

		}

	    }



/*
	//Draw circle around all points
	for (i=1;i<21;i++){

	    int pom1 = (int)(2*dblSize1[3] - 2);
	    int pom2 = (int)(2*dblSize1[3] - 1);
	    int pomX = (int)(dodecahedron2D[2*i-2]);
	    int pomY = (int)(dodecahedron2D[2*i-1]);

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
	    int pomX = (int)(dodecahedron2D[pom1]);
	    int pomY = (int)(dodecahedron2D[pom2]);

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
	       for (i=0;i<12;i++) {
		     
		   for (j=0;j<5;j++) {// Find corner co-ordinates 
		      rectX5[j]=(int)dodecahedron2D[sides1[i*5+j]];
		      rectY5[j]=(int)dodecahedron2D[sides1[i*5+j]+1];
		   }

		offGraphics.setPaint(Color.black);
		offGraphics.drawPolygon(rectX5,rectY5,5);

	       }
	}

    }//End of fixBlock

}//End of DodecahedronPanel
