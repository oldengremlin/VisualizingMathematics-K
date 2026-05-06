package ua.org.olden.visualizingmathematics

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage

class App : Application() {

    override fun start(stage: Stage) {
        Font.loadFont(App::class.java.getResourceAsStream("fonts/DejaVu Sans Mono.ttf"), 13.0)

        scene = Scene(loadFXML("primary"))

        stage.setOnCloseRequest { quit() }
        stage.setOnShown {
            stage.minWidth = stage.width
            stage.minHeight = stage.height
        }

        stage.scene = scene
        stage.show()
    }

    companion object {
        lateinit var scene: Scene

        internal fun setRoot(fxml: String) {
            scene.root = loadFXML(fxml)
        }

        private fun loadFXML(fxml: String) =
            FXMLLoader(App::class.java.getResource("$fxml.fxml")).load<javafx.scene.Parent>()

        fun quit() {
            Platform.exit()
            System.exit(0)
        }

        @JvmStatic
        fun main(args: Array<String>) = launch(App::class.java, *args)
    }
}
