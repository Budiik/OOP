package GUI;

import User.UserManager;
import javafx.stage.Stage;

public abstract class controller {
    public Stage stage;
    UserManager userManager = new UserManager("src\\main\\java\\User\\users.ser");
}
