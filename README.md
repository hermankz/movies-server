# movies-server
movies-server build with Spring Boot as REST API.

Open `application.properties`, then change `file.data.location` to point to your JSON data file e.g. `/home/test/movies.json`

To build the app: `mvn package`

To run the app with preset properties: `java -jar target/movies-server-1.0.0.jar`

To run the app with external properties: `java -jar myproject.jar --spring.config.location=file./[your config properties file path]`

TODO LIST:
- add more testcase
- 