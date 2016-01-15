package User_Interface.Screens.Quiz;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.stream.Collectors;


public class OptionsPanel extends GridPane  {

    protected List<Option> options;
    protected ToggleGroup optionsToggleGroup;

    public OptionsPanel(List<String> optionStrings) {
        options = optionStrings.stream().map(Option::new).collect(Collectors.toList());
        addColumn(0, options.toArray(new Option[options.size()]));
        setupOptionsListener();
    }

    public int getOptionIndex(Option selectedOption) {
        return options.indexOf(selectedOption);
    }

    private void setupOptionsListener() {
        optionsToggleGroup = new ToggleGroup();
        options.forEach(option -> option.setToggleGroup(optionsToggleGroup));
    }

}
