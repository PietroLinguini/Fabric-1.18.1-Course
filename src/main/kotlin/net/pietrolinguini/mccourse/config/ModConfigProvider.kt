package net.pietrolinguini.mccourse.config

class ModConfigProvider: SimpleConfig.DefaultConfig {
    private var configContents = ""
    private val configsList = arrayListOf<Pair<String, *>>()

    fun addKeyValuePair(pair: Pair<String, *>, comment: String) {
        configsList.add(pair)
        configContents += "${pair.first}=${pair.second} #$comment | default: ${pair.second}\n"
    }

    fun getConfigsList() = configsList
    override fun get(namespace: String?) = configContents
}