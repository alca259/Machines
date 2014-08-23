package com.alca259.machines.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.alca259.machines.Machines;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;
import com.alca259.machines.tileentity.TileEntityPosicion;
import com.alca259.machines.util.Position;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAutoFarmer extends BlockContainer {
	/********************************************** VARIABLES DE LA CLASE **************************************************/
	Random aleatorio = new Random();

	public BlockAutoFarmer() {
		super(Material.rock);

		// Propiedades
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);

		this.setBlockName("alca259AutoFarmer");
		// this.setBlockBounds(MinX, MinY, MinZ, maxX, maxY, maxZ);
		this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.5f ,0.9375f);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	/********************************************** METODOS PROPIEDADES **************************************************/
	/**
	 * Devuelve la cantidad de items/bloques que se obtienen al romper el bloque
	 */
	@Override
	public int quantityDropped(Random par1) {
		return 1;
	}

	/**
	 * Informa al juego de si este bloque proporciona corriente de redstone
	 */
	@Override
	public boolean canProvidePower() {
		return false;
	}

	/**
	 * Devuelve el item que se obtiene al romper el bloque y la direccion
	 */
	@Override
	public Item getItemDropped(int x, Random yRandom, int z) {
		return Item.getItemFromBlock(Machines.autoFarmer);
	}

	/**
	 * Actualizacion del bloque por ticks
	 */
	@Override
	public int tickRate(World world) {
		return 10;
	}

	/**
	 * Asigna las texturas al bloque
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		// Registra la miniatura 3D
		this.blockIcon = iconReg.registerIcon("planks_oak");
	}

	/**
	 * Esta funcion asigna funcionalidad al boton del medio del raton en modo
	 * creativo, proveyendo un stack del bloque seleccionado
	 */
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		Item item = getItem(world, x, y, z);
		if (item == null) {
			return null;
		}
		return new ItemStack(item, 1, Machines.autoFarmer.getDamageValue(world, x, y, z));
	};

	/**
	 * Establece la entidad de almacenamiento de datos
	 */
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityAutoFarmer();
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}

	/**
	* Because we render our block ourselves, we need to set this to -1.
	*/
    @Override
    public int getRenderType()
    {
        return -1;
    }
    
    /**
     * Do not render as a normal block
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     *  If a block is solid, and 1x1x1, MC doesnt render the sides of the block next to this block.
     *  Ingame, this looks like the old see-through piston or sand effect.
     *  To prevent this, set this to false.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3;

		if (l != 3 && l != 2) {
			this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.5f ,0.9375f);
		} else {
			this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.5f ,0.9375f);
		}
	}
    
	/********************************************** METODOS PROPIOS **************************************************/
	/**
	 * Synonim: Esta funcion indica si le llega corriente de redstone
	 */
	public boolean isPowered(World world, int x, int y, int z) {
		return world.isBlockIndirectlyGettingPowered(x, y, z);
	}
	
	// set a blocks direction
	//
	private void setDefaultDirection(World par1World, int par2, int par3, int par4) {
		if (!par1World.isRemote) {
			Block l = par1World.getBlock(par2, par3, par4 - 1);
			Block i1 = par1World.getBlock(par2, par3, par4 + 1);
			Block j1 = par1World.getBlock(par2 - 1, par3, par4);
			Block k1 = par1World.getBlock(par2 + 1, par3, par4);
			byte b0 = 3;

			if (l.isOpaqueCube() && !i1.isOpaqueCube()) {
				b0 = 3;
			}

			if (i1.isOpaqueCube() && !l.isOpaqueCube()) {
				b0 = 2;
			}

			if (j1.isOpaqueCube() && !k1.isOpaqueCube()) {
				b0 = 5;
			}

			if (k1.isOpaqueCube() && !j1.isOpaqueCube()) {
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}
	
	private void plantSeeds(World world, int x, int y, int z, TileEntityAutoFarmer entity) {
		int meta = entity.getBlockMetadata();
		if (meta == 0) meta = 3;
		
		// Inicializamos
		int x1 = x;
		int z1 = z;
		int face = 0;

		for (int i = 0; i < 12; i++) {
			// Obtenemos la direccion en la que mira el bloque segun el metadato guardado
			switch (meta) {
				// Norte (z -)
				case 2:
					z1 -= 1;
					break;
				// Sur (z +)
				case 3:
					z1 += 1;
					break;
				// Oeste (x -)
				case 4:
					x1 -= 1;
					break;
				// Este (x +)
				case 5:
					x1 += 1;
					break;
			}
			
			if (canPlantSeed(world, x1, y, z1)) {

				// Obtenemos un slot al azar con items
				int slot = entity.getIndexSlotWithItems();

				if (slot >= 0) {
					// Obtenemos los items en el slot
					ItemStack items = entity.getStackInSlot(slot);
					
					if (TileEntityAutoFarmer.isSeedValid(items)) {
						// Obtenemos el item
						Item seed = items.getItem();
						
						// Según el tipo de semilla, vamos a obtener el bloque que utiliza
						Block seedBlock = ((IPlantable)seed).getPlant(world, x1, y, z1);
						
						// Spawneamos el bloque/item?
						world.setBlock(x1, y, z1, seedBlock);
						world.setBlockMetadataWithNotify(x1, y, z1, meta, 2);

						// Reproducimos el sonido
						world.playAuxSFX(1000, x, y, z, 0);
						
						// FIXME: Mostrar particulas (Esto no funciona, aunque está correcto)
						showParticles(world, x1, y, z1);

						// Le restamos uno
						items.stackSize--;

						if (items.stackSize == 0) items = null;
						
						// Cambiamos los items en el slot
						entity.setInventorySlotContents(slot, items);
						
						// Marcamos la maquina para actualizar
						world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
						
						// Rompemos el bucle
						break;
					}
				} else {
					// Reproducimos el sonido de vacio
					world.playAuxSFX(1001, x, y, z, 0);
				}
			}
		}

	}

	/**
	 * Devuelve si puede plantar en una posicion, si no esta ocupado, y
	 * si el suelo inferior es plantable
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private boolean canPlantSeed(World world, int x, int y, int z) {
		// Obtenemos el bloque superior
		Block bloque = world.getBlock(x, y, z);
		
		// Si el bloque esta ocupado, devolvemos falso
		if (bloque != Blocks.air) return false;
		
		// Obtenemos el bloque inferior a donde queremos plantar
		Block bloqueInferior = world.getBlock(x, y - 1, z);

        if (bloqueInferior == Blocks.farmland)
        {
            return true;
        }

        return false;
	}
	
	@SideOnly(Side.CLIENT)
	public void showParticles(World world, int x, int y, int z){
		float f1 = (float)x + 0.5F;
		float f2 = (float)y + 1.5F;
		float f3 = (float)z + 0.5F;
		float f4 = aleatorio.nextFloat() * 0.6F - 0.3F;
		float f5 = aleatorio.nextFloat() * -0.6F - -0.3F;

        world.spawnParticle("happyVillager", (double)f1+f4, (double)f2, (double)f3+f5, 0.0D, 0.0D, 0.0D);
	}

	/********************************************** METODOS EVENTOS **************************************************/
	/**
	 * Esta funcion se activa cuando se hace click derecho sobre el bloque
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par1, float par2, float par3) {
		if (!world.isRemote) {
			player.openGui(Machines.instance, 1, world, x, y, z);
		}

		return true;
	}
	
	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */

	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
		EntityLivingBase player, ItemStack item) {
		
		int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}
	}
	
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer)world.getTileEntity(x, y, z);
		
		if (!world.isRemote) {
			if (this.isPowered(world, x, y, z)) {
				// Lo marcamos para comprobar en el próximo world tick
				world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			} else {
				int meta = blockEntity.getBlockMetadata();
				if (meta == 0) meta = 3;
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
	}

	/**
	 * Comprueba si el bloque esta marcado para actualizar
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);

		if (!world.isRemote) {
			if (this.isPowered(world, x, y, z)) {
				this.plantSeeds(world, x, y, z, blockEntity);
			} else {
				int meta = blockEntity.getBlockMetadata();
				if (meta == 0) meta = 3;
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int j) {
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);

		if (blockEntity != null) {
			for (int i1 = 0; i1 < blockEntity.getSizeInventory(); ++i1) {
				ItemStack itemstack = blockEntity.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.aleatorio.nextFloat() * 0.8F + 0.1F;
					float f1 = this.aleatorio.nextFloat() * 0.8F + 0.1F;
					float f2 = this.aleatorio.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = this.aleatorio.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world,
								(double) ((float) x + f),
								(double) ((float) y + f1),
								(double) ((float) z + f2),
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.aleatorio.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.aleatorio.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.aleatorio.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, j);
	}
}
