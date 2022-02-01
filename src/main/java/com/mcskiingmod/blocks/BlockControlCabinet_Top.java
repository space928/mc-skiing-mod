package com.mcskiingmod.blocks;

import com.google.common.collect.Lists;
import com.mcskiingmod.Main;
import com.mcskiingmod.tileentity.TileEntityControlCabinet;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class BlockControlCabinet_Top extends BlockBase implements ITileEntityProvider, IPeripheralProvider {
	private static final AxisAlignedBB CABINET_BASE = new AxisAlignedBB(0, 0-1, 0, 1, 1.938-1, 0.875);
	private static final AxisAlignedBB LID = new AxisAlignedBB(0, 1.938-1, 0, 1, 2-1, 1);
	private static final AxisAlignedBB FOOT = new AxisAlignedBB(0, 0-1, 0.875, 1, 0.062-1, 1);
	private final TileEntityControlCabinet tileEntityControlCabinet;
	/**
	 * AxisAlignedBBs and methods getBoundingBox, collisionRayTrace, and collisionRayTrace generated using MrCrayfish's Model Creator <a href="https://mrcrayfish.com/tools?id=mc">https://mrcrayfish.com/tools?id=mc</a>
	 */
	private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(CABINET_BASE, LID, FOOT);
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0-1, 0, 1, 2-1, 1);


	public static final PropertyDirection FACING = PropertyDirection.create("facing");


	public BlockControlCabinet_Top(String name) {
		super(Material.PISTON, name);
		setHardness(3f);
		setResistance(5f);
		this.tileEntityControlCabinet = new TileEntityControlCabinet();

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

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return tileEntityControlCabinet;
	}

	// used by the renderer to control lighting and visibility of other blocks.
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	// used by the renderer to control lighting and visibility of other blocks, also by
	// (eg) wall or fence to control whether the fence joins itself to this block
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return true;
	}

	// render using a BakedModel (mbe30_inventory_basic.json --> mbe30_inventory_basic_model.json)
	// required because the default (super method) is INVISIBLE for BlockContainers.
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState iBlockState, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
		if (world.getTileEntity(blockPos) != null) {
			player.sendStatusMessage(new TextComponentString("EnergyStored: " + Objects.requireNonNull(world.getTileEntity(blockPos)).getCapability(CapabilityEnergy.ENERGY, null).getEnergyStored()), true);
		}
		return true;
	}

	/**
	 * Produce an peripheral implementation from a block location.
	 * @see dan200.computercraft.api.ComputerCraftAPI#registerPeripheralProvider(IPeripheralProvider)
	 * @return a peripheral, or null if there is not a peripheral here you'd like to handle.
	 */

	@Nullable
	@Override
	public IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
		TileEntity te = world.getTileEntity(pos.down());
		return te instanceof IPeripheral ? (IPeripheral)te : null;
	}


	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState()
				.withProperty(FACING, EnumFacing.byIndex(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
}
