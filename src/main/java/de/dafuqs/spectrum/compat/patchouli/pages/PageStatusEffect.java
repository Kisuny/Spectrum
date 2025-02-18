package de.dafuqs.spectrum.compat.patchouli.pages;

import com.google.gson.annotations.*;
import com.mojang.blaze3d.systems.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.texture.*;
import net.minecraft.entity.effect.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import vazkii.patchouli.client.book.*;
import vazkii.patchouli.client.book.gui.*;
import vazkii.patchouli.client.book.page.abstr.*;

public class PageStatusEffect extends PageWithText {
	
	String title;
	@SerializedName("status_effect_id")
	String statusEffectId;
	transient StatusEffect statusEffect;
	transient Sprite statusEffectSprite;
	
	@Override
	public void build(World world, BookEntry entry, BookContentsBuilder builder, int pageNum) {
		super.build(world, entry, builder, pageNum);
		statusEffect = Registries.STATUS_EFFECT.get(new Identifier(statusEffectId));
		statusEffectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(statusEffect);
	}
	
	@Override
	public void render(DrawContext drawContext, int mouseX, int mouseY, float pticks) {
		RenderSystem.enableBlend();
		drawContext.drawSprite(49, 14, 0, 18, 18, statusEffectSprite);

		Text toDraw;
		if (title != null && !title.isEmpty()) {
			toDraw = i18nText(title);
		} else {
			toDraw = statusEffect.getName();
		}
		parent.drawCenteredStringNoShadow(drawContext, toDraw.asOrderedText(), GuiBook.PAGE_WIDTH / 2, 0, book.headerColor);
		
		super.render(drawContext, mouseX, mouseY, pticks);
	}
	
	@Override
	public int getTextHeight() {
		return 40;
	}
	
}
