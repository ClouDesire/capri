package com.cloudesire.capra

interface UrbanDataService {

    fun findByCap(cap: String): ProvinceData?

    fun findByCode(code: String): UrbanData

}
