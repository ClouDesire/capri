package com.cloudesire.capri

import com.cloudesire.capri.client.ProvinceData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.net.URL
import java.util.*

@Service
class UrbanDataServiceImpl : UrbanDataService {

    lateinit var urbanData: List<UrbanData>
    lateinit var capData: Map<String, ProvinceData>

    @Autowired
    constructor(@Qualifier("applicationProperties") properties: ApplicationProperties, mapper: ObjectMapper) {
        val json = URL(properties.dataSource).openStream()
        urbanData = mapper.readValue(json)
        capData = indexByCap(urbanData)
        json.close()
    }

    override fun findByCap(cap: String): ProvinceData? = capData[cap]

    override fun findByCode(code: String): UrbanData? = urbanData.singleOrNull { u -> u.codice.contains( code ) }

    private fun indexByCap(urbanData: List<UrbanData>): Map<String, ProvinceData> {
        val capData = HashMap<String, ProvinceData>()
        for (data in urbanData) {
            for (cap in data.cap) {
                capData.putIfAbsent(cap, ProvinceData(data.province(), data.abbreviation(), data.region()))
            }
        }
        return capData
    }

}
