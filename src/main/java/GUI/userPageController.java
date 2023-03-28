package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import User.*;
import java.io.IOException;

public class userPageController extends controller{

    @FXML
    private Button questionareButton;

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
    public void initialize() {
        questionareButton.setOnAction(event -> {
            try {
                changeToQuestionare(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
