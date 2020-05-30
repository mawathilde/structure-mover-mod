package fr.mawathilde.structuremover.init;

import fr.mawathilde.structuremover.entity.EntityMovingBlock;
import fr.mawathilde.structuremover.entity.RenderMovingBlock;
import fr.mawathilde.structuremover.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public final class EntityInit {

    @GameRegistry.ObjectHolder("moving_block")
    public static EntityEntry MOVING_BLOCK = null;

    public static void register(IForgeRegistry<EntityEntry> registry) {
        registry.register(EntityEntryBuilder.create().id(new ResourceLocation(Reference.MOD_ID, "moving_block"), 0).name("moving_block").entity(EntityMovingBlock.class).tracker(60, 1, true).build());
    }

    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMovingBlock.class, new RenderMovingBlock(Minecraft.getMinecraft().getRenderManager()));
    }

}
