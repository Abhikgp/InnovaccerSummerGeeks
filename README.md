# InnovaccerSummerGeeks
Entry Management Software developed as a part of the Innovaccer Hiring Challange.

# Problem Statement
The Software aims to provide users with an interface to record check-in and check-outs for visiting various hosts within a building.
At the time of check in the visitor enters their details (Name, Email ID, Phone Number) and the Email id of the host. Once check-in is done, the host receives a message and email stating these details and the check-in time.
At the time of check out 

# Usage
1. Clone the Repository.
2. Run db.sql to CREATE DATABASES and a User with all permissions on the database.
3. Modify DatabaseHandler.java (line 25) - change the username and password to the MySQL user created above.
4. Modify EntryManager.java (line 854-855,864-865) - change the username and password to a Gmail ID that should be used for sending emails to Hosts and Visitors. Note that the gmail ID must allow "Less Secure Applications" to access it under Security Settings.

# Technologies Used
- JavaFX for UI
- JDBC for storing HOSTS and CHECK-IN, CHECK-OUT Information
- JavaMail Library for sending Emails
- BulkSMS REST API for Sending SMS

# Screenshots
![Check-In](https://github.com/Abhikgp/InnovaccerSummerGeeks/blob/master/Screenshots/check_in.png)
![Check-Out](https://github.com/Abhikgp/InnovaccerSummerGeeks/blob/master/Screenshots/checkout.png)
![Show Visitors](https://github.com/Abhikgp/InnovaccerSummerGeeks/blob/master/Screenshots/show_visitors.png)
![Search-Token](https://github.com/Abhikgp/InnovaccerSummerGeeks/blob/master/Screenshots/search_token.png)
