package bangbangproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CowBoySprite extends JPanel {
	public static String LEFT = "_0";
	public static String RIGHT = "_1";
	private static String SHOOT = "_shoot_";
	private static String address = "../ressources/images/cowboy";
	private static String idleMode = "_idle";
	private static String ext = ".png";
	private int animPos = 0;
	private static int animDeathSize = 4;
	private BufferedImage bi;
	private ImageIcon icon;
	private Timer timer;
	private JPanel rootPanel;
	/**
	 * Initializes the cowboy sprite
	 * 
	 * @param orientation
	 *            CowBoySprite.LEFT for left CowBoySprite.RIGHT for right
	 */
	public CowBoySprite(String orientation,JPanel rootPanel) {
		this.rootPanel = rootPanel;
		updateImage(address + idleMode + ext);
	}

	private void updateImage(String url) {
		try {
			bi = ImageIO.read(new File(url));
			repaint();
			rootPanel.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(url);
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		AffineTransform at = AffineTransform.getScaleInstance(10, 10);
        AffineTransformOp aop =
            new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        //bi.getScaledInstance(-1.5, 1.5,)
		((Graphics2D)g).drawImage(bi,aop, 0, 0);
	}

	/**
	 * Sets the cowboy in shoot state
	 */
	public void setShootState() {

	}

	/**
	 * Sets the cowboy in dead state
	 */
	public void setDeadState() {
		animPos = 0;
		updateDeadState();
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateDeadState();

			}
		};
		timer = new Timer(125, taskPerformer);
		timer.start();

	}

	private void updateDeadState() {
		if(animPos >= animDeathSize){
			timer.stop();
			resetState();
			return;
		}
		System.out.println(address + SHOOT + animPos + ext);
		updateImage(address + SHOOT + animPos + ext);
		if (animPos < animDeathSize)
			animPos++;
	}

	/**
	 * Resets the cowboy for a new round
	 */
	public void resetState() {
		updateImage(address + idleMode + ext);
		repaint();
	}
}
