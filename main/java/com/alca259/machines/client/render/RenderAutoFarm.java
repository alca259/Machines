package com.alca259.machines.client.render;

import org.lwjgl.opengl.GL11;

import com.alca259.machines.Machines;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderAutoFarm extends TileEntitySpecialRenderer
{
    /**
     * Making fields of our texture and model.
     */
	//TODO: CAMBIAR LAS TEXTURAS Y EL MODELO DEL RENDERIZADO
	private static final ResourceLocation texture = new ResourceLocation(Machines.MODID.toLowerCase() + ":" + "textures/models/lamp.png");
    private IModelCustom model;

    public RenderAutoFarm()
    {
        //Set the model fielt to our Model File.
    	this.model = AdvancedModelLoader.loadModel(new ResourceLocation(Machines.MODID.toLowerCase() + ":" + "models/Lamp.obj"));
    }

    /**
     * Render our Model
     */
    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
        //'Clear memory'
        GL11.glPushMatrix();
        //Translate, no actual changes
        GL11.glTranslated(x, y, z);
        //Scale. Size = 1
        GL11.glScalef(1, 1, 1);
        //Bind our texture
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        //Rotate 0 degrees
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        //Render Model
        model.renderAll();
        //Pop the matrix
        GL11.glPopMatrix();
    }
}