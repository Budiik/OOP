package GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import User.*;


public class Maingui extends Application{

    private Parent root;

    public void start(Stage primaryStage) throws Exception{


        FXMLLoader loader = new FXMLLoader(Maingui.class.getResource("login.fxml"));
        loginController controller = new loginController();


        loader.setController(controller);
        root = loader.load();
        controller.setStage(primaryStage);

        primaryStage.setTitle("yo");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
