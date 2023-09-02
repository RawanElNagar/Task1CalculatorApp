package com.ex.calculatorapp

import android.os.Bundle

import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression


class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.textView)
        display.showSoftInputOnFocus = false
        display.setOnClickListener {
            if (getString(R.string.display).equals(display.getText().toString())){
                display.setText("")
            }
        }


    }
    private fun updateText(toAdd: String) {
        val oldStr = display.text.toString()
        val cursorPosition = display.selectionStart
        val leftHalf = oldStr.substring(0, cursorPosition)
        val rightHalf = oldStr.substring(cursorPosition)
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(toAdd)
        }
        else{
        display.setText(String.format("%s%s%s", leftHalf, toAdd, rightHalf))
        display.setSelection(cursorPosition + 1)
        }}

    fun zeroBTN(view: View){
        updateText("0")
    }
    fun oneBTN(view: View){
        updateText("1")
    }
    fun twoBTN(view: View){
        updateText("2")
    }
    fun threeBTN(view: View){
        updateText("3")
    }
    fun fourBTN(view: View){
        updateText("4")
    }
    fun fiveBTN(view: View){
        updateText("5")
    }
    fun sixBTN(view: View){
        updateText("6")
    }
    fun sevenBTN(view: View){
        updateText("7")
    }
    fun eightBTN(view: View){
        updateText("8")
    }
    fun nineBTN(view: View){
        updateText("9")
    }
    fun multiplyBTN(view: View){
        updateText("*")
    }
    fun divideBTN(view: View){
        updateText("/")
    }
    fun subtractBTN(view: View){
        updateText("-")
    }
    fun addBTN(view: View){
        updateText("+")
    }
    fun clearBTN(view: View){
        display.setText("")
    }
    fun parBTN(view: View){
        val cursorPosition = display.selectionStart
            var openPar = 0
            var closePar = 0
           val textLen = display.text.length

            for (i in 0 until cursorPosition) {
               if (display.text.toString().substring(i, i + 1) == "(") {
                   openPar += 1
               }
                if (display.text.toString().substring(i, i + 1) == ")") {
                    closePar += 1
                }
           }

           if (openPar == closePar || display.text.toString().substring(textLen - 1, textLen) == "(") {
                updateText("(")
           }
           else if (closePar < openPar && display.text.toString().substring(textLen - 1, textLen) != "(") {
               updateText(")")
           }

           display.setSelection(cursorPosition + 1)

    }
    fun expBTN(view: View){
        updateText("^")
    }
    fun plusMinusBTN(view: View){
        updateText("-")
    }
    fun decimalBTN(view: View){
        updateText(".")
    }

    fun equalBTN(view: View) {
        val userExp: String = display.text.toString()

        val exp = Expression(userExp)

        val result = exp.calculate().toString()

        display.setText(result)
        display.setSelection(result.length)
    }

    fun backspaceBTN(view: View) {
        val cursorPosition = display.selectionStart
        val textLen = display.text.length

        if (cursorPosition != 0 && textLen != 0) {
            val selection = display.text as SpannableStringBuilder
            selection.replace(cursorPosition - 1, cursorPosition, "")
            display.text = selection
            display.setSelection(cursorPosition - 1)
        }
    }
}



