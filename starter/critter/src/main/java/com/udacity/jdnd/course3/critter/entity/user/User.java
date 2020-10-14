package com.udacity.jdnd.course3.critter.entity.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Nationalized
    private String name;

    @OneToOne(targetEntity = Customer.class)
    private Customer customer;

    @OneToOne(targetEntity = Employee.class)
    private Employee employee;


    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
