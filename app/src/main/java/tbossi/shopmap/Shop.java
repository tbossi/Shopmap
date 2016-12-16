package tbossi.shopmap;

/**
 * Created by tommaso on 16/12/16.
 */
public class Shop {
    private String name;

    private double lat, lon;

    public Shop(String name, double lat, double lon) {
        setName(name);
        setLat(lat);
        setLon(lon);
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("shop name is null");
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
