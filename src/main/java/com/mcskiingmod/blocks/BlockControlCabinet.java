package com.mcskiingmod.blocks;

import com.google.common.collect.Lists;
import com.mcskiingmod.Main;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockControlCabinet extends BlockBase {

	private static final AxisAlignedBB CABINET_BASE = new AxisAlignedBB(0, 0, 0, 1, 1.938, 0.875);
	private static final AxisAlignedBB LID = new AxisAlignedBB(0, 1.938, 0, 1, 2, 1);
	private static final AxisAlignedBB CABNET_HANDLE = new AxisAlignedBB(0.062, 0.75, 0.875, 0.125, 0.938, 0.906);
	private static final AxisAlignedBB CABNET_DISPLAY_PANEL = new AxisAlignedBB(0.062, 1.25, 0.875, 0.938, 1.75, 0.887);
	private static final AxisAlignedBB CABNET_DISPLAY_ESTOP = new AxisAlignedBB(0.125, 1.312, 0.875, 0.138, 1.325, 0.906);
	private static final AxisAlignedBB CABNET_DISPLAY_ESTOP2 = new AxisAlignedBB(0.109, 1.297, 0.906, 0.153, 1.341, 0.912);
	private static final AxisAlignedBB CABNET_DISPLAY_ESTOP3 = new AxisAlignedBB(0.094, 1.281, 0.887, 0.156, 1.344, 0.891);
	private static final AxisAlignedBB FOOT = new AxisAlignedBB(0, 0, 0.875, 1, 0.062, 1);
	/**
	 * AxisAlignedBBs and methods getBoundingBox, collisionRayTrace, and collisionRayTrace generated using MrCrayfish's Model Creator <a href="https://mrcrayfish.com/tools?id=mc">https://mrcrayfish.com/tools?id=mc</a>
	 */
	private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(CABINET_BASE, LID, CABNET_HANDLE, CABNET_DISPLAY_PANEL, CABNET_DISPLAY_ESTOP, CABNET_DISPLAY_ESTOP2, CABNET_DISPLAY_ESTOP3, FOOT);
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 2, 1);

	public BlockControlCabinet(String name) {
		super(Material.PISTON, name);
		setHardness(3f);
		setResistance(5f);
		super.setCreativeTab(Main.SKIING_MOD_TAB);

	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return BOUNDING_BOX;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
	{
		entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
		for (AxisAlignedBB box : COLLISION_BOXES)
		{
			if (entityBox.intersects(box))
				collidingBoxes.add(box.offset(pos));
		}
	}

	@Override
	@Nullable
	public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
	{
		double distanceSq;
		double distanceSqShortest = Double.POSITIVE_INFINITY;
		RayTraceResult resultClosest = null;
		RayTraceResult result;
		start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
		end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
		for (AxisAlignedBB box : COLLISION_BOXES)
		{
			result = box.calculateIntercept(start, end);
			if (result == null)
				continue;

			distanceSq = result.hitVec.squareDistanceTo(start);
			if (distanceSq < distanceSqShortest)
			{
				distanceSqShortest = distanceSq;
				resultClosest = result;
			}
		}
		return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.add(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
	}

}
