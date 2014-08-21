package com.alca259.machines.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
        //return par1ItemStack.getItem() == ModItems.testItem;
    	// TODO: Validar items que puede contener
    	return true;
    }
}