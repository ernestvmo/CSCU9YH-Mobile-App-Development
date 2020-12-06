package uk.ac.stir.cs.yhproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel()
{
    /** The initial unit that will be converted. */
    val unitInitial = MutableLiveData<String>()
    /** The unit that the input will be converted to. */
    val unitConvert = MutableLiveData<String>()
    /** A list of Arrays that will hold every information from previous conversions. */
    val history = ArrayList<Array<String>>()
    /** A MutableLiveData object that will hold the history list. */
    val historyLiveData = MutableLiveData<ArrayList<Array<String>>>()
    /** The input put in by the user. */
    val input = MutableLiveData<String>()
    /** The calculated conversion result. */
    val result = MutableLiveData<String>()

    /**
     * This method set the value of the initial unit to the passed parameter.
     * @param unit The new value for the initial unit.
     */
    fun setInitialUnit(unit: String) {
        unitInitial.value = unit
    }

    /**
     * This method set the value of the convert to unit to the passed parameter.
     * @param unit The new value for the convert to unit.
     */
    fun setConvertUnit(unit: String) {
        unitConvert.value = unit
    }

    /**
     * This method will add the passed parameter to the list of arrays and then set the MutableLiveData list to the history list.
     * @param item An array of the values from the last conversion.
     */
    fun addItemToHistory(item: Array<String>)
    {
        history.add(item)
        historyLiveData.value = history
    }

    /**
     * This method will return the item at the passed index.
     * @param index The index at which we want to retrieve the item.
     * @return An array of a previous conversion.
     */
    fun getItemFromList(index: Int): Array<String>
    {
        return history[index]
    }

    /**
     * This method will set the input value to the passed parameter.
     * @param value The new value for the input.
     */
    fun setInput(value: String)
    {
        input.value = value
    }

    /**
     * This method will set the result to the passed parameter.
     * @param value The new value for the result.
     */
    fun setCalculatedResult(value: String)
    {
        result.value = value
    }
}