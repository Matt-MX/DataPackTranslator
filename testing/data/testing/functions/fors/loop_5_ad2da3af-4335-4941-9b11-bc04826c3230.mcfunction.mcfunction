###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 1
execute if score $global loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 matches 5 run schedule clear testing:fors/loop_5_ad2da3af-4335-4941-9b11-bc04826c3230.mcfunction
execute if score $global loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 matches 5 run scoreboard objectives remove loop_5_ad2da3af-4335-4941-9b11-bc04826c3230
execute unless score $global loop_5_ad2da3af-4335-4941-9b11-bc04826c3230 matches 5 run schedule function testing:fors/loop_5_ad2da3af-4335-4941-9b11-bc04826c3230.mcfunction 1s replace