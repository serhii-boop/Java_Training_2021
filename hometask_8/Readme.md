1)Build and run docker image of the app
    
    First, we have to create Dockerfile as follow below in the project root
        FROM adoptopenjdk:11-jre-hotspot
        ADD target/firstdocker.jar firstdocker.jar
        EXPOSE 8070
        ENTRYPOINT ["java", "-jar", "firstdocker.jar"]
        
    Then build an image with the name of your jar file
    
![1](https://user-images.githubusercontent.com/74115834/152702670-9bb87e0f-9c0d-4b91-a332-f1c5044c3133.png)


    Next run the image.
![2](https://user-images.githubusercontent.com/74115834/152702718-777e1014-3dd3-4f0e-a453-304f3eb73087.png)


    Success
![3](https://user-images.githubusercontent.com/74115834/152702756-446efe28-a4cb-41f0-b6df-0add4bfc9023.png)


2)Creating docker image to deploy to the AWS Elastic Beanstalk instance


        First, we have to create a user group in IAM
  ![4](https://user-images.githubusercontent.com/74115834/152702119-5ba8b3a9-c70b-4509-aacb-681cd75e5f2c.png)
        
        Then, create a new user and add hem to the previously created userGroup
  ![5](https://user-images.githubusercontent.com/74115834/152702212-8af4ec54-797c-450e-82b0-c97b633c4586.png)
        
        Next create an enviroment at EB and deploy dockerfile    
   ![6](https://user-images.githubusercontent.com/74115834/152702862-42440a75-cec8-4a76-bbd1-eb410319f27c.png)
   
   URL of the deployed application
   http://my-env.eba-ibyyptmu.eu-north-1.elasticbeanstalk.com/swagger-ui.html
   ![7](https://user-images.githubusercontent.com/74115834/152702898-a2bd223c-d25d-4bdd-ab54-6119fa57f685.png)


        

