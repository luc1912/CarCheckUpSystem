package com.infinum.academyproject.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class HttpConfiguration {

    @Bean
    fun provideWebClient(
        webClientBuilder: WebClient.Builder,
        @Value("\${https://62d922dd5d893b27b2df0731.mockapi.io}") baseUrl: String
    ): WebClient {
        return webClientBuilder.baseUrl(baseUrl).build()
    }

}
