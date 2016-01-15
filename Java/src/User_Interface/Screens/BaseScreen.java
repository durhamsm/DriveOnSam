package User_Interface.Screens;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.apache.log4j.Logger;

/* @author Sam Durham
 * This is the parent screen that all of the other screens will extend. It is a border pane. The top of the border pane
 * will hold the back button (if applicable) and the name of the screen (if applicable). The rest of the sections (bottom,
 * center, left, right) of the border pane will be merged, and will hold the contents that are added to that "mainPane"
 * in the constructors of the sub type screens.
 */

public class BaseScreen extends BorderPane {
    protected Logger logger = Logger.getLogger(this.getClass());
    private String screenTitle = "";
    protected FlowPane mainPane = new FlowPane();
    protected BorderPane topPane = new BorderPane();
    protected HBox bottomPane = new HBox();
    protected Button backButton = new Button();

    private String styleProperties = "-fx-background-color: lightgreen;";

    public BaseScreen() {
        super.setStyle(styleProperties);
        setupMainPane();
        setupTopPane();
    }

    public BaseScreen(String screenTitle) {
        this.screenTitle = screenTitle;
        super.setStyle(styleProperties);
        setupMainPane();
        setupTopPane();
    }

    //Formatting of main pane is established and added to center position of border pane.
    private void setupMainPane() {
        mainPane.setOrientation(Orientation.VERTICAL);
        mainPane.setVgap(30);
        mainPane.setAlignment(Pos.CENTER);
        setCenter(mainPane);
    }

    //Formatting of top pane is established, and unused panes of border pane are set to null (in order to merge with center).
    private void setupTopPane() {
        Text screenTitleText = new Text(400, 50, screenTitle);
        screenTitleText.setFont(new Font(40));

        topPane.setLeft(backButton);
        topPane.setCenter(screenTitleText);
        topPane.setBottom(null);
        topPane.setTop(null);

        backButton.setGraphic(new ImageView("back arrow.png"));

        setTop(topPane);
        setBottom(null);
        setRight(null);
        setLeft(null);
    }

}