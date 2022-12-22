package com.mattmx.datapack.variables.executes

import com.mattmx.datapack.DataPackTranslator

open class ExecuteCondition(val value: String, val name: String) {
}

class IfCondition(value: String) : ExecuteCondition(value, "if")
class UnlessCondition(value: String) : ExecuteCondition(value, "unless")
class AsCondition(value: String) : ExecuteCondition(value, "as")
class AtCondition(value: String) : ExecuteCondition(value, "at")
class PositionedCondition(value: String) : ExecuteCondition(value, "positioned")
class RunCondition(value: String) : ExecuteCondition(value, "run")