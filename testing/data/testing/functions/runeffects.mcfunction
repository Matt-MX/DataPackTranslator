###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:amethyst_block run effect give @s[] minecraft:speed 1 5 true
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:amethyst_block run title @s[] title {"text":""}
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:amethyst_block run title @s[] subtitle {"color":"aqua","text":"Run!"}
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:emerald_block run effect give @s[] minecraft:jump_boost 1 5 true
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:emerald_block run title @s[] title {"text":""}
execute as @a[] at @s[] if block ~0 ~-1 ~0 minecraft:emerald_block run title @s[] subtitle {"color":"green","text":"Jump!"}
execute as @a[] at @s[] if block ~ ~ ~ minecraft:tall_grass run effect give @s[] minecraft:slowness 1 2 true
execute as @a[] at @s[] if block ~ ~ ~ minecraft:tall_grass run effect give @s[] minecraft:blindness 1 1 true
execute as @a[] at @s[] if block ~ ~ ~ minecraft:tall_grass run effect give @s[] minecraft:jump_boost 1 250 true