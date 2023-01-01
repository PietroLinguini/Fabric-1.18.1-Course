package net.pietrolinguini.mccourse.entity.variants

enum class RaccoonVariant(val id: Int) {
    DEFAULT(0),
    DARK(1),
    RED(2);

    companion object {
        fun byId(id: Int) = values()[id % values().size]
    }
}