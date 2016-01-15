package User_Interface.Screens.Quiz;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;


public class Option extends RadioButton {

    private final String textFont =
            "-fx-font-size: 15;";

    public Option(String optionString) {
        setFormatting();
        setText(optionString);
    }

    private void setFormatting() {
        setStyle(textFont);
        setPrefHeight(50);
    }

}
