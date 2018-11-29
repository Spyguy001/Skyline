# YOUR PRODUCT/TEAM NAME

 > _Note:_ This document is meant to be written during (or shortly after) your review meeting, which should happen fairly close to the due date.      
 >      
 > _Suggestion:_ Have your review meeting a day or two before the due date. This way you will have some time to go over (and edit) this document, and all team members should have a chance to make their contribution.


## Iteration XX - Review & Retrospect

 * When: FILL IN THE DATE WHEN YOU ACTUALLY HAD YOUR REVIEW MEETING
 * Where: PHYSICAL LOCATION AND/OR ONLINE

## Process - Reflection

(Optional) Short introduction

#### Decisions that turned out well

 1. While there was a rocky start with regards to team organization and branching, near the 
 end of development, good use of specialized branches allowed for excellent task management 
 and overall organization. There were no cases of duplicate code or unexpected merge conflicts.

 2. Good task assignment during this iteration; everyone's roles and responsibilities were clear
 and straightforward. The primary goals were achieved at the quickest possible pace as a result
 of this.

#### Decisions that did not turn out as well as we hoped

 1. Git issues was rarely used and did not impact the development of the project all that much. 
 It would have been more successful if it was expanded upon and provided a more detailed rundown 
 of all the issues and requirements that were present.

 2. Several features were neglected during development leading to them being introduced quite late 
 and in an inefficient manner. This could be resolved through better monitoring of our initial 
 requirements and all promised features.

#### Planned changes

List any process-related changes you are planning to make (if there are any)

 * Ordered from most to least important.
 * Explain why you are making a change.


## Product - Review

#### Goals and/or tasks that were met/completed:

- Goal: Implement the Data Access Object (For Java)
    - Completed tasks:
       - Methods for adding / deleting Managers and Residents to a specified Condo 
       - Methods for retrieving Managers and Residents from a specified Condo 
       - Methods for adding / deleting Events, Amenities and Announcements to a specified Condo 
       - Methods for retrieving Events, Amenities and Announcements from a specified Condo 
    - Artifacts to verify completion:
       - Source code under git
 
##### Desktop App 
  - Goal: Allow Condo Owners to add Condos to their profile 
      - Completed tasks:
         - Fields for owners to enter details of their new Condo 
         - Getting data from the fields and creating a new Condo.
         - Changing the Database to reflect the addition of the new Condo
           to the owner
      - Artifacts to verify completion:
          - Screenshots are provided under "deliverables/screenshots/d3/" on the git repo 
          
  - Goal: Allow Condo Owners to add Managers to specified Condo
      - Completed tasks:
         - Fields for owners to enter credentials for new Manager 
         - Getting data from the fields and creating a new Manager.
         - Changing the Database to reflect the addition of the new Manager
           to the Condo specified
      - Artifacts to verify completion:
         - Screenshots are provided under "deliverables/screenshots/d3/" on the git repo 
         
  - Goal: Allow Condo Managers create Events, Amenities and Announcements for the condo
          they are managing  
      - Completed tasks:
         - Fields for entering details of new Event / Amenity / Announcement 
         - Getting data from the fields and creating a new Event / Amenity / Announcement 
         - Changing the Database to reflect the addition of the new 
           Event / Amenity / Announcement to the Condo the manager is managing 
      - Artifacts to verify completion:
         - Screenshots are provided under "deliverables/screenshots/d3/" on the git repo 
         
  - Goal: Allow Condo Managers to add Residents to the Condo they are managing
      - Completed tasks:
         - Fields to enter credentials for new Resident 
         - Getting data from the fields and creating a new Residnet.
         - Changing the Database to reflect the addition of the new Resident 
           to the Condo specified
      - Artifacts to verify completion:
         - Screenshots are provided under "deliverables/screenshots/d3/" on the git repo 
  
  - Goal: Allow Condo Managers to mark certain announcements as important 
      - Completed tasks:
         - A check box added to GUI mark an announcement as important when creating it 
         - Flag added to Announcement object. The flag is set to true
           when the above mentioned check box is checked
      - Artifacts to verify completion:
         - Looking at the code to check addition of flag and logic to set it to true.
         
  - Goal: Allow Condo Managers to send out push notifications 
      - Completed tasks:
         - A check box added to GUI mark if an announcement should be sent out 
           as a push notification 
         - Method added in database handler to send out notification data
           to all residents in the specified condo
         - Logic to trigger the above mentioned method when the above mentioned
           check box is checked
      - Artifacts to verify completion:
         - Video under "deliverables/" on the git repo   
         
  - Goal: Allow Mangers to make certain amenities bookable 
      - Completed tasks:
         - A check box added to GUI to mark an amenity as bookable 
         - Flag added to Amenity object. The flag is set to true
           when the above mentioned check box is checked
         - Field added to GUI to allow managers to decide for how long an 
           amenity can be booked (eg booking time intervals)
         - Field added to Amenity object to store this time interval
      - Artifacts to verify completion:
         - Looking at the code to check addition of flag and dield and logic to set it
         
  - Goal: Allow Mangers to refresh their feed 
      - Artifacts to verify completion:
        - Video under "deliverables/" on the git repo   
