package ucb.bo.edu.antojitosapp;

/**
 * Created by Pablo on 11/8/2017.
 */

public class BookResponse {

    private String isbn;
    private String title;
    private String author;
    private String publish_date;
    private int number_of_pages;

    public BookResponse(String isbn, String title, String author, String publish_date, int number_of_pages) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publish_date = publish_date;
        this.number_of_pages = number_of_pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

}
