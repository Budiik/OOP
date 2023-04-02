package User;

public class RepeatedUser extends User{
    public RepeatedUser(User user) {
        super(user.getPriceRange(), user.getDistanceRange(), user.getTemp(), user.getHolidayType(), user.getProfilePic(), user.getUsername(), user.getPassword());
    }

}
