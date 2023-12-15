package com.example.travelhelper.domain.usecase

import com.example.travelhelper.domain.entity.Currency
import com.example.travelhelper.domain.repository.HomeRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val homeRepository: HomeRepository
): UseCase {
    suspend operator fun invoke(): List<Currency> {
        return homeRepository
            .getCurrency()
            .filter {
                it.cur_nm == "영국 파운드" ||
                it.cur_nm == "미국 달러" ||
                it.cur_nm == "일본 옌" ||
                it.cur_nm == "유로" ||
                it.cur_nm == "위안화"
            }.map {
                Currency(
                    currency = it.kftc_deal_bas_r.replace(",", ""),
                    country = it.cur_nm
                )
            }
    }
}