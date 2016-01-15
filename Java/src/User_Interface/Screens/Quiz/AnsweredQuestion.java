package User_Interface.Screens.Quiz;

import javafx.scene.paint.Color;

public class AnsweredQuestion extends Question {

    private final Color wrongAnswerColor = Color.RED;
    private final Color correctAnswerColor = Color.GREEN;

    private int selectedOptionIndex;
    public boolean isCorrect;

    public AnsweredQuestion(Question question, int selectedOptionIndex) {
        super(question);
        optionsPanel.options.forEach(option -> option.setDisable(true));
        this.selectedOptionIndex = selectedOptionIndex;
        isCorrect = selectedOptionIndex == answerIndex;
        setOptionsFormatting();
    }

    private void setOptionsFormatting() {

        questionBodyPanel.setMaxHeight(300);
        optionsPanel.setMaxHeight(200);

        if (!isCorrect) {
            optionsPanel.options.get(selectedOptionIndex).setTextFill(wrongAnswerColor);
            optionsPanel.options.get(answerIndex).setTextFill(correctAnswerColor);
        }

    }




}
