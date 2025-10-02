package me.marek2810.persoLib.v1_21_R3.interaction;

import me.marek2810.persoLib.hologram.Hologram;
import me.marek2810.persoLib.interaction.InteractionAction;
import me.marek2810.persoLib.interaction.PersoInteraction;
import me.marek2810.persoLib.v1_21_R3.entity.EntityDataAdapter;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class PersoInteraction_V1_21_R3 extends PersoInteraction {

    private Interaction entity;

    public PersoInteraction_V1_21_R3(Hologram hologram, @NotNull InteractionAction action) {
        super(hologram, action);
    }

    @Override
    public void create() {
        if (!getLocation().isWorldLoaded())
            return;

        this.entity = new Interaction(EntityType.INTERACTION, getLevel());

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
        Interaction entity = getEntity();
        if (entity == null)
            return;

        if (!target.isWorldLoaded())
            return;

        Packet<?> teleportPacket = new ClientboundTeleportEntityPacket(entity.getId(), PositionMoveRotation.of(entity), Set.of(), entity.onGround());
        Bukkit.getOnlinePlayers().forEach(player -> getConnection(player).sendPacket(teleportPacket));
    }

    @Override
    public void showTo(Player player) {
        if (getEntity() == null)
            create();

        Interaction entity = getEntity();
        Location location = getLocation();

        Packet<?> addPacket = new ClientboundAddEntityPacket(
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
        getConnection(player).sendPacket(addPacket);

        update(player);
    }

    @Override
    public void hideFrom(Player player) {
        if (getEntity() == null)
            return;

        Packet<?> removePacket = new ClientboundRemoveEntitiesPacket(getEntity().getId());
        getConnection(player).sendPacket(removePacket);
    }

    @Override
    public void update(Player player) {
        if (getEntity() == null)
            return;

        List<SynchedEntityData.DataValue<?>> data = EntityDataAdapter.processSettings(this.getSettings());

        Packet<?> dataPacket = new ClientboundSetEntityDataPacket(getEntity().getId(), data);
        getConnection(player).sendPacket(dataPacket);
    }

    public Interaction getEntity() {
        return entity;
    }

    protected CraftWorld getCraftWorld() {
        return (CraftWorld) getLocation().getWorld();
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
