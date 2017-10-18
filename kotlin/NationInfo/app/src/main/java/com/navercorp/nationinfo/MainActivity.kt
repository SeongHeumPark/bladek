package com.navercorp.nationinfo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nation_list.layoutManager = LinearLayoutManager(this)

        val adapter = NationAdapter(this, listOf(
                NationData(R.drawable.flag_belgium, "벨기에", "브뤼쉘"),
                NationData(R.drawable.flag_argentina, "아르헨티나", "부에노스아이레스"),
                NationData(R.drawable.flag_brazil, "브라질", "브라질리아"),
                NationData(R.drawable.flag_canada, "캐나다", "오타와"),
                NationData(R.drawable.flag_china, "중국", "베이징")))
        adapter.setOnItemClickListener(this)

        nation_list.adapter = adapter
    }

    override fun onClick(view: View?) {
        val textView = view?.findViewById<TextView>(R.id.text_name)
        val name = textView?.text ?: "None"
        val intent = Intent(this, NationDetailActivity::class.java)
        intent.putExtra(NationDetailActivity.EXTRA_NATION_NAME, name)

        startActivity(intent)
    }
}
