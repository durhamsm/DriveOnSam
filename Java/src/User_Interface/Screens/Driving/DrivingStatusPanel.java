package User_Interface.Screens.Driving;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;

import Objects.Sign;
import User_Interface.DrivingResults;
import User_Interface.Session;
import User_Interface.Screens.DrivingResultsScreen.ResultFieldAndValue;

/* @author Sam Durham, Joo-Wang John Lee and Paul Spears
 * This gives the driving status panel showing the score, level,
 * the number of violations, the sign and the speed limit.
 */

public class DrivingStatusPanel extends GridPane {


    public ResultFieldAndValue scorePanel;
    public ResultFieldAndValue levelPanel;
    public ResultFieldAndValue violationsPanel;
    public ResultFieldAndValue speedPanel;
    public DrivingStatusItemSign signPanel;
    public ResultFieldAndValue speedLimitPanel;

    public DrivingStatusPanel(DrivingResults drivingResults) {
        scorePanel = new ResultFieldAndValue("Score", 0);
        levelPanel = new ResultFieldAndValue("Level", 1);
        violationsPanel = new ResultFieldAndValue("Number of Violations", 0);
        speedPanel = new ResultFieldAndValue("Car Speed", 0);
        signPanel = new DrivingStatusItemSign();
        speedLimitPanel = new ResultFieldAndValue("Speed Limit", 0);

        addColumn(0, scorePanel, levelPanel, violationsPanel, speedPanel, signPanel, speedLimitPanel);
    }

    public void updatePanel(DrivingResults drivingResults) {
        scorePanel.updateValue(drivingResults.score);
        levelPanel.updateValue(drivingResults.level);
        violationsPanel.updateValue(drivingResults.violatedSigns.size());
    }

}


class DrivingStatusItemSign extends ResultFieldAndValue {

    public DrivingStatusItemSign() {
        super("Sign", new ArrayList<Sign>());
    }

    public void displaySign(Sign newSign) {
        resultValuePane.getChildren().clear();
        ImageView signv = new ImageView(getSignImage(newSign));
        ImageView fogv = new ImageView(getFogImage());
        fogv.setFitHeight(100);
        fogv.setFitWidth(100);
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(signv);

        // For second level, add fog overlay to show fog weather condition.
        if (Session.getInstance().getDrivingResults().getLevel() == 2) {
            stackPane.getChildren().add(fogv);
        }

        resultValuePane.getChildren().add(stackPane);
    }

    private Image getSignImage(Sign newSign) {
        return new Image(newSign.getImageLocation(), 100, 100, true, true);
    }
    
    private Image getFogImage() {
    	BufferedImage fogImage;
    	Image image = null;
		try {
			fogImage = ImageIO.read(getClass().getResource("fogOverlay.jpg"));
			image = createImage(AnimationPanel.makeColorTransparent(fogImage, new Color(fogImage.getRGB(0, 0))));
		} catch (IOException e) {
			e.printStackTrace();
		}

    	return image;
    }

    public void removeSign() {
        resultValuePane.getChildren().clear();
    }
    
    public static javafx.scene.image.Image createImage(java.awt.Image image) throws IOException {
		
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		
		image = bufferedImage;
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }

}