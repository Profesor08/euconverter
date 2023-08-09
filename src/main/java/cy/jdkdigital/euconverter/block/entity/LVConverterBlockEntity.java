package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.Config;
import cy.jdkdigital.euconverter.EUConverter;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.network.buffer.NetworkInfo;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public class LVConverterBlockEntity extends ConverterBlockEntity
{
    public LVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 1, 32, 40000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.LV_CONVERTER_BLOCK_ENTITY.get();
    }
}
