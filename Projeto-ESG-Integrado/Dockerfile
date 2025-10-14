
# ===== Stage 1: Build (Maven + JDK 21) =====
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia POM e resolve deps em cache
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copia o código e gera o JAR
COPY src ./src
RUN mvn -B -e -DskipTests=true package

# ===== Stage 2: Runtime (JRE 21 enxuto) =====
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR compilado
COPY --from=builder /app/target/*.jar app.jar

# Porta padrão Spring Boot
EXPOSE 8080

# Flags opcionais em tempo de execução (podem vir de env)
ENV JAVA_OPTS=""

# Sobe a aplicação
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
