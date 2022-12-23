###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_b2150548-a411-4d1f-96af-836536dd3777 1
execute if score $global loop_5_b2150548-a411-4d1f-96af-836536dd3777 matches 5 run schedule clear testing:fors/loop_5_b2150548-a411-4d1f-96af-836536dd3777.mcfunction
execute if score $global loop_5_b2150548-a411-4d1f-96af-836536dd3777 matches 5 run scoreboard objectives remove loop_5_b2150548-a411-4d1f-96af-836536dd3777
execute unless score $global loop_5_b2150548-a411-4d1f-96af-836536dd3777 matches 5 run schedule function testing:fors/loop_5_b2150548-a411-4d1f-96af-836536dd3777.mcfunction 1s replace