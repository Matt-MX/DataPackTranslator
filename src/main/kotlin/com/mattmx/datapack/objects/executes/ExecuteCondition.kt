package com.mattmx.datapack.objects.executes

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.objects.executes.selector.EntitySelector

open class ExecuteCondition(val value: String, val name: String) {
}

class IfCondition(value: String) : ExecuteCondition(value, "if")
class UnlessCondition(value: String) : ExecuteCondition(value, "unless")
class AsCondition(value: EntitySelector) : ExecuteCondition(value.build(), "as")
class AtCondition(value: String) : ExecuteCondition(value, "at")
class PositionedCondition(value: String) : ExecuteCondition(value, "positioned")
class RunCondition(value: String) : ExecuteCondition(value, "run")
class StoreCondition(action: String) : ExecuteCondition(action, "store")