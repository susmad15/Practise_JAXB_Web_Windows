package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.io.File;
import javax.xml.bind.JAXB;

public class XML_Access {
    
    private final String path;
    
    private static XML_Access theInstance;
    
    private XML_Access() {
        path = "D:\\HTL\\5DHIF\\POS\\Practise_JAXB_Web\\res\\countries.xml";
        
       // path = System.getProperty("user.dir") + "\\res\\countries.xml";
    }
    
    public static XML_Access getInstance() {
        if (theInstance == null) {
            theInstance = new XML_Access();
        }
        
        return theInstance;
    }
    
    public DataHolder loadLocations() {
        File file = new File(path);
        
        if (!file.exists()) {
            System.out.println("Es ist ein Fehler passiert, die Datei existiert nicht!!!");
        }
        
        DataHolder holder = JAXB.unmarshal(file, DataHolder.class);
        
        for (Country c : holder.getCountries()) {
            for (Representative rep : c.getRepresentatives()) {
                rep.setCountry(c);
                
                for (Customer cust : rep.getCustomers()) {
                    cust.setRepresentative(rep);
                }
            }
        }
        
        JAXB.marshal(holder, System.out);
        
        return holder;
    }
    
    public static void main(String[] args) {
        System.out.println("Pfad: " + XML_Access.getInstance().path);
        
        XML_Access.getInstance().loadLocations();
    }
    
}
