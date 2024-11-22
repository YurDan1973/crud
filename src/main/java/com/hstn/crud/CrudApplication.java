package com.hstn.crud;

import com.hstn.crud.dao.PupilDAO;
import com.hstn.crud.dao.PupilDAOImpl;
import com.hstn.crud.entity.Pupil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PupilDAOImpl pupilDAOImpl) {
        return runner -> {
            createPupil(pupilDAOImpl);
//			readPupilInfo(pupilDAOImpl);
//			getAllPupils(pupilDAOImpl);
//          getPupilByLastName(pupilDAOImpl);
//			updatePupil(pupilDAOImpl);
//          deletePupilById(pupilDAOImpl);
//            deleteAll(pupilDAOImpl);
//            getPupilByFirstName(pupilDAOImpl);
            // при написании этой строки этот метод подчёркивается красным, т.к. такого метода пока
            // у нас нет, далее наводим на него и нажимаем Alt + Enter и создаём его
        };
    }

    private void getPupilByFirstName(PupilDAOImpl pupilDAOImpl) {
        System.out.println("List of all pupils ...");
        List<Pupil> pupils = pupilDAOImpl.findPupilByFirstName("Oleg");
        for (var p : pupils) {
            System.out.println(p);
        }
    }


    private void deleteAll(PupilDAOImpl pupilDAOImpl) {
        System.out.println("Delete all pupils ...");
        int quantityDeletedPupils = pupilDAOImpl.deleteAllPupils();
        System.out.println("Quantity deleted pupils " + quantityDeletedPupils);
    }

    private void deletePupilById(PupilDAOImpl pupilDAOImpl) {
        int idForDeletePupil = 3;
        pupilDAOImpl.delete(3);
        // или так как ниже
        // pupilDAO.delete(idForDeletePupil);
        // System.out.println("Delete Pupil with id = " + idForDeletePupil);
    }

    private void updatePupil(PupilDAOImpl pupilDAOImpl) {

        System.out.println("Getting pupil from DB ...");
        Pupil pupil = pupilDAOImpl.findById(3);
        // Это получение ученика из БД по id (Почему по id? Потому что это поле,
        // в отличие от других точно уникально и мы изменяем данные у нужного нам ученика)

        System.out.println("Pupil before update.");
        System.out.println(pupil);
        // Выводим о нём, о полученном ученике,  информацию

        System.out.println("Update pupil.");
        pupil.setFirstName("Dima");
        pupil.setLastName("Antonov");
        pupil.setEmai("antonov@gmail.com");
        pupilDAOImpl.update(pupil);
        // Изменяем данные ученика

        System.out.println("Pupil after update.");
        System.out.println(pupil);
        // Выводим новую, изменённую информацию на экран
    }

    private void getPupilByLastName(PupilDAOImpl pupilDAOImpl) {
        System.out.println("List of all pupils ...");
        List<Pupil> pupils = pupilDAOImpl.findPupilByLastName("Petrov");
        for (var p : pupils) {
            System.out.println(p);
        }
    }

    private void getAllPupils(PupilDAOImpl pupilDAOImpl) {
        System.out.println("List of all pupils ...");
        List<Pupil> pupils = pupilDAOImpl.findAll();
        for (var p : pupils) {
            System.out.println(p);
        }
    }

    private void readPupilInfo(PupilDAOImpl pupilDAOImpl) {

        System.out.println("Read Pupil info ...");
        Pupil pupil = pupilDAOImpl.findById(1);
        System.out.println("Pupil info from in DB: \n\t" + pupil);
    }

    private void createPupil(PupilDAOImpl pupilDAOImpl) {
        System.out.println("Creating new pupil ...");
        Pupil pupil = new Pupil("Ivan", "Ivanov", "ivanov@gmail.com");
//        Pupil pupil = new Pupil("Oleg", "Petrov", "petrov@gmail.com");
        System.out.println("Saving pupil in DB ...");
        pupilDAOImpl.save(pupil);
        System.out.println("Pupil in DB: \n\t" + pupil);
    }
}