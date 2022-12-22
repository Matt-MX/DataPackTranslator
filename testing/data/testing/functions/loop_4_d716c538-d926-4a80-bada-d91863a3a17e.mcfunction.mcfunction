tellraw @a {"bold":true,"color":"red","text":"Hello, world"}
scoreboard players add $global loop_4_d716c538-d926-4a80-bada-d91863a3a17e 1
execute if score $global loop_4_d716c538-d926-4a80-bada-d91863a3a17e matches 4 run schedule clear testing:loop_4_d716c538-d926-4a80-bada-d91863a3a17e.mcfunction
execute unless score $global loop_4_d716c538-d926-4a80-bada-d91863a3a17e matches 4 run schedule function testing:loop_4_d716c538-d926-4a80-bada-d91863a3a17e.mcfunction 1s replace