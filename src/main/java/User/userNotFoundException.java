package User;

public class userNotFoundException extends RuntimeException{

    userNotFoundException(){}


    private String error = "Nesprávne meno alebo heslo";
    public String getError(){
        return error;
    }


}
