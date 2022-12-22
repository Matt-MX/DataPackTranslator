package com.mattmx.datapack.util

class SmartError(message: String, lineError: String? = null, line: Int? = null) : Error(
    "\n" +
            "\nWhoops, looks like we have an error!" +
            "\n" +
            "\n ${if (lineError != null) "$line | $lineError" else message}" +
            "\n ${if (lineError != null) message else ""}" +
            "\n" +
            "\n"
)