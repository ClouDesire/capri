package com.cloudesire.capri

import com.cloudesire.capri.client.CapriClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTest {

    @LocalServerPort
    private val port: Int = -1

    @Test
    fun getByCap() {
        val client = CapriClient("http://localhost:$port")
        val response = client.service.query("55015").execute()
        val provinceData = response.body()
        assertThat( provinceData!!.province ).isEqualTo("Lucca")
    }
}
