###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c 1
execute if score $global loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c matches 5 run schedule clear testing:fors/loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c.mcfunction
execute if score $global loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c matches 5 run scoreboard objectives remove loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c
execute unless score $global loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c matches 5 run schedule function testing:fors/loop_5_8d1ecbc2-c604-41f7-8bd7-40914d185c0c.mcfunction 1s replace