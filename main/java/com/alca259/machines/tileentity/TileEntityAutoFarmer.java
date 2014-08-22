package com.alca259.machines.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.IPlantable;

public class TileEntityAutoFarmer extends TileEntity implements IInventory {

	// Variables
	private ItemStack[] objetos = new ItemStack[9];
	private Random aleatorio = new Random();
	protected String inventoryName = "inventaryOfAutoFarmer";
	// NBT Tags
	private static final String NBT_TAG_ITEMS = "Alca259_Machines_Items";
	private static final String NBT_TAG_ITEMS_SLOT = "Alca259_Machines_Items_Slot";
	private static final String NBT_TAG_INVENTORY_NAME = "Alca259_Machines_InventoryName";
	
	
	// Constructor
	public TileEntityAutoFarmer() {}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.objetos.length; ++i)
        {
            if (this.objetos[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte(NBT_TAG_ITEMS_SLOT, (byte)i);
                this.objetos[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

		nbt.setTag(NBT_TAG_ITEMS, nbttaglist);

		if (this.hasCustomInventoryName())
        {
            nbt.setString(NBT_TAG_INVENTORY_NAME, this.inventoryName);
        }
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		NBTTagList nbttaglist = nbt.getTagList(NBT_TAG_ITEMS, 10);
		this.objetos = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte(NBT_TAG_ITEMS_SLOT) & 255;
			
			if (j >= 0 && j < this.objetos.length) {
				this.objetos[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		if (nbt.hasKey(NBT_TAG_INVENTORY_NAME, 8))
        {
            this.inventoryName = nbt.getString(NBT_TAG_INVENTORY_NAME);
        }
	}
	
	/*
	 *  Add the following methods. These will send the data of the TileEntity from the server to the client.
	 *	These are required, or your TileEntity won't function correctly.(non-Javadoc)
	 */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tileTag = new NBTTagCompound();
		this.writeToNBT(tileTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tileTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.func_148857_g();
		this.readFromNBT(tagCom);
	}

    /**
     * Returns the number of slots in the inventory.
     */
	@Override
	public int getSizeInventory() {
		return 9;
	}

    /**
     * Returns the stack in slot i
     */
	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.objetos[slot];
	}

	/**
	 * @return Index slot with items, -1 if nothing found
	 */
	public int getIndexSlotWithItems()
    {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.objetos.length; ++k)
        {
            if (this.objetos[k] != null && this.aleatorio.nextInt(j++) == 0)
            {
                i = k;
            }
        }

        return i;
    }
	
    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
	@Override
	public ItemStack decrStackSize(int slot, int quantity) {
        if (this.objetos[slot] != null)
        {
            ItemStack itemstack;

            if (this.objetos[slot].stackSize <= quantity)
            {
                itemstack = this.objetos[slot];
                this.objetos[slot] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.objetos[slot].splitStack(quantity);

                if (this.objetos[slot].stackSize == 0)
                {
                    this.objetos[slot] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.objetos[slot] != null)
        {
            ItemStack itemstack = this.objetos[slot];
            this.objetos[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
	@Override
	public void setInventorySlotContents(int slot, ItemStack items) {
        this.objetos[slot] = items;

        if (items != null && items.stackSize > this.getInventoryStackLimit())
        {
            items.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
	}

    /**
     * Returns the name of the inventory
     * Returns the Inv name, not req.
     */
	@Override
	public String getInventoryName() {
		return this.inventoryName;
	}

	/**
	 * If you want, return true. Not req.
     */
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	/**
     * Returns the maximum stack size for a inventory slot.
     */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	 /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	/**
	 * Do very little
     */
	@Override
	public void openInventory() {}

	/**
	 * Do as little as.
     */
	@Override
	public void closeInventory() {}

	/**
     * Returns true if automation is allowed to insert the given stack
     * (ignoring stack size) into the given slot.
     */
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		// Filtramos las semillas
		return isSeedValid(itemstack);
	}
	
	/************************************ PRIVATE METHODS ********************************************/
	public static boolean isSeedValid(ItemStack itemstack) {
		Item seed = itemstack.getItem();
		if (seed != null && seed instanceof IPlantable 
				&& (seed == Items.potato || seed == Items.carrot || seed == Items.melon_seeds
					|| seed == Items.pumpkin_seeds || seed == Items.wheat_seeds)) {
			return true;
		}
		return false;
	}
}