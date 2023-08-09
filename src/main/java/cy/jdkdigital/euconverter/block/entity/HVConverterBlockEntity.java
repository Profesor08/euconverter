package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.EUConverter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HVConverterBlockEntity extends ConverterBlockEntity
{
    public HVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 3, 512, 800000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.HV_CONVERTER_BLOCK_ENTITY.get();
    }
}
