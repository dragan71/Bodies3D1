package Bodies3D1;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
//import javax.swing.border.*;
 

public class Logo1Panel extends JPanel {

	Graphics2D offGraphics;

    public Logo1Panel() {
	//super();
	setPreferredSize(new Dimension(100, 130));
	//setBorder(BorderFactory.createTitledBorder(""));

    }


    public void paintComponent(Graphics g) {

	offGraphics = (Graphics2D)g;

        offGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        offGraphics.setPaint(Color.black);

        GeneralPath fl = new GeneralPath();
        fl.moveTo(89F, 46F);
        fl.lineTo(87F, 44F);
        fl.lineTo(86F, 37F);
        fl.lineTo(85F, 29F);
        fl.lineTo(84F, 24F);
        fl.lineTo(82F, 19F);
        fl.lineTo(80F, 15F);
        fl.lineTo(76F, 10F);
        fl.lineTo(72F, 8F);
        fl.lineTo(66F, 5F);
        fl.lineTo(61F, 3F);
        fl.lineTo(47F, 2F);
        fl.lineTo(31F, 4F);
        fl.lineTo(29F, 5F);
        fl.lineTo(29F, 7F);
        fl.lineTo(24F, 8F);
        fl.lineTo(22F, 10F);
        fl.lineTo(14F, 12F);
        fl.lineTo(11F, 14F);
        fl.lineTo(8F, 18F);
        fl.lineTo(6F, 23F);
        fl.lineTo(2F, 32F);
        fl.lineTo(2F, 37F);
        fl.lineTo(5F, 48F);
        fl.lineTo(9F, 55F);
        fl.lineTo(11F, 60F);
        fl.lineTo(11F, 64F);
        fl.lineTo(10F, 65F);
        fl.lineTo(16F, 71F);
        fl.lineTo(13F, 54F);
        fl.lineTo(11F, 43F);
        fl.lineTo(11F, 36F);
        fl.lineTo(18F, 26F);
        fl.lineTo(28F, 25F);
        fl.lineTo(31F, 23F);
        fl.lineTo(37F, 23F);
        fl.lineTo(40F, 20F);
        fl.lineTo(45F, 21F);
        fl.lineTo(47F, 24F);
        fl.lineTo(54F, 25F);
        fl.lineTo(55F, 31F);
        fl.lineTo(54F, 35F);
        fl.lineTo(58F, 39F);
        fl.lineTo(58F, 43F);
        fl.lineTo(44F, 52F);
        fl.lineTo(44F, 56F);
        fl.lineTo(46F, 59F);
        fl.lineTo(47F, 68F);
        fl.lineTo(48F, 72.5F);
        fl.lineTo(48.5F, 75F);
        fl.lineTo(47F, 78F);
        fl.lineTo(44F, 79.5F);
        fl.lineTo(40F, 78F);
        fl.lineTo(39F, 78.5F);
        fl.lineTo(44F, 81F);
        fl.lineTo(46F, 83F);
        fl.lineTo(47.5F, 85F);
        fl.lineTo(51F, 85F);
        fl.lineTo(51F, 86F);
        fl.lineTo(45F, 86F);
        fl.lineTo(41F, 87F);
        fl.lineTo(38.5F, 88F);
        fl.lineTo(36.5F, 87.5F);
        fl.lineTo(35F, 89F);
        fl.lineTo(34.5F, 92.5F);
        fl.lineTo(36.5F, 96F);
        fl.lineTo(38F, 93F);
        fl.lineTo(39F, 91F);
        fl.lineTo(41F, 91F);
        fl.lineTo(46F, 92F);
        fl.lineTo(52F, 92F);
        fl.lineTo(53F, 91F);
        fl.lineTo(56F, 91F);
        fl.lineTo(56F, 92F);
        fl.lineTo(55F, 92F);
        fl.lineTo(51F, 94F);
        fl.lineTo(49F, 94F);
        fl.lineTo(46.5F, 95.5F);
        fl.lineTo(48F, 97F);
        fl.lineTo(51F, 98F);
        fl.lineTo(55F, 98F);
        fl.lineTo(62.5F, 98.5F);
        fl.lineTo(62.5F, 100.5F);
        fl.lineTo(51.5F, 108.5F);
        fl.lineTo(47.5F, 110F);
        fl.lineTo(45F, 110F);
        fl.lineTo(44F, 109F);
        fl.lineTo(40F, 108F);
        fl.lineTo(44F, 111F);
        fl.lineTo(46F, 113F);
        fl.lineTo(48F, 116F);
        fl.lineTo(61F, 122F);
        fl.lineTo(71F, 125F);
        fl.lineTo(77F, 127F);
        fl.lineTo(84F, 128F);
        fl.lineTo(91F, 117F);
        fl.lineTo(93F, 111F);
        fl.lineTo(96F, 104F);
        fl.lineTo(98F, 102F);
        fl.lineTo(99F, 98F);
        fl.lineTo(99F, 88F);
        fl.lineTo(98F, 87F);
        fl.lineTo(96F, 87F);
        fl.lineTo(96F, 84F);
        fl.lineTo(93F, 84F);
        fl.lineTo(90F, 85F);
        fl.lineTo(89F, 86F);
        fl.lineTo(87F, 86F);
        fl.lineTo(86F, 84F);
        fl.lineTo(86F, 78.5F);
        fl.lineTo(84.5F, 77.5F);
        fl.lineTo(83F, 79F);
        fl.lineTo(81.5F, 81F);
        fl.lineTo(81F, 75F);
        fl.lineTo(79F, 72F);
        fl.lineTo(78F, 68F);
        fl.lineTo(78F, 63F);

        fl.lineTo(68F, 63F);
        fl.lineTo(68F, 67F);
        fl.lineTo(64F, 72F);
        fl.lineTo(62F, 72F);
        fl.lineTo(55F, 64F);
        fl.lineTo(55F, 62F);
        fl.lineTo(65F, 62F);
        fl.lineTo(68F, 63F);

        fl.lineTo(78F, 63F);
        fl.lineTo(78F, 58F);
        fl.lineTo(77F, 57F);
        fl.lineTo(77F, 56F);
        fl.lineTo(79F, 54F);
        fl.lineTo(80F, 54F);
        fl.lineTo(80F, 55F);
        fl.lineTo(82.5F, 56F);
        fl.lineTo(84F, 62F);
        fl.lineTo(85F, 72F);
        fl.lineTo(87.5F, 73F);
        fl.lineTo(90F, 72F);
        fl.lineTo(91F, 70F);
        fl.lineTo(91F, 56F);
        fl.closePath();
        offGraphics.fill(fl);


        GeneralPath fl2 = new GeneralPath();
        fl2.moveTo(17F, 61F);
        fl2.lineTo(20F, 61F);
        fl2.lineTo(22.5F, 59F);
        fl2.lineTo(24F, 59F);
        fl2.lineTo(24.5F, 58F);
        fl2.lineTo(27F, 58F);
        fl2.lineTo(27F, 60F);
        fl2.lineTo(25F, 60F);
        fl2.lineTo(22F, 61F);
        fl2.lineTo(22F, 62F);
        fl2.lineTo(21F, 62F);
        fl2.lineTo(21F, 63F);
        fl2.lineTo(20F, 63F);
        fl2.lineTo(20F, 66F);
        fl2.lineTo(22F, 66F);
        fl2.lineTo(24F, 64F);
        fl2.lineTo(26F, 64F);
        fl2.lineTo(27F, 63F);
        fl2.lineTo(29F, 64F);
        fl2.lineTo(29F, 65F);
        fl2.lineTo(28F, 66F);
        fl2.lineTo(25F, 66F);
        fl2.lineTo(27F, 68F);
        fl2.lineTo(28.5F, 68F);
        fl2.lineTo(30F, 67F);
        fl2.lineTo(32F, 67F);
        fl2.lineTo(34F, 66F);
        fl2.lineTo(36F, 64F);
        fl2.lineTo(35F, 62F);
        fl2.lineTo(36F, 62F);
        fl2.lineTo(36F, 61F);
        fl2.lineTo(38F, 61F);
        fl2.lineTo(39F, 59F);
        fl2.lineTo(39F, 55.5F);
        fl2.lineTo(40F, 54F);
        fl2.lineTo(40F, 53F);
        fl2.lineTo(29F, 53F);
        fl2.lineTo(26F, 54F);
        fl2.lineTo(23F, 55F);
        fl2.lineTo(21F, 56F);
        fl2.lineTo(17.5F, 57F);
        fl2.closePath();
        offGraphics.fill(fl2);

        GeneralPath fl3 = new GeneralPath();
        fl3.moveTo(35F, 77F);
        fl3.lineTo(33F, 79F);
        fl3.lineTo(31F, 82F);
        fl3.lineTo(30F, 84F);
        fl3.lineTo(29.5F, 86.5F);
        fl3.lineTo(32F, 87F);
        fl3.lineTo(33F, 86F);
        fl3.lineTo(34F, 84F);
        fl3.lineTo(35F, 82F);
        fl3.lineTo(36F, 80F);
        fl3.lineTo(36F, 77F);
        fl3.closePath();
        offGraphics.fill(fl3);

        GeneralPath fl4 = new GeneralPath();
        fl4.moveTo(39F, 108F);;
        fl4.lineTo(32F, 102F);
        fl4.lineTo(38F, 108F);
        fl4.closePath();
        offGraphics.fill(fl4);

    }


}
