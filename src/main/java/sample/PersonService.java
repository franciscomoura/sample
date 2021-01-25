package sample;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final Map<Long, Person> cache;
    private static Long SEQUENCE = 1L;

    public PersonService() {
        this.cache = new HashMap<>();

        Person person = new Person()
        .setAge(20)
        .setCreditCardNumber("4111111111111111")
        .setEmail("abc@abc.com")
        .setEmail1("abc1@abc.com")
        .setFirstName("Bruce")
        .setLastName("Lee")
        .setId(SEQUENCE);
        cache.put(SEQUENCE, person);
        SEQUENCE++;
    }

    public Person save(Person person) {
        if (Objects.nonNull(person)) {
            person.setId(SEQUENCE);
            cache.put(SEQUENCE, person);
            SEQUENCE++;
            return person;
        }

        throw new ResourceNotFoundException("Uma person null foi fornecida");
    }

    public Person get(Long id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        throw new ResourceNotFoundException("Recurso não encontrado");
    }

    public List<Person> getByLastName(String lastname) {

        if (Objects.nonNull(lastname)) {
            //cache.values().stream().filter(person -> person.getLastName().equals(lastname))
            return cache
                    .values()
                    .stream()
                    .filter(p -> p.getLastName().equals(lastname))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        throw new ResourceNotFoundException("Recurso não encontrado");
    }

    public void delete(Long id) {
        if (Objects.nonNull(id) && cache.containsKey(id)) {
            Person remove = cache.remove(id);
            return;
        }
        throw new ResourceNotFoundException("Recurso não encontrado");
    }

    public void update(Long id, Person person) {
        if (Objects.nonNull(id) && cache.containsKey(id)) {
            cache.replace(id, person);
            return;
        }
        throw new ResourceNotFoundException("Recurso não encontrado");
    }

    public Collection<Person> getAll() {
        return cache.values();
    }
}
