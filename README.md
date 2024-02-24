## Piotr Wojcieszak
# [My Personal Project](https://personal-project-pwojcieszak.azurewebsites.net/)
Project is deployed on Azure under this link: https://personal-project-pwojcieszak.azurewebsites.net/. Deployed version has limited functionality, I highly recommend runnning project locally using docker-compose.
<br><br>
This project is intended to be a more attractive alternative to the traditional resume and to showcase my skills I've gained by doing minor projects. I can't show everything here so I encourage you to browse the rest of my portfolio on GitHub. On the "About this project" subpage, I describe one by one all the technologies used in the project.

# How to run the application using Docker
1. Run `mvn clean package -DskipTests` to build the applications and create the docker image locally.
2. Run `docker-compose up -d` to start the applications.
3. After giving applications some time to register with Discovery Server application is available under `localhost:8080`.

# How to run the application without Docker
1. Run `mvn clean verify -DskipTests` by going inside each folder to build the applications.
2. After that run `mvn spring-boot:run` by going inside each folder to start the applications.
3. After giving applications some time to register with Discovery Server application is available under `localhost:8080`.
