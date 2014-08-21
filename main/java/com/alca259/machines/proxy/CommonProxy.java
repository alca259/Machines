package com.alca259.machines.proxy;

import com.alca259.machines.tileentity.TileEntityAutoFarmer;
import com.alca259.machines.tileentity.TileEntityPosicion;
import com.alca259.machines.util.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	/**
	 * Registrar entidades de bloques e items
	 */
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityPosicion.class, "Alca259-Machines-Posicion");
		GameRegistry.registerTileEntity(TileEntityAutoFarmer.class, "Alca259-Machines-AutoFarmer");
	}

	public void registerRenders() {}
}
