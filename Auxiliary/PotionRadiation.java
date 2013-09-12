/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.Auxiliary;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

public class PotionRadiation extends Potion {

	public PotionRadiation(int par1, boolean par2) {
		super(par1, par2, 0x111111);
	}

	@Override
	public void performEffect(EntityLiving e, int level) {
		Random r = new Random();
		int h = e.getHealth();
		int mh = e.getMaxHealth();
		float f = h/mh;
		if (f >= 0.5) {
			if (r.nextInt(h/4) == 0)
				e.attackEntityFrom(DamageSource.wither, 1);
		}
		else if  (f > 0.25) {
			if (r.nextInt(h/2) == 0)
				e.attackEntityFrom(DamageSource.wither, 1);
		}
		else if (h > 1) {
			if (r.nextInt(h) == 0)
				e.attackEntityFrom(DamageSource.wither, 1);
		}

		if (e instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer)e;
			FoodStats st = ep.getFoodStats();
			st.addExhaustion(0.25F);

			ep.capabilities.setPlayerWalkSpeed(0.075F);
		}

		if (!e.isPotionActive(Potion.confusion))
			e.addPotionEffect(new PotionEffect(Potion.confusion.id, 120, 0));

		e.addPotionEffect(new PotionEffect(Potion.poison.id, 20, 0));
	}

	@Override
	public boolean isReady(int time, int amp)
	{
		return time%20 == 0;
	}

}