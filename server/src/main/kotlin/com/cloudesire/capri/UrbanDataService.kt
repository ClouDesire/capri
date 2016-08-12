package com.cloudesire.capri

import com.cloudesire.capri.client.ProvinceData

interface UrbanDataService {

    fun findByCap(cap: String): ProvinceData?

    fun findByCode(code: String): UrbanData?

}
