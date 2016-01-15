package User_Interface.Screens.QuizResults;

import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.Quiz.AnsweredQuestion;
import User_Interface.Screens.Start.StartScreen;
import User_Interface.Screens.WarningPane;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.stream.Collectors;


public class QuizResultsScreen extends BaseScreen {

    QuestionsScrollPanel questionsScrollPanel;
    List<AnsweredQuestion> answeredQuestions;
    QuizSummaryPanel summaryPanel;

    public QuizResultsScreen(List<AnsweredQuestion> answeredQuestions) {
        super("Quiz Results");
        this.answeredQuestions = answeredQuestions;
        setupSummaryPanel();
        setupScrollPanel();
        backButton.setOnAction(new NewScreenButtonHandler(StartScreen.class));
    }

    private void setupScrollPanel() {
        questionsScrollPanel = new QuestionsScrollPanel(getWrongQuestions());
        FlowPane incorrectAnswersPanel = new FlowPane();
        incorrectAnswersPanel.getChildren().addAll(new WarningPane("Incorrect Answers"), questionsScrollPanel);
        mainPane.getChildren().add(incorrectAnswersPanel);
    }

    private void setupSummaryPanel() {
        summaryPanel = new QuizSummaryPanel(answeredQuestions);
        mainPane.getChildren().add(summaryPanel);
    }

    private List<AnsweredQuestion> getWrongQuestions() {
        return answeredQuestions.stream()
                .filter(answeredQuestion -> !answeredQuestion.isCorrect).collect(Collectors.toList());
    }


}
