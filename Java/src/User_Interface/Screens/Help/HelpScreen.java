package User_Interface.Screens.Help;


import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.WarningPane;
import User_Interface.Screens.Start.StartScreen;

//This Screen will show the basic help information (what game is intended for, and up/down controls)
public class HelpScreen extends BaseScreen {

    private String warningString = "This game is intended to test your knowledge of traffic \n" +
                                    "rules and regulations in the United States of America";
    private String controlsTitleString = "Controls";
    private String upArrowString = "Press Up Arrow to Increase Speed";
    private String downArrowString = "Press Down Arrow to Decrease Speed";

    public HelpScreen() {
        super("Help");
        backButton.setOnAction(new NewScreenButtonHandler(StartScreen.class));
        setUpMainPane();
    }

    private void setUpMainPane() {

        mainPane.getChildren().addAll(
                new WarningPane(warningString),
                new ControlsTitleText(controlsTitleString),
                new ArrowControlPane("up arrow.jpg", upArrowString),
                new ArrowControlPane("down arrow.jpg", downArrowString)
        );
    }
}





