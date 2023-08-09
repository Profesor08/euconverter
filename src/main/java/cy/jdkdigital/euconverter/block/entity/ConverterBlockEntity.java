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

public abstract class ConverterBlockEntity extends BaseEnergyStorageTileEntity
{
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new EnergyStorage(100000));
    @NetworkInfo
    public int production;

    public ConverterBlockEntity(BlockPos pPos, BlockState pBlockState, int tier, int output, int maxEnergy) {
        super(pPos, pBlockState, tier, output, maxEnergy);
        this.production = output;
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.LV_CONVERTER_BLOCK_ENTITY.get();
    }

    @Override
    public void onTick() {
        super.onTick();

        boolean active = this.gainEnergy();
        if (active) {
            this.updateGuiField("energy");
        }

        this.setActive(active);
    }

    public boolean gainEnergy() {
        if (this.needsEnergy()) {
            this.energyHandler.ifPresent(h -> {
                int possibleAddedPower = Math.min(this.production, h.getEnergyStored() / Config.SERVER.conversionRate.get());
                int addedPower = Math.min(this.maxEnergy - this.energy, possibleAddedPower);
                this.energy = this.energy + addedPower;
                h.extractEnergy(addedPower * Config.SERVER.conversionRate.get(), false);
            });
            return true;
        } else {
            return false;
        }
    }

    public boolean needsEnergy() {
        return this.energy < this.maxEnergy;
    }

    @Override
    public boolean canEmitEnergy(IEnergyAcceptor acceptor, Direction side) {
        return this.isActive() && (this.getFacing() != side);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY && (side == null || side.equals(getBlockState().getValue(DirectionalBlock.FACING)))) {
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("energy")) {
            getCapability(ForgeCapabilities.ENERGY).ifPresent(handler -> {
                ((EnergyStorage) handler).deserializeNBT(tag.get("energy"));
            });
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        getCapability(ForgeCapabilities.ENERGY).ifPresent(handler -> {
            tag.put("energy", ((EnergyStorage) handler).serializeNBT());
        });
    }

    @Override
    public boolean isHarvestWrenchRequired(Player player) {
        return false;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0D;
    }

    @Override
    public int getGuiOffset() {
        return 0;
    }
}
