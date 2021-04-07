package com.example.coding_exercise.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Override
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        http
            ?.cors()
            ?.and()
            ?.csrf()
            ?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/**")
            ?.permitAll()
    }
}
