package bookstoreapp.edu.ateneo.cie199;

/**
 * Created by francis on 10/26/17.
 */

public class Book {
    private String title;
    private String author;
    private int icon;

    public Book(String title, String author, int icon) {
        this.title = title;
        this.author = author;
        this.icon = icon;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "[ " + icon + "] " + title + " by " + author;
    }
}
