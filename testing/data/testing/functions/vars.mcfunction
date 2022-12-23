###########################################
#         COMPILED BY MATTMX'S            #
#       Kotlin DataPack Translator        #
###########################################
scoreboard objectives add x dummy
scoreboard players set $global x 10
scoreboard objectives add y dummy
scoreboard players set $global y 2
scoreboard objectives add first dummy
scoreboard players set $global first x
scoreboard objectives add second dummy
scoreboard players set $global second y
function testing:util/math/sqrt/exe
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"x"}}],"text":"x = "}
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"y"}}],"text":"y = "}
scoreboard objectives add temp_before_f5b16f8a-b1a4-47f2-b35c-12aa740b3753 dummy
scoreboard players operation $global temp_before_f5b16f8a-b1a4-47f2-b35c-12aa740b3753 = $global x
scoreboard objectives add temp_after_d1136e3f-91dc-4fc0-9175-43259cb727a7 dummy
execute store result score $global temp_after_d1136e3f-91dc-4fc0-9175-43259cb727a7 run scoreboard players operation $global x *= $global x
scoreboard players operation $global x = $global temp_before_f5b16f8a-b1a4-47f2-b35c-12aa740b3753
scoreboard objectives remove temp_before_f5b16f8a-b1a4-47f2-b35c-12aa740b3753
scoreboard objectives add temp_before_576b8155-be7e-4fad-82d0-60fe8a960591 dummy
scoreboard players operation $global temp_before_576b8155-be7e-4fad-82d0-60fe8a960591 = $global temp_after_d1136e3f-91dc-4fc0-9175-43259cb727a7
scoreboard objectives add temp_after_f79eb4c5-8426-47b9-9406-b74e5e7b1811 dummy
execute store result score $global temp_after_f79eb4c5-8426-47b9-9406-b74e5e7b1811 run scoreboard players operation $global temp_after_d1136e3f-91dc-4fc0-9175-43259cb727a7 *= $global y
scoreboard players operation $global temp_after_d1136e3f-91dc-4fc0-9175-43259cb727a7 = $global temp_before_576b8155-be7e-4fad-82d0-60fe8a960591
scoreboard objectives remove temp_before_576b8155-be7e-4fad-82d0-60fe8a960591
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_after_f79eb4c5-8426-47b9-9406-b74e5e7b1811
scoreboard objectives remove temp_after_f79eb4c5-8426-47b9-9406-b74e5e7b1811
tellraw @a[] {"color":"gray","extra":[{"score":{"name":"$global","objective":"z"}},{"text":")"}],"text":"z = x * y (="}
execute if score $global x matches 10.. run tellraw @a[name=MattMX] {"color":"light_purple","text":"Hi, matt, x >= 10"}