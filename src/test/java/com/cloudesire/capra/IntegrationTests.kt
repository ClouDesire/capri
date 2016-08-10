package com.cloudesire.capra

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun getByCap() {
        var body = restTemplate.getForObject("/cap=10040", ProvinceData::class.java)
        assertThat(body.province).isEqualTo("Torino")
        assertThat(body.abbreviation).isEqualTo("TO")
        assertThat(body.region).isEqualTo("Piemonte")

        body = restTemplate.getForObject("/cap=56121", ProvinceData::class.java)
        assertThat(body.province).isEqualTo("Pisa")
        assertThat(body.abbreviation).isEqualTo("PI")
        assertThat(body.region).isEqualTo("Toscana")

        body = restTemplate.getForObject("/cap=71043", ProvinceData::class.java)
        assertThat(body.province).isEqualTo("Foggia")
        assertThat(body.abbreviation).isEqualTo("FG")
        assertThat(body.region).isEqualTo("Puglia")
    }

    @Test
    fun getByCode() {
        val body = restTemplate.getForObject("/codice=001315", UrbanData::class.java)
        assertThat(body.nome).isEqualTo("Volvera")
    }

    @Test
    fun fourOhFour() {
        val any = restTemplate.getForEntity("/no", Any::class.java)
        assertThat(any.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        var provinceData = restTemplate.getForEntity("/cap=100400", Any::class.java)
        assertThat(provinceData.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        provinceData = restTemplate.getForEntity("/cap=00000", Any::class.java)
        assertThat(provinceData.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        var urbanData = restTemplate.getForEntity("/codice=0013150", Any::class.java)
        assertThat(urbanData.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        urbanData = restTemplate.getForEntity("/codice=000000", Any::class.java)
        assertThat(urbanData.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

}
