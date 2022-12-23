###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
scoreboard objectives remove x
scoreboard objectives add x dummy
scoreboard players set $global x 9
scoreboard objectives remove y
scoreboard objectives add y dummy
scoreboard players set $global y 2
scoreboard players set min nnmath_sqrt 0
scoreboard players set max nnmath_sqrt 100
function nnmath:rand/exe
scoreboard objectives add result dummy
scoreboard players operation $global result = out nnmath_sqrt
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"result"}}],"text":"Random number: "}
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"x"}}],"text":"x = "}
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"y"}}],"text":"y = "}
scoreboard players operation $global temp_10529673-f46b-4c52-a818-d6cde2442efd = $global x
scoreboard players operation $global temp_572644d4-1b35-4265-aa92-1cb1d1001931 = $global temp_10529673-f46b-4c52-a818-d6cde2442efd
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_572644d4-1b35-4265-aa92-1cb1d1001931
scoreboard objectives remove temp_572644d4-1b35-4265-aa92-1cb1d1001931
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"z"}},{"text":")"}],"text":"z = x * y (="}
execute if score $global x matches 10.. run tellraw @a[name=MattMX] {"color":"light_purple","text":"Hi, matt, x >= 10"}