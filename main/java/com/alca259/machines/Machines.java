package com.alca259.machines;

//Importaciones
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.alca259.machines.blocks.BlockAdvDestructor;
import com.alca259.machines.blocks.BlockAutoFarmer;
import com.alca259.machines.blocks.BlockDestructor;
import com.alca259.machines.core.GUIHandler;
import com.alca259.machines.proxy.CommonProxy;
import com.alca259.machines.tileentity.TileEntityAutoFarmer;
import com.alca259.machines.tileentity.TileEntityPosicion;
import com.alca259.machines.util.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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
	public static final String MODVERSION = "1.2.0";

	// Declaracion de bloques
	public static Block destructor;
	public static Block destructorActive;
	public static Block advDestructor;
	public static Block advDestructorActive;
	public static Block autoFarmer;

	/**
	 * Metodos de evento
	 */
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		// Codigo a ser pre-inicializado
		LogHelper.init(event.getModLog());

		// Creamos bloques y recetas
		loadItemsAndBlocks();
		registrarBloques();
		registrarRecetas();
		
		// Inicializar el proxy
		proxy.registerTileEntities();
		proxy.registerRenders();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		registrarGUI();
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
		destructor = new BlockDestructor(false);
		destructorActive = new BlockDestructor(true);
		advDestructor = new BlockAdvDestructor(false);
		advDestructorActive = new BlockAdvDestructor(true);
		autoFarmer = new BlockAutoFarmer();
	}

	/**
	 * Registrar bloque en el juego
	 */
	public void registrarBloques() {
		GameRegistry.registerBlock(destructor, "alca259_destructor");
		GameRegistry.registerBlock(destructorActive, "alca259_destructorActive");
		GameRegistry.registerBlock(advDestructor, "alca259_advDestructor");
		GameRegistry.registerBlock(advDestructorActive,	"alca259_advDestructorActive");
		GameRegistry.registerBlock(autoFarmer, "alca259_autoFarmer");
	}

	/**
	 * Registramos la recetas para poder craftearlos
	 */
	public void registrarRecetas() {
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
		
		GameRegistry.addRecipe(new ItemStack(autoFarmer, 4), new Object[] {
			"WWW", "RIB", "OOO",
			Character.valueOf('W'), Blocks.planks,
			Character.valueOf('R'), Items.redstone,
			Character.valueOf('I'), Blocks.iron_block,
			Character.valueOf('B'), Items.bow,
			Character.valueOf('O'), Items.iron_ingot
		});
	}

	public void registrarGUI() {
		//Register our GUI Handler.
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
	}

}
