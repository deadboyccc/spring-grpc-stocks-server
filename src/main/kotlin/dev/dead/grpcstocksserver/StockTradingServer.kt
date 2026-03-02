package dev.dead.grpcstocksserver

import dev.dead.StockRequest
import dev.dead.StockResponse
import dev.dead.StockTradingServiceGrpc
import io.grpc.stub.StreamObserver
import org.springframework.grpc.server.service.GrpcService
import java.time.Instant

@GrpcService
class StockTradingServer : StockTradingServiceGrpc.StockTradingServiceImplBase() {

    override fun getPrice(
        request: StockRequest,
        responseObserver: StreamObserver<StockResponse>
    ) {
        // 1. Logic for random price
        val price = (100..500).random() + (0..99).random() / 100.0

        // 2. Capture current time as Java Instant
        val now = Instant.now()

        // 3. Build the response, converting Instant to Protobuf Timestamp
        val response = StockResponse.newBuilder()
            .setSymbol(request.symbol)
            .setPrice(price)
            .setTimestamp(
                com.google.protobuf.Timestamp.newBuilder()
                    .setSeconds(now.epochSecond)
                    .setNanos(now.nano)
                    .build()
            )
            .build()

        // 4. Send and complete
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun subscribePrice(
        request: StockRequest,
        responseObserver: StreamObserver<StockResponse>
    ) {
        // Simulate streaming price updates every second for 5 seconds
        for (i in 1..5) {
            val price = (100..500).random() + (0..99).random() / 100.0
            val now = Instant.now()

            val response = StockResponse.newBuilder()
                .setSymbol(request.symbol)
                .setPrice(price)
                .setTimestamp(
                    com.google.protobuf.Timestamp.newBuilder()
                        .setSeconds(now.epochSecond)
                        .setNanos(now.nano)
                        .build()
                )
                .build()

            responseObserver.onNext(response)

            // Sleep for 1 second before sending the next update
            Thread.sleep(1000)
        }
        responseObserver.onCompleted()
    }
}
