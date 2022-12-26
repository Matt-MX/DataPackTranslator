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
scoreboard objectives add temp_d252a16b-e19e-42fe-b912-490ac929f3fb dummy
scoreboard players operation $global temp_d252a16b-e19e-42fe-b912-490ac929f3fb = $global x
scoreboard players operation $global temp_d252a16b-e19e-42fe-b912-490ac929f3fb *= $global x
schedule function testing:temp_var_cleanup 1t replace
scoreboard objectives add temp_606a0cea-928e-4696-9a0c-54ad34c505bd dummy
scoreboard players operation $global temp_606a0cea-928e-4696-9a0c-54ad34c505bd = $global temp_d252a16b-e19e-42fe-b912-490ac929f3fb
scoreboard players operation $global temp_606a0cea-928e-4696-9a0c-54ad34c505bd *= $global y
schedule function testing:temp_var_cleanup 1t replace
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_606a0cea-928e-4696-9a0c-54ad34c505bd
scoreboard objectives remove temp_606a0cea-928e-4696-9a0c-54ad34c505bd
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"z"}},{"text":")"}],"text":"z = x * y (="}
execute if score $global x matches 10.. run tellraw @a[name=MattMX] {"color":"light_purple","text":"Hi, matt, x >= 10"}