package cy.jdkdigital.euconverter.block;

import cy.jdkdigital.euconverter.Config;
import cy.jdkdigital.euconverter.block.entity.ConverterBlockEntity;
import ic2.core.block.base.blocks.BaseActivityBlock;
import ic2.core.block.machines.BaseMachineBlock;
import ic2.core.platform.rendering.features.ITextureProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Converter extends BaseActivityBlock<ConverterBlockEntity>
{
    private final BlockEntityType.BlockEntitySupplier<ConverterBlockEntity> pFactory;

    public Converter(BlockEntityType.BlockEntitySupplier<ConverterBlockEntity> pFactory, ITextureProvider textureProvide) {
        super("converter", BaseMachineBlock.BASE_MACHINE, textureProvide, null);
        this.pFactory = pFactory;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return this.pFactory.create(pPos, pState);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.translatable("euconverter.fe_to_eu_conversion", Config.SERVER.conversionRate.get()).withStyle(ChatFormatting.DARK_GRAY));
    }
}
