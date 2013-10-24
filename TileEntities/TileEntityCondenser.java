/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.TileEntities;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import Reika.ReactorCraft.Base.TileEntityTankedReactorMachine;
import Reika.ReactorCraft.Registry.ReactorTiles;
import Reika.RotaryCraft.Registry.MachineRegistry;

public class TileEntityCondenser extends TileEntityTankedReactorMachine {

	@Override
	public int getIndex() {
		return ReactorTiles.CONDENSER.ordinal();
	}

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {
		this.getSteam(world, x, y, z);
	}

	private void getSteam(World world, int x, int y, int z) {
		for (int i = 0; i < 6; i++) {
			ForgeDirection dir = dirs[i];
			int dx = x+dir.offsetX;
			int dy = y+dir.offsetY;
			int dz = z+dir.offsetZ;
			ReactorTiles rt = ReactorTiles.getTE(world, dx, dy, dz);
			if (rt == ReactorTiles.WATERLINE) {
				TileEntityWaterLine te = (TileEntityWaterLine)world.getBlockTileEntity(dx, dy, dz);

			}
		}
	}

	@Override
	public void animateWithTick(World world, int x, int y, int z) {

	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		int maxDrain = resource.amount;
		if (resource.getFluid().equals(FluidRegistry.WATER))
			return tank.drain(maxDrain, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canConnectToPipe(MachineRegistry m) {
		return false;
	}

	@Override
	public int getCapacity() {
		return 3000;
	}

	@Override
	public boolean canReceiveFrom(ForgeDirection from) {
		return false;
	}

	@Override
	public Fluid getInputFluid() {
		return null;
	}

}
