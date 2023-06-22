package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Classes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClassesRepository extends CrudRepository<Classes, Long> {
    @Query(value = "select studentCount from countStudentView;", nativeQuery = true)
    List<Integer> countStudent();

    @Query(value = "select studentCount from countStudentView order by studentCount desc ;", nativeQuery = true)
    List<Integer> countStudentDes();

    @Query(value = "select studentCount from countStudentView order by studentCount asc ;", nativeQuery = true)
    List<Integer> countStudentAsc();

    @Query(value = "select id,name from countStudentView order by studentCount asc ", nativeQuery = true)
    List<Classes> sortClassByStudentCountAsc();


    @Query(value = "select id,name from countStudentView order by studentCount desc ", nativeQuery = true)
    List<Classes> sortClassByStudentCountDes();

    @Query(value = "select avg(point) from students right join classes on classes.id = students.classes_id group by classes_id;", nativeQuery = true)
    List<Float> getAvg();

}
