package cmsc256;

public class Testing {

    public static void main(String[] args) {
        CollegeStudent collegeStudent = new CollegeStudent("Noah", "L", "Hendrickson", new Address(), "", "", CollegeStudent.Level.Freshman);

        System.out.println(collegeStudent.toString());
    }
}
