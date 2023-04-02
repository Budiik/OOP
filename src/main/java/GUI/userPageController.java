package GUI;

import Holiday.Holiday;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import User.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @FXML
    private Button customProfilePic;
    @FXML
    private VBox vBox;

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
    void updateNumberOfOrders() {
        level.setText("Počet u nás vykonaných cieset je: " + user.getNumberOfOrders());
    }

    void loadHolidayOptions() throws IOException {
        user.setHolidaysJson();
        JSONArray holidayArray = user.getHolidaysJson();
        userManager.updateUser(user);


        for (int i = 0; i < 3; i++) {


            JSONObject holiday = (JSONObject) holidayArray.get(i);
            String name = holiday.get("name").toString();
            String country = holiday.get("country").toString();
            int distanceFromBratislava = Integer.parseInt(holiday.get("distance_from_bratislava").toString());



            int averageTemp = Integer.parseInt(holiday.get("average_temp").toString());

            Pane pane = new Pane();
            pane.setPrefHeight(143.0);
            pane.setPrefWidth(361.0);
            pane.setId("pane" + i);

            ImageView imageView = new ImageView();
            imageView.setFitHeight(98.0);
            imageView.setFitWidth(125.0);
            imageView.setLayoutX(14.0);
            imageView.setLayoutY(23.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setId("cityImage" + i);



            try {
                FileInputStream inputstream = new FileInputStream("src/main/images/" + name + "_image.jpg");
                Image image = new Image(inputstream);
                imageView.setImage(image);

            }catch (Exception e){
                searchImage(name);
                FileInputStream inputstream = new FileInputStream("src/main/images/" + name + "_image.jpg");
                Image image = new Image(inputstream);
                imageView.setImage(image);

            }

            pane.getChildren().add(imageView);


            Button selectButton = new Button();
            selectButton.setLayoutX(329.0);
            selectButton.setLayoutY(10.0);
            selectButton.setMnemonicParsing(false);
            selectButton.setPrefHeight(25.0);
            selectButton.setPrefWidth(34.0);
            selectButton.setText("->");
            selectButton.setId("selectButton" + i);
            selectButton.setOnAction(event -> {
                user.addNumberOfOrders(user);
                updateNumberOfOrders();
            });
            pane.getChildren().add(selectButton);

            Button changeButton = new Button();
            changeButton.setLayoutX(282.0);
            changeButton.setLayoutY(10.0);
            changeButton.setMnemonicParsing(false);
            changeButton.setPrefHeight(25.0);
            changeButton.setPrefWidth(34.0);
            changeButton.setText("+");
            changeButton.setId("changeButton" + i);
            pane.getChildren().add(changeButton);


            Text cityName = new Text();
            cityName.setLayoutX(145.0);
            cityName.setLayoutY(16.0);
            cityName.setStrokeType(StrokeType.OUTSIDE);
            cityName.setStrokeWidth(0.0);
            cityName.setText(name);
            cityName.setWrappingWidth(131.13671875);
            Font font = new Font(15.0);
            cityName.setFont(font);
            cityName.setId("cityName" + i);
            pane.getChildren().add(cityName);

            Text type = new Text();
            type.setLayoutX(145.0);
            type.setLayoutY(56.0);
            type.setStrokeType(StrokeType.OUTSIDE);
            type.setStrokeWidth(0.0);
            type.setText("Krajina: " + country);
            type.setWrappingWidth(217.71430315290172);
            type.setId("type" + i);
            pane.getChildren().add(type);

            Text temp = new Text();
            temp.setLayoutX(145.0);
            temp.setLayoutY(76.0);
            temp.setStrokeType(StrokeType.OUTSIDE);
            temp.setStrokeWidth(0.0);
            temp.setText("Vzdialenosť: " + distanceFromBratislava + "km");
            temp.setWrappingWidth(217.71430315290172);
            temp.setId("temp" + i);
            pane.getChildren().add(temp);

            Text distance = new Text();
            distance.setLayoutX(145.0);
            distance.setLayoutY(96.0);
            distance.setStrokeType(StrokeType.OUTSIDE);
            distance.setStrokeWidth(0.0);
            distance.setText("Priemerná teplota: " + averageTemp + "°C");
            distance.setWrappingWidth(217.71430315290172);
            distance.setId("distance" + i);
            pane.getChildren().add(distance);

            Text price = new Text();
            price.setLayoutX(145.0);
            price.setLayoutY(116.0);
            price.setStrokeType(StrokeType.OUTSIDE);
            price.setStrokeWidth(0.0);
            price.setText("Cena: " + distanceFromBratislava * 0.5 + "€");
            price.setWrappingWidth(217.71430315290172);
            price.setId("price" + i);
            pane.getChildren().add(price);

            vBox.getChildren().add(pane);

            Separator separator = new Separator();
            separator.setPrefWidth(361.0);
            separator.orientationProperty().set(Orientation.HORIZONTAL);

            vBox.getChildren().add(separator);

        }
    }

    void chooseProfPic(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        File destDir = new File("src/main/images");
        String check = user.getProfilePic();
        if (selectedFile != null) {

            Path fileToDelete = Paths.get("src/main/images/" + user.getProfilePic());

            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());

            String currentName = System.currentTimeMillis() + "_" + selectedFile.getName();
            Path destPath = Paths.get(destDir.getAbsolutePath(), currentName);

            try {
                Files.copy(sourcePath, destPath);
                user.setProfilePic(currentName);
                userManager.updateUser(user);
                FileInputStream inputstream2 = new FileInputStream("src/main/images/" + user.getProfilePic());
                Image image2 = new Image(inputstream2);
                profPic.setImage(image2);

            } catch (IOException ignored) {

            }

            if (check.equals("profile.png")){
                try {
                    Files.delete(fileToDelete);
                } catch (IOException ignored) {
                    System.out.println("No file to delete");
                }

            }

        }
    }


    public void searchImage(String name) throws IOException {
        String url = "https://www.google.com/search?q=" + name + "&tbm=isch";

        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        Elements elements = doc.select("img[src^=https://encrypted-tbn0.gstatic.com/images]");

        if (!elements.isEmpty()) {
            Element firstImage = elements.first();
            String imageUrl = firstImage.attr("src");

            String fileName = "src/main/images/" + name + "_image.jpg";
            File file = new File(fileName);
            if (!file.exists()) {
                try (InputStream in = new URL(imageUrl).openStream()) {
                    Files.copy(in, Paths.get(fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("No images found");
        }
    }


    public void initialize() throws IOException {
        loadHolidayOptions();



        questionareButton.setOnAction(event -> {
            try {
                changeToQuestionare(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        username.setText("Ahoj " + user.getUsername() + "!");
        updateNumberOfOrders();

        currentSettings.setText(user.getHolidayType() + " | 200 - " + user.getPriceRange() + "€\n100 - " + user.getDistanceRange() + "km | " + user.getTemp() + "°C");
        logoutButton.setOnAction(event -> {
            try {
                logout(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



        FileInputStream inputstream = new FileInputStream("src/main/images/" + user.getProfilePic());
        Image image = new Image(inputstream);
        profPic.setImage(image);
        customProfilePic.setOnAction(event -> {
            chooseProfPic();
        });

    }
}
