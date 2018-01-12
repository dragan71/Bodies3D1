package Bodies3D1;

import java.awt.*;
import java.lang.Math;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import javax.swing.border.*;
 

public class BodyPanel extends JPanel implements MouseListener, MouseMotionListener{

	int i,j,lastX,lastY,dx,dy;//pomocne promenljive
	double temp[]={0,0,0};

	double eZ[]={0,0,1}; // Z-osa
	double eX[]={1,0,0}; // X-osa
	double eY[]; 	     // Y-osa

	Color cDEV[];
	TitledBorder titleEmpty;
	Math m;


        Point origin = new Point(220, 220);//coordinate of body midpoint

        double dblSize1[] = new double[4];//Size of a body
	//dblSize1[0] - default size value of a body 
	//dblSize1[1] - changeble size value of a body
	//dblSize1[2] - number of points used to draw a body
	//dblSize1[3] - point selected in shape panel

	boolean bodyFlags1[]=
	{false,true,false,true,false, false, true, false, false, false, false, false};
	//bodyFlags1[0] - Setting option for moving body
	//bodyFlags1[1] - Setting option for rotate body
	//bodyFlags1[2] - Drawing Coo System
	//bodyFlags1[3] - Drawing Color on sides
	//bodyFlags1[4] - Draw with Rendering
	//bodyFlags1[5] - Setting border around body 100
	//bodyFlags1[6] - Setting solid(true) or empty(false) body
	//bodyFlags1[7] - Drawing midle polygon
	//bodyFlags1[8] - Edit shape mode
	//bodyFlags1[9] - Draw Out Sphere
	//bodyFlags1[10] - Draw In Sphere
	//bodyFlags1[11] - Draw Normal Vectors


        String strBody[] = new String[1];//Size of a body
	//strBody[0] - name of a body 


	//Values for x,y,z coordinates of body points (6x3 = 18)
        double bodyCorners1[] = new double[60];
        double defaultBodyCorners1[] = new double[60];


    public BodyPanel() {
	//super();
	setSize(610,790);
	addMouseListener(this);
	addMouseMotionListener(this);
	titleEmpty = BorderFactory.createTitledBorder("");


	//Podesavanje koordinatnog sistema da bude malo nakrivljen
	eZ[0]=  0.5394326553698643;
	eZ[1]=  0.41665832023522686;
	eZ[2]=  0.7317159657267132;

	//Yt[0]= -0.06109552385153984;
	//Yt[1]=  0.9393429235243569;
	//Yt[2]= -0.3374940132654507;

	eX[0]=  0.8342371011599166;
	eX[1]= -0.14651201723313087;
	eX[2]= -0.5315850711359169;


	eY=new double[3];
	vecProd(eZ,0,eX,0,eY,0); // Fix y axis of observer co-ordinate system
	normalize(eY,0);


	cDEV=new Color[20];
	cDEV[0] = Color.red;
	cDEV[1] = Color.yellow;
	cDEV[2] = Color.cyan;
	cDEV[3] = Color.green;
	cDEV[4] = Color.blue;
	cDEV[5] = Color.magenta;
	cDEV[6] = Color.pink;
	cDEV[7] = Color.orange;

	cDEV[8] = Color.blue;
	cDEV[9] = Color.red;
	cDEV[10] = Color.green;
	cDEV[11] = Color.magenta;

	cDEV[12] = Color.orange;
	cDEV[13] = Color.pink;
	cDEV[14] = Color.green;
	cDEV[15] = Color.blue;
	cDEV[16] = Color.magenta;
	cDEV[17] = Color.cyan;
	cDEV[18] = Color.yellow;
	cDEV[19] = Color.red;


/*
	cDEV[12] = Color.red;
	cDEV[13] = Color.yellow;
	cDEV[14] = Color.cyan;
	cDEV[15] = Color.green;
	cDEV[16] = Color.blue;
	cDEV[17] = Color.magenta;
	cDEV[18] = Color.pink;
	cDEV[19] = Color.orange;
*/



	dblSize1[0] = 84;//default size of a body
	dblSize1[1] = 84;//setting size of a body

	repaint();

    }


    public void settingBodyMidPoint(int coordx, int coordy){
	origin.x = coordx;
	origin.y = coordy;
	repaint();

    }


    public void mousePressed(MouseEvent e){
	lastX=e.getX();
	lastY=e.getY();
    }


    public void mouseDragged(MouseEvent e){

	int x = e.getX();
	int y = e.getY();

	dx=lastX-x; // Vertical shift
	dy=y-lastY; // Horizontal shift


	if (bodyFlags1[0]) { //Move body

            origin.x -= dx;
            origin.y += dy;

	} else if (bodyFlags1[1]) { // Rotate body

	    copyVec(eX,0,temp,0);
	    scalMult(temp,0,((double)dx)*0.016);
	    addVec(temp,0,eZ,0);
	    vecProd(eY,0,eZ,0,eX,0);
	    normalize(eX,0);
	    normalize(eZ,0);
		
	    copyVec(eY,0,temp,0);
	    scalMult(temp,0,((double)dy)*0.016);
	    addVec(temp,0,eZ,0);
	    vecProd(eZ,0,eX,0,eY,0);
	    normalize(eY,0);
	    normalize(eZ,0);

	}		

	lastX=x;
	lastY=y;
	repaint();

    }


    public void paintComponent(Graphics g) {

    }

// Metods existing because Implements interfaces
    public void mouseReleased(MouseEvent e){}

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e)  {}

    public void mouseMoved(MouseEvent e)   {}

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


}
