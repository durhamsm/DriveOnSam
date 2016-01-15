package User_Interface.Screens.ViewTrafficRules;

import User_Interface.Session;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

//This will be a grid, with a sign at each position in grid.
public class SignGridPanel extends GridPane {

    public static final int SIGNS_PER_ROW = 5;
    private List<ClickableSignImage> signImageViews;

    public SignGridPanel() {
        addSignsToPanel();
    }

    //sign Image views are create with 150x150 size, ignoring original image ratio.
    private void createSignImageViews() {
        signImageViews = Session.getInstance().getSigns().stream()
                .map(signObject -> new ClickableSignImage(new Image(signObject.getImageLocation(), 125, 125, false, true)))
                        .collect(Collectors.toList());
    }

    //This addes the sign image views to the grid, after calling the createSignImageViews method.
    private void addSignsToPanel() {
        createSignImageViews();
        int rowIndex = 0;
        int columnIndex = 0;
        for (int index = 0;index < signImageViews.size(); ++index) {
            this.add(signImageViews.get(index), columnIndex++, rowIndex);
            if (columnIndex > SIGNS_PER_ROW - 1) {
                columnIndex = 0;
                ++rowIndex;
            }
        }
    }

}
