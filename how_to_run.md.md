

For this project I have also created a video to 
  see some steps! ( this video is private, you can only view it if you have the link )

Link: https://youtu.be/sMYiL3aDmjE

1) Unzip the .ZIP file
2) Import into IntelliJ as an existing project
3) Install Postgres in your PC, Open a new terminal and write all this commands ( I use Mac ):

   a) brew update
   b) brew install postgresql
   c) brew services start postgresql
   d) CREATE ROLE mihai WITH LOGIN PASSWORD 'password';
      ALTER ROLE chris CREATEDB;
      Or create another user/pass like you want and modify: application.properties

   c) If you have problems, Followed this guide for install Postgres  ( I followed this )
       https://daily-dev-tips.com/posts/installing-postgresql-on-a-mac-with-homebrew/

Now you are ready to move for next steps

3) Open the "terminal" tab in IntelliJ and run the command:
     mvn clean install

4) Open the class: AcademyAwards and launch the application

5) Open a browser, now you can login into application by TOKEN
    http://localhost:8080/login?username=mihai&password=11111

    See class: AcademyAwards if you want to add another users!  here one example:
      userService.saveUser(new User(null, "Mihai Stefan", "mihai", "11111"));

6) Open a browser, now you can test via swagger  ( use the token obtained in step 5 )
      http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

7) Now the first thing to do is upload the file to the database, 
    then go to: api/movie/upload-file
    then enter the file name: academy_awards.csv
    to load all the data into the database, the file is found
    the "input" folder of the project





