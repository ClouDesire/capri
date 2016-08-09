package com.cloudesire.capra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.InputStream
import javax.annotation.PostConstruct

@Service
class UrbanDataServiceImpl : UrbanDataService {

    lateinit var urbanData: List<UrbanData>

    @Autowired constructor(loader: ResourceLoader, mapper: ObjectMapper) {
        val json: InputStream = loader.getResource("classpath:comuni.json").inputStream
        urbanData = mapper.readValue(json)
        json.close()
    }

    override fun findByCap(cap: String): List<UrbanData> = urbanData.filter { u -> u.cap.contains( cap ) }

    override fun findByCode(code: String): UrbanData = urbanData.single { u -> u.codice.contains( code ) }

}