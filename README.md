# Bookmark API Hello World Project

Tutorial by SivaLabs at https://www.youtube.com/playlist?list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ

Although use cases and requirements are the same as the tutorial, this project leverages Spring Boot 3, and Next.js's beta feature - app directory.
My goal is to learn more about testing, Docker, Kubernetes, and microservices in general and keep the other parts pretty straightforward.

- DTO Projection
- Integration Testing with H2 and with a real database with Testcontainers
- Dockerizing the application
- CI/CD with GitHub Actions

### Runtime Requirements

- Java 19
- Docker
- node ^19.7.0 and npm ^9.5.0

### To run

```shell
git clone https://github.com/kemalyildirim/bookmarker-app
cd bookmarker-api
./run.sh --start
```

Run `./run.sh --help` for other commands.
