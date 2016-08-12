package com.cloudesire.capri

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix="application")
open class ApplicationProperties() {
    var dataSource = ""
}

