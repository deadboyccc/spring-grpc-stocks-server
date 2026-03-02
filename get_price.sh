#!/bin/bash
grpcurl -plaintext \
  -import-path ./src/main/proto \
  -proto stock_trading.proto \
  -d '{"symbol": "AAPL"}' \
  localhost:9090 \
  stocktrading.StockTradingService/GetPrice