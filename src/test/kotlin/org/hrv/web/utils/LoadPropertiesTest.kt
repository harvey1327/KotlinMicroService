package org.hrv.web.utils

import org.junit.jupiter.api.Test

class LoadPropertiesTest {

    @Test
    fun loadPropertiesContainsKeys(){
        val properties = LoadProperties("hikari.properties")
        assert(properties.containsKey("dataSourceClassName"))
        assert(properties.containsKey("dataSource.user"))
        assert(properties.containsKey("dataSource.password"))
        assert(properties.containsKey("dataSource.databaseName"))
        assert(properties.containsKey("dataSource.portNumber"))
        assert(properties.containsKey("dataSource.serverName"))
        assert(properties.containsKey("maximumPoolSize"))
        assert(properties.containsKey("minimumIdle"))
    }
}