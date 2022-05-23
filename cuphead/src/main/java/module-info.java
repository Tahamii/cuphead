module cupheadProject {
    requires javafx.controls;
    requires javafx.fxml;

    opens cupheadProject to javafx.fxml;
    exports cupheadProject;
}
