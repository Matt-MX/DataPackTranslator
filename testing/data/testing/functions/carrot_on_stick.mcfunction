###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_ad2da3af-4335-4941-9b11-bc04826c3230.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick