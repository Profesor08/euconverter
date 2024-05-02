package cy.jdkdigital.euconverter;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import cy.jdkdigital.euconverter.block.Converter;
import cy.jdkdigital.euconverter.block.entity.LuVConverterBlockEntity;
import cy.jdkdigital.euconverter.block.entity.IVConverterBlockEntity;
import cy.jdkdigital.euconverter.block.entity.EVConverterBlockEntity;
import cy.jdkdigital.euconverter.block.entity.HVConverterBlockEntity;
import cy.jdkdigital.euconverter.block.entity.LVConverterBlockEntity;
import cy.jdkdigital.euconverter.block.entity.MVConverterBlockEntity;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EUConverter.MODID)
public class EUConverter
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "euconverter";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<Block> LV_CONVERTER = BLOCKS.register("lv_converter", () -> new Converter(LVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/lv")));
    public static final RegistryObject<Block> MV_CONVERTER = BLOCKS.register("mv_converter", () -> new Converter(MVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/mv")));
    public static final RegistryObject<Block> HV_CONVERTER = BLOCKS.register("hv_converter", () -> new Converter(HVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/hv")).setOverrideRarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> EV_CONVERTER = BLOCKS.register("ev_converter", () -> new Converter(EVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/ev")).setOverrideRarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> IV_CONVERTER = BLOCKS.register("iv_converter", () -> new Converter(IVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/iv")).setOverrideRarity(Rarity.RARE));
    public static final RegistryObject<Block> LuV_CONVERTER = BLOCKS.register("luv_converter", () -> new Converter(LuVConverterBlockEntity::new, new NoStateProvider(EUConverter.MODID, "converter/luv")).setOverrideRarity(Rarity.EPIC));
    public static final RegistryObject<Item> LV_CONVERTER_ITEM = ITEMS.register("lv_converter", () -> new BlockItem(LV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MV_CONVERTER_ITEM = ITEMS.register("mv_converter", () -> new BlockItem(MV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> HV_CONVERTER_ITEM = ITEMS.register("hv_converter", () -> new BlockItem(HV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> EV_CONVERTER_ITEM = ITEMS.register("ev_converter", () -> new BlockItem(EV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> IV_CONVERTER_ITEM = ITEMS.register("iv_converter", () -> new BlockItem(IV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> LuV_CONVERTER_ITEM = ITEMS.register("luv_converter", () -> new BlockItem(LuV_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.EPIC)));
    public static final RegistryObject<BlockEntityType<LVConverterBlockEntity>> LV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("lv_converter",
            () -> BlockEntityType.Builder.of(LVConverterBlockEntity::new, LV_CONVERTER.get()).build(null)
    );
    public static final RegistryObject<BlockEntityType<MVConverterBlockEntity>> MV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("mv_converter",
            () -> BlockEntityType.Builder.of(MVConverterBlockEntity::new, MV_CONVERTER.get()).build(null)
    );
    public static final RegistryObject<BlockEntityType<HVConverterBlockEntity>> HV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("hv_converter",
            () -> BlockEntityType.Builder.of(HVConverterBlockEntity::new, HV_CONVERTER.get()).build(null)
    );
    public static final RegistryObject<BlockEntityType<EVConverterBlockEntity>> EV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("ev_converter",
            () -> BlockEntityType.Builder.of(EVConverterBlockEntity::new, EV_CONVERTER.get()).build(null)
    );
    public static final RegistryObject<BlockEntityType<IVConverterBlockEntity>> IV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("iv_converter",
            () -> BlockEntityType.Builder.of(IVConverterBlockEntity::new, IV_CONVERTER.get()).build(null)
    );
    public static final RegistryObject<BlockEntityType<LuVConverterBlockEntity>> LuV_CONVERTER_BLOCK_ENTITY = BLOCK_ENTITIES.register("luv_converter",
            () -> BlockEntityType.Builder.of(LuVConverterBlockEntity::new, LuV_CONVERTER.get()).build(null)
    );

    public EUConverter()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }
}
