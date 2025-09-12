package com.codingshuttle.youtube.hospitalManagement;

import com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.entity.type.BloodGroupType;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import com.codingshuttle.youtube.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionalMethod() {
        //Patient patient = patientService.getPatientById(1L);
        //Patient patient = patientRepository.findByName("Diya Patel");

        //List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1992, 12, 1), "dishant.verma@example.com");
//        List<Patient> patientList = patientRepository.findByNameContaining("Di");

        //List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.valueOf("AB_POSITIVE"));

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1990, 5, 10));
//        for(Patient patient : patientList) {
//            System.out.println(patient);
//        }

//        List<Object[]> bloodGroup = patientRepository.countEachBloodGroup();
//        for(Object[] bloodGroup1 : bloodGroup) {
//            System.out.println(bloodGroup1[0] + " " + bloodGroup1[1]);
//        }
//
//        List<Patient> patientList = patientRepository.findAllPatient();
//        for(Patient patient : patientList) {
//            System.out.println(patient);
//        }

//        int rowUpdated = patientRepository.updateNameWithId("Aarjav Sharma", 1L);
//        System.out.println(rowUpdated);


        //Projection
//        List<BloodGroupCountResponseEntity> bloodGroup = patientRepository.countEachBloodGroup();
//        for(BloodGroupCountResponseEntity bloodGroupResponse : bloodGroup) {
//            System.out.println(bloodGroupResponse);
//        }

        //Pagination
        //Advantages : low bandwidth usage, less load on server
        Page<Patient> patientList = patientRepository.findAllPatient(PageRequest.of(0, 2, Sort.by("name"))); //starting 2 patients would be returned
        for(Patient patient : patientList) {
            System.out.println(patient);
        }
    }
}
