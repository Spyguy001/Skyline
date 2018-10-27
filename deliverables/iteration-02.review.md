# Skyline

 > _Note:_ This document is meant to be written during (or shortly after) your review meeting, which should happen fairly close to the due date.      
 >      
 > _Suggestion:_ Have your review meeting a day or two before the due date. This way you will have some time to go over (and edit) this document, and all team members should have a chance to make their contribution.


## Iteration 02 - Review & Retrospect

 * When: October 30th, 2018 
 * Where: BA3200

## Process - Reflection

(Optional) Short introduction

#### Decisions that turned out well

1. Synchronous development of both the desktop and mobile versions of the product has provided us with
insight into the various issues with the individual platforms. Due to this decision a lot of time has
been saved and potential issues have been discovered very quickly.

2. Through the use of branching, development has been split into specific sections each dedicated to a
single objective. This has created a clear-cut view of what everybody is working on and allowed for 
careful review of each feature before it is merged to master. 

3. Abandoning Style Builder in favour of manual editing of fxml and css files. This resolved a lot of bugs 
and inconsistencies in the development of the desktop GUI as well as allowed for better fine tuning of the user interface. 

#### Decisions that did not turn out as well as we hoped

1. Lack of clarity within the assigned tasks and goals, leading to delayed developement of the desktop
application. Better communication and and organization would be required to improve upon this in the
future.

2. No overarching vision for the entire project has resulted in several instances of contradictory ideas,
 and numerous changes in plans throughout development. While this can be expected as problems arise, a 
 quick resolution to these issues would be preferable. 


#### Planned changes

List any process-related changes you are planning to make (if there are any)

 * We are planning on making less documentation and deliberating on decisions less and coding more.
	* We have wasted a lot of time in both iterations deliberating over a few decisions that were important 
	but not worth stopping progress on our project over. We were being bottlenecked waiting for a decision 
	to be made when we could have coded either one and moved on and reviewed the changes afterwards. 
	This caused us to lose time and what we intially planned on doing (making complete functionality 
	for all features) was not done fully according to plan. By focusing more on development and less
	 on documentation, we can complete features faster and have time left to review them.
 * We are planning on having stricter deadlines for features that are to be completed by 
   individual group members.
	* While no one is able to consistently work on the project together due to conflicting 
	  schedules, no one gets anything done when there is no coordinating team leader or a strict schedule
	  for feature releases. We have planning and review meetings, but the deadlines for the features themselves
	  is very lax. We plan to making those enforced more and shifting work around if someone is busy so that 
	  things get done and feature development does not stagnate while waiting for the developers to get
	  back to working.
 * During out meetings, we plan to clearly outline what features each member is
   supposed to develop by what time.
	* We have had a few issues where team members do not know what they are doing or 
	think they have to do one feature that is supposed to be done by another group member. 
	We are fairly connected outside our meetings so this usually gets clarified quickly,
	but there is no reason to have this problem in the first place. We will clearly 
	outline the tasks and their developers in the meetings so that it does not happen again.

## Product - Review

