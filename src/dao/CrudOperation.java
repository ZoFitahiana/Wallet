package dao;

import java.util.List;

public interface CrudOperation<T> {
    T findById(T id);
    List<T> findAll();
    T save(T toSave);
    List<T> saveAll(List<T> tosave);
    T update(T toUpdate);
}
