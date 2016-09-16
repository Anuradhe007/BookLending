package booktransaction;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField book1_person_id, book1_book_id;
    @FXML
    private DatePicker book1_given_date, book1_submit_date;
    public void handleBook1Lending() {
        Book1 book1Object = new Book1();
        book1Object.setStudent_id(Integer.parseInt(book1_person_id.getText()));
        book1Object.setBook_id(Integer.parseInt(book1_book_id.getText()));
        book1Object.setGiven_date(String.valueOf(book1_given_date.getValue()));
        book1Object.setSubmit_date(String.valueOf(book1_submit_date.getValue()));
        book1Object.setAvailability(0);
        DBConnection saveDetails = new DBConnection();
        saveDetails.saveBook1Lending(book1Object);
        System.out.println("successfully saved");
    }
}
