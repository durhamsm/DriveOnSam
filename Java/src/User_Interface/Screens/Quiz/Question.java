package User_Interface.Screens.Quiz;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class Question extends GridPane {

    protected int answerIndex;
    protected OptionsPanel optionsPanel;
    protected QuestionBodyPanel questionBodyPanel;

    public Question(Question question) {
        questionBodyPanel = question.questionBodyPanel;
        optionsPanel = question.optionsPanel;
        answerIndex = question.answerIndex;
        addNodes();
    }

    public Question(String questionString, String imagePath, List<String> optionStrings, int answerIndex) {
        questionBodyPanel = new QuestionBodyPanel(questionString, imagePath);
        this.answerIndex = answerIndex;
        optionsPanel = new OptionsPanel(optionStrings);
        addNodes();
    }

    private void addNodes() {
        addColumn(0, questionBodyPanel, optionsPanel);
        getRowConstraints().addAll(new RowConstraints(250), new RowConstraints(250));
    }

}
