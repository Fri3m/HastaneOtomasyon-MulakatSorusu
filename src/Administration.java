public class Administration {

    boolean makeOperation(Hospital hospital, int docCount, int nurseCount, Patient patient, Material[] materials){
        if (!isPatientAvailableForOperation(patient) ||docCount < hospital.minDoctorForOperation || nurseCount > hospital.maxNurseForOperation || docCount > hospital.doctors.length || nurseCount > hospital.nurses.length) {
            return false;
        }
        Doctor[] operationDoctors = new Doctor[docCount];
        Nurse[] operationNurses = new Nurse[nurseCount];
        for (int i= 0; i < docCount; i++) {
            operationDoctors[i] = hospital.getSurgeonDoctors()[i];
        }
        for (int i = 0;i<nurseCount;i++) {
            operationNurses[i] = hospital.nurses[i];
        }
        Operation operation = createOperation(java.time.LocalDate.now(), operationDoctors, operationNurses, materials, patient);
        hospital.operations.add(operation);
        return true;
    }
    boolean makeInspection(Hospital hospital, int docCount, int nurseCount, Patient patient, Material[] materials){
        if (!isPatientAvailableForInspection(patient) || docCount > hospital.doctors.length || nurseCount > hospital.nurses.length) {
            return false;
        }
        Doctor[] inspectionDoctors = new Doctor[docCount];
        Nurse[] inspectionNurses = new Nurse[nurseCount];
        System.arraycopy(hospital.doctors, 0, inspectionDoctors, 0, docCount);
        System.arraycopy(hospital.nurses, 0, inspectionNurses, 0, nurseCount);
        Inspection inspection = createInspection(java.time.LocalDate.now(), inspectionDoctors, inspectionNurses, materials, patient);
        hospital.inspections.add(inspection);
        return true;
    }


    private Operation createOperation(java.time.LocalDate date, Doctor[] doctors, Nurse[] nurses, Material[] materials, Patient patient){
        Operation operation = new Operation();
        operation.date = date;
        operation.doctors = doctors;
        operation.nurses = nurses;
        operation.materials = materials;
        operation.calculateCost();
        operation.patient = patient;
        return operation;
    }
    private Inspection createInspection(java.time.LocalDate date, Doctor[] doctors, Nurse[] nurses, Material[] materials, Patient patient){
        Inspection inspection = new Inspection();
        inspection.date = date;
        inspection.doctors = doctors;
        inspection.nurses = nurses;
        inspection.materials = materials;
        inspection.patient = patient;
        inspection.totalCost = 0;
        for (Material material : materials) {
            inspection.totalCost += material.price;
        }
        return inspection;
    }
    private boolean isPatientAvailableForOperation(Patient patient){
        patient.rearrangeOperations();
        return patient.operations.length < 1;
    }
    private boolean isPatientAvailableForInspection(Patient patient){
        patient.rearrangeInspections();
        return patient.inspections.length < 5;
    }

}
