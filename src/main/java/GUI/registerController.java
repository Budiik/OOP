package GUI;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import User.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class registerController extends controller{

    @FXML
    private Hyperlink changeToLoginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordAgain;
    @FXML
    private Button registerButton;
    @FXML
    private Text errorText;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void changeToLogin(ActionEvent event) throws IOException {
        errorText.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loginController controller = new loginController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void takeCredentials(ActionEvent event) throws IOException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String passwordAgainText = passwordAgain.getText();
        try {
            if (passwordText.equals(passwordAgainText)) {
                User user = new User(usernameText, passwordText);
                if (!userManager.userExists(user)) {
                    userManager.addUser(user);
                    errorText.setFill(Color.GREEN);
                    errorText.setText("Account created successfully");
                } else {
                    errorText.setFill(Color.RED);
                    errorText.setText("Username taken");
                }
            } else {
                throw new Exception("Passwords don't match");
            }
        } catch (Exception e) {
            errorText.setFill(Color.RED);
            errorText.setText(e.getMessage());
        }

    }

    public void initialize() {
        changeToLoginButton.setOnAction(event -> {
            try {
                changeToLogin(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        registerButton.setOnAction(event -> {
            try {
                takeCredentials(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
