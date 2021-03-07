# Workshops - Servlets & JSP

Before running this project it is necessary to:
1. Add JDBC Connector to dependencies - you can download it here: 
   https://dev.mysql.com/downloads/connector/j/.
   
2. Add schema "workshops_2" in local database. If you want to use different schema then the name has to be changed in files:
   - context.xml line 10 (src/main/webapp/META-INF/context.xml),
   - DBUtil line 22 (src/main/java/org/example/dao/DbUtil.java).
    
3. Add your local database username and password to the file context.xml lines 6 and 7.

4. Import data from file data.sql in content root.

5. Add tomcat configuration to run the project.


## Project overview

This project is extension of JDBC workshops that you can find here: https://github.com/martyna-matuszak/Warsztaty_JDBC 

It consists of three main sections.

### Main page
Homepage allows user to see recent solutions, see user details, user groups and exercises without the necessity of logging in.
There is no possibility of changing any the data from this section.

### User panel
It requires inserting valid username and password. Login system in this section is very simple because it is restricted to only details page and form, so I've written it manually and it is based on just one class and session parameters.

Logged in user can see the their details, solutions to the exercises, gpa and unsolved exercises. User can add solution to the unsolved exercise if there are any.

### Admin panel
It requires inserting valid username and password and the user role must be admin (role is stored in the database). Here login system is based on the web filter that forbids the access to all pages under url /panelAdmin before entering valid credentials.

Logged in admin can see, add, edit and delete users, user groups and exercises. Solution section allows for viewing variously filtered lists of solutions and grading users solutions. It is impossible to add, edit or delete them in any way.
