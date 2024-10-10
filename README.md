GenAi personal assistant using Spring Boot and Ollama

## Running it locally

1. Install required environments

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)
- [curl](https://help.ubidots.com/en/articles/2165289-learn-how-to-install-run-curl-on-windows-macosx-linux)

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

6. Test with curl
```
curl -XPOST -H "Content-type: application/json" -d '{
   "messages": [
        {
            "userId": "sachinTestId2",
            "content": "hello",
            "timestamp": 1728265388553,
            "role": "user"
        }
    ]
}' 'http://localhost:8081/api/chat/getChatResponse'
```


You can run the project directly by opening the project in IntelliJ IDEA

## Demo



https://github.com/user-attachments/assets/017f0101-d715-461e-a524-1f2e4ecc3c6f

[Youtube demo](https://youtu.be/HH7BskWgU6Q)