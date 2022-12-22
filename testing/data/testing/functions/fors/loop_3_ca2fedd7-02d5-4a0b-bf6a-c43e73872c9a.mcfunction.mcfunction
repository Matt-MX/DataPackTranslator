# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
tellraw @a {"text":"{\"color\":\"red\",\"text\":\"You're in air\"}"}
scoreboard players add $global loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a 1
execute if score $global loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a matches 3 run schedule clear testing:fors/loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a.mcfunction
execute unless score $global loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a matches 3 run schedule function testing:fors/loop_3_ca2fedd7-02d5-4a0b-bf6a-c43e73872c9a.mcfunction 1t replace