package com.codingshuttle.youtube.hospitalManagement.repository;

import com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name); // query method : nomenclature findBy__ -> fill in the variable(same as that of where clause)
    List<Patient> findByBirthDateOrEmail(LocalDate birthData, String email);
    List<Patient> findByNameContaining(String query);

    @Query("SELECT p from Patient p where p.bloodGroup = ?1") // ?1 is used to avoid sql injection attack otherwise we can use :bloodGroup also (: <- mandatory)
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p from Patient p where p.birthDate > ?1 ORDER BY p.birthDate ASC")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("Select new com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup," + " Count(p)) from Patient p group by p.bloodGroup") //jpql (native query dont support projection)
    //List<Object[]> countEachBloodGroup();
    List<BloodGroupCountResponseEntity> countEachBloodGroup();

    @Query(value = "select * from patient", nativeQuery = true) // native query
    Page<Patient> findAllPatient(Pageable pageable);

    @Transactional //mandatory for updation or modifying queries
    @Modifying
    @Query("UPDATE Patient p set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

    //@Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointments();
}
