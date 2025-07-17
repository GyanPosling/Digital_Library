package anton.dao;

import anton.models.Book;
import anton.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO implements BookDAOInterface{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Book> findByPersonId(int personId) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE person_id = ?",
                new BeanPropertyRowMapper<>(Book.class),
                personId
        );
    }

    @Override
    public void assign(int bookId, Person updatedPerson) {
        jdbcTemplate.update("SET Book.personId = ? WHERE id = ?", updatedPerson.getId(), bookId);

    }

    @Override
    public void release(int bookId) {
        jdbcTemplate.update("UPDATE Book SET Book.personId = NULL where id = ?", bookId);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Optional<Book> findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    @Override
    public void save(Book entity) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year, personId) VALUES (?, ?, ?, ?)",
                entity.getTitle(), entity.getAuthor(), entity.getYear(), entity.getPersonId());
    }

    @Override
    public void update(int id, Book entity) {
        jdbcTemplate.update("UPDATE Book SET title = ?, author = ?, year = ?, personId = ? where id = ?",
                entity.getTitle(), entity.getAuthor(), entity.getYear(), entity.getPersonId(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }
}
