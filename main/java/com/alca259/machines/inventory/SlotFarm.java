package com.alca259.machines.inventory;

import com.alca259.machines.tileentity.TileEntityAutoFarmer;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public class SlotFarm extends Slot
{
    public SlotFarm(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x ,y);
    }

    /*
    In this case, you can only place this item
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
    	// Validar items que puede contener
    	return TileEntityAutoFarmer.isSeedValid(par1ItemStack);
    }
}