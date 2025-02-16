# 1. ビルドステージ
FROM maven:3-eclipse-temurin-22 AS build

# 作業ディレクトリを /app に設定
WORKDIR /app

# プロジェクトのソースコードをコピー
COPY . .

# Maven を使ってビルド（テストはスキップ）
RUN mvn clean package -DskipTests

# 2. 実行ステージ
FROM eclipse-temurin:22-alpine

# 作業ディレクトリを /app に設定
WORKDIR /app

# ビルドした JAR をコピー（finalName を指定している場合は `booker.jar` になる）
COPY --from=build /app/target/booker.jar /app/booker.jar

# JAR の実行権限を付与
RUN chmod +x /app/booker.jar

# コンテナがリッスンするポートを指定
EXPOSE 8080

# アプリを実行
ENTRYPOINT ["java", "-jar", "/app/booker.jar"]

