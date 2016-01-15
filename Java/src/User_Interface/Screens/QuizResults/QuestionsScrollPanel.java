package User_Interface.Screens.QuizResults;

import User_Interface.Screens.Quiz.AnsweredQuestion;
import javafx.scene.control.ScrollPane;

import java.util.List;

public class QuestionsScrollPanel extends ScrollPane {

    public static final int WIDTH = 800;

    public QuestionsScrollPanel(List<AnsweredQuestion> questions) {
        setContent(new AnsweredQuestionsPanel(questions));
        setPrefSize(WIDTH, 400);
        setFitToWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        //setContent(questions.toArray(new AnsweredQuestion[questions.size()]));
    }

}
