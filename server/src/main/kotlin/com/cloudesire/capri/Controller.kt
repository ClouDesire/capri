package com.cloudesire.capri

import com.cloudesire.capri.client.ProvinceData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller @Autowired constructor(val repository: UrbanDataService) {

    @RequestMapping("/cap/{cap:\\d{5}}")
    fun findByCap(@PathVariable cap: String): ProvinceData = repository.findByCap(cap) ?: throw NotFoundException()

    @RequestMapping("/codice/{code:\\d{6}}")
    fun findByCode(@PathVariable code: String): UrbanData = repository.findByCode(code) ?: throw NotFoundException()

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class NotFoundException : RuntimeException()
}
