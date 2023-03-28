package User;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    private int numberOfOrders;

    private int priceRange;
    private int distanceRange;
    private int temp;
    private String holidayType;

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
    public void addNumberOfOrders(){
        this.numberOfOrders ++;
    }
    public int getNumberOfOrders(){
        return this.numberOfOrders;
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
        this.priceRange = 200;
        this.distanceRange = 100;
        this.temp = 0;
        this.holidayType = "";
        this.username = username;
        this.password = password;
    }


}