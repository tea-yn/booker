# 1. ビルドステージ - Mavenをインストール
FROM maven:3.8.4-openjdk-17-slim as build

# プロジェクトのソースコードをコピー
COPY . /usr/src/app

# 作業ディレクトリを設定
WORKDIR /usr/src/app

# Mavenビルドを実行（依存関係のダウンロード、プロジェクトのビルド）
RUN mvn clean install

# 2. 実行ステージ - Tomcatを使用
FROM tomcat:9.0-jdk17

# ビルド済みWARファイルをコピー
COPY --from=build /usr/src/app/target/booker.war /usr/local/tomcat/webapps/booker.war

# Tomcatの起動
CMD ["catalina.sh", "run"]

