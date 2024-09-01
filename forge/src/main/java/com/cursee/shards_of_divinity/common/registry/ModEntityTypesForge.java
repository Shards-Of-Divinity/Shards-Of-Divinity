package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.common.entity.custom.SmoulderingAntiquatedLogEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypesForge {

    public static void register() {}

    public static final RegistryObject<EntityType<SmoulderingAntiquatedLogEntity>> SMOULDERING_ANTIQUATED_LOG_ENTITY =
            RegistryForge.registerEntityType("smouldering_antiquated_log_entity", () -> EntityType.Builder
                    .<SmoulderingAntiquatedLogEntity>of(SmoulderingAntiquatedLogEntity::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(1, 1)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build("smouldering_antiquated_log_entity"));
}
