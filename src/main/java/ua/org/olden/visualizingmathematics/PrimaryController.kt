package ua.org.olden.visualizingmathematics

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.control.MenuItem
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import ua.org.olden.stringnumeric.StringNumeric
import ua.org.olden.stringnumeric.StringNumericRecord

class PrimaryController {

    @FXML private lateinit var mainWindows: VBox
    @FXML private lateinit var valueA: TextField
    @FXML private lateinit var valueB: TextField
    @FXML private lateinit var operationResult: TextArea
    @FXML private lateinit var btnAddAB: Button
    @FXML private lateinit var btnSubAB: Button
    @FXML private lateinit var btnMulAB: Button
    @FXML private lateinit var btnDivAB: Button
    @FXML private lateinit var btnSqrtA: Button
    @FXML private lateinit var btnSqrtB: Button
    @FXML private lateinit var mitemAddAB: MenuItem
    @FXML private lateinit var mitemSubAB: MenuItem
    @FXML private lateinit var mitemMulAB: MenuItem
    @FXML private lateinit var mitemDivAB: MenuItem
    @FXML private lateinit var mitemSqrtA: MenuItem
    @FXML private lateinit var mitemSqrtB: MenuItem

    private var isValidA = false
    private var isValidB = false

    @FXML private fun quit() = App.quit()

    @FXML
    private fun about() {
        val dialogPane: DialogPane = FXMLLoader(App::class.java.getResource("about.fxml")).load()
        Dialog<Void>().apply {
            setDialogPane(dialogPane)
            initOwner(mainWindows.scene.window)
            show()
        }
    }

    @FXML private fun addAB() = result(
        StringNumeric(valueA.text.trim()).add(StringNumeric(valueB.text.trim()), true)
    )

    @FXML private fun subAB() = result(
        StringNumeric(valueA.text.trim()).sub(StringNumeric(valueB.text.trim()), true)
    )

    @FXML private fun mulAB() = result(
        StringNumeric(valueA.text.trim()).mul(StringNumeric(valueB.text.trim()), true)
    )

    @FXML private fun divAB() = result(
        StringNumeric(valueA.text.trim()).div(StringNumeric(valueB.text.trim()), 10, true)
    )

    @FXML private fun sqrtA() = result(StringNumeric(valueA.text.trim()).sqrtLongDivision(10, true))
    @FXML private fun sqrtB() = result(StringNumeric(valueB.text.trim()).sqrtLongDivision(10, true))

    @FXML
    private fun validateA() {
        isValidA = isValidNumber(valueA.text.trim())
        setButtonState()
    }

    @FXML
    private fun validateB() {
        isValidB = isValidNumber(valueB.text.trim())
        setButtonState()
    }

    private fun result(snr: StringNumericRecord) {
        operationResult.text = "Результат обчислення: ${snr.value()}\n\n${snr.visualize()}"
    }

    private fun isValidNumber(v: String) = v.isNotEmpty() && numberRegex.matches(v)

    private fun setButtonState() {
        val bothValid = isValidA && isValidB
        listOf(btnAddAB, btnSubAB, btnMulAB, btnDivAB).forEach { it.isDisable = !bothValid }
        listOf(mitemAddAB, mitemSubAB, mitemMulAB, mitemDivAB).forEach { it.isDisable = !bothValid }
        btnSqrtA.isDisable = !isValidA
        btnSqrtB.isDisable = !isValidB
        mitemSqrtA.isDisable = !isValidA
        mitemSqrtB.isDisable = !isValidB
    }

    companion object {
        private val numberRegex = Regex("^-?\\d+(\\.\\d+)?$")
    }
}
