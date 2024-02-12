public class Staff {
    int id,salary;
    String Name;
}

class Doctor extends Staff {
    DoctorType docType;
    int operationCost;
}
enum DoctorType {
    SURGEON, NORMAL
}
class Nurse extends Staff {
}
