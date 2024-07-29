package net.underplayer97.ResonantEnemies.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.underplayer97.ResonantEnemies.blocks.entity.ResonantPortalBlockEntityRenderer;
import net.underplayer97.ResonantEnemies.mixin.RenderLayersOfInvoker;

@Environment(EnvType.CLIENT)
public class ModRenderLayers extends RenderLayer {


    public ModRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static final RenderLayer RESONANT_PORTAL_BLOCK;

    public static RenderLayer getPortalBlock() {
        return RESONANT_PORTAL_BLOCK;
    }

    static {

        RESONANT_PORTAL_BLOCK = RenderLayersOfInvoker.of("resonant_portal_block", VertexFormats.POSITION, VertexFormat.DrawMode.QUADS,
                256, false, false, MultiPhaseParameters.builder().shader(RenderPhase.END_PORTAL_SHADER).texture(Textures.create()
                        .add(ResonantPortalBlockEntityRenderer.SKY_TEXTURE, false, false).add(ResonantPortalBlockEntityRenderer.PORTAL_TEXTURE,
                                true, false).build()).build(false));

    }


}
