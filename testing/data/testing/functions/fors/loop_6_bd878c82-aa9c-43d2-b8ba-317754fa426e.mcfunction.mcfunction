###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"opposite"}},{"text":"s"}],"text":"Done in "}
scoreboard players remove $global opposite 1
scoreboard players add $global loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e 1
execute if score $global loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e matches 6 run schedule clear testing:fors/loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e.mcfunction
execute if score $global loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e matches 6 run scoreboard objectives remove loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e
execute unless score $global loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e matches 6 run schedule function testing:fors/loop_6_bd878c82-aa9c-43d2-b8ba-317754fa426e.mcfunction 1s replace