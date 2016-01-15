package User_Interface.Screens.SelectCar;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.layout.GridPane;
import Objects.Car;
import User_Interface.Session;
import javafx.scene.layout.RowConstraints;

//This is a GridPane that will have a car within each grid position.
public class CarOptionsPanel extends GridPane {
    //Easily specify the number of cars per row here.
    public static final int CARS_PER_ROW = 3;
    private List<CarOption> carOptions;

    public CarOptionsPanel() {
        createCarOptions();
        addCarOptionsToPanel();
    }

    //Generate a list of CarOptions (pane to hold each car image and select button)
    private void createCarOptions() {
        List<Car> cars = Session.getInstance().getCars();
        carOptions = cars.stream().map(car -> new CarOption(car)).collect(Collectors.toList());
    }

    //This handles the layout of the CarOptions within the CarOptionPanel, such that we have appropriate cars/row.
    private void addCarOptionsToPanel() {
        int rowIndex = 0;
        int columnIndex = 0;
        for (int index = 0;index < carOptions.size(); ++index) {
            this.add(carOptions.get(index), columnIndex++, rowIndex);
            if (columnIndex > CARS_PER_ROW - 1) {
                columnIndex = 0;
                ++rowIndex;
            }
        }

        getRowConstraints().addAll(new RowConstraints(275), new RowConstraints(275));

    }

}
