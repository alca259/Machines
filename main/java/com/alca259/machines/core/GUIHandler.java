package com.alca259.machines.core;

import com.alca259.machines.client.gui.GUIAutoFarm;
import com.alca259.machines.inventory.InventoryAutoFarm;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler
{
    public GUIHandler(){}

    /**
     * Gets the server element.
     * This means, do something server side, when this ID gets called.
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1)
        {
            // Create an Object of our TE, so we can give that to our inventory.
            TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);
            return new InventoryAutoFarm(player.inventory, blockEntity);
        }
        return null;
    }

    /**
     * Gets the client element.
     * This means, do something client side, when this ID gets called.
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1)
        {
            // Create an Object of our TE, so we can give that to our GUI.
        	TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);
            return new GUIAutoFarm(player.inventory, blockEntity);
        }
        return null;
    }
}