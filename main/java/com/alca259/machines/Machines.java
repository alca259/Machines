package com.alca259.machines;

//Importaciones
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.alca259.machines.blocks.BlockAdvDestructor;
import com.alca259.machines.blocks.BlockDestructor;
import com.alca259.machines.proxy.CommonProxy;
import com.alca259.machines.tileentity.TileEntityPosicion;
import com.alca259.machines.util.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Inicio del mod, propiedades del mod en forge
 */
// Requerido para identificar al mod
@Mod(modid = Machines.MODID, name = Machines.MODNAME, version = Machines.MODVERSION)

/**
 * Clase principal
 * @author alca259
 */
public class Machines {

	// El nombre de la instancia del mod que Forge utiliza
	// Es MUY recomendable que tenga el mismo nombre que el ModId
	// Para evitar errores con otros mods
	@Instance(value = Machines.MODID)
	public static Machines instance;

	// Indicacion de las clases controladoras de los proxys
	// para que el mod sea compatible cliente - servidor
	@SidedProxy(clientSide = "com.alca259.machines.proxy.ClientProxy", serverSide = "com.alca259.machines.proxy.CommonProxy")
	public static CommonProxy proxy = new CommonProxy();

	// Variables de solo lectura
	public static final String MODID = "alca259_machines";
	public static final String MODNAME = "Machines & Misc";
	public static final String MODVERSION = "1.1.2";

	// Declaracion de bloques
	public static Block destructor;
	public static Block destructorActive;
	public static Block advDestructor;
	public static Block advDestructorActive;

	/**
	 * Metodos de evento
	 */
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		// Codigo a ser pre-inicializado
		LogHelper.init(event.getModLog());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// Creamos bloques e items
		loadItemsAndBlocks();

		registrarBloques();
		registrarItems();
		registrarTileEntities();
		registrarEntities();
		registrarRecetas();
		registrarFundiciones();
		registrarGeneracionesMundo();
		registrarEventos();

		// Inicializar el proxy
		proxy.registerRenders();
	}

	@EventHandler
	public void postLoad(FMLPostInitializationEvent event) {
		// Codigo a ser post-inicializado
		LogHelper.info("Machines mod loaded");
	}

	/**
	 * Inicializamos bloques y objetos
	 */
	public void loadItemsAndBlocks() {
		LogHelper.debug("[Alca] loadItemsAndBlocks:Machines.java");
		destructor = new BlockDestructor(false);
		destructorActive = new BlockDestructor(true);
		advDestructor = new BlockAdvDestructor(false);
		advDestructorActive = new BlockAdvDestructor(true);
	}

	/**
	 * Registrar bloque en el juego
	 */
	public void registrarBloques() {
		LogHelper.debug("[Alca] registrarBloques:Machines.java");
		GameRegistry.registerBlock(destructor, "alca259_destructor");
		GameRegistry.registerBlock(destructorActive, "alca259_destructorActive");
		GameRegistry.registerBlock(advDestructor, "alca259_advDestructor");
		GameRegistry.registerBlock(advDestructorActive,	"alca259_advDestructorActive");
	}
	
	/**
	 * Registrar item en el juego
	 */
	public void registrarItems() {
		LogHelper.debug("[Alca] registrarItems:Machines.java");
	}

	/**
	 * Registrar entidades de bloques e items
	 */
	public void registrarTileEntities() {
		LogHelper.debug("[Alca] registrarTileEntities:Machines.java");
		GameRegistry.registerTileEntity(TileEntityPosicion.class, "Alca259-Machines-Posicion");
	}

	/**
	 * Registrar entidades
	 */
	public void registrarEntities() {
		LogHelper.debug("[Alca] registrarEntities:Machines.java");
	}

	/**
	 * Registramos la recetas para poder craftearlos
	 */
	public void registrarRecetas() {
		LogHelper.debug("[Alca] registrarRecetas:Machines.java");
		GameRegistry.addRecipe(new ItemStack(destructor, 1), new Object[] {
			"WWW", "XRI", "XXX",
			Character.valueOf('W'), Blocks.planks, // Planks
			Character.valueOf('X'), Blocks.stone, // Stone
			Character.valueOf('R'),	Items.redstone, // Redstone
			Character.valueOf('I'), Items.iron_pickaxe // Pico de hierro
		});
		
		GameRegistry.addRecipe(new ItemStack(advDestructor, 1), new Object[] {
			"BBB", "XRD", "XXX",
			Character.valueOf('B'), Blocks.stonebrick, // Stone Brick
			Character.valueOf('X'), Blocks.stone, // Stone
			Character.valueOf('R'),	Items.redstone, // Redstone
			Character.valueOf('D'), Items.diamond_pickaxe // Pico de diamante
		});
	}

	/**
	 * Registramos las recetas que se preparan en el horno
	 */
	public void registrarFundiciones() {
		LogHelper.debug("[Alca] registrarFundiciones:Machines.java");
	}
	
	public void registrarGeneracionesMundo() {
		LogHelper.debug("[Alca] registrarGeneracionesMundo:Machines.java");
	}
	
	public void registrarEventos() {
		LogHelper.debug("[Alca] registrarEventos:Machines.java");
	}

}
