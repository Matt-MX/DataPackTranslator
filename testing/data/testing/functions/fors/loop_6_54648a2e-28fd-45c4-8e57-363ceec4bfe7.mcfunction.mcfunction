###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"opposite"}},{"text":"s"}],"text":"Done in "}
scoreboard players remove $global opposite 1
scoreboard players add $global loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7 1
execute if score $global loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7 matches 6 run schedule clear testing:fors/loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7.mcfunction
execute if score $global loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7 matches 6 run scoreboard objectives remove loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7
execute unless score $global loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7 matches 6 run schedule function testing:fors/loop_6_54648a2e-28fd-45c4-8e57-363ceec4bfe7.mcfunction 1s replace