package User_Interface;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;

import Objects.QuizDatabaseRead;
import User_Interface.Screens.Quiz.Question;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Objects.Car;
import Objects.Sign;
import User_Interface.Screens.BaseScreen;

//Singleton for storing information about session
public class Session {

    private static Session ourInstance = new Session();

    private static List<Car> cars;
    private static List<Sign> signs;
    private static DrivingResults drivingResults;

    private Car selectedCar;
    private Stage primaryStage;

    public int currentSpeed = 0;
    public int currentScore = 0;
    public int stopFlag = 0;

    public static Session getInstance() {
        return ourInstance;
    }

    private Session() {
    }
    
    public Objects.Car getSelectedCar() {
    	return selectedCar;
    }

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //Find the selected car based on car Name and set this to the current "car".
    public void selectCar(String carName) {
        selectedCar = getCars().stream().filter(car -> car.getCarName().equals(carName)).findFirst().get();
    }

    //This is used to "repaint" the scene with the new screen, each time a new screen is instantiated.
    public void setNewScreen(BaseScreen baseScreen) {
        double stageWidth = getInstance().getPrimaryStage().getScene().getWidth();
        double stageHeight = getInstance().getPrimaryStage().getScene().getHeight();
        getInstance().getPrimaryStage().setScene(new Scene(baseScreen, stageWidth, stageHeight));
    }

    //This method will check to see if the list of signs is already available on the Session instance, and will
    //retrieve them if available. Otherwise, it will use the static method on the Sign class in order to get the list
    //from the database.
    public List<Sign> getSigns() {
        if (signs == null) {
            Sign.fillSignList();
            signs = Sign.getSignList();
        }

        return signs;
    }

    public List<Question> getRandomQuestions(int numQuestions) {

        List<Question> allQuestions = QuizDatabaseRead.getAllQuestions();
        Collections.shuffle(allQuestions);
        return allQuestions.subList(0, numQuestions);

    }

    //Similar to the getSigns method describe above, except this is for the Cars.
    public static List<Car> getCars() {
        if (cars == null) {
            Car.fillCarList();
            cars = Car.getCarList();
        }
        return cars;
    }

    //Increases speed by 5
    public int increaseSpeed() {
    	currentSpeed = currentSpeed + 5;
    	return currentSpeed;
    }
    
    //Decreases speed by 5
    public int decreaseSpeed() {
    	currentSpeed = currentSpeed - 5;
    	return currentSpeed;
    }
    
    //Returns the current speed of the car
    public int getCurrentSpeed() {
    	return this.currentSpeed;
    }
    
    //Handling the key press up and down
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
                increaseSpeed();
                break;
            case KeyEvent.VK_DOWN:
                decreaseSpeed();
                break;
         }
    } 
    
    //Checking if the vehicle was stopped
    public int checkStop() {
    	if(this.currentSpeed == 0) {
    		stopFlag = 1;
    	} else {
    		stopFlag = 0;
    	}
    	return stopFlag;
    }
    
    //Getting the current sign from sign object based on signID
    public String getCurrentSign(int signID) {
    	Sign sign = new Sign();
    	sign.selectSign(signID);
    	return sign.getSignName();
    }

    //Getting the current sign speed from sign object based on signID
    public int getCurrentSignSpeed(int signID) {
    	Sign sign = new Sign();
    	sign.selectSign(signID);
    	return sign.getSignSpeed();
    }

    
    //This method updates the current score based on the sign just passed
    public int getCurrentScore(int signID) {
    	//If there is a stop sign, and the vehicle was stopped, then increment the points
    	if(signID == 1 && this.checkStop() == 1) {
    		currentScore = currentScore+1;
    	} else {
    		//Else, check for speed limit associated with that sign and determine if score need an increment or a decrement.
    		if(this.getCurrentSignSpeed(signID) >= getCurrentSpeed()) {
        		currentScore = currentScore+1;
        	} else {
        		currentScore = currentScore-1;
        	}
    	}
    
    	return currentScore;
    }

    public void setDrivingResults(DrivingResults newDrivingResults) {
        drivingResults = newDrivingResults;
    }

    public DrivingResults getDrivingResults() {
        return drivingResults;
    }


}
