package anton.dao;

import java.util.List;
import java.util.Optional;

public interface CrudOperations<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void save(T entity);
    void update(int id, T entity);
    void delete(int id);
}
