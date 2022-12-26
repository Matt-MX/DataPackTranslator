package com.mattmx.datapack.util.raycast

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.objects.DPVariable
import com.mattmx.datapack.objects.executes.block
import com.mattmx.datapack.objects.executes.location
import com.mattmx.datapack.objects.executes.selector.*
import java.util.UUID

class Raycast(
    val function: FunctionBuilder,
    val target: EntitySelector,
    val maxDistance: Int = 200,
    val step: Double = 0.1,
    val debug: Boolean = false
) {

    /**
     * Performs a raycast.
     * @return a number, 0 or -1.
     * -1 indicates it hit a block or entity
     * 0 means it did not hit anything
     */
    fun start() : DPVariable {
        // Create a variable to store the maxDistance
        var raycast = function.variable("raycast_${UUID.randomUUID()}", default = maxDistance).reset()
        val funcName = "raycast/${raycast.id}"
        // We now need to loop maxDistance times
        function.translator(funcName) {
            if (debug) {
                this += "particle dust 0.8 0 0 1 ~ ~ ~ 0 0 0 0 1"
            }
            raycast.update(this)
            raycast--
            // Colliding about the entity
            comment("Check if we are colliding at an entity")
            execAs(allPlayers() where (dx() eq 0) + (limit() eq 1)) {
                positioned(position(-0.99, -0.99, -0.99)) {
                    execIf("entity " + (selected() where (dx() eq 0))) {
                        run {
                            raycast.update(this)
                            raycast set -1
                            // Spawn an entity with tag @raycast at this location
                        }
                    }
                }
            }.build()
            // On the block
            comment("Check if we are colliding with a block")
            execUnless(block(location()) eq "air") {
                run {
                    raycast.update(this)
                    raycast set -1
                    // Spawn an entity with tag @raycast at this location
                }
            }.build()
            // If not encountered then we should move on with an increase in distance
            comment("If we're not colliding then increase the step and continue")
            execIf(score(raycast.id) gte 1) {
                positioned(position(dz = step)) {
                    run {
                        call(funcName)
                    }
                }
            }.build()
        }
        function.call(funcName)
        raycast.update(function)
        return raycast
    }
}

fun FunctionBuilder.raycast(
    target: EntitySelector,
    maxDistance: Int = 200,
    step: Double = 0.1,
    debug: Boolean = false
) = Raycast(this, target, maxDistance, step, debug).start()