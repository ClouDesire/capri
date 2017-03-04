package com.cloudesire.capri

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.mvc.WebContentInterceptor
import java.util.concurrent.TimeUnit

@Configuration
@EnableAutoConfiguration
open class WebMvcConfiguration : WebMvcConfigurerAdapter() {
    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**").allowedMethods("GET", "HEAD").allowedOrigins("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry?) {
        registry!!.addInterceptor(webContentInterceptor())
    }

    private fun webContentInterceptor(): WebContentInterceptor {
        val interceptor = WebContentInterceptor()
        val oneMonthPublic = CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic()
        interceptor.addCacheMapping(oneMonthPublic, "/**")
        return interceptor
    }
}