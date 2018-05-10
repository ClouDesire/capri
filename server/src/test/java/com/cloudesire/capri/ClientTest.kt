package com.cloudesire.capri

import com.cloudesire.capri.client.CapriClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTest {

    @LocalServerPort
    lateinit var port: Integer;

    @Test
    fun getByCap() {
        val client = CapriClient("http://localhost:$port");
        val response = client.service.query("55015").execute()
        val provinceData = response.body()
        assertThat( provinceData!!.province ).isEqualTo("Lucca");
    }
}
