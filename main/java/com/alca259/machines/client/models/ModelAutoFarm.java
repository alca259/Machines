package com.alca259.machines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAutoFarm extends ModelBase {
	// fields
	ModelRenderer Mango;
	ModelRenderer Cortador;
	ModelRenderer Semilla1;
	ModelRenderer Semilla2;
	ModelRenderer Semilla3;
	ModelRenderer Semilla4;
	ModelRenderer Semilla_5;
	ModelRenderer Semilla_6;
	ModelRenderer BaseSup;
	ModelRenderer PanelTrasSup;
	ModelRenderer PanelFrontSup;
	ModelRenderer PanelIzqSup;
	ModelRenderer PanelDchoSup;
	ModelRenderer PanelTrasero;
	ModelRenderer PanelIzq;
	ModelRenderer PanelDcho;
	ModelRenderer Base;
	ModelRenderer Barrera;
	ModelRenderer Agua;

	public ModelAutoFarm() {
		textureWidth = 64;
		textureHeight = 64;

		Mango = new ModelRenderer(this, 56, 29);
		Mango.addBox(0F, 0F, 0F, 1, 14, 1);
		Mango.setRotationPoint(-3F, 0.4F, 6F);
		Mango.setTextureSize(64, 64);
		Mango.mirror = true;
		setRotation(Mango, -0.2792527F, 0F, 0F);
		Cortador = new ModelRenderer(this, 28, 54);
		Cortador.addBox(0F, 0F, -3F, 2, 1, 7);
		Cortador.setRotationPoint(-3.466667F, 1F, 3F);
		Cortador.setTextureSize(64, 64);
		Cortador.mirror = true;
		setRotation(Cortador, -0.1745329F, 0F, 0F);
		Semilla1 = new ModelRenderer(this, 24, 54);
		Semilla1.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla1.setRotationPoint(0F, 13F, -3F);
		Semilla1.setTextureSize(64, 64);
		Semilla1.mirror = true;
		setRotation(Semilla1, 0F, 0F, 0F);
		Semilla2 = new ModelRenderer(this, 24, 54);
		Semilla2.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla2.setRotationPoint(2F, 13F, 0F);
		Semilla2.setTextureSize(64, 64);
		Semilla2.mirror = true;
		setRotation(Semilla2, 0F, 0F, 0F);
		Semilla3 = new ModelRenderer(this, 24, 54);
		Semilla3.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla3.setRotationPoint(2F, 13F, 4F);
		Semilla3.setTextureSize(64, 64);
		Semilla3.mirror = true;
		setRotation(Semilla3, 0F, 0F, 0F);
		Semilla4 = new ModelRenderer(this, 24, 54);
		Semilla4.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla4.setRotationPoint(-3F, 13F, -2F);
		Semilla4.setTextureSize(64, 64);
		Semilla4.mirror = true;
		setRotation(Semilla4, 0F, 0F, 0F);
		Semilla_5 = new ModelRenderer(this, 24, 54);
		Semilla_5.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla_5.setRotationPoint(-1F, 13F, 2F);
		Semilla_5.setTextureSize(64, 64);
		Semilla_5.mirror = true;
		setRotation(Semilla_5, 0F, 0F, 0F);
		Semilla_6 = new ModelRenderer(this, 24, 54);
		Semilla_6.addBox(0F, 0F, 0F, 1, 1, 1);
		Semilla_6.setRotationPoint(0F, 13F, 0F);
		Semilla_6.setTextureSize(64, 64);
		Semilla_6.mirror = true;
		setRotation(Semilla_6, 0F, 0F, 0F);
		BaseSup = new ModelRenderer(this, 0, 29);
		BaseSup.addBox(0F, 0F, 0F, 14, 2, 14);
		BaseSup.setRotationPoint(-7F, 14F, -7F);
		BaseSup.setTextureSize(64, 64);
		BaseSup.mirror = true;
		setRotation(BaseSup, 0F, 0F, 0F);
		PanelTrasSup = new ModelRenderer(this, 24, 45);
		PanelTrasSup.addBox(0F, 0F, 0F, 14, 7, 2);
		PanelTrasSup.setRotationPoint(-7F, 7F, 5F);
		PanelTrasSup.setTextureSize(64, 64);
		PanelTrasSup.mirror = true;
		setRotation(PanelTrasSup, 0F, 0F, 0F);
		PanelFrontSup = new ModelRenderer(this, 24, 45);
		PanelFrontSup.addBox(0F, 0F, 0F, 14, 7, 2);
		PanelFrontSup.setRotationPoint(-7F, 7F, -7F);
		PanelFrontSup.setTextureSize(64, 64);
		PanelFrontSup.mirror = true;
		setRotation(PanelFrontSup, 0F, 0F, 0F);
		PanelIzqSup = new ModelRenderer(this, 0, 45);
		PanelIzqSup.addBox(0F, 0F, 0F, 2, 7, 10);
		PanelIzqSup.setRotationPoint(5F, 7F, -5F);
		PanelIzqSup.setTextureSize(64, 64);
		PanelIzqSup.mirror = true;
		setRotation(PanelIzqSup, 0F, 0F, 0F);
		PanelDchoSup = new ModelRenderer(this, 0, 45);
		PanelDchoSup.addBox(0F, 0F, 0F, 2, 7, 10);
		PanelDchoSup.setRotationPoint(-7F, 7F, -5F);
		PanelDchoSup.setTextureSize(64, 64);
		PanelDchoSup.mirror = true;
		setRotation(PanelDchoSup, 0F, 0F, 0F);
		PanelTrasero = new ModelRenderer(this, 18, 16);
		PanelTrasero.addBox(0F, 0F, 0F, 10, 6, 2);
		PanelTrasero.setRotationPoint(-5F, 16F, 3F);
		PanelTrasero.setTextureSize(64, 64);
		PanelTrasero.mirror = true;
		setRotation(PanelTrasero, 0F, 0F, 0F);
		PanelIzq = new ModelRenderer(this, 0, 16);
		PanelIzq.addBox(0F, 0F, 0F, 2, 6, 7);
		PanelIzq.setRotationPoint(3F, 16F, -4F);
		PanelIzq.setTextureSize(64, 64);
		PanelIzq.mirror = true;
		setRotation(PanelIzq, 0F, 0F, 0F);
		PanelDcho = new ModelRenderer(this, 0, 16);
		PanelDcho.addBox(0F, 0F, 0F, 2, 6, 7);
		PanelDcho.setRotationPoint(-5F, 16F, -4F);
		PanelDcho.setTextureSize(64, 64);
		PanelDcho.mirror = true;
		setRotation(PanelDcho, 0F, 0F, 0F);
		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 14, 2, 14);
		Base.setRotationPoint(-7F, 22F, -7F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Barrera = new ModelRenderer(this, 18, 21);
		Barrera.addBox(0F, 0F, 0F, 6, 2, 1);
		Barrera.setRotationPoint(-3F, 20F, -4F);
		Barrera.setTextureSize(64, 64);
		Barrera.mirror = true;
		setRotation(Barrera, 0F, 0F, 0F);
		Agua = new ModelRenderer(this, 39, 54);
		Agua.addBox(0F, 0F, 0F, 6, 2, 6);
		Agua.setRotationPoint(-3F, 20F, -3F);
		Agua.setTextureSize(64, 64);
		Agua.mirror = true;
		setRotation(Agua, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Mango.render(f5);
		Cortador.render(f5);
		Semilla1.render(f5);
		Semilla2.render(f5);
		Semilla3.render(f5);
		Semilla4.render(f5);
		Semilla_5.render(f5);
		Semilla_6.render(f5);
		BaseSup.render(f5);
		PanelTrasSup.render(f5);
		PanelFrontSup.render(f5);
		PanelIzqSup.render(f5);
		PanelDchoSup.render(f5);
		PanelTrasero.render(f5);
		PanelIzq.render(f5);
		PanelDcho.render(f5);
		Base.render(f5);
		Barrera.render(f5);
		Agua.render(f5);
	}
	
	public void renderModel(float f5) {
		PanelTrasero.render(f5);
		PanelIzq.render(f5);
		PanelDcho.render(f5);
		Base.render(f5);
		Barrera.render(f5);
		Agua.render(f5);
		
		BaseSup.render(f5);
		PanelTrasSup.render(f5);
		PanelFrontSup.render(f5);
		PanelIzqSup.render(f5);
		PanelDchoSup.render(f5);
		
		Mango.render(f5);
		Cortador.render(f5);
		Semilla1.render(f5);
		Semilla2.render(f5);
		Semilla3.render(f5);
		Semilla4.render(f5);
		Semilla_5.render(f5);
		Semilla_6.render(f5);
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
