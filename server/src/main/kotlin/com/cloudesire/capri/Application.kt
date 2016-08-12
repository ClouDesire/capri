package com.cloudesire.capri

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@EnableConfigurationProperties(ApplicationProperties::class)
@SpringBootApplication
open class Application {
    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
