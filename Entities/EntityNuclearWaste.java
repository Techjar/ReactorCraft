/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.Entities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.MathSci.ReikaMathLibrary;
import Reika.ReactorCraft.Auxiliary.RadiationEffects;

public class EntityNuclearWaste extends EntityItem {

	public static final int RANGE = 6;
	private int timer = 0;

	public EntityNuclearWaste(World par1World) {
		super(par1World);
	}

	public EntityNuclearWaste(World world, double x, double y, double z, ItemStack is)
	{
		super(world, x, y, z, is);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		age = 0;
		this.applyRadiation();
		if (posY < 0) {
			//motionY = 0;
			//posY = 0;
			if (!worldObj.isRemote)
				velocityChanged = true;
			motionY = Math.abs(motionY);
			posY = Math.max(posY, 0);
		}
		timer++;
	}

	@Override
	public boolean isEntityInvulnerable()
	{
		return true;
	}

	private void applyRadiation() {
		World world = worldObj;
		double x = posX;
		double y = posY;
		double z = posZ;
		AxisAlignedBB box = AxisAlignedBB.getAABBPool().getAABB(x, y, z, x, y, z).expand(RANGE, RANGE, RANGE);
		List<EntityLivingBase> inbox = world.getEntitiesWithinAABB(EntityLivingBase.class, box);
		for (int i = 0; i < inbox.size(); i++) {
			EntityLivingBase e = inbox.get(i);
			double dd = ReikaMathLibrary.py3d(e.posX-x, e.posY-y, e.posZ-z);
			if (dd <= RANGE) {
				if (!RadiationEffects.hasHazmatSuit(e))
					RadiationEffects.applyEffects(e);
			}
		}

		int ix = MathHelper.floor_double(x);
		int iy = MathHelper.floor_double(y);
		int iz = MathHelper.floor_double(z);

		//Contaminate the area slightly every 10 min left in the world
		if (timer%12000 == 0 && timer >= 18000) {
			RadiationEffects.contaminateArea(world, ix, iy, iz, RANGE*4, 2);
		}
	}

}
