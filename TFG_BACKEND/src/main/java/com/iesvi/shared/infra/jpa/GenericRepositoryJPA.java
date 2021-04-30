package com.iesvi.shared.infra.jpa;

/*
import com.iesvi.shared.domain.repos.GenericRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

public abstract class GenericRepositoryJPA<T, K> implements GenericRepo<T, K> {

    @PersistenceContext
    private EntityManager em;

    private Class<T> type;

    public GenericRepositoryJPA(Class<T> type) {
        this.type = type;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public T findOne(K pk) {
        return em.find(type, pk);
    }

    public Iterable<T> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<T> query = cb.createQuery(type);

        Root<T> root = query.from(type);
        TypedQuery<T> queryExecute = em.createQuery(query);

        return queryExecute.getResultList();
    }

    @Transactional
    public T save(T tipo) {
        em.persist(tipo); //vemos como acepta sin problema el tipo generico
        return tipo;
    }

    @Transactional
    public Boolean delete(T entidad) {
        em.remove(entidad); //vemos como acepta sin problema el tipo generic
        return true;
    }
}*/
