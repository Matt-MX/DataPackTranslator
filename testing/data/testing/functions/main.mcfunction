# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
scoreboard objectives add x dummy
scoreboard objectives add y dummy
scoreboard players operation $global x >< $global y
scoreboard objectives add temp_before dummy
scoreboard objectives add temp_before dummy
scoreboard players operation $global temp_before = $global x
scoreboard players operation $global x *= $global y
scoreboard objectives add temp_after dummy
scoreboard players operation $global temp_after = $global x
scoreboard players operation $global x = $global temp_before
scoreboard objectives add z dummy
scoreboard players operation $global z = $global temp_after
scoreboard players remove $global temp_after
function testing:notifyloaded