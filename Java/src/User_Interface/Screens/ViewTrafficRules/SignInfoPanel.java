package User_Interface.Screens.ViewTrafficRules;

import Objects.Sign;
import User_Interface.Screens.DrivingResultsScreen.ResultFieldAndValue;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;

//This is the information that is displayed for each sign when clicked. Just sign description and the MPH by which the
//driver must abide in the driving game (if applicable).
public class SignInfoPanel extends FlowPane {

    public SignInfoPanel(Sign selectedSign) {
        setOrientation(Orientation.VERTICAL);
        ResultFieldAndValue field1 = new ResultFieldAndValue("Sign Description", selectedSign.getDescription());
        ResultFieldAndValue field2 = new ResultFieldAndValue("Speed Limit in Game (mph)", String.valueOf(selectedSign.getSignSpeed()));
        getChildren().addAll(field1, field2);
    }

}
