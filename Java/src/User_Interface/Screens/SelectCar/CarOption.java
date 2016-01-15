package User_Interface.Screens.SelectCar;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import Objects.Car;
import User_Interface.Control.CustomButton;


//On the SelectCarScreen, there will be a CarOption pane for each of the cars. Each CarOption pane contains and image and
//a select button, with the appropriate handler.
public class CarOption extends FlowPane {
    private ImageView carImageView;
    private CustomButton carSelectButton;


    public CarOption(Car car) {
        this.setOrientation(Orientation.VERTICAL);
        createPanel(car);
    }

    private void createPanel(Car car) {
        carImageView = new ImageView(new Image(car.getImageLocation(), 200, 200, false, true));
        carSelectButton = new CustomButton(car.getCarName());
        carSelectButton.setOnAction(new SelectCarHandler());
        carSelectButton.setAlignment(Pos.CENTER);
        carSelectButton.setPrefSize(225, 40);
        getChildren().addAll(carImageView, carSelectButton);
    }
}


