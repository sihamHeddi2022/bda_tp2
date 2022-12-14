package db;

public class Ville {
    	private int ID;
    private String  Name;
    private String CountryCode;
    private   String District;
    private  int Population;
        public Ville(int i, String n, String cc, String d, int p){
            this.ID=i;
            this.Name=n;
            this.CountryCode=cc;
            this.District=d;
            this.Population=p;
        }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPopulation() {
        return Population;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public String getName() {
        return Name;
    }
}
