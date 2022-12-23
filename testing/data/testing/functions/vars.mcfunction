###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
scoreboard objectives add x dummy
scoreboard objectives add y dummy
scoreboard objectives add temp_before_135e2743-f367-4c17-8f15-484317d05fe3 dummy
scoreboard players operation $global temp_before_135e2743-f367-4c17-8f15-484317d05fe3 = $global x
scoreboard objectives add temp_after_ec6c384a-1dce-4b4c-a91c-82d8596f6508 dummy
execute store result score $global temp_after_ec6c384a-1dce-4b4c-a91c-82d8596f6508 run scoreboard players operation $global x *= $global y
scoreboard players operation $global x = $global temp_before_135e2743-f367-4c17-8f15-484317d05fe3
scoreboard objectives remove temp_before_135e2743-f367-4c17-8f15-484317d05fe3
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_after_ec6c384a-1dce-4b4c-a91c-82d8596f6508
scoreboard objectives remove temp_after_ec6c384a-1dce-4b4c-a91c-82d8596f6508