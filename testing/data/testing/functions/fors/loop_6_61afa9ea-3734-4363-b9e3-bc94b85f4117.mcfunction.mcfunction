###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"opposite"}},{"text":"s"}],"text":"Done in "}
scoreboard players remove $global opposite 1
scoreboard players add $global loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117 1
execute if score $global loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117 matches 6 run schedule clear testing:fors/loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117.mcfunction
execute if score $global loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117 matches 6 run scoreboard objectives remove loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117
execute unless score $global loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117 matches 6 run schedule function testing:fors/loop_6_61afa9ea-3734-4363-b9e3-bc94b85f4117.mcfunction 1s replace