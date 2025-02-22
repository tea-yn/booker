# 1. ビルドステージ
FROM tomcat:9.0-jdk17

# プロジェクトのソースコードをコピー
COPY target/booker.war /usr/local/tomcat/webapps/booker.war


# Tomcatの起動
CMD ["catalina.sh", "run"]

