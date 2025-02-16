# ビルド環境
FROM maven:3-eclipse-temurin-22 AS build

WORKDIR /app

# プロジェクト全体をコピー（pom.xml だけでなく、ソースコード全体を含む）
COPY . .

# JAR を作成（テストをスキップ）
RUN mvn clean package -Dmaven.test.skip=true

# 実行環境
FROM eclipse-temurin:22-alpine

# JAR を正しいパスからコピー
COPY --from=build /app/target/*.jar /app/booker.jar

# ポートを公開
EXPOSE 8080

# アプリケーションを起動
ENTRYPOINT ["java", "-jar", "/app/booker.jar"]
