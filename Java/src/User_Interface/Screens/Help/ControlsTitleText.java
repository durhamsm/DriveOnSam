package User_Interface.Screens.Help;

import javafx.scene.text.Text;

//This was made to in order to abstract from the HelpScreen code and allow for formatting to be changed and possible reused.
public class ControlsTitleText extends Text {

    private String controlsTitleStyle = "-fx-font: 40 serif;";

    public ControlsTitleText(String controlsTitleString) {
        super(controlsTitleString);
        this.setStyle(controlsTitleStyle);
    }

}