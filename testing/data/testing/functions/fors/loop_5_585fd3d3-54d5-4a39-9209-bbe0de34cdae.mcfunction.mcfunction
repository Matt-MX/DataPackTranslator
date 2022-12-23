# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
teleport @a[scores={use_carrot_stick=1..}] ~ ~2 ~
scoreboard players add $global loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae 1
execute if score $global loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae matches 5 run schedule clear testing:fors/loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae.mcfunction
execute if score $global loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae matches 5 run scoreboard objectives remove loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae
execute unless score $global loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae matches 5 run schedule function testing:fors/loop_5_585fd3d3-54d5-4a39-9209-bbe0de34cdae.mcfunction 1s replace