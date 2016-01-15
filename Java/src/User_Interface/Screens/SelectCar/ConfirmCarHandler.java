package User_Interface.Screens.SelectCar;

import javafx.event.ActionEvent;
import User_Interface.Control.NewScreenButtonHandler;
import User_Interface.Screens.Driving.DrivingScreen;

//The driving game actually begins when the user confirms their car selection. The event will be handled, which
//will invoke the runDriving method, where the main pane of the DrivingScreen will be repainted/replaced.
public class ConfirmCarHandler extends NewScreenButtonHandler {

    public ConfirmCarHandler() {
        super(DrivingScreen.class);
    }

    public void handle(ActionEvent e) {
        super.handle(e);
        DrivingScreen drivingScreen = (DrivingScreen)this.screenTarget;
        drivingScreen.runDriving();
    }

}
