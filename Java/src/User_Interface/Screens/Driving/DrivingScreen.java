package User_Interface.Screens.Driving;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import User_Interface.Session;
import User_Interface.Screens.BaseScreen;
import User_Interface.Screens.DrivingResultsScreen.DrivingResultsScreen;

/*
 * @author: Sam Durham and Joo-Wang John Lee
 * This is the screen that will be displayed when the player begins driving.
 */

public class DrivingScreen extends BaseScreen {
    private DrivingPanel drivingPanel;

    public DrivingScreen() {
        backButton.setVisible(false);
        setTop(null);
        setupMainPane();
        setKeyListeners();
    }

    private void setupMainPane() {
        drivingPanel = new DrivingPanel();
        this.setCenter(drivingPanel);
    }

    //This method will make calls and instantiate other objects in order to repaint/replace the contents of the main
    //pane, in order to run the driving simulation within this Driving Screen.
    public void runDriving() {
    	this.requestFocus();
        Task<Void> task = new Task<Void>() {
            @Override public Void call() {
                Task<Void> task = this;
                drivingPanel.setTask(task);
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        logger.info(e.getMessage());
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            drivingPanel.moveIt();
                        }
                    });
                    if (isCancelled()) break;
                }
                return null;
            }
        };
        task.setOnCancelled(new javafx.event.EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Session.getInstance().setNewScreen(new DrivingResultsScreen(Session.getInstance().getDrivingResults()));
            }
        });
        
        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();
    }

    private void setKeyListeners() {
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        drivingPanel.speedUp();
                        break;
                    case DOWN:
                        drivingPanel.slowDown();
                        break;
                    default:
                    	break;
                }
            }
        });
    }


}
