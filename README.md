# FireworksBan

Author : Pompier15

This is a minecraft plugin to prevent usage of laggy fireworks on your server.
Each time a firework spawn it checks how many effects are on it. If there is too many, the spawn is prevented and a command is executed on the sender.

You can parameter the max number of effects on a single rocket you want, and what command to execute on the player who launch it.

When writting your custom command use %PLAYER% to get the name of the player who launch the rocket. And write none if you don't want any command to execute.

You can give the permission "firework.limitation.bypass" to a player for not being affected by the plugin restrictions
