package net.underplayer97.ResonantEnemies.item.custom;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.underplayer97.ResonantEnemies.easing.Easing;
import net.underplayer97.ResonantEnemies.network.screenshake.PositionedScreenshakePacket;

public class ScreenshakeTestItem extends Item {
    public ScreenshakeTestItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld() instanceof ServerWorld serverWorld) {
            PlayerEntity user = context.getPlayer();
            serverWorld.getPlayers(players -> players.getWorld().isChunkLoaded(new ChunkPos(user.getBlockPos()).x, new ChunkPos(user.getBlockPos()).z)).forEach
                    (players -> {
                        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                        new PositionedScreenshakePacket(70, Vec3d.ofCenter(context.getBlockPos()), 20f, 0.3f, 25f,
                                Easing.CIRC_IN).setIntensity(0f, 1f, 0f).setEasing(Easing.CIRC_OUT, Easing.CIRC_IN).write(buf);
                        ServerPlayNetworking.send(players, PositionedScreenshakePacket.ID, buf);
                    });
        }
        return super.useOnBlock(context);
    }


}
