package uk.ac.stir.cs.yhproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout

class Page3Fragment : Fragment()
{
    /** The list that will contain all the previous conversions. */
    private var list = ArrayList<String>()
    /** The ListView object that will display the previous conversions. */
    private lateinit var listView: ListView
    /** The PageViewModel used to store value between fragments. */
    private lateinit var model: PageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.page3_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate the ListView object.
        listView = view.findViewById(R.id.historyList) as ListView
        // Create and set the adapter of the listview to the content of the ArrayList.
        listView.adapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, list) }

        // Create a listener that will activate when the user clicks on an item in the ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            sendBackCalculation(position)
        }

        // Create a reference to the PageViewModel object to access the variables.
        model = ViewModelProviders.of(activity!!).get(PageViewModel::class.java)
        // Observe any changes to the LiveData List
        model.historyLiveData.observe(this, object: Observer<Any>
        {
            override fun onChanged(t: Any?) {// When the list is changed, reset the list and re-assign the adapter to use the updated list.
                list.clear()
                model.history.forEach {
                    list.add(it[1] + " " + it[0] + "\t=\t" + it[3] + " " + it[2])
                    (listView.adapter as ArrayAdapter<String>?)?.notifyDataSetChanged()
                }
            }

        })
    }

    /**
     * This method updates the values of variables in the model and then switches to the Conversion tab.
     * @param position The position within the ListView
     */
    private fun sendBackCalculation(position: Int) {
        var array = model.getItemFromList(position)

        model.setInitialUnit(array[0])
        model.setInput(array[1])
        model.setConvertUnit(array[2])
        model.setCalculatedResult(array[3])

        var tabs: TabLayout = activity!!.findViewById(R.id.tab_layout)
        tabs.getTabAt(1)!!.select()
    }
}
