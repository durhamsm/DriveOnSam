package User_Interface.Screens.SelectCar;

import User_Interface.Control.CustomButton;
import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.Start.StartScreen;

//This screen will be used to select the car for the driving game.
public class SelectCarScreen extends BaseScreen {

    private CustomButton confirmCarButton = new CustomButton("Confirm Car");

    public SelectCarScreen() {
        super("Select Your Car");
        confirmCarButton.setOnAction(new ConfirmCarHandler());
        confirmCarButton.setDisable(true);
        mainPane.getChildren().addAll(new CarOptionsPanel(), confirmCarButton);
        backButton.setOnAction(new NewScreenButtonHandler(StartScreen.class));
    }

    //Once the user selects a car, this method will be called for the SelectCarHandler, such that confirm button can
    //then be clicked.
    public void enableConfirmButton() {
        confirmCarButton.setDisable(false);
    }
}
