package com.example.stickyrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyrecyclerview.RecyclerItemDecoration.SectionCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dataList: ArrayList<HashMap<String, String>> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()

        recyclerView = recycler_view_main_container

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val dataAdapter: DataAdapter = DataAdapter(this, dataList)
        recyclerView.adapter = dataAdapter

        val recyclerItemDecoration: RecyclerItemDecoration =
            RecyclerItemDecoration(
                this,
                resources.getDimensionPixelSize(R.dimen.header_height),
                true,
                getSectionCallback(dataList)
            )
        recyclerView.addItemDecoration(recyclerItemDecoration)
    }

    private fun getSectionCallback(list: ArrayList<HashMap<String,String>>) : SectionCallback {
        return object : SectionCallback {
            override fun isSectionHeader(position: Int): Boolean {
                return position == 0 || list[position].get("Title") != list[position-1].get("Title")
            }

            override fun getSectionHeaderName(position: Int): String {
                val dataMap: HashMap<String, String> = list[position]
                return dataMap.get("Title")!!
            }
        }
    }

    private fun getData() {
        val dataMap: HashMap<String, String> = HashMap()
        dataMap.put("Title", "Arround the corent")
        dataMap.put("Desc1", "Meaning: The natural")
        dataMap.put("Desc2", "Example: With many")
        dataList.add(dataMap)

        val dataMap2: HashMap<String, String> = HashMap()
        dataMap2.put("Title", "Arround the corent 2")
        dataMap2.put("Desc1", "Meaning: The natural 2")
        dataMap2.put("Desc2", "Example: With many 2")
        dataList.add(dataMap2)

        val dataMap3: HashMap<String, String> = HashMap()
        dataMap3.put("Title", "Arround the corent 3")
        dataMap3.put("Desc1", "Meaning: The natural 3")
        dataMap3.put("Desc2", "Example: With many 3")
        dataList.add(dataMap3)

        val dataMap4: HashMap<String, String> = HashMap()
        dataMap4.put("Title", "Arround the corent   4")
        dataMap4.put("Desc1", "Meaning: The natural 4")
        dataMap4.put("Desc2", "Example: With many   4")
        dataList.add(dataMap4)

        val dataMap5: HashMap<String, String> = HashMap()
        dataMap5.put("Title", "Arround the corent   5")
        dataMap5.put("Desc1", "Meaning: The natural 5")
        dataMap5.put("Desc2", "Example: With many   5")
        dataList.add(dataMap5)

        val dataMap6: HashMap<String, String> = HashMap()
        dataMap6.put("Title", "Arround the corent   6")
        dataMap6.put("Desc1", "Meaning: The natural 6")
        dataMap6.put("Desc2", "Example: With many   6")
        dataList.add(dataMap6)

        val dataMap7: HashMap<String, String> = HashMap()
        dataMap7.put("Title", "Arround the corent   7")
        dataMap7.put("Desc1", "Meaning: The natural 7")
        dataMap7.put("Desc2", "Example: With many   7")
        dataList.add(dataMap7)

        val dataMap8: HashMap<String, String> = HashMap()
        dataMap8.put("Title", "Arround the corent   8")
        dataMap8.put("Desc1", "Meaning: The natural 8")
        dataMap8.put("Desc2", "Example: With many   8")
        dataList.add(dataMap8)

        val dataMap9: HashMap<String, String> = HashMap()
        dataMap9.put("Title", "Arround the corent   9")
        dataMap9.put("Desc1", "Meaning: The natural 9")
        dataMap9.put("Desc2", "Example: With many   9")
        dataList.add(dataMap9)

    }

}