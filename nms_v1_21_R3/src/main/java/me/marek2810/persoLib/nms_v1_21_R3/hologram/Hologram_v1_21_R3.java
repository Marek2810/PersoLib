package me.marek2810.persoLib.nms_v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.AbstractHologram;
import me.marek2810.persoLib.hologram.line.HologramLine;
import me.marek2810.persoLib.interaction.PersoInteraction;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class Hologram_v1_21_R3 extends AbstractHologram {

    @Nullable
    private Display entity;

    public Hologram_v1_21_R3(String id, Location location, HologramLine hologramLine) {
        super(id, location, hologramLine);
    }

    @Override
    public void create() {
        if (!location.isWorldLoaded())
            return;

        switch (hologramLine.getType()) {
            case TEXT ->
                    this.entity = new Display.TextDisplay(EntityType.TEXT_DISPLAY, getLevel());
            case ITEM ->
                    this.entity = new Display.ItemDisplay(EntityType.ITEM_DISPLAY, getLevel());
            case BLOCK ->
                    this.entity = new Display.BlockDisplay(EntityType.BLOCK_DISPLAY, getLevel());
        }

        this.entity.setPos(getLocation().getX(), getLocation().getY(), getLocation().getZ());
    }

    @Override
    public int getEntityId() {
        if (entity == null)
            return -1;
        return entity.getId();
    }

    @Override
    public void teleport(Location target) {
        net.minecraft.world.entity.Display entity = getEntity();
        if (entity == null)
            return;

        if (!target.isWorldLoaded())
            return;
        setLocation(target);

        Packet<?> teleportDisplayPacket = new ClientboundTeleportEntityPacket(entity.getId(), PositionMoveRotation.of(entity), Set.of(), entity.onGround());
        Bukkit.getOnlinePlayers().forEach(player -> getConnection(player).sendPacket(teleportDisplayPacket));

        if (persoInteraction != null) {
            persoInteraction.teleport(target);
        }
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
                entity.getType(),
                0,
                new Vec3(0, 0, 0),
                entity.getYHeadRot()
        );
        getConnection(player).sendPacket(addDisplayPacket);

        update(player);
        if (persoInteraction != null) {
            persoInteraction.showTo(player);
        }
    }

    @Override
    public void hideFrom(Player player) {
        if (getEntity() == null)
            return;

        Packet<?> removeDisplayPacket = new ClientboundRemoveEntitiesPacket(getEntity().getId());
        getConnection(player).sendPacket(removeDisplayPacket);

        if (persoInteraction != null) {
            persoInteraction.hideFrom(player);
        }
    }

    //https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Entity_metadata#Display
    @Override
    public void update(Player player) {
        if (getEntity() == null)
            return;

        if (hologramLine == null)
            return;

        List<SynchedEntityData.DataValue<?>> data = EntityDataAdapter.processSettings(hologramLine.getSettings());

        Packet<?> dataPacket = new ClientboundSetEntityDataPacket(getEntity().getId(), data);
        getConnection(player).sendPacket(dataPacket);

        if (persoInteraction != null) {
            persoInteraction.update(player);
        }
    }

    @Override
    public void removeInteraction() {
        PersoInteraction interaction = this.getInteraction();
        if (interaction == null)
            return;

        Bukkit.getOnlinePlayers().forEach(interaction::hideFrom);
        this.persoInteraction = null;
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

}
