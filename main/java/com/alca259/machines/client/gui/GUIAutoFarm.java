package com.alca259.machines.client.gui;

import com.alca259.machines.Machines;
import com.alca259.machines.inventory.InventoryAutoFarm;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIAutoFarm extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(
			Machines.MODID.toLowerCase() + ":" + "textures/gui/AutoFarm.png");
	private static TileEntityAutoFarmer te;

	public GUIAutoFarm(InventoryPlayer inventoryPlayer,	TileEntityAutoFarmer blockEntity) {
		super(new InventoryAutoFarm(inventoryPlayer, blockEntity));
		xSize = 176;
		ySize = 173;
		te = blockEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		/*
		 * Draw GUI Background
		 */

		// Bind Texture
		this.mc.getTextureManager().bindTexture(texture);
		// set the x for the texture, Total width - textureSize / 2
		par2 = (this.width - xSize) / 2;
		// set the y for the texture, Total height - textureSize - 30 (up) / 2,
		int j = (this.height - ySize) / 2;
		// draw the texture
		drawTexturedModalRect(par2, j, 0, 0, xSize, ySize);
	}
}