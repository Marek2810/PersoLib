package me.marek2810.persoLib.nms_v1_21_R3.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramLineType;
import me.marek2810.persoLib.hologram.line.HologramLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import me.marek2810.persoLib.nms_v1_21_R3.entity.EntityDataAdapter;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class HologramLine_v1_23_R3<T> implements HologramLine<T> {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Global.TRANSLATION, new Vector3f(0, 0, 0)),
            Map.entry(HologramSetting.Global.SCALE, new Vector3f(1, 1, 1)),
//            Map.entry(HologramSetting.BILLBOARD, Display.Billboard.FIXED), - MC DEFAULT
            Map.entry(HologramSetting.Global.BILLBOARD, org.bukkit.entity.Display.Billboard.CENTER),
            Map.entry(HologramSetting.Global.BRIGHTNESS, -1),
//            Map.entry(HologramSetting.Global.VIEW_RANGE, 1f), - MC DEFUALT
            Map.entry(HologramSetting.Global.VIEW_RANGE, 0.5f),
            Map.entry(HologramSetting.Global.SHADOW_RADIUS, 0f),
            Map.entry(HologramSetting.Global.SHADOW_STRANGE, 0f),
            Map.entry(HologramSetting.Global.WIDTH, 0f),
            Map.entry(HologramSetting.Global.HEIGHT, 0f),
            Map.entry(HologramSetting.Global.GLOW_COLOR, -1)
    );

    protected final HologramLineType type;
    private final Map<EntitySetting<?>, Object> settings;

    protected Display display;
    protected Location location;

    protected HologramLine_v1_23_R3(HologramLineType type, Location location, Map<EntitySetting<?>, Object> defaultSettings) {
        this.type = type;
        this.location = location;
        this.settings = new HashMap<>();
        this.settings.putAll(DEFAULT_SETTINGS);
        this.settings.putAll(defaultSettings);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <S> S getSetting(EntitySetting<S> setting) {
        Object value = settings.get(setting);
        if (value == null) throw new IllegalArgumentException("Invalid setting: " + setting);
        return (S) value;
    }

    @Override
    public <S> void setSetting(EntitySetting<S> setting, S value) {
        if (settings.replace(setting, value) == null)
            throw new IllegalArgumentException("Invalid setting: " + setting);
        //TODO optimize, maybe timer? send update only ever X seconds
        Bukkit.getOnlinePlayers().forEach(this::update);
    }

    public Map<EntitySetting<?>, Object> getSettings() {
        return Map.copyOf(this.settings);
    }

    @Override
    public void teleport(Location target) {
        net.minecraft.world.entity.Display entity = getEntity();
        if (entity == null)
            return;

        if (!target.isWorldLoaded())
            return;

        Packet<?> teleportDisplayPacket = new ClientboundTeleportEntityPacket(entity.getId(), PositionMoveRotation.of(entity), Set.of(), entity.onGround());
        Bukkit.getOnlinePlayers().forEach(player -> getConnection(player).sendPacket(teleportDisplayPacket));
    }

    @Override
    public void showTo(Player player) {
        Display entity = getEntity();
        Packet<?> addDisplayPacket = new ClientboundAddEntityPacket(
                entity.getId(),
                entity.getUUID(),
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                location.getPitch(),
                location.getYaw(),
                entity.getType(),
                0,
                new Vec3(0, 0, 0),
                entity.getYHeadRot()
        );
        getConnection(player).sendPacket(addDisplayPacket);

        update(player);
    }

    @Override
    public void hideFrom(Player player) {
        Packet<?> removeDisplayPacket = new ClientboundRemoveEntitiesPacket(getEntity().getId());
        getConnection(player).sendPacket(removeDisplayPacket);
    }

    //https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Display
    @Override
    public void update(Player player) {
        List<SynchedEntityData.DataValue<?>> data = EntityDataAdapter.processSettings(getSettings());

        Packet<?> dataPacket = new ClientboundSetEntityDataPacket(getEntity().getId(), data);
        getConnection(player).sendPacket(dataPacket);
    }

    private Display getEntity() {
        return this.display;
    }

    @Override
    public int getDisplayEntityId() {
        return this.display.getId();
    }

    @Override
    public Location getLocation() {
        return this.location;
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

}
