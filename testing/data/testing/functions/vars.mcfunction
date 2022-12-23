###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
scoreboard objectives add x dummy
scoreboard objectives add y dummy
scoreboard objectives add temp_before_43d0d469-5e41-42ad-a136-23f2165b54da dummy
scoreboard players operation $global temp_before_43d0d469-5e41-42ad-a136-23f2165b54da = $global x
scoreboard objectives add temp_after_fa340c8f-e832-421f-8f66-6715ce73ba57 dummy
execute store result score $global temp_after_fa340c8f-e832-421f-8f66-6715ce73ba57 run scoreboard players operation $global x *= $global y
scoreboard players operation $global x = $global temp_before_43d0d469-5e41-42ad-a136-23f2165b54da
scoreboard objectives remove temp_before_43d0d469-5e41-42ad-a136-23f2165b54da
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_after_fa340c8f-e832-421f-8f66-6715ce73ba57
scoreboard objectives remove temp_after_fa340c8f-e832-421f-8f66-6715ce73ba57