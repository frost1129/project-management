import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String fullName;
    private String email;
    private String gender;
    private Department department;
    private double coefficientSalary;
    private double basicSalary;
    private List<Project> listProject = new ArrayList<>();

    //Constructor
    public Employee(int id, String fullName, String email, String gender, Department department,
                    double coefficientSalary, double basicSalary) {
        this.setId(id);
        this.setFullName(fullName);
        this.setEmail(email);
        this.setGender(gender);
        this.setDepartment(department);
        this.setCoefficientSalary(coefficientSalary);
        this.setBasicSalary(basicSalary);
    }

    //Function input information of 1 employee
    public void input() {
        System.out.print("Type ID: ");
        this.id = Integer.parseInt(Configuration.sc.nextLine());

        System.out.print("Type full name: ");
        this.fullName = Configuration.sc.nextLine();

        System.out.print("Type email: ");
        this.email = Configuration.sc.nextLine();

        System.out.print("Type gender: ");
        this.gender = Configuration.sc.nextLine();

        System.out.print("Type department ID: ");
        int departmentId = Integer.parseInt(Configuration.sc.nextLine());
    }

    //Setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public List<Project> getListProject() {
        return listProject;
    }

    public void setListProject(List<Project> listProject) {
        this.listProject = listProject;
    }
}
