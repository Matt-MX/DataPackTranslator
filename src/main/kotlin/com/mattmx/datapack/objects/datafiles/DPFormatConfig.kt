package com.mattmx.datapack.objects.datafiles

import com.mattmx.datapack.util.Module

class DPFormatConfig {
    /**
     * Configuration for storage of loops.
     * Loops must be in sub functions, customize their directory here.
     */
    var forLoopStorage = "fors/"
    var whileLoopStorage = "while/"

    /**
     * A list of extra modules which you want to build with.
     * Available modules can be found in the resources/modules folder.
     */
    var modules = arrayListOf<Module>()

    /**
     * This variable determines if execute run blocks are to be stored in
     * a sub function. This can help with optimizing your datapack since
     * we won't need to check for all execute statements to run each operation.
     *
     * If your execute blocks are large then this is recommended.
     * Please note that some things will not work, such as the @s operator, since
     * we're calling another function the selected entity will be null.
     */
    var executeBlockStoredInFunction = false

    inline operator fun invoke(builder: DPFormatConfig.() -> Unit) = builder(this)
}