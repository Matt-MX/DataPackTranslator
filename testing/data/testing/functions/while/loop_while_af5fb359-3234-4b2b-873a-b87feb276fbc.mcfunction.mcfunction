# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
tellraw @a {"text":"{\"color\":\"red\",\"text\":\"Looping\"}"}
scoreboard players add $global loop_while_af5fb359-3234-4b2b-873a-b87feb276fbc 1
execute run schedule function testing:while/loop_while_af5fb359-3234-4b2b-873a-b87feb276fbc.mcfunction 1t replace