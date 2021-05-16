UVBOT App

During the internship by KCIRI, I created a UVBOT app which is used to control the UVBOT. The UVBOT is a remote-controlled bot with the app. 
The bot is mainly used to sanitise the whole room. 

The app contains two screens.
The first is about the instructions page and the next one is the console for controlling the bot.


The second screen will look like a gaming console with motion-controlling buttons and the timer on the bottom. 
There is a camera-window on the left to see the environment around the bot using the cam mounted on bot. On the top there will be buttons to control the UV-light on the bot.
You can find the two screens for the app here https://github.com/Raajakishore/UV_BOT/tree/master/UV_UI .


For communication between bot and the app TCP/IP protocol is used. Sockets are used to establish the connection. The camera feed is hosted on the raspiberry pi on port 8080.
That port is accessed on the app.


In the app, when one button on D-pad is pressed the other buttons should go inactive, implementing this was the hardest part.
