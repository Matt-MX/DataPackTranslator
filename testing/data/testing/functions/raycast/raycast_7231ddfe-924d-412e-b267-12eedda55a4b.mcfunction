###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
particle dust 0.8 0 0 1 ~ ~ ~ 0 0 0 0 1
scoreboard players remove $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b 1
#Check if we are colliding at an entity
execute as @a[dx=0,limit=1] positioned ^-0.99 ^-0.99 ^-0.99 if entity @s[dx=0] run scoreboard players set $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b -1
#Check if we are colliding with a block
execute unless block ~ ~ ~ minecraft:air run scoreboard players set $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b -1
#If we're not colliding then increase the step and continue
execute if score $global raycast_7231ddfe-924d-412e-b267-12eedda55a4b matches 1.. positioned ^0.0 ^0.0 ^0.1 run function testing:raycast/raycast_7231ddfe-924d-412e-b267-12eedda55a4b