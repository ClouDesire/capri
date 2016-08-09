package com.cloudesire.capra

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller @Autowired constructor(val repository: UrbanDataService) {

    @RequestMapping("/cap={cap}")
    fun findByCap(@PathVariable cap: String) = repository.findByCap(cap)

    @RequestMapping("/codice={code}")
    fun findByCode(@PathVariable code: String) = repository.findByCode(code)

}
