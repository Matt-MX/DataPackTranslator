###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_b2150548-a411-4d1f-96af-836536dd3777 dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_b2150548-a411-4d1f-96af-836536dd3777 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_b2150548-a411-4d1f-96af-836536dd3777.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick