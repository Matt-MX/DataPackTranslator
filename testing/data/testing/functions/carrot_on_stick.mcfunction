###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[scores={use_carrot_stick=1..}] at @s[] run tellraw @s[] {"color":"yellow","text":"You used it!"}
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard objectives add loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 dummy
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players set $global loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 0
execute as @a[scores={use_carrot_stick=1..}] at @s[] run function testing:fors/loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786.mcfunction
execute as @a[scores={use_carrot_stick=1..}] at @s[] run scoreboard players reset @s use_carrot_stick