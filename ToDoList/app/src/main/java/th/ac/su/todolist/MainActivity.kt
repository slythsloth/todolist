package th.ac.su.todolist

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

class MainActivity : AppCompatActivity() {

    var itemList:ArrayList<String> = ArrayList<String>()
    lateinit var arrayAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList.add("Watch TV")
        itemList.add("Play ROV")
        itemList.add("Watch Netflix")

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,itemList)
        var listview1 = findViewById<ListView>(R.id.listView)
        listview1.adapter = arrayAdapter

        registerForContextMenu(listview1)

        var btnAdd = findViewById<Button>(R.id.btnAdd)
        var edtInput = findViewById<EditText>(R.id.edtInput)
        btnAdd.setOnClickListener {
            //if (0,edtInput.text.toString() == "")
            //itemList.add(edtInput.text.toString()) ต่อท้าย
            itemList.add(0,edtInput.text.toString())
            arrayAdapter.notifyDataSetChanged()
            hideKeyboard()
        }
        listview1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,itemList[position],Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_context,menu)

        super.onCreateContextMenu(menu, v, menuInfo)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        when(item.itemId){
            R.id.action_remove ->{
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
            }
            R.id.action_edit ->{
                Toast.makeText(this,itemList[position],Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }
    fun Activity.hideKeyboard() {
        hideKeyboard (currentFocus ?: View(this))
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
