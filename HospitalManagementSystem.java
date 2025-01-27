import java.util.ArrayList;
import java.util.List;

class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Disease: " + disease;
    }
}

class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    public Appointment(Patient patient, Doctor doctor, String date, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment: [Patient: " + patient.getName() + ", Doctor: " + doctor.getName() +
                ", Date: " + date + ", Time: " + time + "]";
    }
}

public class HospitalManagementSystem {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addPatient(int id, String name, int age, String disease) {
        patients.add(new Patient(id, name, age, disease));
    }

    public void addDoctor(int id, String name, String specialization) {
        doctors.add(new Doctor(id, name, specialization));
    }

    public void scheduleAppointment(int patientId, int doctorId, String date, String time) {
        try {
            Patient patient = findPatientById(patientId);
            Doctor doctor = findDoctorById(doctorId);

            if (patient == null) {
                throw new Exception("Patient with ID " + patientId + " not found.");
            }
            if (doctor == null) {
                throw new Exception("Doctor with ID " + doctorId + " not found.");
            }

            appointments.add(new Appointment(patient, doctor, date, time));
            System.out.println("Appointment scheduled successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listPatients() {
        System.out.println("Patients:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void listDoctors() {
        System.out.println("Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    public void listAppointments() {
        System.out.println("Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();

        system.addPatient(1, "John Doe", 30, "Fever");
        system.addPatient(2, "Jane Smith", 25, "Cold");

        system.addDoctor(101, "Dr. Alice", "Pediatrics");
        system.addDoctor(102, "Dr. Bob", "Cardiology");

        system.listPatients();
        system.listDoctors();

        system.scheduleAppointment(1, 101, "2025-02-01", "10:30 AM");
        system.scheduleAppointment(2, 102, "2025-02-01", "11:00 AM");

        system.listAppointments();
    }
}
