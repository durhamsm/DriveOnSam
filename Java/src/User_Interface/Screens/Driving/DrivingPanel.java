package User_Interface.Screens.Driving;

/*
 * @author Sam Durham, Joo-Wang John Lee and Paul Spears
 * This is the main driving panel that will be composed of animation and driving status panels.
 */

import java.util.List;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import Objects.Level;
import Objects.Sign;
import User_Interface.DrivingResults;
import User_Interface.Session;

public class DrivingPanel extends BorderPane {
    private boolean isGameOver;
    private boolean isSignShown = false;
    private static final int MAXSPEED = 100;
    private List<Sign> levelSigns;
    private int currentSignIndex;
    private DrivingResults drivingResults = new DrivingResults();
    private Task<Void> task;

    private int speed = 0;
    private AnimationPanel animationPanel = new AnimationPanel();
    private DrivingStatusPanel drivingStatusPanel = new DrivingStatusPanel(new DrivingResults());


    public DrivingPanel() {
        Session.getInstance().setDrivingResults(drivingResults);
        loadLevelSigns();
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(animationPanel);
        this.setCenter(swingNode);
        this.setRight(drivingStatusPanel);
        setTop(null);
        setBottom(null);
        setLeft(null);
        animationPanel.setDistanceToSign(getSpacingBetweenSigns());
    }

    public void setTask(Task<Void> task) {
        this.task = task;
    }

    public void speedUp() {
        if (speed < MAXSPEED) {
            speed+=5;
            drivingStatusPanel.speedPanel.updateValue(speed);
        }
    }

    public void slowDown() {
        if (speed > 0) {
            speed-=5;
            drivingStatusPanel.speedPanel.updateValue(speed);
        }
    }

    public int getSpacingBetweenSigns() {
        return -2000*5/levelSigns.size();
    }

    private void loadLevelSigns() {
    	Level level = new Level();
    	level.selectLevel(getLevel());
        levelSigns = level.getSignList();
    }

    public void moveIt() {
        if (!animationPanel.moveIt(speed))
            return;
        int distanceToSign = animationPanel.getDistanceToSign();

        if (distanceToSign > AnimationPanel.BOTTOM_OF_SCREEN) {
            gradeSign();
        } else if (distanceToSign > 0 && isSignShown == false) {
            isSignShown = true;
            drivingStatusPanel.signPanel.displaySign(getCurrentSign());
            drivingStatusPanel.speedLimitPanel.updateValue(getCurrentSign().getSignSpeed());
        } else if (getCurrentSign().getSignName().equals("Stop") && shouldGradeStopSign(distanceToSign)) {
            gradeSign();
        }

    }

    private boolean shouldGradeStopSign(int distanceToSign) {
        boolean isInStopRange = AnimationPanel.getSignY() > AnimationPanel.getCarOffsetY()-100
        		&& AnimationPanel.getSignY() < AnimationPanel.getCarOffsetY()+100;
        return speed == 0 && isInStopRange;
    }

    private void gradeSign() {

        if (speed <= getCurrentSign().getSignSpeed()) {
            Session.getInstance().getDrivingResults().score+=getCurrentSign().getSignPoints();
        } else {
            showSignViolatedMessage();
            Session.getInstance().getDrivingResults().violatedSigns.add(getCurrentSign());
        }
        isSignShown = false;
        drivingStatusPanel.signPanel.removeSign();
        drivingStatusPanel.speedLimitPanel.updateValue(-1);

        isGameOver();

        drivingStatusPanel.updatePanel(Session.getInstance().getDrivingResults());
    }

    private void isGameOver() {
        if (Session.getInstance().getDrivingResults().violatedSigns.size() == 3) {
            failDrivingSimulation();
        } else if (currentSignIndex == levelSigns.size() - 1) {
            leaveLevel();
        } else {
            loadNextSign();
        }
    }

    private void leaveLevel() {
        drivingStatusPanel.speedPanel.updateValue(speed = 0);
        currentSignIndex = 0;
        showLevelSuccessMessage();
        ++Session.getInstance().getDrivingResults().level;
        if (getLevel() == 4) {
            endDrivingSimulation();
        } else {
            startNextLevel();
        }
    }

    
    public static int getLevel() {
    	return Session.getInstance().getDrivingResults().getLevel();
    }

    private void loadNextSign() {
        ++currentSignIndex;
        animationPanel.setDistanceToSign(getSpacingBetweenSigns());
    }

    private void showSignViolatedMessage() {
        animationPanel.setSignViolated();
    }

    private void showTransitionMessage(String message) {
        animationPanel.showTransitionMessage();
    }

    private void showLevelSuccessMessage() {
        showTransitionMessage("You successfully passed Level " + getLevel() + "!");
    }

    private void endDrivingSimulation() {
        this.isGameOver = true;
        task.cancel();
    }

    private void failDrivingSimulation() {
        endDrivingSimulation();
        showTransitionMessage("GAME OVER");
    }

    private void startNextLevel() {
        drivingStatusPanel.updatePanel(Session.getInstance().getDrivingResults());
        loadLevelSigns();
    }

    private Sign getCurrentSign() {
        return levelSigns.get(currentSignIndex);
    }
}

