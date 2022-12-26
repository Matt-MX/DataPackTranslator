package com.mattmx.datapack.util.raycast

import com.mattmx.datapack.FunctionBuilder
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

    fun start() {
        // Create a variable to store the maxDistance
        val raycast = function.variable("raycast_${UUID.randomUUID()}").reset()
        // We now need to loop maxDistance times
        function.repeat(maxDistance) {
            if (debug) {
                this += "particle dust 0.8 0 0 1 ~ ~ ~ 0 0 0 0 1"
                // Colliding about the entity
                execAs(allPlayers() where (dx() eq 0) + (limit() eq 1)) {
                    positioned(position(-0.99, -0.99, -0.99)) {
                        execIf("entity" + (selected() where (dx() eq 0))) {
                            run {
                                raycast.function = this
                                raycast set 0
                                // Spawn an entity with tag @raycast at this location
                            }
                        }
                    }
                }
                // On the block
                execUnless(block(location()) eq "air") {
                    run {
                        raycast.function = this
                        raycast set 0
                        // Spawn an entity with tag @raycast at this location
                    }
                }
                // If not encountered then we should move on with a 0.1 block step
                execIf(score(raycast.id) gte 0) {
                    positioned(position(dz = step)) {
                        // todo call this again
                    }
                }
            }
        }
    }

}