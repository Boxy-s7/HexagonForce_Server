package de.jamilo.hexagon.server.persistence.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public abstract class CrudRepository<T, ID> {
    protected final EntityManager entityManager;
    private final Class<T> entityType;

    protected CrudRepository(EntityManager entityManager, Class<T> entityType) {
        this.entityManager = entityManager;
        this.entityType = entityType;
    }

    public T findById(ID id) {
        return entityManager.find(entityType, id);
    }

    public List<T> findAll() {
        String entityName = entityType.getSimpleName();
        return entityManager.createQuery("select e from " + entityName + " e", entityType)
                .getResultList();
    }

    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Transactional
    public T update(T entity) {
        T updated = entityManager.merge(entity);
        entityManager.flush();
        return updated;
    }

    @Transactional
    public boolean deleteById(ID id) {
        T entity = findById(id);
        if (entity == null) {
            return false;
        }
        entityManager.remove(entity);
        entityManager.flush();
        return true;
    }
}





