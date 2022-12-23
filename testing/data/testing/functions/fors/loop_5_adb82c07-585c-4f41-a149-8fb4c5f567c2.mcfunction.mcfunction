###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 1
execute if score $global loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 matches 5 run schedule clear testing:fors/loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2.mcfunction
execute if score $global loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 matches 5 run scoreboard objectives remove loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2
execute unless score $global loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2 matches 5 run schedule function testing:fors/loop_5_adb82c07-585c-4f41-a149-8fb4c5f567c2.mcfunction 1s replace