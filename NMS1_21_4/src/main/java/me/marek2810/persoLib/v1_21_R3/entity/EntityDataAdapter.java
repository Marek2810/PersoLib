package me.marek2810.persoLib.v1_21_R3.entity;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_21_R3.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_21_R3.inventory.CraftItemStack;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class EntityDataAdapter {

    private static final Map<Class<?>, EntityDataSerializer<?>> SERIALIZERS = Map.ofEntries(
            Map.entry(Vector3f.class, EntityDataSerializers.VECTOR3),
            Map.entry(Byte.class, EntityDataSerializers.BYTE),
            Map.entry(Float.class, EntityDataSerializers.FLOAT),
            Map.entry(Integer.class, EntityDataSerializers.INT),
            Map.entry(net.minecraft.world.level.block.state.BlockState.class, EntityDataSerializers.BLOCK_STATE),
            Map.entry(ItemStack.class, EntityDataSerializers.ITEM_STACK),
            Map.entry(MutableComponent.class, EntityDataSerializers.COMPONENT)
    );

    private EntityDataAdapter() {
        throw new UnsupportedOperationException("Utility class");
    }

    @SuppressWarnings("unchecked")
    private static EntityDataSerializer<Object> getEntityDataSerializer(Class<?> clazz) {
        EntityDataSerializer<?> serializer = SERIALIZERS.get(clazz);
        if (serializer == null) {
            throw new IllegalArgumentException("No serializer for type: " + clazz);
        }
        return (EntityDataSerializer<Object>) serializer;
    }

    public static SynchedEntityData.DataValue<?> get(EntitySetting<?> setting, Object value) {
        if (setting == HologramSetting.Text.TEXT && value instanceof String text) {
            value = Component.literal(text);
        } else if (value instanceof Display.Billboard billboard) {
            value = getBillboardByte(billboard);
        } else if (value instanceof Display.Brightness brightness) {
            value = getBrightnessInt(brightness);
        } else if (value instanceof org.bukkit.inventory.ItemStack item) {
            value = CraftItemStack.asNMSCopy(item);
        } else if (value instanceof ItemDisplay.ItemDisplayTransform itemDisplayTransform) {
            value = getItemDisplayContext(itemDisplayTransform).getId();
        } else if (value instanceof BlockState blockState) {
            BlockData bukkitData = blockState.getBlockData();
            CraftBlockData craftData = (CraftBlockData) bukkitData;
            value = craftData.getState();
        }

        EntityDataSerializer<Object> serializer = getEntityDataSerializer(value.getClass());
        EntityDataAccessor accessor = new EntityDataAccessor<>(setting.index(), serializer);

        return SynchedEntityData.DataValue.create(accessor, value);
    }

    public static List<SynchedEntityData.DataValue<?>> processSettings(Map<EntitySetting<?>, Object> settings) {
        return settings.entrySet().stream()
                .map(entry -> get(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static byte getBillboardByte(Display.Billboard billboard) {
        return switch (billboard) {
            case FIXED -> 0;
            case VERTICAL -> 1;
            case HORIZONTAL -> 2;
            case CENTER -> 3;
        };
    }

    private static int getBrightnessInt(Display.Brightness brightness) {
        if (brightness == null) return -1;
        return brightness.getBlockLight() << 4 | brightness.getSkyLight() << 20;
    }

    private static ItemDisplayContext getItemDisplayContext(ItemDisplay.ItemDisplayTransform transformation) {
        return switch (transformation) {
            case FIRSTPERSON_LEFTHAND -> ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
            case FIRSTPERSON_RIGHTHAND -> ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
            case THIRDPERSON_LEFTHAND -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
            case THIRDPERSON_RIGHTHAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            default -> ItemDisplayContext.valueOf(transformation.name());
        };
    }

}
