package com.mattmx.datapack.variables.functions

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.selector.player

/**
 * This class allows you to create function wrappers.
 * This way, we can simulate having functions that can return a value.
 *
 * If we have a FunctionWrapper called add(x, y), we may call
 * add(10, y) // y being a variable
 * The function wrapper will automatically translate this into something in datapack form.
 *
 * @param id of the function to call it by
 * @param path of the function so we know where to get it once built
 * @param
 */
class FunctionWrapper(val id: String, val path: String, vararg params: Pair<String, String>) {
    private val params = params.toList()

    operator fun invoke(functionBuilder: FunctionBuilder, vararg arguments: Any) {
        if (arguments.size != params.size)
            throw Error("FunctionWrapper $id requires ${params.size} parameters. ($path)")
        // Need to set the variables to their values
        params.forEachIndexed { index, it ->
            // Set this param to that param
            val variable = functionBuilder.variable(it.first, player(it.second))
            if (arguments[index] is DPVariable) {
                // set to value
            } else {
                variable set arguments[index]
            }
        }
        functionBuilder.call(path)
    }

}