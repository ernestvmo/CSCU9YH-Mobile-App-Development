package uk.ac.stir.cs.yhproject

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.page2_fragment.*
import java.lang.Exception
import java.text.DecimalFormat

class Page2Fragment: Fragment()
{
    /** The PageViewModel used to store value between fragments. */
    private lateinit var model: PageViewModel
    /** The UnitsDatabase object that will be instantiated later. */
    private lateinit var db: UnitsDatabase
    /** The initial unit involved in the conversion. */
    private var unitInitial: String = ""
    /** The unit that the input will be converted to. */
    private var unitConvert: String = ""
    /** The input from the user to be converted. */
    private var input: String = ""
    /** The converted result. */
    private var result: String = ""
    /** A flag to notify whether a conversion has just been made. */
    private var converted = false

    /** The TextView object displaying the initial unit. */
    private lateinit var initialValueTextView: TextView
    /** The EditText object receiving the input from the user. */
    private lateinit var initialUnitBox: EditText
    /** The TextView object displaying the convert to unit. */
    private lateinit var convertedValueTextView: TextView
    /** The EditText object displaying the converted results. */
    private lateinit var convertedUnitBox: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.page2_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the database
        db = UnitsDatabase(context!!)
        // Populate the database with all the unit conversions ratios.
        db.populateDatabase()

        // Create a reference to the PageViewModel object to access the variables.
        model = ViewModelProviders.of(activity!!).get(PageViewModel::class.java)
        // Observe if the value of the initial unit has changed.
        model.unitInitial.observe(this, object: Observer<Any> {
            override fun onChanged(t: Any?)
            {// If changed, updated the class variable and modify the TextView
                initialUnitValue.text = t!!.toString()
                unitInitial = t!!.toString()
            }
        })
        // Observe if the value of the convert to unit has changed.
        model.unitConvert.observe(this, object: Observer<Any> {
            override fun onChanged(t: Any?)
            {// If changed, updated the class variable and modify the TextView
                convertedUnitValue.text = t!!.toString()
                unitConvert = t!!.toString()
            }
        })
        // Observe if the value of the input box has changed.
        model.input.observe(this, object: Observer<Any> {
            override fun onChanged(t: Any?)
            {// If changed, updated the class variable and modify the TextView
                initialUnitBox.setText(t!!.toString())
                input = t!!.toString()
                converted = false
            }
        })
        // Observe if the value of the result box has changed.
        model.result.observe(this, object : Observer<Any> {
            override fun onChanged(t: Any?)
            {// If changed, updated the class variable and modify the TextView
                convertedUnitBox.setText(t!!.toString())
                result = t!!.toString()
                converted = false
            }
        })

        // Instantiate the TextView object
        initialValueTextView = view.findViewById(R.id.initialUnitValue)
        // Add a click listener to the TextView
        initialValueTextView.setOnClickListener {// When the user click on the TextView, switch back to the first tab.
            var tabs: TabLayout = activity!!.findViewById(R.id.tab_layout)
            tabs.getTabAt(0)!!.select()
        }

        // Instantiate the EditText object
        initialUnitBox = view.findViewById(R.id.inputBox)

        // Instantiate the TextView object
        convertedValueTextView = view.findViewById(R.id.convertedUnitValue)
        // Add a click listener to the TextView
        convertedValueTextView.setOnClickListener {// When the user click on the TextView, switch back to the first tab.
            var tabs: TabLayout = activity!!.findViewById(R.id.tab_layout)
            tabs.getTabAt(0)!!.select()
        }

        // Instantiate the EditText object
        convertedUnitBox = view.findViewById(R.id.calculatedResult)

        // Instantiate all the buttons
        val btn0 = view.findViewById(R.id.button_0) as Button
        val btn1 = view.findViewById(R.id.button_1) as Button
        val btn2 = view.findViewById(R.id.button_2) as Button
        val btn3 = view.findViewById(R.id.button_3) as Button
        val btn4= view.findViewById(R.id.button_4) as Button
        val btn5 = view.findViewById(R.id.button_5) as Button
        val btn6 = view.findViewById(R.id.button_6) as Button
        val btn7 = view.findViewById(R.id.button_7) as Button
        val btn8 = view.findViewById(R.id.button_8) as Button
        val btn9 = view.findViewById(R.id.button_9) as Button

