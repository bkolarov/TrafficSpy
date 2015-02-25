# TrafficSpy
Internet Programming school project - Android application that shows which streets has traffic jam at the moment.

#Basic functionality 
Anyone with the app must make a registration. After this registration he'll be able to point on the map where he saw a trafic jam or if there was a traffic jam but not anymore, the user can tell that the road is free.

#Backend
With every registration, every device will be registered on Google Cloud Messaging Server and the regitration number will be saved in a database. This will be used for push notifications. Users will receive push notification for a traffic jam or a free road. An admin user will be able to delete registered accounts and to remove traffic jam pins.

#Server-side
The server will be written in Java. For sending notifications the Google API will be used. Server implementation will use the REST API.