#### Goals and/or tasks that were met/completed:

 - Goal: Decide on schema for the database
   - Completed tasks:
     - We agreed on how data should be organized in the database. The Key points are:
       - We will store within each Condo Collections (think objects) for Events,
         Amenities and Announcements. Each of these collections will contain 
         individual events, amenities and announcements documents (think keys) respectively 
         and each these documents will contain attributes like names and details (think values)
       - We have a Users Collection (think object) which contains individual user
         documents (think keys) and these documents will have a reference to the 
         Condo the user belongs to / owns / manages as well as a level 
         to indicate their position (ie. Owner / Manger / Resident)
     - Artifacts to verify completion:
       - Screenshots are provided under "deliverables/screenshots" on the git repo 

 - Goal: Login and Authentication for the Java App
   - Completed Tasks:
     - Login page with username/email and password fields
     - functionality for checking that the username and passwords 
       are valid
     - functionality for directing the user to the appropriate 
       page after a successful login       
    - Artifacts to verify completion:
      - Screenshots are provided under "deliverables/screenshots" on the git repo
      - Functionality can be verified via source code on repo under
        Skyline-Admin 
        
 - Goal: Login and Authentication for the Android App
   - Completed Tasks:
     - Login page with username/email and password fields
     - functionality for checking that the username and passwords 
       are valid
     - Passing the user id to an initialization activity upon successful
       login
     - functionality for retrieving the Condo the resident "belongs" to    
    - Artifacts to verify completion:
      - Screenshots are provided under "deliverables/screenshots" on the git repo
      - Functionality can be verified via source code on repo under
        Skyline-Residents 
          
 - Goal: Residents being able to view Events 
   - Completed Tasks:
     - GUI for each individual Event item which includes
       the name, description and date of the event
     - Residents are able to view a list of all the events
       which have been created for the condo by clicking on 
       the "events" tab in the "home page" 
    - Artifacts to verify completion:
      - Screenshots are provided under "deliverables/screenshots" on the git repo
      - Functionality can be verified via source code on repo under
        Skyline-Residents 
          
      
 - Goal: Residents being able to view Announcements 
   - Completed Tasks:
     - GUI for each individual Announcement item which includes
       the title, details of the announcement and date on which 
       it was made. The GUI also includes a star which is displayed
       for announcements which are considered important by Management.
     - Residents are able to view a list of all the announcements 
       which have been created for the condo by clicking on 
       the "announcements" tab in the "home page"    
    - Artifacts to verify completion:
      - Screenshots are provided under "deliverables/screenshots" on the git repo
      - Functionality can be verified via source code on repo under
        Skyline-Residents 
          
 - Goal: Residents being able to view Amenities 
   - Completed Tasks:
     - GUI for each individual Amenities item which includes
       the name and a description of the amenity.
       for announcements which are considered important by Management.
     - Residents are able to view a list of all the amenities 
       which have been created for the condo by clicking on 
       the "amenities" tab in the "home page"      
    - Artifacts to verify completion:
      - Screenshots are provided under "deliverables/screenshots" on the git repo
      - Functionality can be verified via source code on repo under
        Skyline-Residents 
          
 - Goal: Residents being able to refresh their feeds (not part of original goal)
   - Completed Tasks:
     - Users can swipe down to refresh the list of Events, Amenities and 
       Announcements   
    - Artifacts to verify completion:
      - Video under "deliverable" folder demonstrates this feature 
      - Functionality can be verified via source code on repo under
        Skyline-Residents 

#### Goals and/or tasks that were planned but not met/completed:

 - Goal: Condo Owners being able to add Condos to their account 
         and Mangers to Condos
     - Tasks not completed:
       - Integration of logic with the GUI
     - Reason: Lack of coordination with the GUI team. The GUI
       team worked on Condo Managers first and this meant that
       there was no view to integrate the "condo owners" logic when 
       it was completed. Lack of communication and oversight
       led to the integration being not done even after GUI 
       for owners was completed
       
- Goal: Condo Managers being able to create Events, Amenities and Announcements
  - Tasks not completed:
     - Functionality for creating Events, Amenities and Announcements
       (Note: GUI for this has been completed)
  - Reason: Learning how to manipulate FireBase using Java took
    longer than expected and this led to delay starting work.
    After work started, Midterms and Assignments from other courses
    led to further delays in work. 
     
## Meeting Highlights

Going into the next iteration, our main insights are:

- We were behind our planned schedule this iteration. Going forward, we hope 
  to make our plans more concrete and have more accurate estimates for the time 
  it will take to implement those plans. 
  
- There seemed to be a lack of coordination between the team responsible
  for the GUI and the team for the functionality in this iteration.
  We hope to have better coordination in the coming iterations.
  
- More focus would be placed on the "manager side" of the app in the 
  coming iterations as this side seems to be behind the others in 
  terms of implemented functionality
  
- Android version of the app is well designed and scalable. So moving forward,
  time will be better spent improving the Desktop app rather than
  continuing to make small improvements to the Mobile version

