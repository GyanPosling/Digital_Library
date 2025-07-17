package anton.dao;

import anton.models.Book;
import anton.models.Person;

import java.util.List;

public interface BookDAOInterface extends CrudOperations<Book> {
    List<Book> findByPersonId(int personId);
    void assign(int bookId, Person updatedPerson);
    void release(int bookId);
}
