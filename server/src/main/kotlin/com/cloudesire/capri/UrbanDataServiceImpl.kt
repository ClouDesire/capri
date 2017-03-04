package com.cloudesire.capri

import com.cloudesire.capri.client.ProvinceData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class UrbanDataServiceImpl : UrbanDataService {

    lateinit var urbanData: List<UrbanData>
    lateinit var capData: Map<String, ProvinceData>
    val one_day = 24 * 60 * 60 * 1000

    @Autowired
    constructor(@Qualifier("applicationProperties") properties: ApplicationProperties, mapper: ObjectMapper) {

        val file = File(System.getProperty("java.io.tmpdir") + "/cap.json")
        val stale = (Date().time - file.lastModified()) > one_day
        if ( stale ) {
            val remoteJson = URL(properties.dataSource).openStream()
            try {
                Files.copy(remoteJson, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
            } finally {
                remoteJson.close()
            }
        }
        urbanData = mapper.readValue(file.inputStream())
        capData = indexByCap(urbanData)
    }

    override fun findByCap(cap: String): ProvinceData? = capData[cap]

    override fun findByCode(code: String): UrbanData? = urbanData.singleOrNull { u -> u.codice.contains( code ) }

    private fun indexByCap(urbanData: List<UrbanData>): Map<String, ProvinceData> {
        val capData = HashMap<String, ProvinceData>()
        for (data in urbanData) {
            for (cap in data.cap) {
                capData.putIfAbsent(cap, ProvinceData(data.province(), data.abbreviation(), data.region()))
                capData[cap]?.addCity(data.name())
            }
        }
        return capData
    }

}
