
I have created 3 tables:
user_application
movie
category_movie

user_application:
  It was created to be able to have users and identify a certain character in our application
  I used PasswordEncoder to encrypt the password and later saved the data in
  the table (  in this way you do NOT see the real password for those who have access to the database )

category_movie:
   since the category is a specific thing and there can be several and repeated 
   I did that create a table to represent it.

movie:
   I created the movie table to hold all the data including the category (linking the two tables together)
   this way I only have a reference ID for each movie in my movie table (categoryId)

By the method: upload-file
I load all the data in the database first I insert the category then the movie, 
at the same time I save the category data in a temporary cache so as not to always query 
the DB for the next records when I save the movie (using the category exists in the cache)

I could also save the movie data always in a cache but then I didn't have the time anymore!

When starting the project via the class: AcademyAwards
a user is created that you could immediately use, 
if you want to add others, just remove the two commented lines