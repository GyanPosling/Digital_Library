package anton.dao;

import anton.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO implements PersonDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Person> findByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny();
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Optional<Person> findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    @Override
    public void save(Person entity) {
        jdbcTemplate.update("INSERT INTO Person (full_name, birthday_year) VALUES (?, ?)", entity.getName(), entity.getAge());

    }

    @Override
    public void update(int id, Person updatedEntity) {
        jdbcTemplate.update("UPDATE Person SET (full_name = ?, birthday_year = ?) WHERE id = ?)",
                updatedEntity.getName(), updatedEntity.getAge(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }
}