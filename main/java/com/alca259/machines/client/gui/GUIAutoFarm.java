package com.alca259.machines.client.gui;

import com.alca259.machines.Machines;
import com.alca259.machines.inventory.InventoryAutoFarm;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

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
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
		this.fontRendererObj.drawString(StatCollector.translateToLocal("tile.alca259AutoFarmer.name"), 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
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
		this.drawTexturedModalRect(par2, j, 0, 0, xSize, ySize);
	}
}