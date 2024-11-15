package com.hstn.crud.entity;

import jakarta.persistence.*;

@Entity
// Так как Pupil - это класс какой-то сущности, то указываем эту аанотацию
// После этого слева от класса появился знак БД (бочонок)
@Table(name = "pupil")
// Эту аннотацию ставим для того чтобы показать (в параметрах этой аннотации) с какой
// таблицей нашей БД (в My SQL) наш класс (в Java) будет связан
public class Pupil {
    // Теперь колонкам, которые имеются в нашей БД (в My SQL) присвоим соответствующие поля
    // в нашем  классе (в Java)
    // В БД слова в названиях колонок отделяются знаком _, а в Java ничем
    // не отделяются, но каждое сл. слово пишется с большой буквы

    @Id
    // Так как в БД колонка id является первичным ключом, то перед сопоставлением колоки id в БД
    // c полем в классе Java необходимо установить эту аннотацию
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Эта аннотация нужна чтобы установить правила,
    // по которым формируется ключевое поле (колонка) id
    @Column(name = "id")
    // Эта аннотация нужна для того, чтобы показать, что полю id в
    // классе Java соответствует колонка id в БД
    // когда мы в параметрах аннотации пишем name = "", то далее
    // наведя курсор между кавычками и нажав Ctrl + ПРОБЕЛ мы можем
    // выбрать соответствующее нужную нам колонку, НО если наведя курсор там между
    // кавычками нам ничего не предлагается, то необходимо в параметрах аннотации
    // @Table(name = "pupil") наведём курсор между кавычками, нажмём Alt + Enter,
    // далее нажмём Assign Data Sources (Назначение источников данных) и во
    //  всплывающем окне
    // в колонке Data Source выбираем нашу БД, т.е. pupil_info@localhost
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "emai")
    private String emai;

    public Pupil() {
    }
    // Сначала создаём конструктор пустой, т.е. без аргументов

    public Pupil(String firstName, String lastName, String emai) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emai = emai;
    }
    // Далее создаём конструкторы для всех полей кроме ключевого поля,
    // т.к. значение ключевого поля у нас будет генерироваться автоматически

    public int getId() {
        return id;
    }
    // Далее для всех полей создаём сеттеры и геттеры

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emai='" + emai + '\'' +
                '}';
    }
    // Тоже через Alt + Ins переопределяем метод toString()
}
