# PostsApplication
Hi this application includes everything in the challenge document and some small behaviors I added myself:

-Date is displayed for every post so it can be sorted by date and new posts added are at the top of the list.

-Since the returned post list from network is static (is always the same) I implemented a system where only new posts

are added to the list so if no new posts are retrieved the list will not change.

-Implemented room database for caching since is one of the most supported libraries by google for local storage.

-Added an image when there are no posts to display.

-A single fab button to save post remote and local.

-Posts and comments are saved on the device every time they are retrieved so they can be shown when there is no connection

-Most changes are in develop branch

-Clean architecture to help with separation of concerns, mostly the data layer

-Repository implementation to separate network request logic from other classes

-MMVM to handle view logic from data logic
