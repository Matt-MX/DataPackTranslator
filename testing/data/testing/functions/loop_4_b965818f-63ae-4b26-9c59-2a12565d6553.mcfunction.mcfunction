tellraw @a {"text":"Hello, world"}
scoreboard players add $global loop_4_b965818f-63ae-4b26-9c59-2a12565d6553 1
execute if score $global loop_4_b965818f-63ae-4b26-9c59-2a12565d6553 matches 4 run schedule clear testing:loop_4_b965818f-63ae-4b26-9c59-2a12565d6553.mcfunction
execute unless score $global loop_4_b965818f-63ae-4b26-9c59-2a12565d6553 matches 4 run schedule function testing:loop_4_b965818f-63ae-4b26-9c59-2a12565d6553.mcfunction 1s replace