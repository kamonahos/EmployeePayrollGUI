public class Employees {
    private String name;
    private String surname;
    private float basicSalary;
    private String speciality;
    private String year;
    private int oms;
    private int overtime;
    private float finalSalary;


    public Employees(String name, String surname, float basicSalary, String speciality, String year, int oms, int overtime) {
        this.name = name;
        this.surname = surname;
        this.basicSalary = basicSalary;
        this.speciality = speciality;
        this.year = year;
        this.oms = oms;
        this.overtime = overtime;
    }


    public Employees() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public float getBasicSalary() {
        return basicSalary;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getYear() {
        return year;
    }

    public int getOms() {
        return oms;
    }

    public int getOvertime() {
        return overtime;
    }


    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }


    @Override
    public String toString(){

        return name + "#" + surname + "#" + basicSalary + "#" + speciality + "#" + year + "#" + oms + "#" +overtime + "#" + finalSalary;
    }


    public void finalSal(int over){
        if ("Καθηγητής".equals(speciality) || "ΕΔΙΠ".equals(speciality) || "Αναπληρωτής".equals(speciality)|| "Επίκουρος".equals(speciality)|| "Λέκτορας".equals(speciality)){
            this.finalSalary=(float) (this.basicSalary + ((float)overtime/this.oms)*(this.basicSalary*1.5) );
        }
        else{
            this.finalSalary=(float) (this.basicSalary + ((float)overtime/this.oms)*(this.basicSalary*1.6) );
        }
    }

}