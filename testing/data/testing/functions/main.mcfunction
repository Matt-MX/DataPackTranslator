# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
scoreboard players set $global arrayTest_size 0
scoreboard players set $global arrayTest_0 -10000
scoreboard players remove $global arrayTest_0 100
scoreboard objectives add loop_while_af5fb359-3234-4b2b-873a-b87feb276fbc dummy
scoreboard players set $global loop_while_af5fb359-3234-4b2b-873a-b87feb276fbc 0
schedule function testing:while/loop_while_af5fb359-3234-4b2b-873a-b87feb276fbc.mcfunction 1t replace
execute if block ~ ~ ~ minecraft:air if block ~ ~1 ~ minecraft:air unless block ~ ~-1 ~ minecraft:air run scoreboard objectives add loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a dummy
execute if block ~ ~ ~ minecraft:air if block ~ ~1 ~ minecraft:air unless block ~ ~-1 ~ minecraft:air run scoreboard players set $global loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a 0
execute if block ~ ~ ~ minecraft:air if block ~ ~1 ~ minecraft:air unless block ~ ~-1 ~ minecraft:air run schedule function testing:fors/loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a.mcfunction 1t replace
execute if block ~ ~ ~ minecraft:air if block ~ ~1 ~ minecraft:air unless block ~ ~ ~ minecraft:air if block ~ ~1 ~ minecraft:air run tellraw @a {"text":"{\"color\":\"green\",\"text\":\"You're not in air\"}"}