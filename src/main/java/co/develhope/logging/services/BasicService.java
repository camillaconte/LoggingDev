package co.develhope.logging.services;

import co.develhope.logging.controllers.BasicController;
import co.develhope.logging.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    @Value("${customEnvs.n1}")
    private int number;

    @Value("${customEnvs.n2}")
    private int power;


    /**
     * Logger nel BasicService:
     * è lo stesso che ho creato in BasicController?
     * non lo so e non mi serve saperlo!
     * è LoggerFactory che se ne occupa, a me non serve sapere nulla
     * è un po' come una versione "primitiva" di @Autowired
     * ".class" = ti passo tutte le info di questa classe
     * perché poi così il Logger può dirmi a proposito di che classe sta dando una info
     */
    Logger log = LoggerFactory.getLogger(BasicService.class);

    public String greetWorld(String nationality) throws Exception {
        if (!nationality.isEmpty()) {
            log.info("Nationality has been typed");
            switch (nationality) {
                case "italian":
                    log.info("Sent service italian greet");
                    return "Buongiorno";
                case "german":
                    log.info("Sent service german greet");
                    return "Guten Morgen!";
                case "english":
                    log.info("Sent service english greet");
                    return "Good Morning!";
                default:
                    log.info("Other nationality - service");
                    return "Hola Hola Hola!";
            }
        } else {
            log.error("Nationality not correctly inserted");
            throw new Exception("Nationality not correctly inserted");
        }
    }

    public int getExponent(){
        log.info("Starting calculating: " + number + " exponent " + power);
        int i;
        int numberPowered = number;
        for(i = power; i > 0; i--){
            numberPowered = numberPowered * number;
            return numberPowered;
        }
        log.info("Calculation ended");
        return numberPowered;
    }


    /**
     * @author Carlo Casiglia
     */
    List<User> users;
    public void createUser (User user) throws Exception{
        if(!user.getEmail().contains("@")){
            //questa riga l'ho aggiunta io
            log.info("Inserted invalid email for user " + user.getName() + " " + user.getSurname());
            throw new Exception("Invalid email, symbol @ absent: " + user.getEmail());
        }
        users.add(user);
        //ora l'info comparirà in consolle
        log.info("Created user in service with valid mail:" + user.getEmail());
    }

}