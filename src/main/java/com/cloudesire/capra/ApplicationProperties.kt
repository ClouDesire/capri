package com.cloudesire.capra

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix="application")
open class ApplicationProperties() {
    var dataSource = ""
}

