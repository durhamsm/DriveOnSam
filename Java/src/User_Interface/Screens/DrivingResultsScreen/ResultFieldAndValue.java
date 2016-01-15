package User_Interface.Screens.DrivingResultsScreen;

import Objects.Sign;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.List;

//This class is a FlowPane object, that will be hold two other panes: one for the result field and one for the
//value of that same field. e.g.: field = "Score", field value = 20.

public class ResultFieldAndValue extends FlowPane {

    //Set formatting and instantiate nodes (Text and FlowPane).
    private String resultValueStyle = "-fx-font: 15 serif";
    private String resultFieldStyle = "-fx-font: 30 serif";
    private String resultValuePaneStyle = "-fx-background-color: white;";

    protected Text resultFieldText;
    protected Text resultValueText;
    protected FlowPane resultValuePane = new FlowPane();


    //This is a common constructor that is used by the other two constructors, that sets the string to the field and
    //also sets some formatting.
    public ResultFieldAndValue(String resultField) {
        setOrientation(Orientation.VERTICAL);
        setAlignment(Pos.CENTER_LEFT);
        resultValuePane.setAlignment(Pos.CENTER_LEFT);
        resultValuePane.setStyle(resultValuePaneStyle);
        setMaxHeight(100);
        resultValuePane.setMaxHeight(50);
        resultFieldText = new Text(resultField);
        resultFieldText.setStyle(resultFieldStyle);
        getChildren().add(resultFieldText);
    }

    //This constructor is used for generating a simple Field/Value combo, where both are simple strings (i.e. not list
    //of signs as in below constructor).
    public ResultFieldAndValue (String resultField, String resultValue) {
        this(resultField);
        resultValueText = new Text(resultValue);
        resultValueText.setStyle(resultValueStyle);
        resultValuePane.getChildren().add(resultValueText);
        getChildren().add(resultValuePane);
    }

    public ResultFieldAndValue (String resultField, int resultValue) {
        this(resultField, String.valueOf(resultValue));
    }

    //Using this constructor, the "Violated Signs" field and corresponding signs are generated and added to the FlowPane.
    public ResultFieldAndValue (String resultField, List<Sign> violatedSigns) {
        this(resultField);
        setMaxHeight(250);
        resultValuePane.setMaxHeight(100);
        violatedSigns.stream().map(violatedSign -> new ImageView(new Image(violatedSign.getImageLocation(), 100, 100, true, true)))
                .forEach(imageView -> resultValuePane.getChildren().add(imageView));
        getChildren().add(resultValuePane);
    }

    public void updateValue(int newValue) {
        String newText = newValue > -1 ? String.valueOf(newValue): "";
        resultValueText.setText(newText);
    }

}