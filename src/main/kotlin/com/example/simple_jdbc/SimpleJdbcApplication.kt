package com.example.simple_jdbc

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.util.*
import javax.sql.DataSource

@SpringBootApplication
class SimpleJdbcApplication(val jdbcTemplate: JdbcTemplate) : CommandLineRunner {
    override fun run(vararg args: String?) {
        jdbcTemplate.update("INSERT INTO users (firstname, lastname, birthdate, country) VALUES ('tanaka', 'taro', '20200206', 'japan')")
        val map: MutableMap<String, Any> =
            jdbcTemplate.queryForMap("SELECT user_id, firstname, lastname, birthdate, country FROM users WHERE user_id = 1")
        println("==================================")
        println(map)
        println("==================================")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<SimpleJdbcApplication>(*args)
        }
    }

    //@Configuration
    //internal class DataSourceConfiguration {
    //    @Bean
    //    fun dataSource(properties : DataSourceProperties) : DataSource {
    //        val dataSource = SimpleDriverDataSource()
    //        dataSource.setDriverClass(org.postgresql.Driver::class.java)
    //        dataSource.url      = properties.determineUrl()
    //        dataSource.username = properties.determineUsername()
    //        dataSource.password = properties.determinePassword()
    //        val connectionProperties = Properties()
    //        connectionProperties.setProperty("autoCommit", "false")
    //        dataSource.connectionProperties = connectionProperties
    //        return dataSource
    //    }
    //}
}
