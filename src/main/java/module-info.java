module com.meuprojeto.pomodorofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;



    opens com.meuprojeto.pomodorofx to javafx.fxml;
    exports com.meuprojeto.pomodorofx;

}