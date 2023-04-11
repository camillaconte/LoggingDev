package co.develhope.logging.controllers;

import co.develhope.logging.entities.User;
import co.develhope.logging.services.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @Autowired
    BasicService service;

    //uno dei possibili modi per creare un oggetto
    //chi ha inventato il Logger non vuole che siamo noi a creare oggetti di quella classe
    //da parte dei programmatori, perché è un'operazione DELICATA
    //quindi non lascia fare Logger log = new Logger()
    //perché Logger è una classe astratta, non posso chiamare il suo costruttore (o magari è privato)
    //quindi dicono: noi ti mettiamo a disposizione un'altra classe
    //che contiene un metodo statico, e lì dentro ci sarà la possibilità di fare "new"
    //LoggerFactory = fabbrica di logger!
    //cosa fa questo metodo: va a vedere se c'è già uin altro logger,
    Logger log = LoggerFactory.getLogger(BasicController.class);


    @GetMapping("/")
    public String friendlyGreet(@RequestParam String nationality) {
        try {
            log.debug("Requested a friendly greet");
            return service.greetWorld(nationality);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Insert nationality as Param and try again!";
        }
    }

    @GetMapping("/exp")
    public int getExponent(){
        return service.getExponent();
    }

    @GetMapping("/errors")
    public String getError(){
        log.error("This is an ERROR logging");
        return "This is an ERROR!";
    }

    User currentUser = new User("Carlo", "Casiglia", "carlo.casiglia");

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            log.debug("Requested the creation of a new user");
            service.createUser(user);
            log.info("Created user in controller " + user.getName() + " " + user.getSurname());
            return ResponseEntity.ok("Created user in controller " + user.getName() + " " + user.getSurname());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}