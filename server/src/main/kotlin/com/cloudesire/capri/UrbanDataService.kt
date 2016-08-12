package com.cloudesire.capri

interface UrbanDataService {

    fun findByCap(cap: String): ProvinceData?

    fun findByCode(code: String): UrbanData?

}
