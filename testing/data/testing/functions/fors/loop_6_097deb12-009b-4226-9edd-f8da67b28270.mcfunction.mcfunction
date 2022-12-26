###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
title @a[] title {"color":"gold","text":"Countdown"}
title @a[] subtitle {"color":"yellow","extra":[{"score":{"name":"$global","objective":"loop_6_097deb12-009b-4226-9edd-f8da67b28270"}},{"text":"s"}],"text":"Done in "}
scoreboard players add $global loop_6_097deb12-009b-4226-9edd-f8da67b28270 1
execute if score $global loop_6_097deb12-009b-4226-9edd-f8da67b28270 matches 6 run function testing:countdown_1
execute if score $global loop_6_097deb12-009b-4226-9edd-f8da67b28270 matches 6 run schedule clear testing:fors/loop_6_097deb12-009b-4226-9edd-f8da67b28270.mcfunction
execute if score $global loop_6_097deb12-009b-4226-9edd-f8da67b28270 matches 6 run scoreboard objectives remove loop_6_097deb12-009b-4226-9edd-f8da67b28270
execute unless score $global loop_6_097deb12-009b-4226-9edd-f8da67b28270 matches 6 run schedule function testing:fors/loop_6_097deb12-009b-4226-9edd-f8da67b28270.mcfunction 1s replace