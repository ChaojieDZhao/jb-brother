https://www.trumpdman.top/

http://trumpdman.top/

# 验证caddy的配置文件

caddy -agree -conf /etc/caddy/Caddyfile

systemctl stop caddy.service

systemctl start caddy.service

systemctl status -l caddy.service

systemctl status -l v2ray.service

ps -ef | grep v2ray

ps -ef | grep caddy


```
## caddy
{
  tls www.trumpdman.top
  log /var/tmp/caddy_2.log
  root /var/www/trumpdman
  proxy /ray https://127.0.0.1:50014 {
     header_upstream Host "www.trumpdman.top"
     header_upstream X-Forwarded-Proto "https"
     insecure_skip_verify
  }
}



## v2ray
{
  "log": {
    "access": "/var/log/v2ray/access.log",
    "error": "/var/log/v2ray/error.log",
    "loglevel": "warning"
  },
  "dns": {},
  "stats": {},
  "inbounds": [
    {
      "port": 50014,
      "protocol": "vmess",
      "settings": {
        "clients": [
        {
          "id": "d26a80a3-a489-4ef7-9bb9-6b0f7649dd2c",
          "level": 1,
          "alterId": 64
        }
       ]
      },
      "tag": "in-0",
      "streamSettings": {
        "network": "http",
        "security": "tls",
        "httpSettings": {
          "path": "/ray",
          "host": [
            "www.trumpdman.top"
          ]
        },
        "tlsSettings": {
          "certificates": [
            {
              "certificateFile": "/etc/ssl/caddy/acme/acme-v02.api.letsencrypt.org/sites/www.trumpdman.top/www.trumpdman.top.crt",
              "keyFile": "/etc/ssl/caddy/acme/acme-v02.api.letsencrypt.org/sites/www.trumpdman.top/www.trumpdman.top.key"
            }
          ]
        }
      },
      "listen": "127.0.0.1"
    }
  ],
  "outbounds": [
    {
      "tag": "direct",
      "protocol": "freedom",
      "settings": {}
    },
    {
      "tag": "blocked",
      "protocol": "blackhole",
      "settings": {}
    }
  ],
  "routing": {
    "domainStrategy": "AsIs",
    "rules": [
      {
        "type": "field",
        "ip": [
          "geoip:private"
        ],
        "outboundTag": "blocked"
      }
    ]
  },
  "policy": {},
  "reverse": {},
  "transport": {}
}
```

```
{
    "log": {
        "access": "/var/log/v2ray/access.log",
        "error": "/var/log/v2ray/error.log",
        "loglevel": "warning"
    },
    "dns": {},
    "stats": {},
    "inbounds": [
        {
            "port": 50014,
            "protocol": "vmess",
            "listen": "127.0.0.1",
            "settings": {
                "clients": [
                    {
                        "id": "d26a80a3-a489-4ef7-9bb9-6b0f7649dd2c",
                        "level": 1,
                        "alterId": 64
                    }
                ]
            },
            "streamSettings": {
                "network": "ws",
                "wsSettings": {
                    "path": "/ray"
                }
            }
        },
        {
            "port": 49999,
            "protocol": "vmess",
            "settings": {
                "clients": [
                    {
                        "id": "9286be8b-67dd-4ecf-ab5f-d1d1b1c4aca3",
                        "level": 1,
                        "alterId": 64
                    }
                ]
            },
            "tag": "in-0",
            "streamSettings": {
                "network": "tcp",
                "security": "none",
                "tcpSettings": {}
            }
        }
    ],
    "outbounds": [
        {
            "tag": "direct",
            "protocol": "freedom",
            "settings": {}
        },
        {
            "tag": "blocked",
            "protocol": "blackhole",
            "settings": {}
        }
    ],
    "routing": {
        "domainStrategy": "AsIs",
        "rules": [
            {
                "type": "field",
                "ip": [
                    "geoip:private"
                ],
                "outboundTag": "blocked"
            }
        ]
    },
    "policy": {},
    "reverse": {},
    "transport": {}
}
```