        val clearBtn = view.findViewById(R.id.clearBtn) as Button
        val equalsBtn = view.findViewById(R.id.equalsBtn) as Button
        val commaBtn = view.findViewById(R.id.commaBtn) as Button


        initialUnitBox.requestFocus()
        initialUnitBox.showSoftInputOnFocus = false

        btn0.setOnClickListener { addToInput(0) }
        btn1.setOnClickListener { addToInput(1) }
        btn2.setOnClickListener { addToInput(2) }
        btn3.setOnClickListener { addToInput(3) }
        btn4.setOnClickListener { addToInput(4) }
        btn5.setOnClickListener { addToInput(5) }
        btn6.setOnClickListener { addToInput(6) }
        btn7.setOnClickListener { addToInput(7) }
        btn8.setOnClickListener { addToInput(8) }
        btn9.setOnClickListener { addToInput(9) }
        commaBtn.setOnClickListener { addComma() }
        clearBtn.setOnClickListener { clearBox() }
        equalsBtn.setOnClickListener { checkConvertion() }
    }

    /**
     * This method will check that all the units have been selected and then fires the conversion method.
     */
    private fun checkConvertion()
    {
        if ((initialUnitValue.text == "Select unit") || (convertedUnitValue.text == "Select unit"))
        {
            Toast.makeText(context, "You have not selected all the Units!", Toast.LENGTH_LONG).show()
        }
        else
        {
            convert()
        }
    }

    /**
     * This method adds a comma to the input, making it a decimal number.
     */
    private fun addComma()
    {
        var inputString: String = initialUnitBox.text.toString()

        if (!inputString.contains('.'))
            inputString += "."

        initialUnitBox.setText(inputString)
    }

    /**
     * This method will get the ratio from the database and then transform the input into the selected unit by multiplying it with the ratio.
     */
    private fun convert()
    {
        val ratio:Double = db.getRatio(db.readableDatabase, initialUnitValue.text.toString().toLowerCase(), convertedUnitValue.text.toString().toLowerCase())
        var resultDouble: Double
        val dec = DecimalFormat("#,###.####")
        var input: String = initialUnitBox.text.toString()

        // Remove the comma if it is the last digit in the output.
        if (input.last() == '.')
            input = input.substring(0, input.length - 1)

        try {
            resultDouble = input.toDouble() * ratio
            // Change the result to the specified format
            var resultFormatted = dec.format(resultDouble)

            // Set the ExitText to the calculated result
            convertedUnitBox.setText(resultFormatted)
            // Add the conversion just performed to the history.
            addToHistory(model.unitInitial.value.toString(), input, model.unitConvert.value.toString(), resultFormatted)
        }
        catch (e: Exception)
        {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        // Set the flag to true
        converted = true
    }

    /**
     * This method clears the content of the two EditText objects.
     */
    private fun clearBox()
    {
        initialUnitBox.setText("")
        convertedUnitBox.setText("")
        // Set the flag to false
        converted = false
    }

    /**
     * This method add the passed parameter to the EditText object.
     * @param num The value to add to the EditText
     */
    private fun addToInput(num: Int)
    {
        // If the input was previously converted, clear the box
        if (converted)
            clearBox()

        val inputString: String = initialUnitBox.text.toString() + num

        initialUnitBox.setText(inputString)
    }

    /**
     * This method add the conversion to the history list stored in the model and replaces the current ListView in the History tab.
     * @param initialLabel The initial unit name.
     * @param input The input given by the user.
     * @param convertedLabel The unit to convert to.
     * @param resultFormatted The calculated conversion result.
     */
    private fun addToHistory(initialLabel: String, input: String, convertedLabel: String, resultFormatted: String)
    {
        val page3Fragment = Page3Fragment()
        val transaction = fragmentManager!!.beginTransaction()

        model.addItemToHistory(arrayOf(initialLabel, input, convertedLabel, resultFormatted))
        transaction.replace(R.id.historyList, page3Fragment)
    }
}