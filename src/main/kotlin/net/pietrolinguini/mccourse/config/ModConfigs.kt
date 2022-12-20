package net.pietrolinguini.mccourse.config

import net.pietrolinguini.mccourse.MCCourseMod
import kotlin.properties.Delegates

object ModConfigs {
    lateinit var CONFIG: SimpleConfig
    private lateinit var configs: ModConfigProvider

    lateinit var TEST: String
    var SOME_INT by Delegates.notNull<Int>()
    var SOME_DOUBLE by Delegates.notNull<Double>()
    var REPLACE_TITLESCREEN by Delegates.notNull<Boolean>()

    fun registerConfigs() {
        configs = ModConfigProvider()
        createConfigs()

        CONFIG = SimpleConfig.of("${MCCourseMod.MOD_ID}config").provider(configs).request()

        assignConfigs()
    }

    private fun createConfigs() {
        configs.addKeyValuePair(Pair("key.test.value1", "Just a Testing String!"), "String")
        configs.addKeyValuePair(Pair("key.test.value2", 50), "int")
        configs.addKeyValuePair(Pair("key.test.value3", 4142.5), "double")
        configs.addKeyValuePair(Pair("replace.titlescreen", true), "boolean")
    }

    private fun assignConfigs() {
        TEST = CONFIG.getOrDefault("key.test.value1", "Nothing")
        SOME_INT = CONFIG.getOrDefault("key.test.value2", 42)
        SOME_DOUBLE = CONFIG.getOrDefault("key.test.value3", 42.0)
        REPLACE_TITLESCREEN = CONFIG.getOrDefault("replace.titlescreen", true)

        println("All ${configs.getConfigsList().size} have been set properly")
    }
}