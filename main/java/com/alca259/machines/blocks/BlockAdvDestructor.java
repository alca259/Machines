package com.alca259.machines.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import com.alca259.machines.Machines;
import com.alca259.machines.tileentity.TileEntityPosicion;
import com.alca259.machines.util.LogHelper;
import com.alca259.machines.util.Position;

public class BlockAdvDestructor extends BlockContainer {

	/********************************************** VARIABLES DE LA CLASE **************************************************/
	@SideOnly(Side.CLIENT)
	private IIcon textureFront, textureSides, textureBottom, textureTop;
	private Map<String, Integer[]> estadosBloque;
	private float estadoActual;
	private int direccion = 0;
	private final boolean powered;

	/********************************************** CONSTRUCTORES **************************************************/
	/**
	 * Constructor de la clase
	 * 
	 * @param active : true o false
	 */
	public BlockAdvDestructor(boolean active) {
		super(Material.rock);

		// Propiedades
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		
		// Custom
		this.estadosBloque = new HashMap<String, Integer[]>();
		this.powered = active;

		if (!active) {
			this.setCreativeTab(CreativeTabs.tabRedstone);
			this.setBlockName("alca259AdvDestructor");
		} else {
			this.setBlockName("alca259AdvDestructorActive");
		}
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
		return Item.getItemFromBlock(Machines.advDestructor);
	}

	/**
	 * Actualizacion del bloque por ticks
	 */
	@Override
	public int tickRate(World world) {
		return 1;
	}

