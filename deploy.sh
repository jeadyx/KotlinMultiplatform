#!/bin/bash

# 配置变量
REMOTE_USER="root"
REMOTE_HOST="alicloud.com"
REMOTE_DIR="/var/www/kotlinmultiplatform.cn"
LOCAL_DIST="composeApp/build/dist"

# 确保本地构建完成
./gradlew wasmJsBrowserProductionWebpack

# 创建远程目录（如果不存在）
ssh $REMOTE_USER@$REMOTE_HOST "mkdir -p $REMOTE_DIR"

# 复制文件到服务器
rsync -avz --delete $LOCAL_DIST/ $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR/

# 配置Nginx
ssh $REMOTE_USER@$REMOTE_HOST "cat > /etc/nginx/conf.d/kotlinmultiplatform.cn.conf << 'EOL'
server {
    listen 80;
    server_name kotlinmultiplatform.cn;
    root $REMOTE_DIR;
    index index.html;

    location / {
        try_files \$uri \$uri/ /index.html;
    }

    location ~ \.wasm$ {
        types {
            application/wasm wasm;
        }
        add_header Cache-Control 'no-store';
    }
}
EOL"

# 测试Nginx配置并重启
ssh $REMOTE_USER@$REMOTE_HOST "nginx -t && systemctl restart nginx"

echo "部署完成！请访问 http://kotlinmultiplatform.cn 查看结果" 