package uk.ac.stir.cs.yhproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.page1_fragment.*

class Page1Fragment : Fragment()
{
    /** The PageViewModel used to store value between fragments. */
    private lateinit var model: PageViewModel
    /** The initial unit involved in the conversion. */
    private var initial: String = ""
    /** The unit that the input will be converted to. */
    private var convert: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.page1_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        // Create a reference to the PageViewModel object to access the variables.
        model = ViewModelProviders.of(activity!!).get(PageViewModel::class.java)

        // The Spinner in charge of choosing the unit family.
        val familySpinner = view.findViewById(R.id.familySpinner) as Spinner

        // The initial unit spinner
        val spinnerInitialUnit = view.findViewById(R.id.spinnerInitialUnit) as Spinner
        // The spinner for the unit to convert to
        val spinnerConvertTo = view.findViewById(R.id.spinnerConvertTo) as Spinner

        // Create a ItemSelectedLister that will change the possible entries for the other two spinners.
        familySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?)
            { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                /** The adapter that enables changing the values of the two unit spinners.*/
                var spinnerAdapter: ArrayAdapter<CharSequence>? = null

                if (position == 0)
                {// Weight
                    // Change the adapter to have the values of the Weight array
                    spinnerAdapter = context?.let { ArrayAdapter.createFromResource(it, R.array.Weight, android.R.layout.simple_spinner_item) }
                }
                else if (position == 1)
                {// Length
                    // Change the adapter to have the values of the Length array
                    spinnerAdapter = context?.let { ArrayAdapter.createFromResource(it, R.array.Length, android.R.layout.simple_spinner_item) }

                }
                else if (position == 2)
                {// Speed
                    // Change the adapter to have the values of the Speed array
                    spinnerAdapter = context?.let { ArrayAdapter.createFromResource(it, R.array.Speed, android.R.layout.simple_spinner_item) }
                }
                else if (position == 3)
                {// Volume
                    // Change the adapter to have the values of the Volume array
                    spinnerAdapter = context?.let { ArrayAdapter.createFromResource(it, R.array.Volume, android.R.layout.simple_spinner_item) }
                }

                spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                // Set the initial spinner to the created adapter
                spinnerInitialUnit.adapter = spinnerAdapter
                // Set the convert to spinner to the created adapter
                spinnerConvertTo.adapter = spinnerAdapter

                setDefaultValues()
            }
        }

        // Create a listener for the initial unit spinner
        spinnerInitialUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            // Records the previous position of the spinner
            var beforePosition: Int = 0

            // Change the initial unit in the model when a value is selected in the spinner
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                // Record the initial value of the spinner before it was changed
                val beforeInitial: String = initial
                // Get the new selected item
                initial = parent?.getItemAtPosition(position).toString()

                // If the selected item is equal to the currently selected item in the convert to spinner, swap the two selections.
                if (initial.equals(convert) && !initial.equals("Select unit"))
                {
                    convert = beforeInitial
                    spinnerConvertTo.setSelection(beforePosition)
                    setUnits()
                }

                beforePosition = position
                // Set the values in the model
                setUnits()
            }
        }

        // Create a listener for the convert to unit spinner
        spinnerConvertTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            // Records the previous position of the spinner
            var beforePosition: Int = 0

            // Change the converted unit in the model when a value is selected in the spinner
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                // Record the initial value of the spinner before it was changed
                val beforeConvert: String = convert
                // Get the new selected item
                convert = parent?.getItemAtPosition(position).toString()

                // If the selected item is equal to the currently selected item in the initial unit spinner, swap the two selections.
                if (convert.equals(initial) && !convert.equals("Select unit"))
                {
                    initial = beforeConvert
                    spinnerInitialUnit.setSelection(beforePosition)
                    setUnits()
                }

                beforePosition = position
                // Set the value in the model
                setUnits()
            }
        }
    }

    /**
     * Set the spinners to the default selection at index 0
     */
    private fun setDefaultValues()
    {
        spinnerInitialUnit.setSelection(0)
        spinnerConvertTo.setSelection(0)
    }

    /**
     * This method calls two methods that update the values in the model.
     */
    private fun setUnits()
    {
        setInitialUnit()
        setConvertUnit()
    }

    /**
     * This method set the initial unit parameter in the model to the selected item in the initial unit spinner.
     */
    private fun setInitialUnit()
    {
        val page2Fragment = Page2Fragment()
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        model.setInitialUnit(initial)
        fragmentTransaction.replace(R.id.initialUnitValue, page2Fragment)
    }

    /**
     * This method set the convert to parameter in the model to the selected item in the convert to spinner.
     */
    private fun setConvertUnit()
    {
        val page2Fragment = Page2Fragment()
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        model.setConvertUnit(convert)
        fragmentTransaction.replace(R.id.convertedUnitValue, page2Fragment)
    }
}