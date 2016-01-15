package User_Interface.Screens.QuizResults;

import User_Interface.Screens.DrivingResultsScreen.ResultFieldAndValue;
import User_Interface.Screens.Quiz.AnsweredQuestion;
import User_Interface.Screens.WarningPane;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.List;


public class AnsweredQuestionsPanel extends GridPane {

    private static final String gridStyle =
            "-fx-grid-lines-visible: true;";

    public AnsweredQuestionsPanel(List<AnsweredQuestion> answeredQuestions) {
        addColumn(0, answeredQuestions.toArray(new AnsweredQuestion[answeredQuestions.size()]));
        setStyle(gridStyle);
    }

}
