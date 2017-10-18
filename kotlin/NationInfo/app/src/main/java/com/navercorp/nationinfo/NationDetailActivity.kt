package com.navercorp.nationinfo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_nation_detail.*
import java.io.InputStream
import java.io.InputStreamReader

class NationDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_NATION_NAME = "nation_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nation = intent.getStringExtra(EXTRA_NATION_NAME)
        setContentView(R.layout.activity_nation_detail)
        val data: NationDetailData? = getDataFromName(nation)
        img_flag.setImageResource(getResourceId(nation))
        initView(data)
    }

    private fun getDataFromName(selected: String): NationDetailData? {
        val gson: Gson = GsonBuilder().create()
        val inputStream: InputStream = assets.open("nation_data.json")
        val reader = InputStreamReader(inputStream)
        val detailData = gson.fromJson<GsonData>(reader, GsonData::class.java)

         detailData.data.forEach {
            if (selected == it.name) {
                return it
            }
        }

        return null
    }

    private fun getResourceId(selected: String): Int {
        when (selected) {
            "벨기에" -> {
                return R.drawable.flag_belgium
            }
            "아르헨티나" -> {
                return R.drawable.flag_argentina
            }
            "브라질" -> {
                return R.drawable.flag_brazil
            }
            "캐나다" -> {
                return R.drawable.flag_canada
            }
            "중국" -> {
                return R.drawable.flag_china
            }
            else -> {
                return 0
            }
        }
    }

    private fun initView(data: NationDetailData?) {
        text_name.text = data?.name
        capital.text = data?.capital
        volumn.text = data?.volume
        weather.text = data?.weather
        language.text = data?.language
        location.text = data?.location
    }
}
