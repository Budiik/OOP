package User;

import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.simple.JSONArray;

public class RepeatedUser extends User{

    public RepeatedUser(User user){
        super(user.getPriceRange(), user.getTemp(), user.getHolidayType(), user.getProfilePic(), user.getUsername(), user.getPassword(), user.getNumberOfOrders());
    }

    @Override
    public int getAmount(){
        return 4;
    }

    @Override
    public void getAdditionalInfo(String url, WebView webView, WebEngine webEngine, Text text){
        text.setText("");
        webEngine = webView.getEngine();
        webEngine.load(url);

    }


    @Override
    protected void createJSONArray(int placeholder, JSONArray holidaysGot) {
        if (placeholder < 3){
            for (int i = 0; i < 5; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        } else if (placeholder > 20) {
            for (int i = 19; i < 24; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }else {
            for (int i = placeholder - 2; i < placeholder + 3; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }
    }

    @Override
    public HolidayTypes[] getHolidayOptions(){
        HolidayTypes[] basicHolidayOptions = {HolidayTypes.PRI_MORI, HolidayTypes.TURISTIKA, HolidayTypes.LYZOVANIE};
        return basicHolidayOptions;
    }


}
