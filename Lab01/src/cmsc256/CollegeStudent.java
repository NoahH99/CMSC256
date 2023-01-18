package cmsc256;

public class CollegeStudent extends Person {

    private final String[] validLevels = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};

    private String level;

    public CollegeStudent() {
        super();
        level = validLevels[0];
    }

    public CollegeStudent(String first, String middle, String last, Address address, String phone, String email, String level) throws IllegalArgumentException  {
        super(first, middle, last, address, phone, email);

        if (!validateLevel(level)) throw new IllegalArgumentException("Not a valid level!");

        this.level = level;
    }

    private boolean validateLevel(String level) {
        for (String validLevel : validLevels) {
            if (validLevel.equalsIgnoreCase(level)) {
                return true;
            }
        }
        return false;
    }

    public String[] getValidLevels() {
        return validLevels;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + "College Level: " + level + "\n";
    }
}
