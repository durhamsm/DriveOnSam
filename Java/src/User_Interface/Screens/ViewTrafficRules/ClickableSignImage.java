package User_Interface.Screens.ViewTrafficRules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

//In the ViewTrafficRulesScreen, there will be a grid of signs. Each position in grid will contain one of these
//ClickableSignImage's.
public class ClickableSignImage extends ImageView {

    public ClickableSignImage(Image signImage) {
        super(signImage);
        this.setEventHandler(MouseEvent.MOUSE_CLICKED, new SignClickHandler());
    }

    //The sign index can be retrieved from this function, in order to determine which sign should be displayed when one
    //of the signs is clicked.
    public int getSignIndex() {
        SignGridPanel signGridPanel = (SignGridPanel)this.getParent();
        int row = signGridPanel.getRowIndex(this);
        int column = signGridPanel.getColumnIndex(this);
        return row*SignGridPanel.SIGNS_PER_ROW + column;
    }
}


