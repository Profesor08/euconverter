package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.EUConverter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LuVConverterBlockEntity extends ConverterBlockEntity
{
    public LuVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 3, 32768, 80000000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.LuV_CONVERTER_BLOCK_ENTITY.get();
    }
}
