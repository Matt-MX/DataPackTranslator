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
scoreboard objectives add temp_bce9d29a-3c6a-4e90-821b-33cfc42c658d dummy
scoreboard players operation $global temp_bce9d29a-3c6a-4e90-821b-33cfc42c658d = $global x
scoreboard players operation $global temp_bce9d29a-3c6a-4e90-821b-33cfc42c658d *= $global x
schedule function testing:temp_var_cleanup 1t replace
scoreboard objectives add temp_16a82b3c-5822-4093-bf39-0d1588d8570a dummy
scoreboard players operation $global temp_16a82b3c-5822-4093-bf39-0d1588d8570a = $global temp_bce9d29a-3c6a-4e90-821b-33cfc42c658d
scoreboard players operation $global temp_16a82b3c-5822-4093-bf39-0d1588d8570a *= $global y
schedule function testing:temp_var_cleanup 1t replace
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_16a82b3c-5822-4093-bf39-0d1588d8570a
scoreboard objectives remove temp_16a82b3c-5822-4093-bf39-0d1588d8570a
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"z"}},{"text":")"}],"text":"z = x * y (="}
execute if score $global x matches 10.. run tellraw @a[name=MattMX] {"color":"light_purple","text":"Hi, matt, x >= 10"}