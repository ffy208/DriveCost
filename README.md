# DriveCostPro

##### *"Budget Smarter, Drive Better"*

___

### What will the application do?

This application will **calculate** and **manage** the costs associated with driving.
Users can input vehicle-related information such as:

- Vehicle name, maker, and model
- Purchase cost
- Monthly expenses
- Current mileage
- Expected mileage
- other relevant expenses

The application will then calculate the cost per kilometer
and provide forecasts for future costs based on the input. Users can also update
their information to reflect changing circumstances, helping them gain a better
understanding of and plan for their travel expenses.

___

### Who will use it?

The app could use for **individual car owners**, **Commercial transportation companies**,
and **financial planners**, etc. Individual car owners can use it to
keep track of their car expenses and create better budgets.
Commercial transportation companies can use it to make their fleet's costs more
efficient and reduce operational expenses.
Financial planners can use it to help clients plan their future driving expenses and
long-term financial planning.
___

### Why is this project of interest to you?

I am interested in this project because it addresses a **real-life problem**.
It **helps people** better manage and understand their driving costs, offering potential
economic benefits to individuals and corporations. As the cost of living continues
to rise in Canada today, more people are concerned about their financial situation.
This application aligns with the growing demand for economic and financial information.
I want to contribute my time and knowledge gained from my coursework to turn this
idea into a usable program that can benefit a wider audience.
___

## User Stories

- As a user, I want to be able to add a vehicle to my vehicles list.
- As a user, I want to be able to view the list of vehicles on my vehicles list.
- As a user, I want to be able to delete a vehicle to my vehicles list.
- As a user, I want to be able to update information to a vehicle.
- As a user, I want to be able to see the current cost per kilometer for a vehicle.
- As a user, I want to be able to save my vehicles list to file (if I so choose).
- As a user, I want to be able to load my vehicles list from file (if I so choose).
- As a user, when I launch the application, I want to log in with the status I last saved manually.
- As a user, I want the ability to delete my account and all associated files.

---

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by choose
  the view my vehicles list, there is a panel which shows all the vehicles already been added to current user.
  There is a add a vehicle button in the bottom area, which can be use as add Xs to Y. In the previous page there
  also have a add a vehicle button.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by choose
  the delete button which allow user to delete the vehicle from their vehicle list/panel. In addition, there is a filter
  function which allow to looking for specific name of the vehicle.
- You can locate my visual component by: in the login page, the Login and Register button all have a small icon,
  in addition, after login the main function panel has a picture as the background.
- You can save the state of my application by exit button that in any panel, once click exit, it will pop-up
  the save data and exit option.
- You can reload the state of my application by pop-up windows that are displayed when the application starts, it will
  provide options to reload the data or start as a new.

---

# Phase 4: Task 2

- Sun Mar 31 23:06:41 PDT 2024
- New user: User1 has been created.
- Sun Mar 31 23:06:41 PDT 2024
- New Electric Vehicle EV1 has been created with purchase cost: $1241.12
- Sun Mar 31 23:06:41 PDT 2024
- Vehicle EV1 changed monthly expenses to: 1224.0
- Sun Mar 31 23:06:41 PDT 2024
- Vehicle EV1 changed other expenses to: 1234.0
- Sun Mar 31 23:06:41 PDT 2024
- Vehicle EV1 changed months owned to: 12
- Sun Mar 31 23:06:41 PDT 2024
- Vehicle EV1 changed current mileage to: 12445 KM
- Sun Mar 31 23:06:41 PDT 2024
- Vehicle EV1 charging cost change to: $1243.0 in total.
- Sun Mar 31 23:06:41 PDT 2024
- EV1 has been added to User1's Vehicle list.
- Sun Mar 31 23:06:41 PDT 2024
- User User1 has been added to Database.
- Sun Mar 31 23:06:53 PDT 2024
- Vehicle EV1 cost per KM: $1.4789971876255523
- Sun Mar 31 23:07:26 PDT 2024
- New Gasoline Vehicle Car1 has been created with purchase cost: $12441.0
- Sun Mar 31 23:07:26 PDT 2024
- Vehicle Car1:
- Monthly expenses change to: $123.0 per month.
- Other expenses change to: $1234.0 in total.
- Months owned change to: 23 months.
- Gasoline cost change to: 5485.0 in total.
- Current mileage change to: 42445 in KM.
- Sun Mar 31 23:07:26 PDT 2024
- Car1 has been added to User1's Vehicle list.
- Sun Mar 31 23:07:30 PDT 2024
- EV1 has been removed from User1's Vehicle list.
- Sun Mar 31 23:07:39 PDT 2024
- User: User1 changed to new name: User2.
- Sun Mar 31 23:07:42 PDT 2024
- User: User2 has been removed from Database

---

# Phase 4: Task 3

- If I had more time to work on this project. I would refactor the code to eliminate the redundancy and enhance
  abstraction. In the current code such as the calculation logic part, I could create a generic calculation class for
  different types of vehicles. This could reduce code duplication and centralize future modifications to the
  calculation logic. Similarly, for common functions between GUI classes the console class, can be abstracted into one
  or more classes.
- In addition, the UserDatabase class is needed only as a single instance through the application. I could use
  Singleton pattern to refactor it which could prevent repeated creation across different parts of the application. 
  Also, the current code seems to be improper use of public/protected/private for some fields and methods. I spend more
  time to carefully review the access level of each class, ensuring only the needed access is granted which enhance
  modularity and encapsulation.

---

### Reference

- UBC CPSC210, JsonSerializationDemo, (2021), GitHub
  repository, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
- UBC CPSC210, AlarmSystem, (2019), GitHub repository, https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
