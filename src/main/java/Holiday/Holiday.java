package Holiday;

public class Holiday {
    private String name;
    private String type;
    private int price;
    private int distance;
    private int temp;
    private String pic;

    public Holiday(String name, String type, int price, int distance, int temp, String pic) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.distance = distance;
        this.temp = temp;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    public int getTemp() {
        return temp;
    }

    public String getPic() {
        return pic;
    }
}
