package User;

import org.json.simple.JSONArray;

public class CoreUser extends User{

    public CoreUser(User user){
        super(user.getPriceRange(),
                user.getTemp(),
                user.getHolidayType(),
                user.getProfilePic(),
                user.getUsername(),
                user.getPassword(),
                user.getNumberOfOrders());
    }

    @Override
    public int getAmount(){
        return 6;
    }

    @Override
    protected void createJSONArray(int placeholder, JSONArray holidaysGot) {
        if (placeholder < 3){
            for (int i = 0; i < 7; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        } else if (placeholder > 20) {
            for (int i = 17; i < 24; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }else {
            for (int i = placeholder - 3; i < placeholder + 4; i++){
                this.holidays.add(holidaysGot.get(i));
            }
        }
    }
    @Override
    public HolidayTypes[] getHolidayOptions(){
        HolidayTypes[] basicHolidayOptions = {HolidayTypes.PRI_MORI, HolidayTypes.TURISTIKA,HolidayTypes.LYZOVANIE,HolidayTypes.SPOZNAVACIE};
        return basicHolidayOptions;
    }

}
