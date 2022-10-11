package com.example.patientscheduling;

import com.example.patientscheduling.entities.Appointment;
import com.example.patientscheduling.entities.Doctor;
import com.example.patientscheduling.entities.Insurance;
import com.example.patientscheduling.entities.Patient;
import com.example.patientscheduling.repos.AppointmentRepository;
import com.example.patientscheduling.repos.DoctorRepository;
import com.example.patientscheduling.repos.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.Doc;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PatientschedulingApplicationTests {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void testCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Mark");
        doctor.setLastName("Mirvani");
        doctor.setSpeciality("bone");
        doctorRepository.save(doctor);
    }

    @Test
    public void testCreatePatient() {

        Patient patient = new Patient();
        patient.setFirstName("Doug");
        patient.setLastName("Bailey");
        patient.setPhone("123456");

        Insurance insurance = new Insurance();
        insurance.setProviderName("Star Health");
        insurance.setCopay(10d);
        patient.setInsurance(insurance);

        Doctor doctor = doctorRepository.findById(1L).get();
        Doctor doctor1 = doctorRepository.findById(2L).get();
        List<Doctor> doctors = Arrays.asList(doctor, doctor1);

        patient.setDoctors(doctors);

        patientRepository.save(patient);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setReason("I have a problem");
        appointment.setStarted(true);
        Timestamp appointmentTime = new Timestamp(new Date().getTime());
        appointment.setAppointmentTime(appointmentTime);
        appointment.setPatient(patientRepository.findById(1L).get());
        appointment.setDoctor(doctorRepository.findById(1L).get());

        appointmentRepository.save(appointment);

    }

}
