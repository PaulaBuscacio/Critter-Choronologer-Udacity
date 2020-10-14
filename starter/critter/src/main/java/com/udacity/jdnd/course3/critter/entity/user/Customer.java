package com.udacity.jdnd.course3.critter.entity.user;


import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class, fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    @OneToOne(targetEntity = User.class)
    private User user;

    public Customer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}