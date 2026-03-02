#!/bin/bash

# 1. UNARY: GetPrice
# Simple request and response
grpcurl -plaintext \
  -import-path ./src/main/proto \
  -proto stock_trading.proto \
  -d '{"symbol": "AAPL"}' \
  localhost:9090 \
  stocktrading.StockTradingService/GetPrice
