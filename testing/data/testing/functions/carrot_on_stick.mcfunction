###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick