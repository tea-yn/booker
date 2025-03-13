# 1. ビルドステージ
FROM maven:3.8.4-openjdk-17 as build

# プロジェクトのソースコードをコピー
COPY . /app

# 作業ディレクトリを設定
WORKDIR /app

# Mavenでビルド（依存関係をダウンロードし、WARファイルを生成）
RUN mvn clean install

# 2. 実行ステージ（Tomcatを使用してWARファイルをデプロイ）
FROM tomcat:9.0-jdk17

# ビルドステージで作成されたWARファイルをコピー
COPY --from=build /app/target/booker.war /usr/local/tomcat/webapps/booker.war

# Tomcatの起動
CMD ["catalina.sh", "run"]
