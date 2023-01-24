package cmsc256;

/**
 * Created by Noah Hendrickson on 1/24/2023
 */
public class MyBook implements Comparable<MyBook> {

    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    public MyBook() {
        setTitle("None Given");
        setAuthorFirstName("None Given");
        setAuthorLastName("None Given");
        setISBN10("0000000000");
        setISBN13("0000000000000");
    }

    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13) {
        setTitle(title);
        setAuthorFirstName(authorFirstName);
        setAuthorLastName(authorLastName);
        setISBN10(ISBN10);
        setISBN13(ISBN13);
    }


    @Override
    public int compareTo(MyBook o) {
        int lastNameCmp = authorLastName.compareTo(o.authorLastName);
        if (lastNameCmp != 0) return lastNameCmp;

        int firstNameCmp = authorFirstName.compareTo(o.authorFirstName);
        if (firstNameCmp != 0) return firstNameCmp;

        return this.title.compareTo(o.title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null) throw new IllegalArgumentException("Title cannot be null.");

        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) throws IllegalArgumentException {
        if (authorFirstName == null) throw new IllegalArgumentException("Author First Name cannot be null.");

        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) throws IllegalArgumentException {
        if (authorLastName == null) throw new IllegalArgumentException("Author Last Name cannot be null");

        this.authorLastName = authorLastName;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) throws IllegalArgumentException {
        if (ISBN10 == null) throw new IllegalArgumentException("ISBN10 cannot be null.");
        if (!ISBN10.matches("[0-9]+")) throw new IllegalArgumentException("ISBN10 must only contain numbers.");
        if (ISBN10.length() != 10) throw new IllegalArgumentException("ISBN10 must have a length of 10.");

        this.ISBN10 = ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) throws IllegalArgumentException {
        if (ISBN13 == null) throw new IllegalArgumentException("ISBN13 cannot be null.");
        if (!ISBN13.matches("[0-9]+")) throw new IllegalArgumentException("ISBN13 must only contain numbers.");
        if (ISBN13.length() != 13) throw new IllegalArgumentException("ISBN13 must have a length of 13.");

        this.ISBN13 = ISBN13;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyBook)) return false;

        MyBook myBook = (MyBook) o;

        if (!ISBN10.equals(myBook.ISBN10)) return false;
        return ISBN13.equals(myBook.ISBN13);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (authorFirstName != null ? authorFirstName.hashCode() : 0);
        result = 31 * result + (authorLastName != null ? authorLastName.hashCode() : 0);
        result = 31 * result + (ISBN10 != null ? ISBN10.hashCode() : 0);
        result = 31 * result + (ISBN13 != null ? ISBN13.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Author: " + authorFirstName + " " + authorLastName + "\n" +
                "ISBN10: " + ISBN10 + "\n" +
                "ISBN13: " + ISBN13;
    }
}
