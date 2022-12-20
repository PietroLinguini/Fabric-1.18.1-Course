package net.pietrolinguini.mccourse.util

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.TitleScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.pietrolinguini.mccourse.MCCourseMod
import net.pietrolinguini.mccourse.mixins.ScreenMixin

class KaupenTitleScreen: TitleScreen() {
    companion object {
        val SPLASH = Identifier(MCCourseMod.MOD_ID, "textures/gui/background/kaupenmenu.jpg")
        val MINECRAFT_TITLE_TEXTURE = Identifier("textures/gui/title/minecraft.png")
    }

    override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.render(matrixStack, mouseX, mouseY, partialTicks)

        drawCustomTitleScreen(matrixStack, width, height)
        drawMinecraftLogo(matrixStack)
        drawCopyrightNotice(matrixStack, width, height)
        drawTitleScreenButtons(matrixStack, mouseX, mouseY, partialTicks)
    }

    private fun drawCopyrightNotice(matrixStack: MatrixStack, width: Int, height: Int) {
        drawStringWithShadow(matrixStack, this.client?.textRenderer, "Copyright Mojang AB. Do not distribute!",
            width - this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!") - 2,
            height - 10, 0xFFFFFFFF.toInt()
        )
    }

    private fun drawCustomTitleScreen(matrixStack: MatrixStack, width: Int, height: Int) {
        RenderSystem.enableTexture()
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)

        RenderSystem.setShaderTexture(0, SPLASH)
        drawTexture(matrixStack, 0, 0, 0f, 0f, this.width, this.height, width, height)
    }

    private fun drawMinecraftLogo(matrixStack: MatrixStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURE)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)

        this.drawWithOutline(width / 2 - 137, 30) {x, y ->
            this.drawTexture(matrixStack, x + 0, y.toInt(), 0, 0, 99, 44)
            this.drawTexture(matrixStack, x + 99, y.toInt(), 129, 0, 27, 44)
            this.drawTexture(matrixStack, x + 99 + 26, y.toInt(), 126, 0, 3, 44)
            this.drawTexture(matrixStack, x + 99 + 26 + 3, y.toInt(), 99, 0, 26, 44)
            this.drawTexture(matrixStack, x + 155, y.toInt(), 0, 45, 155, 44)
        }
    }

    private fun drawTitleScreenButtons(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        for (widget in (this as ScreenMixin).drawables) {
            widget.render(matrixStack, mouseX, mouseY, partialTicks)
        }
    }
}