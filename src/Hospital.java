import java.util.ArrayList;

public class Hospital {
    Doctor[] doctors;
    Nurse[] nurses;
    Material[] materials;
    ArrayList<Operation> operations;
    ArrayList<Inspection> inspections;
    int minDoctorForOperation,maxNurseForOperation;
    Doctor[] getSurgeonDoctors(){
        ArrayList<Doctor> surgeonDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.docType == DoctorType.SURGEON){
                surgeonDoctors.add(doctor);
            }
        }
        return surgeonDoctors.toArray(new Doctor[surgeonDoctors.size()]);
    }
    void printOperations(){
        for (Operation operation : operations) {
            System.out.println("************************");
            System.out.println("Operation Date: "+operation.date);
            System.out.println("Patient Name: "+operation.patient.name);
            System.out.println("Total Cost: "+operation.totalCost);
            System.out.print("Doctors: ");
            for (Doctor doctor : operation.doctors) {
                System.out.print(doctor.Name+", ");
            }
            System.out.print("\nNurses: ");
            for (Nurse nurse : operation.nurses) {
                System.out.print(nurse.Name+", ");
            }
            System.out.println("\nTotal materials: " + operation.materials.length);
            System.out.println("************************");
        }
    }
    void printInspections(){
        for (Inspection inspection : inspections) {
            System.out.println("************************");
            System.out.println("Inspection Date: "+inspection.date);
            System.out.println("Patient Name: "+inspection.patient.name);
            System.out.println("Total Cost: "+inspection.totalCost);
            System.out.print("Doctors: ");
            for (Doctor doctor : inspection.doctors) {
                System.out.print(doctor.Name+", ");
            }
            System.out.print("\nNurses: ");
            for (Nurse nurse : inspection.nurses) {
                System.out.print(nurse.Name+", ");
            }
            System.out.println("\nTotal materials: " + inspection.materials.length);
            System.out.println("************************");
        }
    }
}
