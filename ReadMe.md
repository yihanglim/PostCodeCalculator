Unzipped the folder first.

To run the program: (Using maven)

1. In command prompt, change directory to the root of the folder (PostcodeCalculator)
2. Run command: mvn clean install
3. Run command: mvn sprint-boot:run
4. The program should start running. In case it show " No plugin found for prefix 'sprint-boot' in the current project....".
   Try run by this command:mvn org.springframework.boot:spring-boot-maven-plugin:run
5. This program provide a API GET method to calculate distance of provided postcode and PUT method to update coordinate of the postcode. Use API tools like Postman to test the program.
6. This API require basic authentication. Use this sample username and password for calling the api.
   username: admin
   password: 1234
7. GET method for calculate distance url: http://localhost:8080/api/v1/distance?postCode1=AB10%201XG&postCode2=AB11%205QN
8. Should return a result like this:
   {
   "location1": {
   "postcode": "AB10 1XG",
   "longitude": 57.144165,
   "latitude": -2.114848
   },
   "location2": {
   "postcode": "AB11 5QN",
   "longitude": 57.142701,
   "latitude": -2.093295
   },
   "distance": 2.40209923695084,
   "unit": "km"
   }
9. PUT method for update coordinate of postcode input: http://localhost:8080/api/v1/location/update
   Body (JSON):  {
   "postcode": "AB10 1XG",
   "latitude": 0,
   "longitude": -2
   }
10. To run the unit test, type in this in command prompt: mvn test
11. This conclude the execution of this program. Thank you.