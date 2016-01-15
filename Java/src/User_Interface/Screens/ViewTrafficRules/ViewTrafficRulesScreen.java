package User_Interface.Screens.ViewTrafficRules;

import Objects.Sign;
import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.Start.StartScreen;
import User_Interface.Session;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import java.util.List;

//This will be the screen where the user can learn about the requirements for various traffic signs.
public class ViewTrafficRulesScreen extends BaseScreen {
    private SignViewPanel signViewPanel;
    SignGridPanel signGridPanel;
    public List<Sign> signObjects = Session.getInstance().getSigns();

    //Formatting of the screen and necessary action handlers are set.
    public ViewTrafficRulesScreen() {
        super("Traffic Rules");
        mainPane.setAlignment(Pos.TOP_LEFT);
        mainPane.setOrientation(Orientation.VERTICAL);
        mainPane.setVgap(5);
        signViewPanel = new SignViewPanel(signObjects.get(0));
        signGridPanel = new SignGridPanel();
        mainPane.getChildren().addAll(signGridPanel, signViewPanel);
        backButton.setOnAction(new NewScreenButtonHandler(StartScreen.class));
    }

    //This method is invoked when a sign is clicked to view the information.
    public void setViewedSign(int clickedSignIndex) {
        signViewPanel = new SignViewPanel(signObjects.get(clickedSignIndex));
        mainPane.getChildren().remove(1);
        mainPane.getChildren().add(signViewPanel);
    }

}
