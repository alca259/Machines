package com.alca259.machines.client.render;

import org.lwjgl.opengl.GL11;

import com.alca259.machines.Machines;
import com.alca259.machines.client.models.ModelAutoFarm;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderAutoFarm extends TileEntitySpecialRenderer
{
    /**
     * Making fields of our texture and model.
     */
	private static final ResourceLocation texture = new ResourceLocation(Machines.MODID.toLowerCase() + ":" + "textures/models/AutoFarm.png");
    private ModelAutoFarm model;

    public RenderAutoFarm()
    {
        //Set the model fielt to our Model File.
    	this.model = new ModelAutoFarm();
    }

    /**
     * Render our Model
     */
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float var8)
    {
    	// Clear memory
    	GL11.glPushMatrix();
    		// Translate
	    	GL11.glTranslatef((float)x+0.5F, (float)y+1.5F, (float)z+0.5F);
	    	// Rotation
	    	GL11.glRotatef(180.0F, 0F, 0F, 1F);
	    	
	    	World world = tileentity.getWorldObj();
	    	if (world != null) {
		    	int meta = world.getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
		        //System.out.println(meta);
		        
		        if (meta==5) meta = 1;
		           else if (meta==2) meta=0; 
		        if (meta==4) meta = 3;
		           else if (meta==3) meta=2;
		        GL11.glRotatef(90 * meta, 0F, 1F, 0F);
	    	}
	    	// Assign texture
	    	this.bindTexture(texture);
	    	// Clear memory
	    	GL11.glPushMatrix();
	    		// Render Model
	    		this.model.renderModel(0.0625F);
	    	// Pop the matrix
	    	GL11.glPopMatrix();
    	// Pop the matrix
    	GL11.glPopMatrix();
    }
}