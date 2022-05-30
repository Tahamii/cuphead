module cupheadProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports cupheadProject;
    opens cupheadProject to javafx.fxml;
    exports cupheadProject.View;
    opens cupheadProject.View to javafx.fxml;
    exports cupheadProject.Enums;
    opens cupheadProject.Enums to javafx.fxml;
}
