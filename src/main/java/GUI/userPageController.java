package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import User.*;
import java.io.IOException;
import java.util.EventObject;
import java.io.*;


public class userPageController extends controller{

    @FXML
    private Button questionareButton;
    @FXML
    private ImageView profPic;
    @FXML
    private Text username;
    @FXML
    private Text level;
    @FXML
    private Text currentSettings;
    @FXML
    private Button logoutButton;


    User user;
    userPageController(User user){
        this.user = user;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void changeToQuestionare(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("questionare.fxml"));
        questionareController controller = new questionareController(user);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    void logout(EventObject event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loginController controller = new loginController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws FileNotFoundException {
        questionareButton.setOnAction(event -> {
            try {
                changeToQuestionare(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        username.setText("Ahoj " + user.getUsername() + "!");
        level.setText(String.valueOf("Počet u nás vykonaných cieset je: " + user.getNumberOfOrders()));
        currentSettings.setText(user.getHolidayType() + " | 200 - " + user.getPriceRange() + "€\n100 - " + user.getDistanceRange() + "km | " + user.getTemp() + "°C");
        logoutButton.setOnAction(event -> {
            try {
                logout(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        FileInputStream inputstream = new FileInputStream("src/main/images/profPic.jpg");
        Image image = new Image(inputstream);
        profPic.setImage(image);

    }
}
