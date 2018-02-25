package Bodies3D1;

import java.awt.*;
import javax.swing.*;
 

public class EditPanel extends JPanel{

    Graphics2D offGraphics;

    public EditPanel() {
	setSize(610,790);

	JPanel areaPanel99 = new JPanel();
	JLabel label99 = new JLabel("Editing Mode");
	areaPanel99.setPreferredSize(new Dimension(610,790));
	areaPanel99.setBackground(Color.gray);
	areaPanel99.add(label99);
	add(areaPanel99);

    }


    private AlphaComposite makeComposite(float alpha) {
	int type = AlphaComposite.SRC_OVER;
	return(AlphaComposite.getInstance(type, alpha));
    } 


    public void paintComponent(Graphics g) {

	setOpaque(false);//ovim se obezbedjuje da je pozadina providna
        setBorder(BorderFactory.createEmptyBorder());
	offGraphics = (Graphics2D)g;
        offGraphics.setComposite(makeComposite(0.9F));

    }

}
