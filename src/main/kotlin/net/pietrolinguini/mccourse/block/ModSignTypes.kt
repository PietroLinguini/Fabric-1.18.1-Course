package net.pietrolinguini.mccourse.block

import net.pietrolinguini.mccourse.mixins.SignTypeAccessor

object ModSignTypes {
    val CHERRY_BLOSSOM =
        SignTypeAccessor.registerNew(
            SignTypeAccessor.newSignType("cherry_blossom")
        )
}