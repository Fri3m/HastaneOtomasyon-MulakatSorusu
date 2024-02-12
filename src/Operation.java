import java.time.LocalDate;

public class Operation {
    LocalDate date;
    Doctor[] doctors;
    Nurse[] nurses;
    Material[] materials;
    Patient patient;
    int totalCost;
    void calculateCost(){
        for (Doctor doctor : doctors) {
            totalCost += doctor.operationCost;
        }
        for (Material material : materials) {
            totalCost += material.price;
        }
    }
}
class Inspection{
    LocalDate date;
    Doctor[] doctors;
    Nurse[] nurses;
    Material[] materials;
    Patient patient;
    int totalCost;
}
