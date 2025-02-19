## Piotr Wojcieszak
## UPDATE 19.02.2025
Project is no longer deployed. I've added screenshots of the website at the end of README. Project still runs locally. For some reason Graphana don't work as before and logo of Microsoft Azure won't load up. I didn't update anything on website except for first two sentences in "About Me" section (education status).

# [My Personal Project](https://personal-project-pwojcieszak.azurewebsites.net/)
Project is deployed on Azure under this link: https://personal-project-pwojcieszak.azurewebsites.net/. Deployed version has limited functionality, I highly recommend runnning project locally using docker-compose.
<br><br>
This project is intended to be a more attractive alternative to the traditional resume and to showcase my skills I've gained by doing minor projects. I can't show everything here so I encourage you to browse the rest of my portfolio on GitHub. On the "About this project" subpage, I describe one by one all the technologies used in the project.


# How to run the application using Docker
(*)0. Run `mvn clean package -DskipTests` to build the applications and create the docker image locally.
1. Run `docker-compose up -d` to start the applications.
2. After giving applications some time to register with Discovery Server application is available under `localhost:8080`.

# How to run the application without Docker
1. Run `mvn clean verify -DskipTests` by going inside each folder to build the applications.
2. After that run `mvn spring-boot:run` by going inside each folder to start the applications.
3. After giving applications some time to register with Discovery Server application is available under `localhost:8080`.

# Screenshots
![0](https://github.com/user-attachments/assets/47af60bd-988c-4bf6-8b0c-796d24642388)
![1](https://github.com/user-attachments/assets/3b47c97a-725f-4ec9-b295-0c3fbdcd368a)
![2](https://github.com/user-attachments/assets/6de81e50-6ffa-4747-96fe-ed669fe4c77c)
![3](https://github.com/user-attachments/assets/d88a31e0-cd4e-4669-baec-e8a300445bd3)
![4](https://github.com/user-attachments/assets/dd6b69a7-64e4-4845-b273-fd4db3c51c0a)
![4b](https://github.com/user-attachments/assets/c28c9473-95b4-4ffc-8082-7041f76c42a2)
![4c](https://github.com/user-attachments/assets/c6776f30-970e-43eb-8a63-34568866f9de)
![5](https://github.com/user-attachments/assets/c9338703-f94b-40c8-a5f4-2e6439c5f159)
![6](https://github.com/user-attachments/assets/2f527f09-ac63-4b42-9d5d-14e9809cf72c)
![7](https://github.com/user-attachments/assets/ff31b133-2e14-44b5-aa56-f29e91a05482)
![8](https://github.com/user-attachments/assets/87a4394a-34af-4f49-bc36-3801c50753bb)
![9](https://github.com/user-attachments/assets/65831841-48fe-4e66-8854-37e2e226b8fb)
![10](https://github.com/user-attachments/assets/b931078d-c734-46e5-9702-256480033505)
![11](https://github.com/user-attachments/assets/73fd1342-a107-42d8-acfe-f8579fdff43a)
![12](https://github.com/user-attachments/assets/10251e63-fd7d-4bac-846e-514990516039)
![13](https://github.com/user-attachments/assets/22ed8d77-4e20-4f5d-9627-49610a4d8bda)
![14](https://github.com/user-attachments/assets/777f5e75-2730-4d9a-9194-f8e26d17b9ad)
