###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a 1
execute if score $global loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a matches 5 run schedule clear testing:fors/loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a.mcfunction
execute if score $global loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a matches 5 run scoreboard objectives remove loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a
execute unless score $global loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a matches 5 run schedule function testing:fors/loop_5_3e5e981a-4709-4356-a20d-b9b96e258b0a.mcfunction 1s replace