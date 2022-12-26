###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 1
execute if score $global loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 matches 5 run schedule clear testing:fors/loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786.mcfunction
execute if score $global loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 matches 5 run scoreboard objectives remove loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786
execute unless score $global loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786 matches 5 run schedule function testing:fors/loop_5_78360cfd-b745-4eec-a7fd-d94ad143f786.mcfunction 1s replace