package com.alca259.machines.proxy;

import com.alca259.machines.Machines;
import com.alca259.machines.client.render.RenderAutoFarm;
import com.alca259.machines.client.render.RenderAutoFarmerItem;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;
import com.alca259.machines.util.LogHelper;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAutoFarmer.class, new RenderAutoFarm());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Machines.autoFarmer), new RenderAutoFarmerItem());
	}
}