##### Android App                          
  - Goal: Allow Residents to create Events 
    - Completed Tasks:
      - GUI for event creation with fields to enter details of event
      - getting data from the fields and creating a new Event object
      - Method in database handler to add the new Event to the Condo the 
        Resident resides in
     - Artifacts to verify completion:
       - Video under "deliverables/" on the git repo   
       
  - Goal: Residents being able to view details of Events and Amenities 
    - Completed Tasks:
      - GUI for an individual Event item which includes
        the title, details, location and time of the event and date on which 
        it was made.
      - Dialogue box with the above mentioned GUI's displayed when
        an event item is clicked on 
      - GUI for an individual Amenity item which includes
        the name and details of the amenity
      - Page with the above mentioned GUI's displayed when
        an amenity item is clicked on 
     - Artifacts to verify completion:
       - Video under "deliverables/" on the git repo   
  
  - Goal: Allow Residents to receive push notifications 
      - Completed tasks: 
         - class and service added receive notifications from FCM (firebase cloud messaging)
         - Display the nitrification received with the data on the notification panel 
         - Take resident to the app when notification clicked on
      - Artifacts to verify completion:
         - Source code under git
         - Video under "deliverables/" on the git repo  
         
  - Goal: Allow Residents to book an Amenity 
      - Completed tasks:
         - GUI added to amenity page to allow selection of date of booking 
         - display all available times for the date in a easily readable format
      - Artifacts to verify completion:
         - Video under "deliverables/" on the git repo
         
  - Goal: Allow Residents to view all the bookings they have made 
      - Completed tasks:
         - GUI added to list details of all bookings made by user  
      - Artifacts to verify completion:
       - Video under "deliverables/" on the git repo    
 
 #### Goals and/or tasks that were planned but not met/completed:
 
- Goal: Allow Residents to book an Amenity 
   - Tasks not completed:
     - Logic to retrieve all the available time slots from the database
     - Logic to change the database to reflect that a new booking has been made 
   - Reason: Due to lack of time, we decided to focus more on making
             sure that the essential eatures where
             completed first
        
- Goal: Allow Residents to select whether they will be going to an event 
  - Tasks not completed:
     - No work on this feature was started
  - Reason: Due to lack of time , we decided to not go forward
            with this feature. We decided to focus on completing the
            features which we thought were more essential (based on our initial research which can 
            be found under deliverable 1).
             
- Goal: Allow Managers to view the bookings made for a particular amenity 
   - Tasks not completed:
     - Work on this feature was not started 
   - Reason: The tasks planned for the iteration together with tasks left over from
             previous iteration turned out to be more than could be completed
             during the iteration. Hence no work for this feature could be started.    

- Goal: Allow Managers to see if residents are interested or going
        to a certain event 
   - Tasks not completed:
     - Work on this feature was not started 
   - Reason: This feature was not started in the android app and hence it would
             have been futile to allow managers to view if residents are going 
             to an event when residents would have no option to actually
             specify their interests.  
     

## Meeting Highlights

Going into the next iteration, our main insights are:

- We were behind our planned schedule this iteration as well. Going forward have
  more accurate estimates for plans taking into account assignments and midterms
  that each member may have 
  
- Having more meetings over Discord seemed to solve (or at least alleviate) the
  problems of lack of clarity of task and problems of lack of direction. Hence going forward, 
  we expect to carry on having more weekly meetings.

- More focus would need to be placed to ensure that the "Booking side" of Amenities
  is complete as this was a feature started on during the iteration but not 
  completed.
  
- Since the MVP as defined by our initial research is now complete, going
  forward more research needs to be conducted to determine what new features 
  are considered important by the potential users of the app and hence should 
  be implemented.
