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
WORKDIR /app
COPY --from=build /app/target/booker-0.0.1-SNAPSHOT.jar /app/booker.jar
RUN chmod +x /app/booker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/booker.jar"]