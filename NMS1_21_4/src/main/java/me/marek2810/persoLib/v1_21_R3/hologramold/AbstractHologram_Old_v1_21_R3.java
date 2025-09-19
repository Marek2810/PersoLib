package me.marek2810.persoLib.v1_21_R3.hologramold;

import com.google.gson.Gson;
import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologramOld.AbstractHologramOld;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_21_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbstractHologram_Old_v1_21_R3 extends AbstractHologramOld {

    protected final EntityType<? extends Display> entityType;

    @Nullable
    protected Display entity;

    public AbstractHologram_Old_v1_21_R3(String id, EntityType<? extends Display> entityType, Location location) {
        super(id, location);
        this.entityType = entityType;
        create();
    }

    @Override
    public void create() {
        if (!location.isWorldLoaded())
            return;

        if (entityType.equals(EntityType.TEXT_DISPLAY)) {
            this.entity = new Display.TextDisplay(entityType, getLevel());
        } else if (entityType.equals(EntityType.ITEM_DISPLAY)) {
            this.entity = new Display.ItemDisplay(entityType, getLevel());
        } else if (entityType.equals(EntityType.BLOCK_DISPLAY)) {
            this.entity = new Display.BlockDisplay(entityType, getLevel());
        } else {
            throw new IllegalArgumentException("Unsupported display entity type: " + entityType);
        }

        this.entity.setPos(getLocation().getX(), getLocation().getY(), getLocation().getZ());
    }

    @Override
    public void teleport(Location target) {
        Display entity = getEntity();
        if (entity == null)
            return;

        if (!target.isWorldLoaded())
            return;
        setLocation(target);

        Packet<?> teleportDisplayPacket = new ClientboundTeleportEntityPacket(entity.getId(), PositionMoveRotation.of(entity), Set.of(), entity.onGround());
        Bukkit.getOnlinePlayers().forEach(player -> getConnection(player).sendPacket(teleportDisplayPacket));
    }

    @Override
    public void showTo(Player player) {
        if (getEntity() == null)
            create();

        Display entity = getEntity();

        Packet<?> addDisplayPacket = new ClientboundAddEntityPacket(
                entity.getId(),
                entity.getUUID(),
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                location.getPitch(),
                location.getYaw(),
                this.entityType,
                0,
                new Vec3(0, 0, 0),
                entity.getYHeadRot()
        );
        getConnection(player).sendPacket(addDisplayPacket);

        update(player);
    }

    @Override
    public void hideFrom(Player player) {
        if (getEntity() == null)
            return;

        Packet<?> removeDisplayPacket = new ClientboundRemoveEntitiesPacket(getEntity().getId());
        getConnection(player).sendPacket(removeDisplayPacket);
    }

    //https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Entity_metadata#Display
    @Override
    public void update(Player player) {
        if (getEntity() == null)
            return;

        List<SynchedEntityData.DataValue<?>> data = getDisplayData(player);

        Packet<?> dataPacket = new ClientboundSetEntityDataPacket(getEntity().getId(), data);
        getConnection(player).sendPacket(dataPacket);
    }

    protected List<SynchedEntityData.DataValue<?>> getDisplayData(Player player) {
        List<SynchedEntityData.DataValue<?>> displayData = new ArrayList<>();

        ServerPlayer handle = ((CraftPlayer) player).getHandle();


        for (Map.Entry<EntitySetting<?>, Object> entry : this.settings.entrySet()) {
            EntitySetting<?> setting = entry.getKey();
            Object value = entry.getValue();
            if (value == null)
                continue;

            if (value instanceof String text) {
                Gson gson = new Gson();
                String jasonString = gson.toJson(String.join("\n", text));
                value = Component.Serializer.fromJson(jasonString, handle.registryAccess());
            } else if (value instanceof TextDisplay.TextAlignment textAlignment) {
                value = getFormat(textAlignment);
            } else if (value instanceof ItemStack itemStack) {
                value = CraftItemStack.asNMSCopy(itemStack);
            } else if (value instanceof org.bukkit.entity.Display.Billboard billboard) {
                value = getBillboardByte(billboard);
            } else if (value instanceof org.bukkit.entity.Display.Brightness brightness) {
                value = getBrightnessInt(brightness);
            }

            EntityDataSerializer serializer;
            try {
                serializer = getEntityDataSerializer(setting);
            } catch (Exception e) {
                continue;
            }

            // vytvor accessor s konkrétnym typom
            EntityDataAccessor accessor = new EntityDataAccessor(setting.index(), serializer);

            displayData.add(SynchedEntityData.DataValue.create(accessor, value));
        }

        return displayData;
    }

    @NotNull
    private static EntityDataSerializer getEntityDataSerializer(EntitySetting<?> setting) {
        EntityDataSerializer<?> serializer;
        Class<?> type = setting.type();
        if (type == Float.class) serializer = EntityDataSerializers.FLOAT;
        else if (type == Integer.class) serializer = EntityDataSerializers.INT;
        else if (type == Byte.class) serializer = EntityDataSerializers.BYTE;
        else if (type == Vector3f.class) serializer = EntityDataSerializers.VECTOR3;
        else if (type == String.class) serializer = EntityDataSerializers.COMPONENT;
        else if (type == TextDisplay.TextAlignment.class) serializer = EntityDataSerializers.BYTE;
        else if (type == org.bukkit.entity.Display.Billboard.class) serializer = EntityDataSerializers.BYTE;
        else if (type == org.bukkit.entity.Display.Brightness.class) serializer = EntityDataSerializers.INT;
        else throw new IllegalArgumentException("No serializer for type: " + type);
        return serializer;
    }

    private byte getFormat(TextDisplay.TextAlignment textAlignment) {
        byte format = 0;
//        if (isHasShadow())
//            format = (byte) (format | 0x01);
//        if (isSeeThrough())
//            format = (byte) (format | 0x02);
//        if (isUseDefaultBackgroundColor())
//            format = (byte) (format | 0x04);
        format = switch (textAlignment) {
            case CENTER -> format;
            case LEFT -> (byte) (format | 0x08);
            case RIGHT -> (byte) (format | 0x10);
        };
        return format;
    }

    private byte getBillboardByte(org.bukkit.entity.Display.Billboard billboard) {
        return switch (billboard) {
            case VERTICAL -> 1;
            case HORIZONTAL -> 2;
            case CENTER -> 3;
            default -> 0;
        };
    }

    private int getBrightnessInt(org.bukkit.entity.Display.Brightness brightness) {
        if (brightness == null) return -1;
        return brightness.getBlockLight() << 4 | brightness.getSkyLight() << 20;
    }

    @Nullable
    public Display getEntity() {
        return entity;
    }

    protected CraftWorld getCraftWorld() {
        return (CraftWorld) location.getWorld();
    }

    protected ServerLevel getLevel() {
        return getCraftWorld().getHandle();
    }

    protected ServerGamePacketListenerImpl getConnection(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer handle = craftPlayer.getHandle();
        return handle.connection;
    }

    @Override
    public String getId() {
        return "";
    }

}
