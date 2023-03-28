package GUI;
import User.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import User.*;
public class loginController extends controller {

    @FXML
    private Hyperlink changeToRegisterButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Text errorText;


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void changeToRegister(ActionEvent event) throws IOException {
        errorText.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        registerController controller = new registerController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void takeCredentials(ActionEvent event) throws IOException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        User user = userManager.getUser(usernameText, passwordText);

        if (user != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userPage.fxml"));
            userPageController controller = new userPageController(user);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            errorText.setText("Nesprávne meno alebo heslo");
        }

    }

    public void initialize() {
        changeToRegisterButton.setOnAction(event -> {
            try {
                changeToRegister(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loginButton.setOnAction(event -> {
            try {
                takeCredentials(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
