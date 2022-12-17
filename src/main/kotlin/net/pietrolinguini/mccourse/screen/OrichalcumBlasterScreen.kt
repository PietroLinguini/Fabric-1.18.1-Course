package net.pietrolinguini.mccourse.screen

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class OrichalcumBlasterScreen(handler: OrichalcumBlasterScreenHandler, inventory: PlayerInventory, title: Text) :
    HandledScreen<OrichalcumBlasterScreenHandler>(handler, inventory, title) {
    companion object {
        private val TEXTURE = Identifier("mccourse", "textures/gui/orichalcum_blaster.png")
    }

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)

        if (handler.isCrafting())
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.getBlastProgress(), 36)

        println("scaledFuelProgress = ${handler.getFuelProgress()}")

        if (handler.hasFuel())
            drawTexture(
                matrices, x + 18, y + 33 + 12 - handler.getFuelProgress(), 176,
                12 - handler.getFuelProgress(), 14, handler.getFuelProgress() + 1
            )
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }
}