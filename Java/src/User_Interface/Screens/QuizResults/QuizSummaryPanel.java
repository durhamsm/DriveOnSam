package User_Interface.Screens.QuizResults;

import User_Interface.Screens.DrivingResultsScreen.ResultFieldAndValue;
import User_Interface.Screens.Quiz.AnsweredQuestion;
import User_Interface.Screens.WarningPane;
import javafx.scene.layout.GridPane;

import java.util.List;


public class QuizSummaryPanel extends GridPane {

    public QuizSummaryPanel(List<AnsweredQuestion> answeredQuestions) {
        int numRightQuestions = (int)answeredQuestions.stream().filter(answeredQuestion -> answeredQuestion.isCorrect).count();
        int totalNumQuestions = answeredQuestions.size();
        addColumn(0,
                new WarningPane("Quiz Results"),
                new ResultFieldAndValue("Number Right", numRightQuestions),
                new ResultFieldAndValue("Number Wrong", totalNumQuestions - numRightQuestions),
                new ResultFieldAndValue("% Correct", (int)100.0*numRightQuestions/totalNumQuestions)
        );

    }

}
