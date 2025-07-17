package anton.dao;

import anton.models.Person;

import java.util.Optional;

public interface PersonDAOInterface extends CrudOperations<Person> {
    Optional<Person> findByFullName(String fullName);
}
