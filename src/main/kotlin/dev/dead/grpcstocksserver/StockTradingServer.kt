package dev.dead.grpcstocksserver

import com.google.protobuf.Timestamp
import dev.dead.StockRequest
import dev.dead.StockResponse
import dev.dead.StockTradingServiceGrpc
import io.grpc.stub.StreamObserver
import org.springframework.grpc.server.service.GrpcService
import java.time.Instant

@GrpcService
class StockTradingServer : StockTradingServiceGrpc.StockTradingServiceImplBase() {

    override fun getPrice(request: StockRequest, responseObserver: StreamObserver<StockResponse>) {
        responseObserver.onNext(createStockResponse(request))
        responseObserver.onCompleted()
    }

    override fun subscribePrice(request: StockRequest, responseObserver: StreamObserver<StockResponse>) {
        // Stream 5 updates with 3s intervals
        repeat(5) {
            responseObserver.onNext(createStockResponse(request))
            Thread.sleep(2500)
        }
        responseObserver.onCompleted()
    }

    private fun createStockResponse(request: StockRequest): StockResponse {
        val now = Instant.now()
        return StockResponse.newBuilder()
            .setSymbol(request.symbol)
            .setPrice((100..500).random() + (0..99).random() / 100.0)
            .setTimestamp(
                Timestamp.newBuilder()
                    .setSeconds(now.epochSecond)
                    .setNanos(now.nano)
                    .build()
            )
            .build()
    }
}
