module ua.org.olden.visualizingmathematics {
    requires javafx.controls;
    requires javafx.fxml;

    requires ua.org.olden.stringnumeric;
    requires java.base;
    requires kotlin.stdlib;

    opens ua.org.olden.visualizingmathematics to javafx.fxml;
    exports ua.org.olden.visualizingmathematics;
}
