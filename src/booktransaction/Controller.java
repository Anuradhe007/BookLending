package booktransaction;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField book1_person_id, book1_book_id, submit_person_id, submit_book_id;
    @FXML
    private DatePicker book1_given_date, book1_submit_date;
    public void handleBook1Lending() {
        int person_id = Integer.parseInt(book1_person_id.getText());
        int book_id = Integer.parseInt(book1_book_id.getText());

        Validations validations = new Validations();
        int bookTable = validations.checkBorrowerSuitability(person_id);
        if(bookTable == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ඉවත රැගෙන ගිය පොත් නැවත ලබා දී නොමැත.");
            alert.showAndWait();
            book1_person_id.setStyle("-fx-text-box-border: red;");
        } else if(bookTable == 1 || bookTable == 2) {
            book1_person_id.setStyle("");
            if(validations.checkFines(person_id)) {
                if(validations.checkSuitabilityBook(book_id)) {
                    book1_book_id.setStyle("");
                    if(bookTable == 1) {
                        Book1 book1Object = new Book1();
                        book1Object.setStudent_id(Integer.parseInt(book1_person_id.getText()));
                        book1Object.setBook_id(Integer.parseInt(book1_book_id.getText()));
                        book1Object.setGiven_date(String.valueOf(book1_given_date.getValue()));
                        book1Object.setSubmit_date(String.valueOf(book1_submit_date.getValue()));
                        book1Object.setAvailability(0);
                        DBConnection saveDetails = new DBConnection();
                        saveDetails.saveBook1Lending(book1Object);
                        book1_person_id.setText("");
                        book1_book_id.setText("");
                    }
                    if(bookTable == 2) {
                        Book2 book2Object = new Book2();
                        book2Object.setStudent_id(Integer.parseInt(book1_person_id.getText()));
                        book2Object.setBook_id(Integer.parseInt(book1_book_id.getText()));
                        book2Object.setGiven_date(String.valueOf(book1_given_date.getValue()));
                        book2Object.setSubmit_date(String.valueOf(book1_submit_date.getValue()));
                        book2Object.setAvailability(0);
                        DBConnection saveDetails = new DBConnection();
                        saveDetails.saveBook2Lending(book2Object);
                        book1_person_id.setText("");
                        book1_book_id.setText("");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("වෙනත් අයකු මෙම පොත දැනටමත් රැගෙන ගොස් ඇත.");
                    alert.showAndWait();
                    book1_book_id.setStyle("-fx-text-box-border: red;");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("දඩ මුදල් ගෙවීමට ඇත.");
                alert.showAndWait();
            }
        } else {
            System.out.println("Nothing");
        }
    }
    public void cancelBookLending() {
        book1_person_id.setText("");
        book1_person_id.setStyle("");
        book1_book_id.setText("");
        book1_book_id.setStyle("");
    }

    //Method to call submit functionality
    public void submitBook() {
        int person_id = Integer.parseInt(submit_person_id.getText());
        int book_id = Integer.parseInt(submit_book_id.getText());
        DBConn1 dbConn1 = new DBConn1();
        if(dbConn1.submitBook1(person_id,book_id)) {
            submit_person_id.setText("");
            submit_book_id.setText("");
        } else {
            if(dbConn1.submitBook2(person_id,book_id)) {
                submit_person_id.setText("");
                submit_book_id.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("වැරදි පුද්ගල අංකයක් හෝ මෙම පොත බැහැර ගෙන ගොස් නොමැත.");
                alert.showAndWait();
            }
        }
    }

    public void submitCancelBook() {
        submit_person_id.setText("");
        submit_book_id.setText("");
    }
}
