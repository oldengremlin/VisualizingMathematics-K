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
    @FXML private lateinit var value_a: TextField
    @FXML private lateinit var value_b: TextField
    @FXML private lateinit var operationResult: TextArea
    @FXML private lateinit var btn_addAB: Button
    @FXML private lateinit var btn_subAB: Button
    @FXML private lateinit var btn_mulAB: Button
    @FXML private lateinit var btn_divAB: Button
    @FXML private lateinit var btn_sqrtA: Button
    @FXML private lateinit var btn_sqrtB: Button
    @FXML private lateinit var mitem_addAB: MenuItem
    @FXML private lateinit var mitem_subAB: MenuItem
    @FXML private lateinit var mitem_mulAB: MenuItem
    @FXML private lateinit var mitem_divAB: MenuItem
    @FXML private lateinit var mitem_sqrtA: MenuItem
    @FXML private lateinit var mitem_sqrtB: MenuItem

    private var isValidA = false
    private var isValidB = false
    private val IS_NUMBER = Regex("^-?\\d+(\\.\\d+)?$")

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
        StringNumeric(value_a.text.trim()).add(StringNumeric(value_b.text.trim()), true)
    )

    @FXML private fun subAB() = result(
        StringNumeric(value_a.text.trim()).sub(StringNumeric(value_b.text.trim()), true)
    )

    @FXML private fun mulAB() = result(
        StringNumeric(value_a.text.trim()).mul(StringNumeric(value_b.text.trim()), true)
    )

    @FXML private fun divAB() = result(
        StringNumeric(value_a.text.trim()).div(StringNumeric(value_b.text.trim()), 10, true)
    )

    @FXML private fun sqrtA() = result(StringNumeric(value_a.text.trim()).sqrtLongDivision(10, true))
    @FXML private fun sqrtB() = result(StringNumeric(value_b.text.trim()).sqrtLongDivision(10, true))

    @FXML private fun validateA() { isValidA = isValidNumber(value_a.text.trim()); setButtonState() }
    @FXML private fun validateB() { isValidB = isValidNumber(value_b.text.trim()); setButtonState() }

    private fun result(snr: StringNumericRecord) {
        operationResult.text = "Результат обчислення: ${snr.value()}\n\n${snr.visualize()}"
    }

    private fun isValidNumber(v: String) = v.isNotEmpty() && IS_NUMBER.matches(v)

    private fun setButtonState() {
        val bothValid = isValidA && isValidB
        btn_addAB.isDisable = !bothValid
        btn_subAB.isDisable = !bothValid
        btn_mulAB.isDisable = !bothValid
        btn_divAB.isDisable = !bothValid
        btn_sqrtA.isDisable = !isValidA
        btn_sqrtB.isDisable = !isValidB
        mitem_addAB.isDisable = !bothValid
        mitem_subAB.isDisable = !bothValid
        mitem_mulAB.isDisable = !bothValid
        mitem_divAB.isDisable = !bothValid
        mitem_sqrtA.isDisable = !isValidA
        mitem_sqrtB.isDisable = !isValidB
    }
}
