package dev.dead.grpcstocksserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcStocksServerApplication

fun main(args: Array<String>) {
    runApplication<GrpcStocksServerApplication>(*args)
}
