# Skyline

## Iteration 3

 * Start date: 29th October 2018 
 * End date: 27th November 2018 

## Process

(Optional:) Quick introduction

#### Changes from previous iteration

List the most significant changes you made to your process (if any).

  - We plan to have more sync meeting than during last iteration. 
    Lack of clarity with assigned tasks and goals lead to 
    delayed development of the desktop application during last iteration
    and we hope to tacle this problem by ensuring better communication
    
  - Lack of concrete plans at the start of the previous iteration led to
    contradictory ideas, and numerous changes throughout development.
    We therefore hope to agree on details of the project early on this time
    and stick to the plan as much as possible

#### Roles & responsibilities

General Roles:

1 Product Owners (Amrit)
-  Research how features worked on in the last iteration could be improved.

1 Scrum Master (Sumit Somani)
- Arrange Planning, Sync and Review meetings
- Coordinate the members of the team to ensure tasks get done coherently.
- Will ensure that progress is moving smoothly and the various tasks are completed on time.

Dev Team (Everyone)	
- Everyone will be responsible for contributing towards the design and coding of 
  the project.
  #### Java team (Amrit, Krishna, Aleksey, Sumit)
  
  - Develop the desktop application to be used by Condo Owners and Condo managers
   ##### For this iteration:
  - Alesksey is responsible for the GUI 
  - Amrit is responsible for the events and announcements controllers and condo/condo manager models
  - Krishna is responsible for residents and amenities controllers and condo/condo manager models
  - Sumit is responsible for implementing the database access object
  
  #### Android team (Sumit)
  - Develop the mobile version of the app for residents
    ##### For this iteration:
    - Allow residents to create events
    - Allow Residents to view details of an event
    - Allow Residents to view details of an amenity 
    - Allow Residents to book an Amenity

#### Events

- We will have a `planning meeting` every Tuesday from 11:30-12pm
  These are held in BA3200 as well. The purpose of this meeting 
  would be to decide on tasks to be completed during the
  week and assign tickets to individuals 
  
- We will have a `review meeting` every Tuesday from 11:00 am to 11:30 am
  in BA3200. The purpose of these meetings would be to discuss the 
  completion of the tasks for previous the week as laid out in the 
  planning meeting the week before and/or difficulties encountered in their pursuit.

- We will have a `sync' meeting` over Discord every Thursday 
  from 11 pm to 11.30 pm to make sure everyone is on track 
  to complete the tasks discussed in the planning meeting

#### Artifacts

Our Artifacts remain unchanged from the previous interation:

- GitHub issues (https://github.com/freakin09/project-team-21/issues)
  - We use issues to keep track of broad coding goals for
    this iteration and track their completion. We create additional
    issues as the need arises.
- GitHub Projects (https://github.com/freakin09/project-team-21/projects/1)
  - We use Git Hub projects to keep track of the progress
    of the various goals. Our GitHub Project is set
    to use the "automated kanban" template available with an additional "testing"
    stage added
- Discord
  - We have different channels to ensure proper communication
    between different teams and between teams. Specifically, 
    we have a "meetings" channel were a summary of the 
    things discussed in the iteration meting is posted

#### Git / GitHub workflow

Out Github workflow remains unchanged from last itteration.

- We have a single forked copy (with github user freakin09) and we make all commits 
  and pushes to that copy
  - The reason for using a forked copy and not directly committing to the main repo 
    is to prevent unintentional bugs going into the master branch (we have a protected master
    in the forked copy)
  - The master in our forked copy therefore acts as an "experimental" branch
    found in more traditional standard git practices  
- We use branching within the forked copy to ensure that self contained features
  are in a separate branch and are only merged to master upon 
  completion
- Each merge request is reviewed by the person to whom it is assigned to 
  (someone in the same team but who was not involved in the feature's development)
- Near the end of each iteration we will make a pull request to the main (CSC301) remote repo 
  and have a review (and test) to ensure nothing is broken (Have a Post Mortem meetinf 
  if something gets broken)


## Product

#### Goals and tasks

- Goals for the Database:
  - Implement the data access object to allow for the addition and deletion of condos
    managers, residents, events, amenities and announcements to the Databse 

- Goals for the Desktop Version:

  - Functionality for Condo Owners
    1)  Condo owners should be able to : (carried from iteration 2 and hence top priority)
        - Add Condos to their account
        - Add Managers to any Condo they "own" creating credentials for managers
          in the process
        - Remove Managers from any given Condo that they "own"      
      
  - Functionality for Condo Managers: 
    2)  Condo managers should be able to : (carried from iteration 2 and hence top priority)
        - Create Events, Amenities and Announcements for the condo
        they are managing  
        
    3)  Condo mangers should be able to create credentials for 
        and add residents to the condo they are managing
    
    4)  Condo managers should be able mark certain announcements as "important"  
    
    5)  Condo managers should be able to send out push notifications to all
        the residents in the condo they are managing
        
    6)  They should be able to make certain amenities as bookable and define
        constraints on how the amenity can be booked
    
    7) They should be able to refresh their view of the Condo to get 
       up to date information
    
    8)  Managers should be able to view the bookings made for a particular
        amenity and be able to cancel a booking 
        
    9)  Managers should be able to see if residents are interested or going
        to a certain event (this is conditional on the android version
        being completed)
  	
- Goals for the Mobile Version: 
   
   1) Residents should be able to create their own events
   
   2) Residents should be able to view details of an event and an amenity
      
   3) They should be able to receive push notifications sent by managers
   
   4) They should be able to book an amenity at a particular date and using
      an available time slot
      
   5) Residents should be able to view all the bookings they have made
   
   6) They should be able to select whether they will be going to a particular
      event or not
      
#### Artifacts
 
 - Screenshots GUI while the GUI is being worked on to ensure that
   the feature looks as what the developer of the feature intended for it
   to look like 
    
 - Desktop application features completed and pushed to github. When cloned
   and ran:
    
    - the application should allow Condo Owners to add Condos to their
      profile and add managers for a specified Condo. 
      
    - A manager should be able to login using the credentials provided by the owner.
    
    - Creation/ Deletion of or changes to an event, amenity or announcement should be 
      reflected in the database (verified by screenshots of the database before and after
      the change) 
      
    - Code for allowing managers to impose constraints on amenity booking should be present  
      
    - Any notification sent out by a manger should be received by residents 
      (verified by visual inspection)
      
 - The android app when ran on an emulator should allow users to view details of 
   an event and amenity, create events, book amenities and view all the bookings made.
   
 - When a manger sends out a push notification, it should be received on the
   emulator on which a resident is logged in (verified by visual inspection)
   
 - Video demonstrating the project uploaded to github. The video would summarize the main problem
   the project is trying to address as well as give a overview of what
   the various users of the app can expect. 
