package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.EUConverter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EVConverterBlockEntity extends ConverterBlockEntity
{
    public EVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 3, 2048, 4000000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.EV_CONVERTER_BLOCK_ENTITY.get();
    }
}
