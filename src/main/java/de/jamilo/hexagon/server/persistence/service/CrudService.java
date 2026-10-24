package de.jamilo.hexagon.server.persistence.service;

import java.util.List;

import de.jamilo.hexagon.server.persistence.repository.CrudRepository;

public abstract class CrudService<T, ID> {
    private final CrudRepository<T, ID> repository;

    protected CrudService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T findById(ID id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T create(T entity) {
        return repository.create(entity);
    }

    public T update(T entity) {
        return repository.update(entity);
    }

    public boolean deleteById(ID id) {
        return repository.deleteById(id);
    }
}
