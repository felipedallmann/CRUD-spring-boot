# Use a imagem oficial do Java
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven
COPY target/spring-boot-crud-0.0.1-SNAPSHOT.jar app.jar

# Define a variável de ambiente para evitar problemas de codificação
ENV LANG=C.UTF-8

# Expõe a porta que a aplicação vai utilizar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]