# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick