package booktransaction;

/**
 * Created by Prabhath on 9/18/2016.
 */
public class Validations {
    int book_1=0,book_2=0;

    //Check whether that person registered in the system.
    public boolean checkBorrowerAvailability(int person_id) {
        return true;
    }

    //Check the suitability of the person.
    public int checkBorrowerSuitability(int person_id) {
        DBConn1 dbconn1 = new DBConn1();
        book_1 = dbconn1.checkBook1(person_id);
        if(book_1 == 0) {
            System.out.println("call db2");
            book_2 = dbconn1.checkBook2(person_id);
            if(book_2==1) return 2;
            else return 0;
        }
        else return 1;
    }

    //Check fines of the person, if he has fines then return false.
    public boolean checkFines(int person_id) {
        DBConn1 dbconn1 = new DBConn1();
        boolean noFines = dbconn1.checkLateSubmissions(person_id);
        return noFines;
    }

    //Check the suitability of the Book.
    public boolean checkSuitabilityBook(int book_id) {
        boolean bookTable_1, bookTable_2;
        DBConn1 dbconn1 = new DBConn1();
        bookTable_1 = dbconn1.checkSuitabilityBook1(book_id);
        bookTable_2 = dbconn1.checkSuitabilityBook2(book_id);
        if(bookTable_1 && bookTable_2) {
            return true;
        } else return false;
    }
}
