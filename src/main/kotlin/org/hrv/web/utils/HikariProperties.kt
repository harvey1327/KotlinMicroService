package org.hrv.web.utils

class HikariProperties {

    fun init(){
        println("----------------START----------------")
        val content = javaClass.classLoader.getResource("hikari.properties").readText()
        println(content)
    }

}

fun main(args: Array<String>) {
    HikariProperties().init()
}