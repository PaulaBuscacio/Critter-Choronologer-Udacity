package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.pet.Pet;
import com.udacity.jdnd.course3.critter.entity.schedule.Schedule;
import com.udacity.jdnd.course3.critter.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getAllByPets(Pet pet);

    List<Schedule> getAllByPetsIn(List<Pet> pets);

    List<Schedule> getAllByEmployeesContains(Employee employee);
}
