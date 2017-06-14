Social Network
======

Introduction:
======

Algorithms and Datastructures fifth assginment. This assignment introduces
Hashmaps to model a Social Network. The use of implementing serializable is 
also introduced.

- The hashmaps are used to create two databases for each user and each account.

- Users log in via email address and password.

- Each user's name gets stored, as well as a list of who the user follows and 
a list of who follows them.

- When a user follows another user, the user's email address is listed in the
followed user's followers, and list of followed people of the following user is
updated to include the person followed.

- Each user can login and logout whenever they want.

- Each user can follow and unfollow users whenever they want.

- If a user deletes their account, their followers and people followed
must be cleared.

- All data must be saved when the program is stopped (serialized).