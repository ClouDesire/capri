package com.cloudesire.capra

interface UrbanDataService {

    fun findByCap(cap: String): List<UrbanData>

    fun findByCode(code: String): UrbanData

}
