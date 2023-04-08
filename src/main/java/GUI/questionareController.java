package GUI;
import User.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EventListener;
import java.util.EventObject;

public class questionareController extends controller {
    @FXML
    private ChoiceBox holidayType;
    @FXML
    private Slider tempRange;
    @FXML
    private Slider priceRange;
    @FXML
    private Text tempNumber;
    @FXML
    private Text distanceNumber;
    @FXML
    private Text priceNumber;
    @FXML
    private Button doneButton;



    User user;
    public questionareController(User user) {
        this.user = user;

    }

    public void doneButton(EventObject event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userPage.fxml"));
        userPageController controller = new userPageController(user);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void initialize() throws IOException {



        holidayType.getItems().addAll(user.getHolidayOptions());
        holidayType.setValue(user.getHolidayType());
        holidayType.setOnAction(event -> {
            user.setHolidayType(holidayType.getValue().toString());
            userManager.updateUser(user);
        });

        priceRange.setValue(user.getPriceRange());
        String euro = " €";
        priceNumber.setText(String.valueOf(user.getPriceRange()) + euro);
        priceRange.valueProperty().addListener((observable, oldValue, newValue) -> {
            user.setPriceRange(newValue.intValue());
            priceNumber.setText(String.valueOf(newValue.intValue()) + euro);
            if (newValue.intValue() == 5000){
                priceNumber.setText(String.valueOf(newValue.intValue()) + "+" + euro);
            }
            userManager.updateUser(user);
        });

        tempRange.setValue(user.getTemp());
        tempNumber.setText(String.valueOf(user.getTemp()) + " °C");
        tempRange.valueProperty().addListener((observable, oldValue, newValue) -> {
            user.setTemp(newValue.intValue());
            tempNumber.setText(String.valueOf(newValue.intValue()) + " °C");
            userManager.updateUser(user);
        });

        doneButton.setOnAction(event -> {
            try {
                doneButton(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });




    }

}
