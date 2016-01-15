package User_Interface.Screens.SelectCar;

import User_Interface.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

//This handle method will be invoked when the user selects a car. It will set the selectedCar in the Session instance,
//and it will also allow the user to click the confirm button and begin driving.
public class SelectCarHandler implements EventHandler<ActionEvent> {

    public SelectCarHandler() {
    }

    public void handle(ActionEvent e) {
        Button clickedButton =  (Button)e.getSource();
        SelectCarScreen selectCarScreen = (SelectCarScreen)clickedButton.getParent().getParent().getParent().getParent();
        selectCarScreen.enableConfirmButton();

        Session.getInstance().selectCar(clickedButton.getText());
    }

}
