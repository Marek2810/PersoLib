package me.marek2810.persoLib.v1_21_R3.hologramold;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EntityAdapter_v1_21_R3 {

    @Nullable
    public EntityType<?> getVanillaType (org.bukkit.entity.EntityType type) {
         NamespacedKey namespacedKey = type.getKey();
         Optional<Holder.Reference<EntityType<?>>> vanillaType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(namespacedKey.getKey()));
        return vanillaType.<EntityType<?>>map(Holder.Reference::value).orElse(null);
    }

}
