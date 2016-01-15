package User_Interface.Screens.ViewTrafficRules;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

//This handler is invoked when a sign is clicked. The handler determines which sign was clicked, the index of that sign,
//and then uses the setViewedSign method to update the displayed sign.
public class SignClickHandler implements EventHandler<MouseEvent> {

    public void handle(MouseEvent e) {
        ClickableSignImage clickedSignImage = (ClickableSignImage)e.getSource();
        ViewTrafficRulesScreen viewTrafficRulesScreen =  (ViewTrafficRulesScreen)clickedSignImage.getParent().getParent().getParent();
        viewTrafficRulesScreen.setViewedSign(clickedSignImage.getSignIndex());
    }
}
