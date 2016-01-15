package User_Interface.Control;

import java.lang.reflect.InvocationTargetException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import User_Interface.Session;
import User_Interface.Screens.BaseScreen;

//This handler is for setting the action for clicking buttons, such that the specified ScreenClass will be instantiated
//and set as the scene of the primary stage.
public class NewScreenButtonHandler implements EventHandler<ActionEvent> {
    @SuppressWarnings("rawtypes")
	protected Class screenTargetClass;
    protected BaseScreen screenTarget;


    @SuppressWarnings("rawtypes")
	public NewScreenButtonHandler(Class newScreen) {
        screenTargetClass = newScreen;
    }

    public void handle(ActionEvent e) {
        Session.getInstance().setNewScreen(getTargetScreenInstance());
    }



    @SuppressWarnings("unchecked")
	protected BaseScreen getTargetScreenInstance() {
        BaseScreen screenTarget = new BaseScreen();

        try {
            screenTarget = (BaseScreen)screenTargetClass.getConstructor().newInstance();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (InstantiationException x) {
            x.printStackTrace();
        }catch (IllegalAccessException x) {
            x.printStackTrace();
        }
        this.screenTarget = screenTarget;
        return screenTarget;
    }

}
