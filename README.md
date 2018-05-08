# README #

### About ###
This chat app only implements the UI interface and without the messangers. 
The database is Google Firebase, and the json file is not included.

### 1. First Page ###

* Just click the Log in button to Login page

### 2. Login Page ###

* Registered User will enter email and password accourindg to Firebase Authentication User list to login
* If the login info invalid, a toast message will display
* password checkbox can display or hind password
* Click New User Register Button will go to Register Page
* If Login success, will go to welcome page.

### 3. Register Page ###

* Require user to input email, password, and username, and the email and password will update to Firebase Authentication User list
* the user ID, email, and username will store in Firebase Database under UserInfo
* password checkbox can display or hind password
* Click Submit button, a toast message will display for Register success, and will return to Login page
* The register email doesn't have to be a real email, and the password must be at least 6 characters

### 4. Welcome Page ###

* This is the page user will see if login successful
* This should be a contact list and will a chatroom function. Unfortunatly, we couldn't develop those function before the deadline. 

