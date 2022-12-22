# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
scoreboard objectives add x dummy
scoreboard players set $global x 5
scoreboard objectives add y dummy
scoreboard players set $global y 2
scoreboard objectives add temp_before_2b5351ea-085d-4b1a-b7e9-b6f5e02bb129 dummy
scoreboard players operation $global temp_before_2b5351ea-085d-4b1a-b7e9-b6f5e02bb129 = $global x
scoreboard objectives add temp_after_30b7f92a-c8e8-447c-913f-b1390c99706b dummy
execute store result score $global temp_after_30b7f92a-c8e8-447c-913f-b1390c99706b run scoreboard players operation $global x *= $global y
scoreboard players operation $global x = $global temp_before_2b5351ea-085d-4b1a-b7e9-b6f5e02bb129
scoreboard players remove $global temp_before_2b5351ea-085d-4b1a-b7e9-b6f5e02bb129
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_after_30b7f92a-c8e8-447c-913f-b1390c99706b
scoreboard players remove $global temp_after_30b7f92a-c8e8-447c-913f-b1390c99706b
scoreboard objectives add temp_before_531a4b57-1830-455d-af05-a2c9c9a6bb5b dummy
scoreboard players operation $global temp_before_531a4b57-1830-455d-af05-a2c9c9a6bb5b = $global x
scoreboard objectives add temp_after_95b9a48d-9d14-4715-9cd9-43be84a10132 dummy
execute store result score $global temp_after_95b9a48d-9d14-4715-9cd9-43be84a10132 run scoreboard players operation $global x *= $global z
scoreboard players operation $global x = $global temp_before_531a4b57-1830-455d-af05-a2c9c9a6bb5b
scoreboard players remove $global temp_before_531a4b57-1830-455d-af05-a2c9c9a6bb5b
scoreboard objectives add w dummy
scoreboard players operation $global w = $global temp_after_95b9a48d-9d14-4715-9cd9-43be84a10132
scoreboard players remove $global temp_after_95b9a48d-9d14-4715-9cd9-43be84a10132
function testing:notifyloaded