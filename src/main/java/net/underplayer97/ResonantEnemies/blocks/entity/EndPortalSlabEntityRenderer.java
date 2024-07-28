package net.underplayer97.ResonantEnemies.blocks.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;
import net.underplayer97.ResonantEnemies.client.ModRenderLayers;

@Environment(EnvType.CLIENT)
public class EndPortalSlabEntityRenderer extends EndPortalBlockEntityRenderer<EndPortalSlabEntity> {

    public static final Identifier SKY_TEXTURE = new Identifier("textures/environment/end_sky.png");
    public static final Identifier PORTAL_TEXTURE = new Identifier("textures/entity/end_portal.png");

    public EndPortalSlabEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public void render(EndPortalSlabEntity blockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {

        super.render(blockEntity, f, matrixStack, vertexConsumerProvider, i, j);

    }

    /**

    private void renderSides(T entity, Matrix4f matrix, VertexConsumer vertexConsumer) {
        float f = this.getBottomYOffset();
        float g = this.getTopYOffset();
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(entity, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, f, f, 1.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, g, g, 1.0F, 1.0F, 1.0F, Direction.UP);

    }


    private void renderSide(T entity, Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3,
                            float z4, Direction side) {
        if(entity.shouldDrawSide(side)) {
            vertices.vertex(model, x1, y1, z1).next();
            vertices.vertex(model, x2, y1, z2).next();
            vertices.vertex(model, x2, y2, z3).next();
            vertices.vertex(model, x1, y2, z4).next();

        }
    }

     **/

    protected float getTopYOffset() {
        return 1.0F;
    }

    protected float getBottomYOffset() {
        return 0.0F;
    }

    protected RenderLayer getLayer() {

        return ModRenderLayers.getPortalSlab();
        //return RenderLayer.getEndPortal();
    }

    public int getRenderDistance() {
        return 256;
    }

}
