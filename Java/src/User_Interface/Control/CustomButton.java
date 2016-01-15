package User_Interface.Control;

import javafx.scene.control.Button;

//This button simply extends the Java fx button class, in order to set desired formatting.
public class CustomButton extends Button {
    private String styleProperties = "-fx-font: 10mm serif;";

    public CustomButton(String buttonText) {
        super(buttonText);
        super.setStyle(styleProperties);
    }

}
