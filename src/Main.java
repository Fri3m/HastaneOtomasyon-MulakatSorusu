public class Main {
    public static void main(String[] args) {
        /* Anlatılan ve istenen şeylerin ucu açık olduğu için(örneğin ameliyat maliyetinde bir malzemeden bahsediliyor ama ne olduğu belirtilmiyor)
        bu kodda rastgele sayıda doktor, hemşire, malzeme, ameliyat ve muayene oluşturulmuştur.
        çıktıları görmek için Hospital.java dosyasındaki main fonksiyonu kullanılabilir.
         */
        Administration administration = new Administration();
        Hospital hospital = new Hospital();
        Main main = new Main();
        hospital.doctors = main.createRandomDoctors();
        hospital.nurses = main.createRandomNurses();
        hospital.materials = main.createRandomMaterials();
        hospital.minDoctorForOperation = 3;
        hospital.maxNurseForOperation = 10;
        hospital.operations = new java.util.ArrayList<>();
        hospital.inspections = new java.util.ArrayList<>();
        Patient[] patients = main.createRandomPatients();

        for (Patient patient: patients){
            if(Math.random()>0.5){
                administration.makeOperation(hospital, (int)(Math.random()*5+1), (int)(Math.random()*10+1), patient, main.selectMaterial((int)(Math.random()*hospital.materials.length), hospital));
            }
            int inspection = (int)(Math.random()*10);
            for (int i = 0; i < inspection; i++) {
                administration.makeInspection(hospital, (int)(Math.random()*5+1), (int)(Math.random()*10+1), patient, main.selectMaterial((int)(Math.random()*hospital.materials.length), hospital));
            }
        }
        hospital.printOperations();
        hospital.printInspections();
    }
    Material[] selectMaterial(int materialCount,Hospital hospital){
        Material[] materials = new Material[materialCount];
        for (int i = 0; i < materialCount; i++) {
            materials[i] = hospital.materials[i*materialCount/hospital.materials.length];
        }
        return materials;
    }
    Doctor[] createRandomDoctors(){
        int totalDoctors = (int)(Math.random()*40+10),
        totalSurgeonDoctors = (int)(Math.random()*(totalDoctors-5));
        Doctor[] doctors = new Doctor[totalDoctors];
        for (int i = 0; i < totalDoctors; i++) {
            if(i<totalSurgeonDoctors){
                doctors[i] = new Doctor();
                doctors[i].docType = DoctorType.SURGEON;
            }else{
                doctors[i] = new Doctor();
                doctors[i].docType = DoctorType.NORMAL;
            }
            doctors[i].id = i;
            doctors[i].Name = "Doctor"+i;
            doctors[i].salary = (int)(Math.random()*10000+5000);
            doctors[i].operationCost = (int)(Math.random()*1000+500);
        }
        return doctors;
    }
    Nurse[] createRandomNurses(){
        int totalNurses = (int)(Math.random()*90+10);
        Nurse[] nurses = new Nurse[totalNurses];
        for (int i = 0; i < totalNurses; i++) {
            nurses[i] = new Nurse();
            nurses[i].id = i;
            nurses[i].Name = "Nurse"+i;
            nurses[i].salary = (int)(Math.random()*5000+3000);
        }
        return nurses;
    }
    Material[] createRandomMaterials(){
        int totalMaterials = (int)(Math.random()*995+5);
        Material[] materials = new Material[totalMaterials];
        for (int i = 0; i < totalMaterials; i++) {
            materials[i] = new Material();
            materials[i].price = (int)(Math.random()*100+5);
        }
        return materials;
    }
    Patient[] createRandomPatients(){
        int totalPatients = (int)(Math.random()*100+10);
        Patient[] patients = new Patient[totalPatients];
        for (int i = 0; i < totalPatients; i++) {
            patients[i] = new Patient();
            patients[i].id = i;
            patients[i].name = "Patient"+i;
            patients[i].operations = new Operation[0];
            patients[i].inspections = new Inspection[0];
        }
        return patients;
    }

}
