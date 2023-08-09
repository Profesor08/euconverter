package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.EUConverter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MVConverterBlockEntity extends ConverterBlockEntity
{
    public MVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 2, 128, 160000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.MV_CONVERTER_BLOCK_ENTITY.get();
    }
}
