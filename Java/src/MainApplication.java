import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Objects.DatabaseCreate;
import User_Interface.Session;
import User_Interface.Screens.Start.StartScreen;

/* @author Sam Durham and Joo-Wang John Lee
 * Main Application class. This will setup the initial screen, set title of the Window, and will set other formatting
 * items.
 */

public class MainApplication extends Application {

    public void start(Stage primaryStage) {
        Scene scene = new Scene(new StartScreen(), 1000, 1000);
        primaryStage.setTitle("Drive On");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
               Platform.exit();
               System.exit(0);
            }
         });
        Session.getInstance().setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
    	DatabaseCreate createDB = new DatabaseCreate();
    	createDB.execute();
    	
        Application.launch();
    }

}
