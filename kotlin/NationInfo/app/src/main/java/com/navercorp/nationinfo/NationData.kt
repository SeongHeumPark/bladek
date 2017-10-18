package com.navercorp.nationinfo

/**
 * @author seongheum.park
 */
data class NationDetailData(val name: String,
                            val capital: String,
                            val location: String,
                            val volume: String,
                            val weather: String,
                            val language: String)

data class GsonData(val data: List<NationDetailData>)