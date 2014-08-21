package com.alca259.machines.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.alca259.machines.Machines;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;
import com.alca259.machines.util.Position;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAutoFarmer extends BlockContainer {
	/********************************************** VARIABLES DE LA CLASE **************************************************/
	@SideOnly(Side.CLIENT)
	private IIcon textureFront, textureSides, textureBottom, textureTop;
	private int direccion = 0;
	Random aleatorio = new Random();

	public BlockAutoFarmer() {
		super(Material.rock);

		// Propiedades
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);

		this.setBlockName("alca259AutoFarmer");
		this.setBlockBounds(0.05f, 0.5f, 0.05f, 0.95f, 1f ,0.95f);
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
		return 4;
	}

	/**
	 * Asigna las texturas al bloque
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		// Registra la miniatura 3D
		this.blockIcon = iconReg.registerIcon(Machines.MODID + ":BlockDestructor");
		// Registra las texturas del bloque
		this.textureFront = iconReg.registerIcon(Machines.MODID + ":BlockDestructorFrontal");
		this.textureSides = iconReg.registerIcon("furnace_side");// iconReg.registerIcon(Machines.MODID + ":BlockDestructor");
		this.textureBottom = iconReg.registerIcon(Machines.MODID + ":BlockDestructorBottom");
		this.textureTop = iconReg.registerIcon(Machines.MODID + ":BlockDestructorTop");
	}

	/**
	 * Esta funcion se encarga de asignar las texturas a cada cara del bloque
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta == 0 && side == 3)
			return textureFront;
		else if (side == 1)
			return textureTop;
		else if (side == 0)
			return textureBottom;
		else if (side == meta)
			return textureFront;
		else
			return textureSides;
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
	
	/********************************************** METODOS PROPIOS **************************************************/
	/**
	 * Synonim: Esta funcion indica si le llega corriente de redstone
	 */
	public boolean isPowered(World world, int x, int y, int z) {
		return world.isBlockIndirectlyGettingPowered(x, y, z);
	}

	/**
	 * Esta funcion establece la direccion en base a una entidad
	 */
	public void setDireccion(World world, int i, int j, int k, EntityLivingBase entitylivingbase) {
		ForgeDirection orientation = Position.get2dOrientation(new Position(
			entitylivingbase.posX, entitylivingbase.posY,
			entitylivingbase.posZ), new Position(i, j, k));
		
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(i, j, k);

		this.direccion = orientation.getOpposite().ordinal();

		if (blockEntity != null) {
			blockEntity.setFacingDirection(this.direccion);
		}
	}

	/********************************************** METODOS EVENTOS **************************************************/
	/**
	 * Esta funcion se encarga de calcular la cara del bloque en funcion de la
	 * direccion en la que mire el jugador, y guarda la direccion del objeto
	 *
     * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entitylivingbase, ItemStack item) {
		this.setDireccion(world, i, j, k, entitylivingbase);
		world.setBlockMetadataWithNotify(i, j, k, this.direccion, 2);
	}

	/**
	 * Called when a block is placed using its ItemBlock
	 */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		// Lo marcamos para comprobar en el próximo world tick
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}
	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);
		if (!world.isRemote) {
			if (!this.isPowered(world, x, y, z)) {
				// Lo marcamos para comprobar en el próximo world tick
				world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			} else {

				// Si no tiene direccion establecida, la obtenemos del NBT y la asignamos
				if (blockEntity != null) {
					if (blockEntity.getFacingDirection() != -1) {
						this.direccion = blockEntity.getFacingDirection();
					} else {
						blockEntity.setFacingDirection(this.direccion);
					}
				}

				// Cambiamos el bloque y lo marcamos para comprobar en el próximo world tick
				world.setBlockMetadataWithNotify(x, y, z, this.direccion, 2);
			}
		}
	}
	
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			// Lo marcamos para comprobar en el próximo world tick
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		}
	}

	/**
	 * Comprueba si el bloque esta marcado para actualizar
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		TileEntityAutoFarmer blockEntity = (TileEntityAutoFarmer) world.getTileEntity(x, y, z);

		if (!world.isRemote) {
			if (!this.isPowered(world, x, y, z)) {
				world.setBlockMetadataWithNotify(x, y, z, this.direccion, 2);
			} else {
				// this.plantSeeds(world, x, y, z, par5Random);
			}
		}
	}

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
