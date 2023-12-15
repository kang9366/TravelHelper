package com.example.travelhelper.data.remote.model

data class CurrencyResponse(
    val result: Int,
    val cur_unit: String,
    val ttb: String,
    val tts: String,
    val deal_bas_r: String,
    val bkpr: String,
    val yy_efee_r: String,
    val ten_dd_efee_r: String,
    val kftc_bkpr: String,
    val kftc_deal_bas_r: String,
    val cur_nm: String
)