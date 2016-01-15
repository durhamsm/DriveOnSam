package User_Interface.Screens.Start;

import User_Interface.Control.CustomButton;
import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.Help.HelpScreen;
import User_Interface.Screens.Quiz.QuizScreen;
import User_Interface.Screens.SelectCar.SelectCarScreen;
import User_Interface.Screens.ViewTrafficRules.ViewTrafficRulesScreen;

//This is the start screen for "Playing Game", "Help" and "View Traffic Rules"
public class StartScreen extends BaseScreen {

    private CustomButton playButton = new CustomButton("Play Game");
    private CustomButton viewRulesButton = new CustomButton("View Traffic Rules");
    private CustomButton getHelpButton = new CustomButton("Help");
    private CustomButton quizButton = new CustomButton("Quiz");

    public StartScreen() {
        super("Start Screen");
        playButton.setOnAction(new NewScreenButtonHandler(SelectCarScreen.class));
        viewRulesButton.setOnAction(new NewScreenButtonHandler(ViewTrafficRulesScreen.class));
        getHelpButton.setOnAction(new NewScreenButtonHandler(HelpScreen.class));
        quizButton.setOnAction(new NewScreenButtonHandler(QuizScreen.class));
        mainPane.getChildren().addAll(playButton, viewRulesButton, getHelpButton, quizButton);
        super.backButton.setVisible(false);
    }

}

