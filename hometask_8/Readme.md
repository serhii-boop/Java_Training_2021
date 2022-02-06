1)Build and run docker image of the app
    
    First, we have to create Dockerfile as follow below in the project root
        FROM adoptopenjdk:11-jre-hotspot
        ADD target/firstdocker.jar firstdocker.jar
        EXPOSE 8070
        ENTRYPOINT ["java", "-jar", "firstdocker.jar"]
        
    Then build an image with the name of your jar file
    ![1](https://user-images.githubusercontent.com/74115834/152702605-23a373c3-e481-4914-8649-289d33e33a0f.png)

        

    Next run the image.
    ![image](https://user-images.githubusercontent.com/74115834/152701914-77e0cb55-89c8-48f2-8e0c-53c938b7d3f3.png)

    Success
    ![image](https://user-images.githubusercontent.com/74115834/152701989-022b3243-a63e-4a8b-a80d-247876da2f7c.png)

2)Creating docker image to deploy to the AWS Elastic Beanstalk instance


        First, we have to create a user group in IAM
        ![image](https://user-images.githubusercontent.com/74115834/152702119-5ba8b3a9-c70b-4509-aacb-681cd75e5f2c.png)
        
        Then, create a new user and add hem to the previosly created userGroup
        ![image](https://user-images.githubusercontent.com/74115834/152702212-8af4ec54-797c-450e-82b0-c97b633c4586.png)
        
        

