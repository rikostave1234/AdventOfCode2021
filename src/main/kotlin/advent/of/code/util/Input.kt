package advent.of.code.util

import java.io.File

fun getInputFromResources(path: String) =
    File(ClassLoader.getSystemResource(path).file).useLines { it.toList() }