package Repository;

import java.util.List;

public interface CrudOperation<T> {
    List<T> findAll();
    T save(T toSave);
    T update(T toUpdate);
}
