package net.underplayer97.ResonantEnemies.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.underplayer97.ResonantEnemies.blocks.entity.EndPortalSlabEntityRenderer;
import net.underplayer97.ResonantEnemies.mixin.RenderLayersOfInvoker;

@Environment(EnvType.CLIENT)
public class ModRenderLayers extends RenderLayer {


    public ModRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static final RenderLayer END_PORTAL_SLAB;

    public static RenderLayer getPortalSlab() {
        return END_PORTAL_SLAB;
    }

    static {

        END_PORTAL_SLAB = RenderLayersOfInvoker.of("END_PORTAL_SLAB", VertexFormats.POSITION, VertexFormat.DrawMode.QUADS,
                256, false, false, MultiPhaseParameters.builder().shader(RenderPhase.END_PORTAL_SHADER).texture(Textures.create()
                        .add(EndPortalSlabEntityRenderer.SKY_TEXTURE, false, false).add(EndPortalSlabEntityRenderer.PORTAL_TEXTURE,
                                true, false).build()).build(false));

    }


}
