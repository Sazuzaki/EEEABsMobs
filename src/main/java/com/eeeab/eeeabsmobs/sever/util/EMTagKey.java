package com.eeeab.eeeabsmobs.sever.util;

import com.eeeab.eeeabsmobs.EEEABMobs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public class EMTagKey {
    public static final TagKey<Structure> EYE_OF_GULING = registerTagKey(Registries.STRUCTURE, "eye_of_guling");
    public static final TagKey<Structure> EYE_OF_BLOODY_ALTAR = registerTagKey(Registries.STRUCTURE, "eye_of_bloody_altar");
    public static final TagKey<Biome> GULING_BIOMES = registerTagKey(Registries.BIOME, "guling_biomes");
    public static final TagKey<Biome> BLOODY_ALTAR_BIOMES = registerTagKey(Registries.BIOME, "bloody_altar_biomes");
    public static final TagKey<DamageType> GENERAL_UNRESISTANT_TO = registerTagKey(Registries.DAMAGE_TYPE, "general_unresistant_to");
    public static final TagKey<DamageType> MAGIC_UNRESISTANT_TO = registerTagKey(Registries.DAMAGE_TYPE, "magic_unresistant_to");
    public static final TagKey<EntityType<?>> TRAP_WHITELIST = registerTagKey(Registries.ENTITY_TYPE, "trap_whitelist");
    public static final TagKey<EntityType<?>> RESISTS_FORCED_CHANGE_TARGET = registerTagKey(Registries.ENTITY_TYPE, "resists_forced_change_target");
    public static final TagKey<EntityType<?>> IMMORTAL_ARMY = registerTagKey(Registries.ENTITY_TYPE, "immortal_army");

    private static <T> TagKey<T> registerTagKey(ResourceKey<Registry<T>> registry, String key) {
        return TagKey.create(registry, new ResourceLocation(EEEABMobs.MOD_ID, key));
    }

    //Other Mod
    private static final String ICE_AND_FIRE = "iceandfire";
    private static final String ALEX_S_CAVES = "alexscaves";
    public static final TagKey<EntityType<?>> BLINDED = registerOtherModTagKey(Registries.ENTITY_TYPE, ICE_AND_FIRE, "blinded");
    public static final TagKey<EntityType<?>> IMMUNE_TO_GORGON_STONE = registerOtherModTagKey(Registries.ENTITY_TYPE, ICE_AND_FIRE, "immune_to_gorgon_stone");
    public static final TagKey<EntityType<?>> RESISTS_RADIATION = registerOtherModTagKey(Registries.ENTITY_TYPE, ALEX_S_CAVES, "resists_radiation");
    public static final TagKey<EntityType<?>> RESISTS_TOTEM_OF_POSSESSION = registerOtherModTagKey(Registries.ENTITY_TYPE, ALEX_S_CAVES, "resists_totem_of_possession");
    public static final TagKey<EntityType<?>> RESISTS_TREMORSAURUS_ROAR = registerOtherModTagKey(Registries.ENTITY_TYPE, ALEX_S_CAVES, "resists_tremorsaurus_roar");

    private static <T> TagKey<T> registerOtherModTagKey(ResourceKey<Registry<T>> registry, String modId, String key) {
        return TagKey.create(registry, new ResourceLocation(modId, key));
    }
}
