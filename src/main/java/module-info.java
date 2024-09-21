module com.a3.mnemosyne {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.commons.codec;


    opens com.a3.mnemosyne to javafx.fxml;
    exports com.a3.mnemosyne;
}