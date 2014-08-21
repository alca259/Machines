package com.alca259.machines.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAutoFarmer extends TileEntity implements IInventory {

	// Variables
	private int facing = -1;
	private ItemStack[] objetos = new ItemStack[9];
	private Random aleatorio = new Random();
	protected String inventoryName = "inventarioNumero005";
	private static final String NBT_POSICION = "Alca259_Posicion";
	
	// Constructor
	public TileEntityAutoFarmer() {}

	public int getFacingDirection() {
		return this.facing;
	}

	public void setFacingDirection(int side) {
		this.facing = side;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.objetos.length; ++i)
        {
            if (this.objetos[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.objetos[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
		
		nbt.setInteger(NBT_POSICION, (int) this.facing);
		nbt.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.inventoryName);
        }
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.facing = nbt.getInteger(NBT_POSICION);
		
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.objetos = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;
			
			if (j >= 0 && j < this.objetos.length) {
				this.objetos[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		if (nbt.hasKey("CustomName", 8))
        {
            this.inventoryName = nbt.getString("CustomName");
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
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		// TODO: Filtrar aqui semillas
		return true;
	}
}