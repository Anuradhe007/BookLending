package booktransaction;

/**
 * Created by Prabhath on 9/15/2016.
 */
import javax.persistence.*;

@Entity
@Table(name="book_1")
public class Book1 {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transaction_id;
    @Column(name="student_id")
    private int student_id;
    @Column(name="book_id")
    private int book_id;
    @Column(name="given_date")
    private String given_date;
    @Column(name="submit_date")
    private String submit_date;
    @Column(name="availability")
    private int availability;

    public int getStudent_id() {
        return student_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getGiven_date() {
        return given_date;
    }

    public void setGiven_date(String given_date) {
        this.given_date = given_date;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
