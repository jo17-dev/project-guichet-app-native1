/* doesn't work with source level 1.8:
module com.mycompany.quickcash {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.quickcash to javafx.fxml;
    exports com.mycompany.quickcash;
}
*/
