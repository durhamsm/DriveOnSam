package User_Interface.Screens.Quiz;

import javafx.geometry.Orientation;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class QuestionBodyPanel extends FlowPane {

    private final String questionBodyStyle =
            "-fx-font: 30 serif";
    private final int IMAGE_SIZE = 100;

    protected Text questionText;
    protected ImageView questionImage;

    public QuestionBodyPanel(String questionString, String imagePath) {

        questionText = new Text(questionString);
        questionImage = imagePath.isEmpty() ? null : new ImageView(imagePath);

        addNodes();
        setFormatting();

    }

    private void addNodes() {
        getChildren().add(questionText);

        if (questionImage != null) {
            getChildren().add(questionImage);
        }
    }

    private void setFormatting() {
        questionText.setStyle(questionBodyStyle);

        if (questionImage != null) {
            questionImage.setFitHeight(IMAGE_SIZE);
            questionImage.setPreserveRatio(true);
        }
        setVgap(50);
        questionText.setWrappingWidth(800 - 50);

        setOrientation(Orientation.VERTICAL);
    }

}
