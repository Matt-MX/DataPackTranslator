tellraw @a {"extra":[{"color":"gray","text":"["},{"color":"red","text":"Test"},{"color":"gray","text":"] "},{"color":"white","text":"Datapack loaded."}],"text":""}
scoreboard objectives add loop_4_d716c538-d926-4a80-bada-d91863a3a17e dummy
scoreboard players set $global loop_4_d716c538-d926-4a80-bada-d91863a3a17e 0
schedule function testing:loop_4_d716c538-d926-4a80-bada-d91863a3a17e.mcfunction 1s replace