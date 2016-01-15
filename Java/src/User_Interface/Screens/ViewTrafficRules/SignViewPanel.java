package User_Interface.Screens.ViewTrafficRules;

import Objects.Sign;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

//This is where all of the image, name, and additional informatio is displayed for each sign.
public class SignViewPanel extends BorderPane {

    private String signNameStyle = "-fx-font: 16mm serif;";

    private ImageView signImageView;
    private Text signNameText;
    private SignInfoPanel signInfoPanel;

    public SignViewPanel(Sign selectedSign) {
        signImageView = new ImageView(new Image(selectedSign.getImageLocation(), 300, 300, false, true));
        signNameText = new Text(selectedSign.getSignName());
        signNameText.setStyle(signNameStyle);
        signInfoPanel = new SignInfoPanel(selectedSign);
        fillPanel();
    }

    //Fill the Pane with appropraite information, in appropriate locations.
    private void fillPanel() {
        setLeft(signImageView);
        setTop(signNameText);
        setAlignment(signNameText, Pos.CENTER);
        setCenter(signInfoPanel);
        setBottom(null);
        setRight(null);
    }

}
