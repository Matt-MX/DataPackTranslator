###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
scoreboard objectives remove raycast_7231ddfe-924d-412e-b267-12eedda55a4b
scoreboard objectives add raycast_7231ddfe-924d-412e-b267-12eedda55a4b dummy
scoreboard players set $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b 200
function testing:raycast/raycast_7231ddfe-924d-412e-b267-12eedda55a4b
scoreboard objectives add raycast_result dummy
scoreboard players operation $global raycast_result = $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b
scoreboard objectives remove raycast_7231ddfe-924d-412e-b267-12eedda55a4b
tellraw @a[] {"color":"red","extra":[{"score":{"name":"$global","objective":"raycast_result"}}],"text":"Raycast result: "}