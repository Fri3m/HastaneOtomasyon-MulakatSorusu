import java.time.LocalDate;
import java.util.ArrayList;

public class Material {
    int price;
}
class Patient {
    String name;
    int id;
    Operation[] operations;
    Inspection[] inspections;
    void rearrangeOperations(){
        ArrayList<Operation> operationList = new ArrayList<>();
        for (Operation operation : operations) {
            if(operation.date.isAfter(LocalDate.of(LocalDate.now().getYear()-1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))){
                operationList.add(operation);
            }
        }
        operations = operationList.toArray(new Operation[operationList.size()]);
    }
    void rearrangeInspections(){
        ArrayList<Inspection> inspectionList = new ArrayList<>();
        for (Inspection inspection : inspections) {
            if(inspection.date.isAfter(LocalDate.now().minusMonths(1))){
                inspectionList.add(inspection);
            }
        }
        inspections = inspectionList.toArray(new Inspection[inspectionList.size()]);
    }
}