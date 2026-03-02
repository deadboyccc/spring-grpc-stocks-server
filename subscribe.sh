#!/bin/bash
grpcurl -plaintext \
  -import-path ./src/main/proto \
  -proto stock_trading.proto \
  -d '{"symbol": "TSLA"}' \
  localhost:9090 \
  stocktrading.StockTradingService/SubscribePrice