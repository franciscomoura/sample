package sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Random;

@RestController
@Validated
public class PersonController {
    private Random ran = new Random();

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //@RequestMapping(path = "/person", method = RequestMethod.POST)
    @PostMapping("/persons")
    public Person person(@Valid @RequestBody Person person,
                         @RequestHeader(value = "x-tenant", required = true) String tenant) {

        /*int nxt = ran.nextInt(10);
        if (nxt >= 5) {
            throw new RuntimeException("Breaking logic");
        }*/
        return personService.save(person);
    }

    @GetMapping("/persons/all")
    public Collection<Person> getAll(@RequestHeader(value = "x-tenant", required = true) String tenant) {
        return personService.getAll();
    }

    //@RequestMapping(path = "/person", method = RequestMethod.GET)
    @GetMapping("/persons")
    public Collection<Person> findByLastName(@RequestParam(name = "lastName", required = true)
                                             @NotNull
                                             @NotBlank
                                             @Size(max = 10) String lastName,
                                             @RequestHeader(value = "x-tenant", required = true) String tenant) {

        return personService.getByLastName(lastName);
    }

    @GetMapping("/persons/{id}")
    public Person get(@PathVariable("id") Long id,@RequestHeader(value = "x-tenant", required = true) String tenant ) {
        return personService.get(id);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, @RequestHeader(value = "x-tenant", required = true) String tenant) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @Valid @RequestBody Person person,
                                    @RequestHeader(value = "x-tenant", required = true) String tenant) {
        personService.update(id, person);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
