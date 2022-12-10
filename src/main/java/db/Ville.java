package db;

public class Ville {
    	int ID;
        String  Name;
        String CountryCode;
        String District;
        int Population;
        public Ville(int i, String n, String cc, String d, int p){
            this.ID=i;
            this.Name=n;
            this.CountryCode=cc;
            this.District=d;
            this.Population=p;
        }
}
