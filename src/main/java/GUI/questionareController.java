package GUI;
import User.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class questionareController extends controller {
    @FXML
    public ChoiceBox holidayType;
    @FXML
    public Slider tempRange;
    @FXML
    public Slider distanceRange;
    @FXML
    public Slider priceRange;
    @FXML
    public Text tempNumber;
    @FXML
    public Text distanceNumber;
    @FXML
    public Text priceNumber;




    User user;
    public questionareController(User user) {
        this.user = user;

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void initialize() {
        holidayType.getItems().addAll("U mora", "Turistika", "Lyžovanie", "Spoznávacia");
        holidayType.setValue(user.holidayType);
        holidayType.setOnAction(event -> {
            user.setHolidayType(holidayType.getValue().toString());
        });

        priceRange.setValue(user.priceRange);
        priceNumber.setText(String.valueOf(user.priceRange) + " €");
        priceRange.valueProperty().addListener((observable, oldValue, newValue) -> {
            user.setPriceRange(newValue.intValue());
            priceNumber.setText(String.valueOf(newValue.intValue()) + " €");
            userManager.updateUser(user);
        });

        tempRange.setValue(user.tempRange);
        tempNumber.setText(String.valueOf(user.tempRange) + " °C");
        tempRange.valueProperty().addListener((observable, oldValue, newValue) -> {
            user.setTempRange(newValue.intValue());
            tempNumber.setText(String.valueOf(newValue.intValue()) + " °C");
            userManager.updateUser(user);
        });

        distanceRange.setValue(user.distanceRange);
        distanceNumber.setText(String.valueOf(user.distanceRange) + " km");
        distanceRange.valueProperty().addListener((observable, oldValue, newValue) -> {
            user.setDistanceRange(newValue.intValue());
            distanceNumber.setText(String.valueOf(newValue.intValue()) + " km");
            userManager.updateUser(user);
        });





    }

}
