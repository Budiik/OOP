package User;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Holiday.*;
import java.io.*;
import java.util.Comparator;
import java.util.Random;

public class User implements Serializable {
    private String username;
    private String password;
    private String profilePic;

    private int numberOfOrders;

    private int priceRange;
    private int distanceRange;
    private int temp;
    private String holidayType;
    private JSONArray holidays;
    UserManager userManager;

    public JSONArray getHolidaysJson() throws IOException {

        return holidays;
    }

    public void setHolidaysJson() throws IOException {

        setHolidays();

    }

    private void setHolidays() throws IOException {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/java/Holiday/holidays.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray holidaysGot = (JSONArray) jsonObject.get(this.holidayType.toLowerCase());

            holidaysGot.sort((Comparator<JSONObject>) (o1, o2) -> {
                int temp1 = ((Long) o1.get("average_temp")).intValue();
                int temp2 = ((Long) o2.get("average_temp")).intValue();
                return Integer.compare(temp1, temp2);
            });

            this.holidays = new JSONArray();

            int placeholder = 0;


            while (placeholder != 24 && this.temp > ((Long) ((JSONObject) holidaysGot.get(placeholder)).get("average_temp")).intValue()){
                placeholder++;
            }

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






        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public String[] getHolidayOptions(){
     String [] basicHolidayOptions = {"Pri mori", "Turistika"};
     return basicHolidayOptions;
    }

    public void setHolidayType(String e){
        this.holidayType = e;
    }
    public void setPriceRange(int e){
        this.priceRange = e;
    }
    public void setDistanceRange(int e){
        this.distanceRange = e;
    }
    public void setTemp(int e){
        this.temp = e;
    }
    public void addNumberOfOrders(User user){

        this.numberOfOrders ++;
        if (this.numberOfOrders == 5){
            RepeatedUser newUser = new RepeatedUser(user);
            userManager.deleteUser(user);
            userManager.addUser(newUser);
        }
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
    public int getDistanceRange(){
        return distanceRange;
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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.priceRange = 200;
        this.distanceRange = 100;
        this.temp = 0;
        this.holidayType = "Pri mori";
        this.profilePic = "profPic.png";
        this.numberOfOrders = 0;
    }

    public User(int priceRange, int distanceRange, int temp, String holidayType, String profilePic, String username, String password){
        this.priceRange = priceRange;
        this.distanceRange = distanceRange;
        this.temp = temp;
        this.holidayType = holidayType;
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
    }
}