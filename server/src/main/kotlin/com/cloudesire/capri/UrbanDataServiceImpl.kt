package com.cloudesire.capri

import com.cloudesire.capri.client.ProvinceData
import com.fasterxml.jackson.core.JsonParseException
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

    var urbanData: List<UrbanData>
    var capData: Map<String, ProvinceData>
    val databaseFilePath = System.getProperty("java.io.tmpdir") + "/cap.json"
    val one_day = 24 * 60 * 60 * 1000

    @Autowired
    constructor(@Qualifier("applicationProperties") properties: ApplicationProperties, mapper: ObjectMapper) {

        val databaseUrl = properties.dataSource
        val file = File(databaseFilePath)
        val stale = (Date().time - file.lastModified()) > one_day
        if ( stale ) {
            fetchDatabase(databaseUrl, file)
        }
        try {
            urbanData = mapper.readValue(file.inputStream())
            capData = indexByCap(urbanData)
        } catch (e: JsonParseException) {
            file.delete()
            fetchDatabase(databaseUrl, file)
            urbanData = mapper.readValue(file.inputStream())
            capData = indexByCap(urbanData)
        }
    }

    private fun fetchDatabase(databaseUrl: String, file: File) {
        val remoteJson = URL(databaseUrl).openStream()
        try {
            val tempFile = File(databaseFilePath + ".tmp")
            Files.copy(remoteJson, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Files.copy(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING)
            tempFile.delete()
        } finally {
            remoteJson.close()
        }
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
