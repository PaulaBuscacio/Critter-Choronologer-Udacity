package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getAllByPets(Pet pet);

    List<Schedule> getAllByPetsIn(List<Pet> pets);

    List<Schedule> getAllByEmployeesContains(Employee employee);
}
