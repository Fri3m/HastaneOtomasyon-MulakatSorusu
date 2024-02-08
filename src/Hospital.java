import java.time.LocalDate;
import java.time.Period;

public class Hospital {
    final int DOCTOR_COUNT = 20;
    final int NURSE_COUNT = 50;
    Doctor[] doctors;
    Nurse[] nurses;
    Patient[] patients;
    private int materialPrice;
    int totalOperatorDoctorCount = 0;

    public Hospital() {
        initDoctors(DOCTOR_COUNT, 0);
        initNurses(NURSE_COUNT);
        initTotalOperatorDoctorCount();
        initPrices();
    }

    public Hospital(int doctorCount, int nurseCount) {
        initDoctors(doctorCount, 0);
        initNurses(nurseCount);
        initTotalOperatorDoctorCount();
        initPrices();
    }

    public Hospital(int operatorDoctorCount, int normalDoctorCount, int nurseCount) {
        initDoctors(operatorDoctorCount, normalDoctorCount);
        initNurses(nurseCount);
        initTotalOperatorDoctorCount();
        initPrices();
    }

    private void initDoctors(int operatorDoctorCount, int normalDoctorCount) {
        doctors = new Doctor[operatorDoctorCount + normalDoctorCount];
        for (int i = 0; i < operatorDoctorCount; i++) {
            doctors[i] = new Doctor(Doctor.doctorType.OPERATOR);
        }
        for (int i = operatorDoctorCount; i < operatorDoctorCount + normalDoctorCount; i++) {
            doctors[i] = new Doctor(Doctor.doctorType.NORMAL);
        }
    }

    private void initNurses(int nurseCount) {
        nurses = new Nurse[nurseCount];
        for (int i = 0; i < nurseCount; i++) {
            nurses[i] = new Nurse();
        }
    }

    private void initTotalOperatorDoctorCount() {
        for (Doctor doctor : doctors) {
            if (doctor.type == Doctor.doctorType.OPERATOR) {
                totalOperatorDoctorCount++;
            }
        }
    }

    private void initPrices() {
        this.materialPrice = 100;
    }

    int makeInspection(Patient patient) {
        if (patient.makeInspection()) {
            return 1;
        } else {
            return -1;
        }
    }

    int makeSurgery(Patient patient, Doctor[] surgeryDoctors, Nurse[] surgeryNurses, int materialCount) {
        if(surgeryDoctors.length <1)
            return -1;

        if (!patient.makeSurgery())
            return -2;

        if(surgeryNurses.length > 5){
            surgeryNurses = new Nurse[]{surgeryNurses[0],surgeryNurses[1],surgeryNurses[2],surgeryNurses[3],surgeryNurses[4]};
        }

        int totalPrice = materialCount * materialPrice;
        for (Doctor doctor : surgeryDoctors) {
            totalPrice += doctor.doctorPrice;
        }
        for (Nurse nurse : surgeryNurses) {
            totalPrice += nurse.nursePrice;
        }
        return totalPrice;
    }


}

class Doctor {
    doctorType type;
    int doctorPrice = 100;

    Doctor() {
        this.type = doctorType.NORMAL;
    }

    Doctor(doctorType type) {
        this.type = type;
    }

    enum doctorType {
        NORMAL,
        OPERATOR,
    }
}

class Nurse {
    int nursePrice = 100;

    Nurse() {
    }
}

class Patient {
    int id;
    LocalDate lastSurgeryDate;
    Integer[] inspectionCounter;

    public Patient() {
        initInspectionCounter();
        lastSurgeryDate = LocalDate.of(2023, 1, 1);
    }

    public Patient(int id) {
        this();
        this.id = id;
    }
    private void initInspectionCounter(){
        inspectionCounter = new Integer[12];
        for (int i = 0; i < 12; i++) {
            inspectionCounter[i] = 0;
        }
    }

    public boolean makeSurgery() {
        if (isAvailableSurgery()) {
            lastSurgeryDate = LocalDate.now();
            return true;
        } else {
            return false;
        }
    }

    public boolean makeInspection() {
        if (isAvailableForInspection()) {
            inspectionCounter[LocalDate.now().getMonth().getValue() -1]++;
            return true;
        } else {
            return false;
        }
    }

    private boolean isAvailableForInspection() {
        return inspectionCounter[LocalDate.now().getMonth().getValue() -1] <= 3;
    }

    private boolean isAvailableSurgery() {
        Period period = Period.between(lastSurgeryDate, LocalDate.now());
        return period.getYears() >= 1;
    }
}