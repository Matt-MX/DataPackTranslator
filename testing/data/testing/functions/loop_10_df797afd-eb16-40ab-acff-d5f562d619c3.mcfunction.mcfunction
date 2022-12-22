# Compiled by MattMX's DataPackTranslator.
# All rights reserved.
scoreboard players add $global x 10
scoreboard players add $global loop_10_df797afd-eb16-40ab-acff-d5f562d619c3 1
execute if score $global loop_10_df797afd-eb16-40ab-acff-d5f562d619c3 matches 10 run schedule clear testing:loop_10_df797afd-eb16-40ab-acff-d5f562d619c3.mcfunction
execute unless score $global loop_10_df797afd-eb16-40ab-acff-d5f562d619c3 matches 10 run schedule function testing:loop_10_df797afd-eb16-40ab-acff-d5f562d619c3.mcfunction 1t replace