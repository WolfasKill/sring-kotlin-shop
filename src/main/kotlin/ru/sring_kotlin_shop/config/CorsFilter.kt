package ru.sring_kotlin_shop.config

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class CorsFilter: Filter{
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest?, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader(
            "Access-Control-Allow-Headers",
            "Content-Type, Authorization, Content-Length, X-Requested-With"
        )
        chain.doFilter(req, res)
    }
}