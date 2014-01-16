/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 24/09/2013 9:40:19 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.ReactorCraft.Models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import Reika.RotaryCraft.Base.RotaryModelBase;

public class ModelTurbine extends RotaryModelBase
{
	private final ModelRenderer shaft1;
	private final ModelRenderer shaft1a;
	private final ModelRenderer blade;

	private final int stage;

	public ModelTurbine(int stage)
	{
		textureWidth = 128;
		textureHeight = 128;
		this.stage = stage;

		shaft1 = new ModelRenderer(this, 0, 80);
		shaft1.addBox(-2F, -2F, 0F, 4, 4, 16);
		shaft1.setRotationPoint(0F, 15F, -8F);
		shaft1.setTextureSize(128, 128);
		shaft1.mirror = true;
		this.setRotation(shaft1, 0F, 0F, 0.7853982F);
		shaft1a = new ModelRenderer(this, 0, 80);
		shaft1a.addBox(-2F, -2F, 0F, 4, 4, 16);
		shaft1a.setRotationPoint(0F, 15F, -8F);
		shaft1a.setTextureSize(128, 128);
		shaft1a.mirror = true;
		this.setRotation(shaft1a, 0F, 0F, 0F);

		blade = new ModelRenderer(this, 0, 0);
		blade.addBox(-0.5F, -this.getBladeLength(), -this.getBladeWidth()/2F, 1, this.getBladeLength(), this.getBladeWidth());
		blade.setRotationPoint(0F, 15F, 0F);
		blade.setTextureSize(128, 128);
		blade.mirror = true;
		this.setRotation(blade, 0F, 0F, 0F);
	}

	@Override
	public void renderAll(ArrayList li, float phi, float theta)
	{
		double vo = 0.9375;
		double sep = 10;
		double dd = 0.25;
		double sc = this.getScaleFactor();

		int damage = li != null ? (Integer)li.get(0) : 0;

		GL11.glTranslated(0, vo, 0);
		GL11.glRotatef(phi, 0, 0, 1);
		GL11.glTranslated(0, -vo, 0);
		shaft1.render(f5);
		shaft1a.render(f5);
		GL11.glTranslated(0, vo, 0);
		GL11.glRotatef(-phi, 0, 0, 1);
		GL11.glTranslated(0, -vo, 0);

		GL11.glTranslated(0, 0, dd);

		this.renderBlades(damage, vo, phi);

		GL11.glTranslated(0, 0, -dd*2);

		GL11.glTranslated(0, vo, 0);
		GL11.glScaled(sc, sc, 1);
		GL11.glTranslated(0, -vo, 0);

		this.renderBlades(damage, vo, phi);

		GL11.glTranslated(0, vo, 0);
		GL11.glScaled(1D/sc, 1D/sc, 1);
		GL11.glTranslated(0, -vo, 0);

		GL11.glTranslated(0, 0, dd);
	}

	private void renderBlades(int damage, double vo, float phi) {
		for (int i = 0; i < 360; i += this.getAngularSeparation()*(damage+1)) {
			GL11.glTranslated(0, vo, 0);
			GL11.glRotatef(i+phi, 0, 0, 1);
			GL11.glTranslated(0, -vo, 0);
			GL11.glRotatef(-this.getBladeTwist(), 0, 1, 0);
			blade.render(f5);
			GL11.glRotatef(this.getBladeTwist(), 0, 1, 0);
			GL11.glTranslated(0, vo, 0);
			GL11.glRotatef(-i-phi, 0, 0, 1);
			GL11.glTranslated(0, -vo, 0);
		}
	}

	public int getBladeLength() {
		switch(stage) {
		case 0:
			return 16;
		case 1:
			return 24;
		case 2:
			return 28;
		case 3:
			return 33;
		case 4:
			return 40;
		}
		return 4;
	}

	public int getAngularSeparation() {
		switch(stage) {
		case 0:
			return 8;
		case 1:
			return 5;
		case 2:
			return 8;
		case 3:
			return 8;
		case 4:
			return 8;
		}
		return 10;
	}

	public int getBladeTwist() {
		switch(stage) {
		case 0:
			return 10;
		case 1:
			return 15;
		case 2:
			return 20;
		case 3:
			return 30;
		case 4:
			return 45;
		}
		return 10;
	}

	public int getBladeWidth() {
		switch(stage) {
		case 0:
			return 2;
		case 1:
			return 2;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		}
		return 2;
	}

	public double getScaleFactor() {
		if (stage < 1)
			return 1.3;
		else
			return 1.1;
	}

}
