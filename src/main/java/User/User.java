package User;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public int priceRange;
    public int distanceRange;
    public int tempRange;
    public String holidayType;

    public void setHolidayType(String e){
        this.holidayType = e;
    }
    public void setPriceRange(int e){
        this.priceRange = e;
    }
    public void setDistanceRange(int e){
        this.distanceRange = e;
    }
    public void setTempRange(int e){
        this.tempRange = e;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public User(String username, String password) {
        this.priceRange = 0;
        this.distanceRange = 0;
        this.tempRange = 0;
        this.holidayType = "";
        this.username = username;
        this.password = password;
    }


}