module studit {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.controls;

    exports studit.ui;
    exports studit.core;

    opens studit.ui to javafx.fxml;
}
