#!/bin/bash

# Pipes the 10 JSON objects into the gRPC stream
cat trade.json | grpcurl -plaintext \
  -import-path ./src/main/proto \
  -proto stock_trading.proto \
  -d @ \
  localhost:9090 \
  stocktrading.StockTradingService/PlaceBulkOrder
