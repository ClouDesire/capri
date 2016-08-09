package com.cloudesire.capra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.InputStream
import java.util.*
import javax.annotation.PostConstruct

@Service
class UrbanDataServiceImpl : UrbanDataService {

    lateinit var urbanData: List<UrbanData>
    lateinit var capData: Map<String, ProvinceData>

    @Autowired constructor(loader: ResourceLoader, mapper: ObjectMapper) {
        val json: InputStream = loader.getResource("classpath:comuni.json").inputStream
        urbanData = mapper.readValue(json)
        capData = indexByCap(urbanData)
        json.close()
    }

    override fun findByCap(cap: String): ProvinceData? = capData.get(cap)

    override fun findByCode(code: String): UrbanData = urbanData.single { u -> u.codice.contains( code ) }

    private fun indexByCap(urbanData: List<UrbanData>): Map<String, ProvinceData> {
        val capData = HashMap<String, ProvinceData>()
        for (data in urbanData) {
            for (cap in data.cap) {
                capData.putIfAbsent(cap, ProvinceData(data.provincia.nome, data.sigla, data.regione.nome))
            }
        }
        return capData
    }

}