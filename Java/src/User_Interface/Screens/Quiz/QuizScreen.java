package User_Interface.Screens.Quiz;

import Objects.QuizDatabaseRead;
import User_Interface.Control.CustomButton;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.QuizResults.QuizResultsScreen;
import User_Interface.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizScreen extends BaseScreen {


    private List<Question> questions;
    private List<AnsweredQuestion> answeredQuestions = new ArrayList<>();
    private int selectedOptionIndex;
    private int currentQuestionIndex = 0;
    private CustomButton submitButton = new CustomButton("Submit");
    private Label questionCountText = new Label("");

    public QuizScreen() {
        super("Quiz");
        backButton.setVisible(false);
        setupTopPane();
        setRight(null);
        setQuestions();
        displayCurrentQuestion();
        setupSubmitButton();
    }

    private void displayCurrentQuestion() {
        if (currentQuestionIndex == questions.size()) {
            Session.getInstance().setNewScreen(new QuizResultsScreen(answeredQuestions));
        } else {
            mainPane.getChildren().clear();

            Question currentQuestion = questions.get(currentQuestionIndex);
            mainPane.getChildren().add(questions.get(currentQuestionIndex));

            questionCountText.setText("Question # " + String.valueOf(currentQuestionIndex + 1) + "/" + questions.size());
        }
    }

    private void setupTopPane() {
        topPane.setRight(questionCountText);
        questionCountText.setFont(new Font(20));
    }

    private void setupSubmitButton() {
        submitButton.setDisable(true);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addAnsweredQuestion();
                ++currentQuestionIndex;
                displayCurrentQuestion();
                submitButton.setDisable(true);
            }
        });

        bottomPane.getChildren().add(submitButton);
        bottomPane.setAlignment(Pos.CENTER);
        setBottom(bottomPane);
        setupSelectOptionListener();
    }

    private void setupSelectOptionListener() {
        this.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Object eventSource = event.getTarget();
                if (eventSource instanceof Option) {
                    Option selectedOption = (Option)eventSource;
                    submitButton.setDisable(false);
                    selectedOptionIndex = questions.get(currentQuestionIndex).optionsPanel.getOptionIndex(selectedOption);
                }
            }
        });
    }

    private void addAnsweredQuestion() {
        answeredQuestions.add(new AnsweredQuestion(questions.get(currentQuestionIndex), selectedOptionIndex));
    }

    private void setQuestions() {
        questions = new ArrayList<>(Session.getInstance().getRandomQuestions(10));
    }

}
