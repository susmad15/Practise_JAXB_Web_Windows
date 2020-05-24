package data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DataHolder")
public class DataHolder {

    private List<Country> countries = new ArrayList<>();

    public DataHolder() {
    }

    @XmlElementWrapper(name = "Countries")
    @XmlElement(name = "Country")
    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

}
