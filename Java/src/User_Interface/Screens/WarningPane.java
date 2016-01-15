package User_Interface.Screens;

import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

//Although this pane is titled "WarningPane" it is used on the results page for the "Results" title and also used
//on the Help page in a more appropriate "warning setting." (where notified that regulations applicable to US).
//It is titled "WarningPane" due to the red background that gives a sense of warning.
public class WarningPane extends FlowPane {
    private String warningPaneStyle = "-fx-background-color: red;";
    private String warningTextStyle = "-fx-font: 30 serif;";

    public WarningPane(String warningString) {
        Text warningText = new Text(warningString);
        warningText.setStyle(warningTextStyle);
        getChildren().add(warningText);
        setStyle(warningPaneStyle);
    }
}
