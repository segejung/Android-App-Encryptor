# Compare Job Offer App UML Class Diagram Description
This is the description of the UML Class Diagram for compare job app. 
SeGe Jung

## 1. When the application is started, the player may choose to (1) enter or edit current job details, (2) enter job offers, (3) Adjust the comparison settings, or (4) compare job offers.  
Main application UI is made along with three sub UI's that show (1)current job details, (2) settings, and (3) compare jobs. Each sub UI's have its set of interfaces as shown in the diagram. They are inheritance to the main UI.

## 2. When choosing to adjust the comparison settings, the user may assign integer weights to (1) yearly salary, (2) yearly bonus, (3) retirement benefits, (4) relocation stipend, and (5) training and development fund. 
When Adjust the comparison weight setting is chosen, the user can make several settings as shown in the settings class. The ranges are listed in the settings class.

## 3. When choosing to enter job details, a user will: 
## a. Be shown a list of fields to enter. 
User can enter title, company, location, cost of living in the location, yearly salary, yearly bonus, retirement benefits, relocation stipend, training and development fund. User may choose to save or return to main menu. 

## b. Be shown a button to enter another offer.
User may enter another job offer using the same input fields. 


## 4. When choosing to compare jobs, a user will: 
## a. Be shown a list of rankings.
comparison of the jobs is between two jobs and will show the title, company, location, yearly salary adjusted for cost of living, yearly bonus adjusted for cost of living, retirement benefits, relocation stipend, and training and development fund. User can go back to the main menu. 

## b. Ranking of the jobs will be done by this equation
AYS + AYB + (RBP * AYS / 100) + RS + TDF

## 5. The user interface must be intuitive and responsive.
UI was not the main focus for this assignment.  

## 6. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
This is outside the scope of this assignment.

## 7. For simplicity, you may assume there is a single system running the application.



