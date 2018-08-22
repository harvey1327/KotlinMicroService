package org.hrv.web.utils

import java.util.*

class LoadProperties(fileName: String): Properties() {

    private val content = javaClass.classLoader.getResource(fileName).openStream()

    init {
        this.load(content)
    }

}