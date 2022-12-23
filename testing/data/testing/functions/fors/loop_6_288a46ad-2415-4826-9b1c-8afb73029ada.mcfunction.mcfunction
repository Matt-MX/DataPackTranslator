###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"opposite"}},{"text":"s"}],"text":"Done in "}
scoreboard players remove $global opposite 1
scoreboard players add $global loop_6_288a46ad-2415-4826-9b1c-8afb73029ada 1
execute if score $global loop_6_288a46ad-2415-4826-9b1c-8afb73029ada matches 6 run schedule clear testing:fors/loop_6_288a46ad-2415-4826-9b1c-8afb73029ada.mcfunction
execute if score $global loop_6_288a46ad-2415-4826-9b1c-8afb73029ada matches 6 run scoreboard objectives remove loop_6_288a46ad-2415-4826-9b1c-8afb73029ada
execute unless score $global loop_6_288a46ad-2415-4826-9b1c-8afb73029ada matches 6 run schedule function testing:fors/loop_6_288a46ad-2415-4826-9b1c-8afb73029ada.mcfunction 1s replace