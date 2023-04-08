package GUI;

import java.io.IOException;

import User.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class extraInfoController extends controller{
    @FXML public WebView webView;
    @FXML public Text text;

    public WebEngine webEngine;

    public void loadWikipediaPage(String name, User user) {

        String googleSearchUrl = "https://www.google.com/search?q=" + name;
        user.getAdditionalInfo(googleSearchUrl, webView, webEngine, text);

    }
}