package net.underplayer97.ResonantEnemies.blocks.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.blocks.entity.EndPortalSlabEntity;
import net.underplayer97.ResonantEnemies.blocks.entity.ModBlockEntities;
import net.underplayer97.ResonantEnemies.client.ModRenderLayers;

import javax.annotation.Nullable;
import java.util.Random;

public class EndPortalSlab extends BlockWithEntity {

    public EndPortalSlab(Settings settings){
        super(settings);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EndPortalSlabEntity(pos, state);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + random.nextDouble();
        double e = (double)pos.getY() + 0.8;
        double f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
    }




}
