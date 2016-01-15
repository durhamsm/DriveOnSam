package User_Interface.Screens.DrivingResultsScreen;

import javafx.geometry.Pos;
import javafx.scene.text.Text;
import Objects.Level;
import User_Interface.DrivingResults;
import User_Interface.Control.CustomButton;
import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.WarningPane;
import User_Interface.Screens.Start.StartScreen;

/* @author Sam Durham and Joo-Wang John Lee
 * This screen will be displayed after driving is finished.
 */
public class DrivingResultsScreen extends BaseScreen {
    private String warningString = "Results";

    private CustomButton restartGameButton = new CustomButton("Restart Game");
    private String successTextStyle = "-fx-font: 20 serif;";
    private DrivingResults drivingResults;

    //Going "back" is not an option while driving, so this button is disabled.
    public DrivingResultsScreen(DrivingResults drivingResults) {
        super("Driving Results");
        backButton.setVisible(false);
        this.drivingResults = drivingResults;

        setUpMainPane();
    }

    //All of the necessary buttons and text fields/values are added to the main pane of this screen.
    private void setUpMainPane() {
        //User will click this button in order to return to the start screen.
        restartGameButton.setOnAction(new NewScreenButtonHandler(StartScreen.class));
        Level passedLevel = new Level();
        passedLevel.selectLevel(drivingResults.level - 1);

        mainPane.setVgap(15);
        mainPane.setAlignment(Pos.CENTER_LEFT);
        mainPane.getChildren().addAll(
                new WarningPane(warningString),
                getSuccessText(),
                new ResultFieldAndValue("Score", String.valueOf(drivingResults.score)),
                new ResultFieldAndValue("Skill Level", passedLevel.getLevelDescription()),
                new ResultFieldAndValue("Violated Signs", drivingResults.violatedSigns),
                restartGameButton
        );
    }

    //this will return the Text node. This actual string that is displayed in this text node will be generated from the
    //"getSuccessString method below.
    private Text getSuccessText() {
        Text successText = new Text(getSuccessString(drivingResults.level));
        successText.setStyle(successTextStyle);
        return successText;
    }

    //Generate the string for the success text node, based on the level that was passed.
    private String getSuccessString(int attainedLevel) {
        String successString = "You passed ";
        if (attainedLevel == 1) {
            successString += "no levels :(";
        } else {
            successString += "level " + String.valueOf(attainedLevel - 1) + ". Congratulations!";
        }
        return successString;
    }


}



