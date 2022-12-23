###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick