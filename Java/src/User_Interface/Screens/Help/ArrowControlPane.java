package User_Interface.Screens.Help;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;


//This is used to add the arrow image and also label for that image to the HelpScreen. Used for up and down arrows.
public class ArrowControlPane extends FlowPane {
    private String arrowTextStyle = "-fx-font: 20 serif;";

    public ArrowControlPane(String imageFileName, String controlString) {
        ImageView arrowImage = new ImageView(imageFileName);
        Text arrowText = new Text(controlString);
        arrowText.setStyle(arrowTextStyle);
        getChildren().addAll(arrowImage, arrowText);
    }
}