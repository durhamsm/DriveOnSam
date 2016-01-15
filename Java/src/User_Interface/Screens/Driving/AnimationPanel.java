package User_Interface.Screens.Driving;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import User_Interface.Session;

/* @author Sam Durham, Joo-Wang John Lee and Paul Spears
 * This panel is for animating the weather conditions and calculate the movement
 * of the screen based on the car's speed.
 */

public class AnimationPanel extends JPanel {
    public static final int BOTTOM_OF_SCREEN = 600;

    private boolean isTransitionMessageShown = false;
    private boolean isSignViolated = false;
    private boolean isSign = false;
    private final int SPEED_COEFFICIENT = 10;

    private BufferedImage level1Image, level2Image, level3Image, lightImage;
    private BufferedImage carImage;
    private BufferedImage level2, level3, violation;

    private int notificationOffset = 140;
    private int notificationStart = 300;
    private int violatedStart = 400;
    private int notificationEnd = BOTTOM_OF_SCREEN;

    private int roadStart = -290;
    private int roadEnd = -5;
    private int roadOffset = 200;

    private static int signStart = -20;
    private int signEnd = BOTTOM_OF_SCREEN;
    private int signOffset = 750;

    private static int distanceToSign = signStart;
    private int roadY = roadStart;
    private int levelNoteY = notificationStart;
    private int violatedSignMessageY = violatedStart;

    private static int carOffsetX = 510;
    private static int carOffsetY = 200;

    private int lightOffsetX = carOffsetX + 10;
    private int lightOffsetY = carOffsetY - 250;

    boolean up = false;
    boolean down = true;

    boolean fog = false;

    private static final long serialVersionUID = 1L;

    public static int getCarOffsetY() {
    	return carOffsetY;
    }
    
    public static int getSignY() {
    	return distanceToSign;
    }
    
    private static BufferedImage imageToBufferedImage(final Image image)
    {
        final BufferedImage bufferedImage =
                new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    public static Image makeColorTransparent(final BufferedImage im, final Color color)
    {
        final ImageFilter filter = new RGBImageFilter()
        {
            public int backMarker = color.getRGB() | 0xFF000000;;

            public final int filterRGB(final int x, final int y, final int rgb)
            {
                if ((rgb | 0xFF000000) == backMarker)// && !white)
                {
                    return 0x00FFFFFF & rgb;
                }
                else
                {
                    return rgb;
                }
            }
        };

        final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public AnimationPanel() {
        try {
            level1Image = ImageIO.read(getClass().getResource("sunnyClear.jpg"));
            level2Image = ImageIO.read(getClass().getResource("nightFog.jpg"));
            level3Image = ImageIO.read(getClass().getResource("nightConstruction.jpg"));
            lightImage = ImageIO.read(getClass().getResourceAsStream("headlights.jpg"));
            level2 = ImageIO.read(getClass().getResource("level2.png"));
            level3 = ImageIO.read(getClass().getResource("level3.png"));
            violation = ImageIO.read(getClass().getResource("violation.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String drivingImageLocation = Session.getInstance().getSelectedCar().getDrivingImageLocation();
        try {
        	carImage = ImageIO.read(getClass().getResource(drivingImageLocation));
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void setSignViolated() {
        violatedSignMessageY = violatedStart;
        isSignViolated = true;
    }



    public void setDistanceToSign(int newDistanceToSign) {
        distanceToSign = newDistanceToSign;
        isSign = true;
    }

    public void showTransitionMessage() {
        levelNoteY = notificationStart;
        isTransitionMessageShown = true;
    }

    public BufferedImage getLevelImage() {
        if (DrivingPanel.getLevel() == 3) {
            return level3Image;
        } else if (DrivingPanel.getLevel() == 2) {
        	return level2Image;
        } else {
            return level1Image;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paint background - based on the level
        g.setColor(Color.DARK_GRAY);
        if(DrivingPanel.getLevel() == 1) g.setColor(Color.GREEN);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //draws the road
        g.drawImage(getLevelImage(), roadOffset, roadY, null);

        //make the sign
        drawSign(g);

        //draw the car
        g.drawImage(imageToBufferedImage(makeColorTransparent(carImage, new Color(carImage.getRGB(0, 0))))
                , carOffsetX, carOffsetY, null);

        //draw the headlights if night
        if(DrivingPanel.getLevel() != 1) g.drawImage(lightImage, lightOffsetX, lightOffsetY, null);

        drawSignViolatedMessage(g);
        drawTransitionMessage(g);

    }

    private void drawSign(Graphics g) {
        if (isSign){
            g.setColor(Color.BLACK);
            g.fillRect(signOffset, distanceToSign, 100, 15);
            if (distanceToSign > signEnd) {
                isSign = false;
            }
        }
    }

    private void drawSignViolatedMessage(Graphics g) {
        if (isSignViolated) {
            g.drawImage(imageToBufferedImage(makeColorTransparent(violation, new Color(violation.getRGB(0, 0))))
                    , notificationOffset, violatedSignMessageY, null);
            if (violatedSignMessageY > notificationEnd) {
                isSignViolated = false;
            }
        }
    }

    private void drawTransitionMessage(Graphics g) {

        if (isTransitionMessageShown) {
            if(DrivingPanel.getLevel()==2){
                g.drawImage(imageToBufferedImage(makeColorTransparent(level2, new Color(level2.getRGB(0, 0))))
                        , notificationOffset, levelNoteY, null);
            }
            else {
                g.drawImage(imageToBufferedImage(makeColorTransparent(level3, new Color(level3.getRGB(0, 0))))
                        , notificationOffset, levelNoteY, null);
            }
            if (levelNoteY > notificationEnd) {
                isTransitionMessageShown = false;
            }
        }

    }

    public int getDistanceToSign() {
        return distanceToSign;
    }

    public boolean moveIt(int speed) {
        if (roadY >= roadEnd) {
            up = true;
            down = false;
        }
        if (up) {
            roadY = roadStart;
            up = false;
            down = true;
        }
        if (down) {
            if(speed > SPEED_COEFFICIENT * 5) roadY += 5;
            else roadY += (speed + SPEED_COEFFICIENT - 1) / SPEED_COEFFICIENT;
        }

        if (isSignViolated) {
            if(speed > SPEED_COEFFICIENT * 5) violatedSignMessageY += 5;
            violatedSignMessageY += (speed + SPEED_COEFFICIENT - 1) / SPEED_COEFFICIENT;
        }
        if (isTransitionMessageShown) {
            if(speed > SPEED_COEFFICIENT * 5) levelNoteY += 5;
            levelNoteY += (speed + SPEED_COEFFICIENT - 1) / SPEED_COEFFICIENT;
        }

        if (isSign) {
            if(speed > SPEED_COEFFICIENT * 5) distanceToSign += 5;
            else distanceToSign += (speed + SPEED_COEFFICIENT - 1) / SPEED_COEFFICIENT;
        }
        this.repaint();

        return true;

    }

}