	/**
	 * Asigna las texturas al bloque
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg)
	{
		if (this.powered) {
			textureFront = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructorFrontalActive");
		} else {
			textureFront = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructorFrontal");
		}
		this.blockIcon = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructor");
		this.textureSides = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructor");
		this.textureBottom = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructorBottom");
		this.textureTop = iconReg.registerIcon(Machines.MODID + ":BlockAdvDestructorTop");
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

        if (item == null)
        {
            return null;
        }

        Block block = Machines.advDestructor;
        return new ItemStack(item, 1, block.getDamageValue(world, x, y, z));
	}

	/**
	 * Establece la entidad de almacenamiento de datos
	 */
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityPosicion();
	}

	/********************************************** METODOS PROPIOS **************************************************/
	/**
	 * Esta funcion comprueba si el bloque a destruir ya tiene un metadato de
	 * destruccion
	 */
	protected boolean tieneDestruccion(int x, int y, int z, String items) {
		LogHelper.debug("[Alca] tieneDestruccion:BlockAdvDestructor.java");
		if (this.estadosBloque.get(items) != null) {
			Integer[] posBloque = this.estadosBloque.get(items);
			int x1 = posBloque[2].intValue();
			int y1 = posBloque[3].intValue();
			int z1 = posBloque[4].intValue();

			if (x1 == x && y1 == y && z1 == z) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Comprueba si el bloque es destruible
	 */
	protected boolean esDestruible(World world, int x, int y, int z) {
		LogHelper.debug("[Alca] esDestruible:BlockAdvDestructor.java");
		Block bloque = world.getBlock(x, y, z);
		return bloque != Blocks.bedrock// Bedrock
				&& bloque.getMaterial() != Material.lava
				&& bloque.getMaterial() != Material.water;
	}
	
	/**
	 * Esta funcion se encarga de romper un bloque del mundo en una determinada posicion
	 */
	public void romperBloque(World world, int x, int y, int z, Random aleatorio) {
		LogHelper.debug("[Alca] romperBloque:BlockAdvDestructor.java");
		// Inicializamos
		int x1 = x;
		int z1 = z;
		String items = String.format("%d;%d;%d", new Object[] { Integer.valueOf(x), Integer.valueOf(y),	Integer.valueOf(z) });
		boolean devolverSilkTouch = false;

		// Obtenemos la direccion en la que mira el bloque segun el metadato
		// guardado
		switch (direccion) {
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
			default:
				return;
		}

		Block bloqueNext = world.getBlock(x1, y, z1);
		boolean estaRoto = tieneDestruccion(x1, y, z1, items);

		if (bloqueNext == Blocks.air) { // Aire
			if (this.estadosBloque.get(items) != null) {
				this.estadosBloque.remove(items);
				this.estadoActual = 0.0F;
			}
		} else {
			// Solo creara nueva destruccion si el bloque no tiene ya
			if (estaRoto == false) {
				int var1 = -aleatorio.nextInt();

				this.estadosBloque.put(items, new Integer[] {
						Integer.valueOf(0),
						Integer.valueOf(var1), Integer.valueOf(x1),
						Integer.valueOf(y), Integer.valueOf(z1) 
				});
				
				this.estadoActual = 0.0F;
			}
		}

		// Importante: Comprobar que no sea aire, que reciba energia y que sea
		// un bloque destruible
		if (bloqueNext != Blocks.air && world.isBlockIndirectlyGettingPowered(x, y, z)
				&& esDestruible(world, x1, y, z1) && estaRoto) {
			
			// Bloque avanzado, comprobamos si es especial
			Block bloque = world.getBlock(x1, y, z1);
			int meta = world.getBlockMetadata(x1, y, z1);

			if (bloque.canSilkHarvest(world, null, x1, y, z1, meta));
			{
				devolverSilkTouch = true;
			}

			if (this.estadosBloque.get(items) != null) {
				int var2 = this.estadosBloque.get(items)[0].intValue();
				int var3 = this.estadosBloque.get(items)[1].intValue();

				if (var2 < 9) {

					// EXPERIMENTAL: Se incrementará/decrementará la velocidad
					// de destrucción en base a la dureza del bloque a romper
					float dureza = bloque.getBlockHardness(world, x1, y, z1);

					if (dureza > 0.0F) {
						// Guardamos la destrucción actual del bloque mas la que ya teniamos
						float hardnessNow = this.estadoActual + (var2 / dureza);
						float hardnessPerTick = 1 / dureza;

						// Si la destrucción actual, es mayor o igual que la que viene del tick, avanzamos
						if ((int) hardnessNow >= var2) {
							world.destroyBlockInWorldPartially(var3, x1, y, z1,	var2 + 1);
							
							this.estadosBloque.put(items, new Integer[] {
									Integer.valueOf(var2 + 1),
									Integer.valueOf(var3), Integer.valueOf(x1),
									Integer.valueOf(y), Integer.valueOf(z1)
							});
						}

						this.estadoActual = this.estadoActual + hardnessPerTick;
						
					} else {
						// Si la dureza es inferior a 0, utilizamos la velocidad de los tick
						world.destroyBlockInWorldPartially(var3, x1, y, z1,	var2 + 1);
						
						this.estadosBloque.put(items, new Integer[] {
								Integer.valueOf(var2 + 1),
								Integer.valueOf(var3), Integer.valueOf(x1),
								Integer.valueOf(y), Integer.valueOf(z1)
						});	
					}

					// Actualizamos el tickrate para que vuelva a pasar por aqui
					world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
				}

				if (var2 >= 9) {
					world.destroyBlockInWorldPartially(var3, x1, y, z1, -1);
					world.setBlock(x1, y, z1, Blocks.air); // Aire
					world.playAuxSFX(2001, x1, y, z1, getIdFromBlock(bloqueNext));

					if (devolverSilkTouch == false) {	
						bloque.dropBlockAsItem(world, x1, y, z1, meta, 0);
					} else {
						this.dropearBloqueComoItemConProbabilidad(world, x1, y, z1, meta, 1.0F, 0, bloque);
					}

					this.estadosBloque.remove(items);
				}
			}
		}

		if (!estaRoto) {
			// Actualizamos el tickrate para que vuelva a pasar por aqui
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		}
	}

	/**
	 * Esta funcion va a devolver el bloque que yo le pase por parametro
	 */
    public void dropearBloqueComoItemConProbabilidad(World world, int x, int y, int z, int metadata, float aleatorio, int fortuna, Block bloque) {
    	LogHelper.debug("[Alca] dropearBloqueComoItemConProbabilidad:BlockAdvDestructor.java");
        if (!world.isRemote) {
            ArrayList<ItemStack> items = obtenerBloqueDropeado(world, x, y, z, metadata, fortuna, bloque);

            for (ItemStack item : items) {
                if (world.rand.nextFloat() <= aleatorio) {
                    this.dropBlockAsItem(world, x, y, z, item);
                }
            }
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param metadata Current metadata
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    public ArrayList<ItemStack> obtenerBloqueDropeado(World world, int x, int y, int z, int metadata, int fortune, Block bloque) {
    	LogHelper.debug("[Alca] obtenerBloqueDropeado:BlockAdvDestructor.java");
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++) {
            if (bloque.getIdFromBlock(bloque) > Block.getIdFromBlock(Blocks.air)) {
                ret.add(new ItemStack(bloque, 1, damageDropped(metadata)));
            }
        }
        return ret;
    }

    /********************************************** METODOS EVENTOS **************************************************/
	/**
	 * Esta funcion se encarga de calcular la cara del bloque en funcion de la
	 * direccion en la que mire, y guarda la direccion del objeto
	 */
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entitylivingbase, ItemStack par6ItemStack) {
		LogHelper.debug("[Alca] onBlockPlacedBy:BlockAdvDestructor.java");
		ForgeDirection orientation = Position.get2dOrientation(new Position(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ), new Position(i, j, k));
		TileEntityPosicion blockEntity = (TileEntityPosicion)world.getTileEntity(i, j, k);

		this.direccion = orientation.getOpposite().ordinal();

		if (blockEntity != null) {
			blockEntity.setFacingDirection(this.direccion);
		}

		world.setBlockMetadataWithNotify(i, j, k, this.direccion, 2);
	}
	
	/**
	 * Esta funcion comprueba si al anyadir el bloque al mundo, algun bloque
	 * adyacente tiene energia y puede darsela a este bloque indirectamente.
	 */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		LogHelper.debug("[Alca] onBlockPlaced:BlockAdvDestructor.java");
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}

    @Override
	public void onBlockAdded(World world, int x, int y, int z) {
    	LogHelper.debug("[Alca] onBlockAdded:BlockAdvDestructor.java");
		TileEntityPosicion blockEntity = (TileEntityPosicion)world.getTileEntity(x, y, z);
        if (!world.isRemote) {
        	
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));

            } else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {

        		if (blockEntity != null) {
        			if (blockEntity.getFacingDirection() != -1) {
        				this.direccion = blockEntity.getFacingDirection();
        			} else {
        				blockEntity.setFacingDirection(this.direccion);
        			}
            	}

                world.setBlock(x, y, z, Machines.advDestructorActive, 0, 2);
                world.setBlockMetadataWithNotify(x, y, z, this.direccion, 2);
            } else {
            	if (this.powered) {
            		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            	}
            }
        }
    }
	
	/**
	 * Comprueba si el bloque esta marcado para actualizar
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		LogHelper.debug("[Alca] updateTick:BlockAdvDestructor.java");
		TileEntityPosicion blockEntity = (TileEntityPosicion)world.getTileEntity(x, y, z);

        if (!world.isRemote) {

        	if (blockEntity != null) {
    			if (blockEntity.getFacingDirection() != -1) {
    				this.direccion = blockEntity.getFacingDirection();
    			}
        	}
        	
        	if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.setBlock(x, y, z, Machines.advDestructor, 0, 2);
                world.setBlockMetadataWithNotify(x, y, z, this.direccion, 2);

            } else if (this.powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            	this.romperBloque(world, x, y, z, par5Random);
            }
        }
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		LogHelper.debug("[Alca] onNeighborBlockChange:BlockAdvDestructor.java");
		TileEntityPosicion blockEntity = (TileEntityPosicion)world.getTileEntity(x, y, z);

        if (!world.isRemote) {
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            } else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {

            	if (blockEntity != null) {
        			if (blockEntity.getFacingDirection() != -1) {
        				this.direccion = blockEntity.getFacingDirection();
        			}
            	}

                world.setBlock(x, y, z, Machines.advDestructorActive, 0, 2);
                world.setBlockMetadataWithNotify(x, y, z, this.direccion, 2);
            } else {
            	if (this.powered) {
            		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            	}
            }
        }
    }

	/**
	 * Anulacion del sistema por defecto
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int j) {
		// Anulamos lo que hace el sistema base, para que se conserve la posicion
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		// Eliminamos la entidad
		world.removeTileEntity(x, y, z);
		// Devolvemos el control
		return super.removedByPlayer(world, player, x, y, z);
	}

}