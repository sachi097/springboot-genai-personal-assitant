GenAi personal assistant using Spring Boot and Ollama

## Running it locally

1. Install required environments

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

2. Clone repository
```
git clone https://github.com/sachi097/springboot-genai-personal-assitant.git
```

3. Update environment files

- add AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY in application.yaml

5. Build and run
```
mvn clean
mvn -f pom.xml clean package -DskipTests
java -jar ./target/*.jar  
```

You can run the project directly by opening the project in IntelliJ IDEA

## Demo



https://github.com/user-attachments/assets/017f0101-d715-461e-a524-1f2e4ecc3c6f

