package com.mattmx.datapack.variables.classbuilder

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.variables.DPVariable

class ClassBuilder(val functionBuilder: FunctionBuilder, val name: String) {
    val variables = arrayListOf<DPVariable>()
    val functions = arrayListOf<FunctionBuilder>()

    fun variable(name: String, value: Int? = null) {
        variables += DPVariable(functionBuilder, name, initial = value)
    }

    fun function(name: String, vararg args: Any) {

    }

    fun build() {
        // Create folder for class
        // Create folder for constructor
        // Create folders for functions

        // Class instances can be accessed via a global variable "$name_accessorId"
        // We can change this variable depending on the object instance we want to access
    }
}

inline fun FunctionBuilder.defineClass(name: String, builder: ClassBuilder.() -> Unit) {
    val clazz = ClassBuilder(this, name)
    builder(clazz)
}