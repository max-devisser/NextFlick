# NextFlick
UCSB CS48 Student Project - A Java movie database with searching and recommendations
https://github.com/max-devisser/NextFlick.git

Creators: Artem Jivotovski, Barbara Korycki, Cole Margerum, Max de Visser, Isaac Zinman


Building and Executing:
We use ant to handle dependencies, build, and run our program. Building works on any OS from the command line.
Downloaing our repo will put all of our files in the correct location. From there:

1. Go into the root of this file structure.
2. Run using the command: ant run

List of Known Bugs:
There are no known bugs.

How to use:

Search Panel
-On the "Search" panel, search for a specific movie attribute (title, actor, director, etc.)
-Searches are made by clicking the enter button or hitting the return key on your keyboard
-If you get no results for your search, or you don't want to filter by a previous search item, remove the filter of choice by clicking the "X" next to the red box of the filter's search item

History Panel
-On the "History" panel, you can see the list of movies that you have previously rated
-Removing a rating on any panel removes that movie from your history list

Recommendations Panel
-On the "Recommendations" panel, you can see the list of movies that we think you might like
-The "Recommendations" panel will only display movies for you to see if you have rated enough movies for us to give you a list

Rating (spans every panel)
-On any panel you are able to rate a movie. Do this by clicking that movie's "Rate" button
-You can also remove a rating by clicking the "Delete Rating" button, but only if you have already rated that movie
