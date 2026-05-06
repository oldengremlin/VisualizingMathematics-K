package ua.org.olden.visualizingmathematics

import javafx.fxml.FXML
import javafx.scene.control.DialogPane
import javafx.stage.Stage

class AboutController {

    @FXML private lateinit var dialog: DialogPane

    @FXML
    private fun close() {
        (dialog.scene.window as Stage).close()
    }
}
