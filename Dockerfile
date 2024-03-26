# Use a imagem oficial do Maven 3.8.1 com OpenJDK 17 para a base
FROM maven:3.8.1-openjdk-17

# Cria o diretório do aplicativo
RUN mkdir -p /app

# Define o diretório de trabalho
WORKDIR /app

# Copia o diretório do projeto para o diretório do aplicativo
COPY . /app

# Compila o projeto com o Maven
RUN mvn clean install

# Expõe a porta 8080
EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]
