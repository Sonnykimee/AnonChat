# AnonChat
AnonChat is a simple Spigot plugin that allows players to chat anonymously. AnonChat can protect players from losing multiplayer access because of the recently added Player Report System in Minecraft version 1.19.84.


# Commands
/anon - Hide your nickname on chat.


# Permissions
anon.use - Use anon command.


# Configurations
**name-display-format: '&f<&7Anon_%NAME%&f> %MSG%'**

&: Color code. See [Minecraft Color Codes](https://htmlcolorcodes.com/minecraft-color-codes/).

%NAME%: Random generated nickname placeholder.

%MSG%: Chat message placeholder.


**name-length: 8**: Length of nickname.


**random-seed-text: 'DownWithM$'**: A small piece of text that will be used to create a random seed along with current local date, and the nickname of the player who sent the chat.


**random-name-charset: 'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890'**: Characters that can be in the generated nickname (will remove duplicates).
