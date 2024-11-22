package com.hstn.crud.dao;

import com.hstn.crud.entity.Pupil;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PupilDAOImpl implements PupilDAO {

    private EntityManager entityManager;

    @Autowired
    public PupilDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Pupil pupil) {
        entityManager.persist(pupil);
    }

    @Override
    // На это месте аннотация @Transactional не нужна, т.к. мы не добавляем, не изменяем, не удаляем,
    // а только считываем данные
    public Pupil findById(int id) {
        return entityManager.find(Pupil.class, id);
        // это мы пишем чтобы программа понимала в каком из классов искать параметр id
    }

    @Override
    public List<Pupil> findAll() {
        List<Pupil> pupils = entityManager.createQuery("from Pupil", Pupil.class).getResultList();
//        List<Pupil> pupils = entityManager.createQuery("from Pupil order by lastName desc ", Pupil.class).getResultList();
        return pupils;
    }

    @Override
    public List<Pupil> findPupilByLastName(String lastName) {
        TypedQuery<Pupil> query = entityManager.createQuery("from Pupil where lastName=:searchlastName", Pupil.class);
        query.setParameter("searchlastName", lastName);
        List<Pupil> pupils = query.getResultList();
        return pupils;
    }

    @Override
    @Transactional
    // Здесь эту аннотацию ставим, так как данные получаемые мы будем не считывать, а изменять
    public void update(Pupil pupil) {
        entityManager.merge(pupil);
    }

    @Override
    @Transactional
    // Здесь эту аннотацию ставим, так как данные получаемые мы будем не считывать, а
    // удалять
    public void delete(int id) {
        Pupil pupil = entityManager.find(Pupil.class, id);

        if (pupil != null) {
            entityManager.remove(pupil);
            System.out.println("Delete successful!");
        }
        else {
            System.out.println("No pupil with id=" + id + " in DB!");
        }

    }

    @Override
    @Transactional
    public int deleteAllPupils() {
        int quantityOfDeletedPupils = entityManager.createQuery("delete from Pupil").executeUpdate();
        // Так как результат метода executeUpdate() возвращает количество строк,
        // то запишем его в переменную int quantityOfDeletedPupils
        return quantityOfDeletedPupils;
    }

    @Override
    public List<Pupil> findPupilByFirstName(String firstName) {
        TypedQuery<Pupil> query = entityManager.createQuery("from Pupil where firstName=:searchfirstName", Pupil.class);
        query.setParameter("searchfirstName", firstName);
        List<Pupil> pupils = query.getResultList();
        return pupils;

    }
}
