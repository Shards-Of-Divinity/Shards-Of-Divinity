package com.cursee.shards_of_divinity.common.entity.custom;

import com.cursee.shards_of_divinity.common.registry.ModEntityTypesForge;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SmoulderingAntiquatedLogEntity extends Entity implements TraceableEntity {

    private static final int DEFAULT_AGE_DELAY = 40;
    private static final EntityDataAccessor<Integer> DATA_AGE_ID = SynchedEntityData.defineId(SmoulderingAntiquatedLogEntity.class, EntityDataSerializers.INT);

    private @Nullable LivingEntity owner;

    public SmoulderingAntiquatedLogEntity(EntityType<? extends SmoulderingAntiquatedLogEntity> entityType, Level pLevel) {
        super(entityType, pLevel);
        this.blocksBuilding = true;
    }

    public SmoulderingAntiquatedLogEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        this(ModEntityTypesForge.SMOULDERING_ANTIQUATED_LOG_ENTITY.get(), level);

        this.setPos(x + 0.5, y+0.5, z+0.5);
        this.beginAgingWithDelay(DEFAULT_AGE_DELAY);

        this.xo = x;
        this.yo = y;
        this.zo = z;

        this.owner = livingEntity;
    }

    private void beginAgingWithDelay(int defaultAgeDelay) {
        this.entityData.set(DATA_AGE_ID, defaultAgeDelay);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_AGE_ID, DEFAULT_AGE_DELAY);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.beginAgingWithDelay(pCompound.getInt("Age"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("Age", this.entityData.get(DATA_AGE_ID));
    }

    @Override
    public @Nullable Entity getOwner() {
        return this.owner;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
