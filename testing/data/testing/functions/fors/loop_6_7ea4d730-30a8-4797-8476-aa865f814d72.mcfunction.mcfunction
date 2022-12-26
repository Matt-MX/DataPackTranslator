###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"loop_6_7ea4d730-30a8-4797-8476-aa865f814d72"}},{"text":"s"}],"text":"Done in "}
scoreboard players add $global loop_6_7ea4d730-30a8-4797-8476-aa865f814d72 1
execute if score $global loop_6_7ea4d730-30a8-4797-8476-aa865f814d72 matches 6 run function testing:countdown_1
execute if score $global loop_6_7ea4d730-30a8-4797-8476-aa865f814d72 matches 6 run schedule clear testing:fors/loop_6_7ea4d730-30a8-4797-8476-aa865f814d72.mcfunction
execute if score $global loop_6_7ea4d730-30a8-4797-8476-aa865f814d72 matches 6 run scoreboard objectives remove loop_6_7ea4d730-30a8-4797-8476-aa865f814d72
execute unless score $global loop_6_7ea4d730-30a8-4797-8476-aa865f814d72 matches 6 run schedule function testing:testing:fors/loop_6_7ea4d730-30a8-4797-8476-aa865f814d72.mcfunction 1s replace