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
scoreboard players operation $global temp_10f681e9-9ac2-437d-8962-b161842e91bd = $global x
scoreboard players operation $global temp_b45dcc9f-491f-4114-bbb8-42f59f1496ff = $global temp_10f681e9-9ac2-437d-8962-b161842e91bd
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_b45dcc9f-491f-4114-bbb8-42f59f1496ff
scoreboard objectives remove temp_b45dcc9f-491f-4114-bbb8-42f59f1496ff
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"z"}},{"text":")"}],"text":"z = x * y (="}
execute if score $global x matches 10.. run tellraw @a[name=MattMX] {"color":"light_purple","text":"Hi, matt, x >= 10"}