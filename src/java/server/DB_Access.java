package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DB_Access {

    private static DB_Access theInstance;

    private EntityManager em;
    private EntityManagerFactory emf;

    private XML_Access xml;
    
    private DB_Access() {
        connect();
        
        xml = XML_Access.getInstance();
    }

    public void connect() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public void disconnect() {

    }

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }

        return theInstance;
    }

    public void persistDataHolder() {
        DataHolder holder = xml.loadLocations();
        
        em.getTransaction().begin();
        
        holder.getCountries()
              .stream()
              .forEach(em::persist);
        
        em.getTransaction().commit();

    }

    public List<Country> getAllCountries() {
        TypedQuery query = em.createNamedQuery("Country.getAll", Country.class);
        
        return query.getResultList();
    }

    public List<Representative> getRepresentativesOfCountry(String countryName) {
        TypedQuery query = em.createNamedQuery("Representative.getByCountry", Representative.class);
        
        query.setParameter("countryName", countryName);
        
        return query.getResultList();
    }
    
    public List<Customer> getCustomersOfRepresentative(String representativeName) {
        TypedQuery query = em.createNamedQuery("Customer.getByRepresentative", Customer.class);
        
        query.setParameter("representativeName", representativeName);
        
        return query.getResultList();
    }
    
    public static void main(String[] args) {
        System.out.println("Starting to persist the data");
        
        /*DB_Access db = DB_Access.getInstance();
        
        db.persistDataHolder();*/
    }

}
