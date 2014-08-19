package com.alca259.machines.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPosicion extends TileEntity {

	// Variables
	private int facing = -1;
	private static final String NBT_POSICION = "Alca259_Posicion";
	
	// Constructor
	public TileEntityPosicion() {}

	public int getFacingDirection() {
		return this.facing;
	}

	public void setFacingDirection(int side) {
		this.facing = side;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_POSICION, (int) this.facing);

	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.facing = nbt.getInteger(NBT_POSICION);
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
}