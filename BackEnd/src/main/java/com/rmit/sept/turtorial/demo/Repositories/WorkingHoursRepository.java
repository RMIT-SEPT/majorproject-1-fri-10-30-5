package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkingHoursRepository extends CrudRepository<WorkingHours, Long>
{
    @Override
    Iterable<WorkingHours> findAllById(Iterable<Long> iterable);

    WorkingHours findWorkingHoursByEmpIDEquals(String empID);

    List<WorkingHours> findAllByEmpIDEqualsAndServiceEquals(String empID, String service);

    List<WorkingHours> findAllByEmpIDEquals(String empID);

    List<WorkingHours> findAllByEmpIDEqualsAndServiceEqualsAndWorkDateEquals(String empID, String service, String date);

    List<WorkingHours> findAllByEmpIDEqualsAndServiceEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(String empID, String service, String date, int start, int end);
}
