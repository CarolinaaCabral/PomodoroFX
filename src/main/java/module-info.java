module com.meuprojeto.pomodorofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.meuprojeto.pomodorofx to javafx.fxml;
    exports com.meuprojeto.pomodorofx;
}