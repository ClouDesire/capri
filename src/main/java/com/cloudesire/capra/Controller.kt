package com.cloudesire.capra

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class Controller @Autowired constructor(val repository: UrbanDataService) {

    @RequestMapping("/cap={cap:\\d{5}}")
    fun findByCap(@PathVariable cap: String): ProvinceData = repository.findByCap(cap) ?: throw NotFoundException()

    @RequestMapping("/codice={code:\\d{6}}")
    fun findByCode(@PathVariable code: String): UrbanData = repository.findByCode(code) ?: throw NotFoundException()

}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException : RuntimeException()
