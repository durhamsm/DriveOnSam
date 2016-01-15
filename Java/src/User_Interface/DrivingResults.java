package User_Interface;

import java.util.ArrayList;
import java.util.List;

import Objects.Sign;

//This class will be filled with necessary information during the driving simulation and passed to the DrivingResults
//screen in order to display the results.
public class DrivingResults {
    public List<Sign> violatedSigns = new ArrayList<>();
    public int score = 0;
    public int level = 1;

    public DrivingResults() {

    }
    
    public int getLevel() {
    	return level;
    }
}
