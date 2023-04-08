package User;
import java.io.FileReader;

import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Holiday.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class User implements Serializable {
    private String username;
    private String password;
    private String profilePic;

    private int numberOfOrders;

    private int priceRange;
    private int temp;
    private String holidayType;
    protected JSONArray holidays;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.priceRange = 200;
        this.temp = 0;
        this.holidayType = "Pri mori";
        this.profilePic = "profPic.png";
        this.numberOfOrders = 0;
    }

    public User(int priceRange, int temp, String holidayType, String profilePic, String username, String password, int numberOfOrders){
        this.priceRange = priceRange;
        this.temp = temp;
        this.holidayType = holidayType;
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
        this.numberOfOrders = numberOfOrders;
    }

    public JSONArray getHolidaysJson() throws IOException {

        return holidays;
    }

    public void setHolidaysJson(User user) throws IOException {

        setHolidays(user);

    }
    public int getAmount() {
        return 2;
    }
    public void getAdditionalInfo(String url, WebView webView, WebEngine webEngine, Text text){
        text.setText("You need to be at least a repeated user to use this feature");

    }

    private void setHolidays(User user) throws IOException {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/java/Holiday/holidays.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray holidaysGot = (JSONArray) jsonObject.get(this.holidayType.toLowerCase());

            holidaysGot.sort((Comparator<JSONObject>) (o1, o2) -> {
                int temp1 = ((Long) o1.get("distance_from_bratislava")).intValue();
                int temp2 = ((Long) o2.get("distance_from_bratislava")).intValue();
                return Integer.compare(temp1, temp2);
            });

            int i = 23;

            if(user.getPriceRange() != 5000) {

                while (user.getPriceRange() * 2 < ((Long) ((JSONObject) holidaysGot.get(i)).get("distance_from_bratislava")).intValue() && i != getAmount()) {

                    holidaysGot.remove(i);
                    i--;
                }
            }



            holidaysGot.sort((Comparator<JSONObject>) (o1, o2) -> {


                int temp1 = ((Long) o1.get("average_temp")).intValue();
                int temp2 = ((Long) o2.get("average_temp")).intValue();
                return Integer.compare(temp1, temp2);
            });

            this.holidays = new JSONArray();

            int placeholder = 0;


            while (placeholder != holidaysGot.size() && this.temp > ((Long) ((JSONObject) holidaysGot.get(placeholder)).get("average_temp")).intValue()){
                placeholder++;
            }

            createJSONArray(placeholder, holidaysGot);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void createJSONArray(int placeholder, JSONArray holidaysGot){
        if (placeholder == 0){
            for (int i = 0; i < 3; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        } else if (placeholder > 22) {
            for (int i = 21; i < 24; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }else {
            for (int i = placeholder - 1; i < placeholder + 2; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }
    }


    public HolidayTypes[] getHolidayOptions(){
        HolidayTypes[] basicHolidayOptions = {HolidayTypes.PRI_MORI, HolidayTypes.TURISTIKA};
     return basicHolidayOptions;
    }

    public void setHolidayType(String e){
        this.holidayType = e;
    }
    public void setPriceRange(int e){
        this.priceRange = e;
    }
    public void setTemp(int e){
        this.temp = e;
    }
    public void addNumberOfOrders(User user){
        this.numberOfOrders ++;
    }
    public int getNumberOfOrders(){
        return this.numberOfOrders;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public int getPriceRange(){
        return priceRange;
    }
    public int getTemp(){
        return temp;
    }
    public String getHolidayType(){
        return holidayType;
    }


    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

}