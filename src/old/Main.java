package old;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.firstQuestion();
        main.secondQuestion();
    }

    void firstQuestion() { //Bir yılda en çok muayene olan hasta kimdir?
        Hospital hospital = new Hospital(20, 50);
        Patient[] patient = new Patient[]{new Patient(1), new Patient(2), new Patient(3), new Patient(4), new Patient(5)};
        patient[0].makeInspection();
        patient[1].makeInspection();
        patient[1].makeInspection();
        patient[2].makeInspection();
        patient[2].makeInspection();
        patient[2].makeInspection();
        patient[3].makeInspection();
        patient[3].makeInspection();
        patient[3].makeInspection();
        patient[4].makeInspection();

        int maxInspection = 0;
        ArrayList<Patient> maxInspectionPatients = new ArrayList<>();
        for (Patient p : patient) {
            if (p.inspectionCounter[1] > maxInspection) {
                maxInspection = p.inspectionCounter[1];
                maxInspectionPatients.clear();
                maxInspectionPatients.add(p);
            } else if (p.inspectionCounter[1] == maxInspection) {
                maxInspectionPatients.add(p);
            }
        }
        System.out.print("Bir yılda en çok muayene olan hasta: ");
        for (Patient p : maxInspectionPatients) {
            System.out.print(p.id + " ");
        }
        System.out.println();
    }

    void secondQuestion() { //ameliyatta oluşan en yüksek maliyet nedir?
        Hospital hospital = new Hospital(20, 50);
        Patient patient = new Patient();
        int maxCost = hospital.makeSurgery(patient, hospital.doctors, hospital.nurses, 6);
        System.out.println("Ameliyatta oluşan en yüksek maliyet: " + maxCost);
    }
}
