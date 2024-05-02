package cy.jdkdigital.euconverter.block.entity;

import org.jetbrains.annotations.NotNull;

import cy.jdkdigital.euconverter.Config;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.network.buffer.NetworkInfo;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class ConverterBlockEntity extends BaseEnergyStorageTileEntity
{
    private EnergyStorage storage;
    private LazyOptional<IEnergyStorage> energyHandler;
    @NetworkInfo
    public int production;

    public ConverterBlockEntity(BlockPos pPos, BlockState pBlockState, int tier, int output, int maxEnergy) {
        super(pPos, pBlockState, tier, output, maxEnergy);
        this.production = output;
        this.storage = new EnergyStorage(maxEnergy * 4);
        energyHandler = LazyOptional.of(() -> storage);
    }

    @Override
    public void onTick() {
        super.onTick();

        if (this.gainEnergy()) {
            this.updateGuiField("energy");
        }
        this.setActive(this.energy > 0);
    }

    public boolean gainEnergy() {
        if (this.needsEnergy()) {
            int possibleAddedPower = Math.min(this.production, this.storage.getEnergyStored() / Config.SERVER.conversionRate.get());
            int addedPower = Math.min(this.maxEnergy - this.energy, possibleAddedPower);
            this.energy = this.energy + addedPower;
            this.storage.extractEnergy(addedPower * Config.SERVER.conversionRate.get(), false);
            return true;
        }
        return false;
    }

    public boolean needsEnergy() {
        return this.energy < this.maxEnergy;
    }

    @Override
    public boolean canEmitEnergy(IEnergyAcceptor acceptor, Direction side) {
        return this.getFacing() != side;
    }

    @Override
    public boolean canAcceptEnergy(IEnergyEmitter emitter, Direction side) {
        return false;
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
        if (tag.contains("fe")) {
            storage.deserializeNBT(tag.get("fe"));
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("fe", storage.serializeNBT());
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

    @Override
    public void setFacing(Direction facing) {
        super.setFacing(facing);
        if (this.energyHandler != null) {
            this.energyHandler.invalidate();
            this.energyHandler = LazyOptional.of(() -> storage);
            this.addToTick();
        }
    }

    @Override
    public void onUnloaded(boolean chunk) {
        if (this.energyHandler != null) {
            this.energyHandler.invalidate();
            this.energyHandler = null;
        }
        super.onUnloaded(chunk);
    }
}
