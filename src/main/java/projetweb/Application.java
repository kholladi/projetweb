package projetweb;
//
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import projetweb.model.Employes;
import projetweb.model.Produits;
import projetweb.repository.EmployesRepository;
import projetweb.repository.ProduitsRepository;



@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        ProduitsRepository repository = context.getBean(ProduitsRepository.class);
        EmployesRepository repositoryemploi = context.getBean(EmployesRepository.class);
        
        // save a couple of products
        repository.save(new Produits("Pomme","fruits",5,9));
        repository.save(new Produits("Abricot","fruits",15,9));
        repository.save(new Produits("banane","fruits",14,9));
        repository.save(new Produits("Datte","fruits",56,9));
        repository.save(new Produits("Fraise","fruits",8,9));
        repository.save(new Produits("Orange","fruits",45,9));
        repository.save(new Produits("Noix","fruits",11,9));
        repository.save(new Produits("Kiwi","fruits",12,9));
        repository.save(new Produits("Melon","fruits",25,9));
        repository.save(new Produits("Citron","fruits",68,9));
        repository.save(new Produits("Avocat","fruits",15,9));
        repository.save(new Produits("Ananas","fruits",14,9));
        repository.save(new Produits("Cerise","fruits",23,9));
        repository.save(new Produits("Cassis","fruits",48,9));
        repository.save(new Produits("Mandarine","fruits",158,9));
        
        
        repositoryemploi.save(new Employes("kholladi", "zino", "2 rue saint fridolin", "0752325410", "kholladi@gmail.com", "aze", "H"));
        repositoryemploi.save(new Employes("Boussouf", "imad", "2 rue saint fridolin", "0752325410", "boussouf@gmail.com", "aze", "H"));
        repositoryemploi.save(new Employes("Elfarouk", "taoufik", "2 rue saint fridolin", "0752325410", "elfarouk@gmail.com", "aze", "H"));
        repositoryemploi.save(new Employes("Benkabchach", "tedj", "2 rue saint fridolin", "0752325410", "benkabchach@gmail.com", "aze", "H"));
        repositoryemploi.save(new Employes("Benattouche", "mohamed", "2 rue saint fridolin", "0752325410", "benattouche@gmail.com", "aze", "H"));


    	
    	
     //   SpringApplication.run(Application.class, args);
    }
}
