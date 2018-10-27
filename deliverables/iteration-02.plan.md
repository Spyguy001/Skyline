# Skyline

 > _Note:_ This document is meant to be written during (or shortly after) your initial planning meeting.     
 > It does not really make sense for you to edit this document much (if at all) while working on the project - Instead, at the end of the planning phase, you can refer back to this document and decide which parts of your plan you are happy with and which parts you would like to change.


## Iteration 02

 * Start date: 15th October 2018 
 * End date: 29th October 2018

## Process

(Optional:) Quick introduction

#### Roles & responsibilities

General Roles:

1 Product Owners (Amrit)
-  Research how features discovered in the last iteration could be improved.
   Decide on the importance of each feature in terms of which ones
   should be implemented first.

1 Scrum Master (Sumit Somani)
- Arrange Planning, Sync and Review meetings
- Coordinate the members of the team to ensure tasks get done coherently.
- Will ensure that progress is moving smoothly and the various tasks are completed on time.

Dev Team (Everyone)	
- Everyone will be responsible for contributing towards the design and coding of 
  the project.
  #### Java team (Amrit, Krishna, Aleksey, Joshua)
  
  - Develop the desktop application to be used by Condo Owners and Condo managers
   ##### For this iteration:
  - Alesksey is responsible for the GUI
  - Joshua and Amrit are responsible for Condo Managers
  - Amrit is responsible for Condo Owners
  - Krishna is responsible for Authentification and Login
  
  #### Android team (Sumit)
  - Develop the mobile version of the app for residents

#### Events

- We  have our `review meeting` every Tuesday from 11:00 am to 11:30 am
  in BA3200. The purpose of these meetings are to discuss the 
  completion of the tasks for the week as laid out in the 
  planning meeting the week before and/or difficulties encountered in their pursuit.
  This weeks review meeting focused on github problems we experienced last week

- We have our `planning meetings` every Tuesday from 11:30-12pm
  These are held in BA3200 as well. The purpose of this iterations meeting 
  was to assign teams and responsibilities (ie. who works on what)

- We  have a `sync' meeting` over Discord every Thursday 
  from 11 pm to11.30 pm to make sure everyone is on track 
  to complete the tasks discussed in the planning meeting


#### Artifacts

- GitHub issues (https://github.com/freakin09/project-team-21/issues)
  - We use issues to keep track of broad coding goals for
    this iteration and track their completion. We create additional
    issues as the need arises.
- GitHub Projects (https://github.com/freakin09/project-team-21/projects/1)
  - We use Git Hub projects to keep track of the progress
    of the various goals. Our GitHub Porject is set
    to use the "automated kanban" template available with an additional "testing"
    stage added
- Discord
  - We have different channels to ensure proper communication
    between different teams and between teams. Specifically, 
    we have a "meetings" channel were a summary of the 
    things discussed in the iteration meting is posted

#### Git / GitHub workflow

- We have a single forked copy (with github user freakin09) and we make all commits 
  and pushes to that copy
  - The reason for using a forked copy and not directly committing to the main repo 
    is to prevent unintentional bugs going into the master branch (we have a protected master
    in the forked copy)
- We use branching to ensure that self contained features are in a 
  separate branch and are only merged to master upon 
  completion
- Each merge request is reviewed by the person to whom it is assigned to 
  (someone in the same team but who was not involved in the feature's development)
- Near the end of each iteration we will make a pull request to the main repo and have a
  review to ensure nothing is broken (Have a Post Mortem if something gets broken)


## Product

#### Goals and tasks

- Goals for the Database:
  - Agree on  how the database is going to be organized (ie. schema for the databse)

- Goals for the Desktop Version:

  - Login and Authentication
     - Basic GUI with entry fields and button for logging in should be in place
     - On login attempt, we should be able to verify that the credentials provided are correct
      by querying the database. 
     - Upon, successful login, depending on the "level" of the user,
      the user should be either taken to the "managers scene" or the 
      "owners scene" (condo owners should be directed to condo owners' page;
       managers should be directed to condo management page) 
      
  - Functionality for Condo Owners
    - Condo owners should be able to :
      - Add Condos to their account
      - Add Managers to any Condo they "own" creating credentials for managers
       in the process
      - Remove Managers from any given Condo that they "own"
      
  - Functionality for Condo Managers: 
    - Condo managers should be able to :
      - Create Events, Amenities and Announcements for the condo
        they are managing  
  	
- Goals for the Mobile Version: 

   - Login and Authentication
     - Upon login , Resident should be "taken" to the
       Condo the Resident belongs to.
  	
   - Announcements feature
     - Basic GUI for an Announcement should be completed
       - This would include title and details of announcement as well
         as the date on which the announcement was made. This would
         also include some symbol to indicate that the announcement
         is considered very important by the Condo management.
  	 - Retrieving from Database and storing locally the Announcements related to the
  	   specific condo the Resident belongs to. 
  	 - Displaying the Announcements fetched to the Resident using
  	   the GUI mentioned in the first point
  	 
   - Events feature
      - Basic GUI for an Event should be completed
        - This would include title and description of the Event as well
          as the date on which the Event is taking place
   	 - Retrieving from Database and storing locally the Events related to the
   	   specific condo the Resident belongs to. 
   	 - Displaying the Events fetched to the Resident using
   	   the GUI mentioned in the first point
  	
   - Amenities feature
     - Basic GUI for an Amenity should be completed
       - This would only include name of the amenity to begin with
  	 - Retrieving from Database and storing locally the Amenities present in 
  	   the specific condo the Resident belongs to. 
  	 - Displaying the Amenities fetched to the Resident using
  	   the GUI mentioned in the first point

#### Artifacts

 - Screenshots of our database schema to illustrate the organization of data.
 - Functional login scene for the desktop application. The login button should 
   perform authentication logic and redirect owners to the "owner scene", and managers 
   to the "manager scene".
 - Screenshots for the "manager scene" for the desktop application. 
   Should have the UI elements in position to view and add events, amenities and announcements. 
 - An android app that allows resident users to view announcements, available
   amenities and events for the condo they belong to. Would be verified
   by a working android app (ie. running the android project on an emulator).
 - Video demonstrating the project. The video would summarize the main problem
   the project is trying to address as well as give a overview of what
   the various users of the app can expect. 
