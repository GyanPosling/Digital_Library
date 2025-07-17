package anton.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String title;
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String author;
    @Min(value = 1800, message = "Year should be greater than 1800")
    private int year;
    @Min(value = 0, message = "personId should be greater than 0")
    private Integer personId;

    public Book() {
    }

    public Book(int id, String title, String author,
                int year, Integer personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
