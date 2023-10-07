-  Splitwise is an expense sharing app. 
- Users log expenses with other users.
- Helps with settlements. 

#### What kind of app? 

- Class Diagram or Interactive App or Backend API 

##### Questions: 
- How the data will be persisted?  (RAM or Make Database)
	- Database
- How will you test the system? (Using POSTMAN or Interactive App)
	- Postman


### Requirement Gathering 

1. Sketch 
2. User Journey 

or 

Requirement documents is already given. 


### Requirements:

- Users can register and update their profiles 
- A user's profile should contain at least their name, phone number and password

- Users can participate in groups.
- Only some participants of the group can add/remove members to the group

- Users can participate in expenses with other users 
- To add an expense
	- along with who paid what and 
	- who owes what. 
	- a user must specify either the group,
	- or the other users involved in the expense, 
	- They must also specify a description for the expense.
    
- A user can see their total owed amount    
- A user can see a history of the expenses they're involved in   
- A user can see a history of the expenses made in a group that they're participating in
- Users shouldn't be able to query about groups they are not a member of -> RBAC (Role Based Access Control)

- Users can request a settle-up for any group they're participating in. The application should show a list of transactions, which if executed, will ensure that everyone participating in the group is settled up (owes a net of 0 Rs). Note that it will only deal with the expenses made inside that group. Expenses outside the group need not be settled.   
- Users can request a settle-up for himself/herself. The application should show a list of transactions, which when executed will ensure that the user no longer owes or receives money from any other user. Note that this need not settle any other user.
- When settling a user or a group, we should try to minimize the number of transactions required to settle up the user or the group. (Algorithm for minimizing transactions)
    

### Input Format


Register vinsmokesanji 003 namisswwaann

u1 is registering with the username "vinsmokesanji", phone "003" and password as "namisswwaann"

u1 UpdateProfile robinchwan

u1 is updating their profile password to "robinchwan"

u1 AddGroup Roommates

u1 is creating a group titled "Roommates"

u1 AddMember g1 u2

u1 is adding u2 as a member of the group "Roommates" (which has the id g1)

u1 MyTotal

u1 is asking to see the total amount they owe/recieve after everything is settled.

u1 History

u1 is asking to see their history of transactions (whether added by themselves or someone else)

u1 Groups

u1 is asking to see the groups that they're a member of

u1 SettleUp

u1 is asking to see the list of transactions they should perform to settle up

u1 SettleUp g1

u1 is asking to see the list of transactions that need to be performed by members of g1 to completely settle up the group.

u1 Expense g1 iPay 1000 Equal Desc Wifi Bill

u1 is adding an expense in the group g1. u1 paid 1000 Rs each user of g1 owes an equal amount (the exact amount will depend on the number of users in group g1. Say g1 has 5 users, then the amount owed by each will be 200Rs).

u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner

u1 is adding an expense with users u2, u3 and u4. u1 paid 1000 Rs each user owes an equal amount - 250Rs.

u1 Expense u2 u3 iPay 1000 Percent 20 30 50 Desc House rent

u1 is adding an expense with users u2 and u3 u1 paid 1000 Rs u1 owes 20% (200Rs), u2 owes 30% (300Rs) and u3 owes 50% (500Rs).

u1 Expense u2 u3 u4 iPay 1000 Ratio 1 2 3 4 Desc Goa trip

u1 is adding an expense with users u2, u3 and u4. u1 paid 1000 Rs u1 owes 100Rs (1 part), u2 owes 200Rs (2 parts), u3 owes 300Rs (3 parts) and u4 owes 400Rs (4 parts).

u1 Expense u2 u3 iPay 1000 Exact 100 300 600 Desc Groceries

u1 is adding an expense with users u2 and u3. u1 paid 1000 Rs u1 owes 100Rs, u2 owes 300Rs and u3 owes 600Rs.

u1 Expense u2 u3 MultiPay 100 300 200 Equal Desc Lunch at office

u1 is adding an expense with users u2 and u3. u1 paid 100 Rs, u2 paid 300Rs and u3 paid 200Rs. Each user owes an equal amount - 200Rs.

u1 Expense u2 u3 MultiPay 500 300 200 Percent 20 30 50 Desc Netflix subscription

u1 is adding an expense with users u2 and u3. u1 paid 500 Rs, u2 paid 300Rs and u3 paid 200Rs. u1 owes 20% (200Rs), u2 owes 30% (300Rs) and u3 owes 50% (500Rs).


### Clarify requirements 

Q: Can one expense be paid by multiple peoples? 
=> Yes

Q: Is the expense shared equally? 
=> No

Q: Should expense is shared by all members of the group always? 
=> No 

Q: Can an expense be added that doesn't belong to a group? 
=> yes 



#### Minimise the Transaction 

1. Round Robin : Settle the previous person 
2. Greedy : The person who paid max amount will get money from person who needs to pay more. 
		- Logic (Two priority Queue)
			-Add paid less in one and paid more in one, AMOUNT = min of two 
			Subtract amount from both side, if not zero then add again in the priority queue.

### Enums
- Role : Admin


### Class Diagram 

| User | 
|-----|
|id|
|name|
|phone_no|
|password |

|Expense |
|---------|
|id |
|name|
|expense_date|
|amount|
|created by|
|created at |

|Group|
|------|
|id |
|name |
|Created by| 

| Group Participants | 
|------|
| id |
|user|
|group|
|role|

| Expense | 
|----------|
| id | 
|name |
|date| 
|Created by|
|created at |
|amount| 


|Expense_Paid_by|
|-----|
| id |
|expense |
| user| 
| amount | 

| Expense_shared_by |
| --------- |
|id |
|expense |
| user |
| amount |

| Group Expense | 
| ------- | 
| id |
| group | 
| expense | 



### Schema Design 

- User : id , name, phone number 
- Group: id, name, created by
- Group_participants: id, group id, user_id, role
- Expense: id, name, expense_date, created_by, created_at, amount 
- expense_paid_by: id, amount, expense_id, uid
- expense_shared_by: id, amount, expense_id, user_id
- Group_expense: id, gid, expense_id

### Controllers 

| Group Controller | 
| -------|
|settleGroup |

| UserController | 
| ----- | 
| getUser | 
| registerUser | 

| ExpenseController | 
| ------ | 
|createExpense | 

-> registerUser name phoneNumber pwd
-> getUser userid
-> settleGroup groupId
-> createExpense  

### Problem 

In command line, if we use scanner, and write different conditions to execute the above statements inside if-else statement, **Single Responsibility Principle and Open Closed Principle** is violated.

### Solution

Create an interface with name Command 
	- Methods
		- boolean canExecute(String input)
		- void execute() 

Derive it 4 times-> 
	- RegisterUserCommand
	- GetUserCommand
	- SettleGroupCommand
	- CreateExpenseCommand


Note: Whatever is declared with @Controller @Service @Repository @RestController @Component, Springboot create one object of each class and application starts, wherever application is required, the object is passe. 

Optional in Java -> gets null check support, could return null 


### Steps for testing using postman

1. Search Maven Repository for spring boot starter web  
2. update pom and refresh maven  
3. change controller to RestController  
4. update methods with get or post mapping  
5. Body or Params @RequestBody, @RequestParams (2048 characters allowed for @RequestParams)