package com.alca259.machines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAutoFarm extends ModelBase {
	// fields
    ModelRenderer Pata1;
    ModelRenderer Pata2;
    ModelRenderer Pata3;
    ModelRenderer Pata4;
    ModelRenderer Caja;
    ModelRenderer Frontal3;
    ModelRenderer Frontal4;
    ModelRenderer Frontal2;
    ModelRenderer Frontal1;
    ModelRenderer Cesta1;
    ModelRenderer Cesta5;
    ModelRenderer Cesta4;
    ModelRenderer Cesta3;
    ModelRenderer Cesta2;
    ModelRenderer CestaAgua;
    ModelRenderer Semilla1;
    ModelRenderer Semilla2;
    ModelRenderer Semilla4;
    ModelRenderer Semilla5;
    ModelRenderer Semilla6;
    ModelRenderer Semilla3;

	public ModelAutoFarm() {
		textureWidth = 64;
		textureHeight = 64;

		Pata1 = new ModelRenderer(this, 0, 0);
	      Pata1.addBox(0F, 0F, 0F, 1, 2, 2);
	      Pata1.setRotationPoint(-4F, 22F, -4F);
	      Pata1.setTextureSize(64, 64);
	      Pata1.mirror = true;
	      setRotation(Pata1, 0F, 0F, 0F);
	      Pata2 = new ModelRenderer(this, 0, 0);
	      Pata2.addBox(0F, 0F, 0F, 1, 2, 2);
	      Pata2.setRotationPoint(3F, 22F, -4F);
	      Pata2.setTextureSize(64, 64);
	      Pata2.mirror = true;
	      setRotation(Pata2, 0F, 0F, 0F);
	      Pata3 = new ModelRenderer(this, 0, 0);
	      Pata3.addBox(0F, 0F, 0F, 1, 2, 2);
	      Pata3.setRotationPoint(-4F, 22F, 5F);
	      Pata3.setTextureSize(64, 64);
	      Pata3.mirror = true;
	      setRotation(Pata3, 0F, 0F, 0F);
	      Pata4 = new ModelRenderer(this, 0, 0);
	      Pata4.addBox(0F, 0F, 0F, 1, 2, 2);
	      Pata4.setRotationPoint(3F, 22F, 5F);
	      Pata4.setTextureSize(64, 64);
	      Pata4.mirror = true;
	      setRotation(Pata4, 0F, 0F, 0F);
	      Caja = new ModelRenderer(this, 0, 4);
	      Caja.addBox(0F, 0F, 0F, 10, 8, 13);
	      Caja.setRotationPoint(-5F, 14F, -5F);
	      Caja.setTextureSize(64, 64);
	      Caja.mirror = true;
	      setRotation(Caja, 0F, 0F, 0F);
	      Frontal3 = new ModelRenderer(this, 6, 0);
	      Frontal3.addBox(0F, 0F, 0F, 2, 1, 2);
	      Frontal3.setRotationPoint(-1F, 16F, -7F);
	      Frontal3.setTextureSize(64, 64);
	      Frontal3.mirror = true;
	      setRotation(Frontal3, 0F, 0F, 0F);
	      Frontal4 = new ModelRenderer(this, 6, 0);
	      Frontal4.addBox(0F, 0F, 0F, 2, 1, 2);
	      Frontal4.setRotationPoint(-1F, 19F, -7F);
	      Frontal4.setTextureSize(64, 64);
	      Frontal4.mirror = true;
	      setRotation(Frontal4, 0F, 0F, 0F);
	      Frontal2 = new ModelRenderer(this, 14, 0);
	      Frontal2.addBox(0F, 0F, 0F, 1, 2, 2);
	      Frontal2.setRotationPoint(1F, 17F, -7F);
	      Frontal2.setTextureSize(64, 64);
	      Frontal2.mirror = true;
	      setRotation(Frontal2, 0F, 0F, 0F);
	      Frontal1 = new ModelRenderer(this, 14, 0);
	      Frontal1.addBox(0F, 0F, 0F, 1, 2, 2);
	      Frontal1.setRotationPoint(-2F, 17F, -7F);
	      Frontal1.setTextureSize(64, 64);
	      Frontal1.mirror = true;
	      setRotation(Frontal1, 0F, 0F, 0F);
	      Cesta1 = new ModelRenderer(this, 20, 0);
	      Cesta1.addBox(0F, 0F, 0F, 8, 2, 1);
	      Cesta1.setRotationPoint(-4F, 12F, -5F);
	      Cesta1.setTextureSize(64, 64);
	      Cesta1.mirror = true;
	      setRotation(Cesta1, 0F, 0F, 0F);
	      Cesta5 = new ModelRenderer(this, 20, 0);
	      Cesta5.addBox(0F, 0F, 0F, 8, 2, 1);
	      Cesta5.setRotationPoint(-4F, 12F, 1F);
	      Cesta5.setTextureSize(64, 64);
	      Cesta5.mirror = true;
	      setRotation(Cesta5, 0F, 0F, 0F);
	      Cesta4 = new ModelRenderer(this, 0, 25);
	      Cesta4.addBox(0F, 0F, 0F, 1, 2, 13);
	      Cesta4.setRotationPoint(4F, 12F, -5F);
	      Cesta4.setTextureSize(64, 64);
	      Cesta4.mirror = true;
	      setRotation(Cesta4, 0F, 0F, 0F);
	      Cesta3 = new ModelRenderer(this, 0, 25);
	      Cesta3.addBox(0F, 0F, 0F, 1, 2, 13);
	      Cesta3.setRotationPoint(-5F, 12F, -5F);
	      Cesta3.setTextureSize(64, 64);
	      Cesta3.mirror = true;
	      setRotation(Cesta3, 0F, 0F, 0F);
	      Cesta2 = new ModelRenderer(this, 20, 0);
	      Cesta2.addBox(0F, 0F, 0F, 8, 2, 1);
	      Cesta2.setRotationPoint(-4F, 12F, 7F);
	      Cesta2.setTextureSize(64, 64);
	      Cesta2.mirror = true;
	      setRotation(Cesta2, 0F, 0F, 0F);
	      CestaAgua = new ModelRenderer(this, 0, 40);
	      CestaAgua.addBox(0F, 0F, 0F, 8, 1, 5);
	      CestaAgua.setRotationPoint(-4F, 13F, -4F);
	      CestaAgua.setTextureSize(64, 64);
	      CestaAgua.mirror = true;
	      setRotation(CestaAgua, 0F, 0F, 0F);
	      Semilla1 = new ModelRenderer(this, 38, 0);
	      Semilla1.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla1.setRotationPoint(-2F, 13F, 2F);
	      Semilla1.setTextureSize(64, 64);
	      Semilla1.mirror = true;
	      setRotation(Semilla1, 0F, 0F, 0F);
	      Semilla2 = new ModelRenderer(this, 38, 0);
	      Semilla2.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla2.setRotationPoint(-3F, 13F, 3F);
	      Semilla2.setTextureSize(64, 64);
	      Semilla2.mirror = true;
	      setRotation(Semilla2, 0F, 0F, 0F);
	      Semilla4 = new ModelRenderer(this, 38, 0);
	      Semilla4.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla4.setRotationPoint(-1F, 13F, 5F);
	      Semilla4.setTextureSize(64, 64);
	      Semilla4.mirror = true;
	      setRotation(Semilla4, 0F, 0F, 0F);
	      Semilla5 = new ModelRenderer(this, 38, 0);
	      Semilla5.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla5.setRotationPoint(2F, 13F, 3F);
	      Semilla5.setTextureSize(64, 64);
	      Semilla5.mirror = true;
	      setRotation(Semilla5, 0F, 0F, 0F);
	      Semilla6 = new ModelRenderer(this, 38, 0);
	      Semilla6.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla6.setRotationPoint(2F, 13F, 6F);
	      Semilla6.setTextureSize(64, 64);
	      Semilla6.mirror = true;
	      setRotation(Semilla6, 0F, 0F, 0F);
	      Semilla3 = new ModelRenderer(this, 38, 0);
	      Semilla3.addBox(0F, 0F, 0F, 1, 1, 1);
	      Semilla3.setRotationPoint(-4F, 13F, 6F);
	      Semilla3.setTextureSize(64, 64);
	      Semilla3.mirror = true;
	      setRotation(Semilla3, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Pata1.render(f5);
	    Pata2.render(f5);
	    Pata3.render(f5);
	    Pata4.render(f5);
	    Caja.render(f5);
	    Frontal3.render(f5);
	    Frontal4.render(f5);
	    Frontal2.render(f5);
	    Frontal1.render(f5);
	    Cesta1.render(f5);
	    Cesta5.render(f5);
	    Cesta4.render(f5);
	    Cesta3.render(f5);
	    Cesta2.render(f5);
	    CestaAgua.render(f5);
	    Semilla1.render(f5);
	    Semilla2.render(f5);
	    Semilla4.render(f5);
	    Semilla5.render(f5);
	    Semilla6.render(f5);
	    Semilla3.render(f5);
	}
	
	public void renderModel(float f5) {
		Pata1.render(f5);
	    Pata2.render(f5);
	    Pata3.render(f5);
	    Pata4.render(f5);
	    Caja.render(f5);
	    Frontal3.render(f5);
	    Frontal4.render(f5);
	    Frontal2.render(f5);
	    Frontal1.render(f5);
	    Cesta1.render(f5);
	    Cesta5.render(f5);
	    Cesta4.render(f5);
	    Cesta3.render(f5);
	    Cesta2.render(f5);
	    CestaAgua.render(f5);
	    Semilla1.render(f5);
	    Semilla2.render(f5);
	    Semilla4.render(f5);
	    Semilla5.render(f5);
	    Semilla6.render(f5);
	    Semilla3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
