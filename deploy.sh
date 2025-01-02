#!/bin/bash

# 设置变量
SERVER="root@alicloud.com"
REMOTE_DIR="/var/www/html"

# 上传文件
scp composeApp/build/kotlin-webpack/wasmJs/productionExecutable/2eaba8643e2ccdf352b4.wasm $SERVER:$REMOTE_DIR/
scp composeApp/build/kotlin-webpack/wasmJs/productionExecutable/composeApp.js $SERVER:$REMOTE_DIR/
scp composeApp/src/wasmJsMain/resources/styles.css $SERVER:$REMOTE_DIR/
scp composeApp/src/wasmJsMain/resources/index.html $SERVER:$REMOTE_DIR/

# 设置权限
ssh $SERVER "chmod 644 $REMOTE_DIR/*"

echo "部署完成！" 