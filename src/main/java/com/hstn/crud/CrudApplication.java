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

    private final PupilDAOImpl pupilDAOImpl;

    public CrudApplication(PupilDAOImpl pupilDAOImpl) {
        this.pupilDAOImpl = pupilDAOImpl;
    }

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
            // при написании этой строки этот метод подчёркивается красным, т.к. такого метода пока
            // у нас нет, далее наводим на него и нажимаем Alt + Enter и создаём его
        };
    }

    private void deleteAll(PupilDAO pupilDAO) {
        System.out.println("Delete all pupils ...");
        int quantityDeletedPupils = pupilDAOImpl.deleteAllPupils();
        System.out.println("Quantity deleted pupils " + quantityDeletedPupils);
    }

    private void deletePupilById(PupilDAO pupilDAO) {
        int idForDeletePupil = 3;
        pupilDAO.delete(3);
        // или так как ниже
        // pupilDAO.delete(idForDeletePupil);
        // System.out.println("Delete Pupil with id = " + idForDeletePupil);
    }

    private void updatePupil(PupilDAO pupilDAO) {

		System.out.println("Getting pupil from DB ...");
		Pupil pupil = pupilDAO.findById(3);
        // Это получение ученика из БД по id (Почему по id? Потому что это поле,
        // в отличие от других точно уникально и мы изменяем данные у нужного нам ученика)

		System.out.println("Pupil before update.");
		System.out.println(pupil);
        // Выводим о нём, о полученном ученике,  информацию

		System.out.println("Update pupil.");
        pupil.setFirstName("Dima");
		pupil.setLastName("Antonov");
        pupil.setEmai("antonov@gmail.com");
		pupilDAO.update(pupil);
        // Изменяем данные ученика

		System.out.println("Pupil after update.");
		System.out.println(pupil);
        // Выводим новую, изменённую информацию на экран
	}

	private void getPupilByLastName(PupilDAO pupilDAO) {
        System.out.println("List of all pupils ...");
        List<Pupil> pupils = pupilDAO.findPupilByLastName("Petrov");
        for (var p : pupils) {
            System.out.println(p);
        }
    }

    private void getAllPupils(PupilDAO pupilDAO) {
        System.out.println("List of all pupils ...");
        List<Pupil> pupils = pupilDAO.findAll();
        for (var p : pupils) {
            System.out.println(p);
        }
    }

    private void readPupilInfo(PupilDAO pupilDAO) {

        System.out.println("Read Pupil info ...");
        Pupil pupil = pupilDAO.findById(1);
        System.out.println("Pupil info from in DB: \n\t" + pupil);
    }

    private void createPupil(PupilDAO pupilDAO) {
        System.out.println("Creating new pupil ...");
//		Pupil pupil = new Pupil("Ivan", "Ivanov", "ivanov@gmail.com");
        Pupil pupil = new Pupil("Oleg", "Petrov", "petrov@gmail.com");
        System.out.println("Saving pupil in DB ...");
        pupilDAO.save(pupil);
        System.out.println("Pupil in DB: \n\t" + pupil);
    }
}
