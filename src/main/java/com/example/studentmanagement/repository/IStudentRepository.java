package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    @Query(value = "select * from students where name like :name", nativeQuery = true)
    Page<Student> searchByName(Pageable pageable, @Param("name") String name);

    List<Student> findAllByOrderByPointAsc();

    List<Student> findAllByOrderByPointDesc();

    List<Student> findStudentByClasses_Id(Long id);

}
