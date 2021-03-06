/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft.Auxiliary.Lua;

import Reika.DragonAPI.ModInteract.Lua.LuaMethod;

public class ReactorLuaMethods {

	private static final LuaMethod getName = new LuaReactorGetName();
	private static final LuaMethod getTemp = new LuaReactorGetTemperature();
	private static final LuaMethod lowerRods = new LuaLowerControlRods();
	private static final LuaMethod raiseRods = new LuaRaiseControlRods();
	private static final LuaMethod checkFuel = new LuaReactorCheckFuel();
	private static final LuaMethod checkPebbles = new LuaReactorCheckPebbles();


}
