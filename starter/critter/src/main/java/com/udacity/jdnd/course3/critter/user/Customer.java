package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer extends User implements Serializable {

    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class, fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer(){}

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
}