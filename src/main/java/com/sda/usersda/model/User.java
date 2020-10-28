package com.sda.usersda.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="Users")
@NamedQuery(name = "dateBetween", query = "select u from User u where u.firstName = :name")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Size(min = 2, max = 25)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 25)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Transient
    private String birthDateStr;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateStr() {
        if(birthDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
            birthDateStr = birthDate.format(formatter);
        }
        return birthDateStr;
    }

    public void setBirthDateStr(String birthDateStr) {
        this.birthDateStr = birthDateStr;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        this.birthDate = LocalDate.parse(birthDateStr, formatter);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
