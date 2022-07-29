# AnonChat
AnonChat is a simple Spigot plugin that allows players to chat anonymously. AnonChat can protect players from losing multiplayer access caused by the recently added Player Report System in Minecraft version 1.19.84.


# Commands
/anon - Hide your nickname on chat.


# Permissions
anon.use - Use anon command.


# Configurations
**name-display-format: '&f<&7Anon_%NAME%&f> %MSG%'**

&: Color code. See (https://htmlcolorcodes.com/minecraft-color-codes/).

%NAME%: Random generated nickname placeholder.

%MSG%: Chat message placeholder.


**name-length: 8**
Length of nickname.


**random-seed-text: 'DownWithM$'**
This small piece of text will be used to create a random seed. The random seed is also based on the current local date, and the name of player.


**random-name-charset: 'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890'**
Characters that can be in the generated nickname (will remove duplicates).
