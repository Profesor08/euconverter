package cy.jdkdigital.euconverter.block.entity;

import cy.jdkdigital.euconverter.EUConverter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IVConverterBlockEntity extends ConverterBlockEntity
{
    public IVConverterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState, 3, 8192, 40000000);
    }

    @Override
    public BlockEntityType<?> createType() {
        return EUConverter.IV_CONVERTER_BLOCK_ENTITY.get();
    }
}
