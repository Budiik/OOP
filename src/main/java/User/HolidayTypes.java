package User;

public enum HolidayTypes {
    LYZOVANIE("Lyžovanie"), PRI_MORI("Pri mori"), TURISTIKA("Turistika"), SPOZNAVACIE("Spoznávacia");

    @Override
    public String toString(){
        return this.value;
    }
    private String value;
    HolidayTypes(String value) {
        this.value = value;
    }
}
