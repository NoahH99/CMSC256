package cmsc256;

public class CollegeStudent extends Person {

    private Level level;

    public CollegeStudent() {
        super();
        level = Level.Freshman;
    }

    public CollegeStudent(String first, String middle, String last, Address address, String phone, String email, Level level) {
        super(first, middle, last, address, phone, email);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + "College Level: " + level + "\n";
    }

    public enum Level {
        Freshman, Sophomore, Junior, Senior, Graduate
    }
}
