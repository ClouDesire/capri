package com.cloudesire.capra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
open class UrbanDataServiceImpl @Autowired constructor(loader: ResourceLoader, mapper: ObjectMapper) : UrbanDataService {

    val json = loader.getResource("classpath:comuni.json").inputStream
    var urbanData: List<UrbanData> = mapper.readValue(json)

    override fun findByCap(cap: String): UrbanData = urbanData.single { u -> u.cap == cap }
    
}