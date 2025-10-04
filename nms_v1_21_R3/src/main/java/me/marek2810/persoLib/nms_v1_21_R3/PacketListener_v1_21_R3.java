package me.marek2810.persoLib.nms_v1_21_R3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import me.marek2810.persoLib.event.event.HologramInteractEvent;
import me.marek2810.persoLib.event.listener.PacketListener;
import me.marek2810.persoLib.hologram.Hologram;
import me.marek2810.persoLib.hologram.HologramManager;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class PacketListener_v1_21_R3 implements PacketListener {

    private final JavaPlugin plugin;
    private final HologramManager hologramManager;

    public PacketListener_v1_21_R3(JavaPlugin plugin, HologramManager hologramManager) {
        this.plugin = plugin;
        this.hologramManager = hologramManager;
    }

    @Override
    public void injectPacketListener(Player player) {
        try {

            ServerGamePacketListenerImpl serverConnection = ((CraftPlayer) player).getHandle().connection;

            Field connectionField = ServerCommonPacketListenerImpl.class.getDeclaredField("connection");
            connectionField.setAccessible(true);

            Connection connection = (Connection) connectionField.get(serverConnection);

            Channel channel = connection.channel;

            if (channel.pipeline().get(PACKET_INJECTOR) != null) {
                System.out.println("PLAYER ALREADY INJECTED");
                return;
            }
            channel.pipeline().addAfter("decoder", PACKET_INJECTOR, new MessageToMessageDecoder<ServerboundInteractPacket>() {
                @Override
                protected void decode(ChannelHandlerContext channelHandlerContext, ServerboundInteractPacket packet, List<Object> list) throws ReflectiveOperationException {
                    list.add(packet);
                    if (packet == null) return;

                    Field entityIdField = ServerboundInteractPacket.class.getDeclaredField("entityId");
                    entityIdField.setAccessible(true);
                    Object entityIdValue = entityIdField.get(packet);

                    int entityId = (int) entityIdValue;

                    Optional<Hologram> clickedHologram = hologramManager.getInteracted(entityId);

                    if (clickedHologram.isEmpty())
                        return;

                    Hologram hologram = clickedHologram.get();

                    Field actionField = ServerboundInteractPacket.class.getDeclaredField("action");
                    actionField.setAccessible(true);
                    Object actionValue = actionField.get(packet);
                    Method getTypeMethod = actionValue.getClass().getMethod("getType");
                    getTypeMethod.setAccessible(true);
                    Object interactionType = getTypeMethod.invoke(actionValue);

                    String stringAction = interactionType.toString();

                    if (stringAction.equalsIgnoreCase("interact_at"))
                        return;

                    EquipmentSlot hand;

                    if (stringAction.equalsIgnoreCase("interact")) {
                        Field handField = actionValue.getClass().getDeclaredField("hand");
                        handField.setAccessible(true);
                        Object packetHand = handField.get(actionValue);
                        InteractionHand interactionHand = (InteractionHand) packetHand;
                        hand = interactionHand == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
                    } else {
                        hand = EquipmentSlot.HAND;
                    }

                    Bukkit.getScheduler().runTask(plugin, () -> {
                        HologramInteractEvent event = new HologramInteractEvent(hologram, player, hand);
                        Bukkit.getPluginManager().callEvent(event);

                        if (event.isCancelled())
                            return;

                        clickedHologram.get().getInteraction().getAction().execute(player);
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uninjectPacketListener(Player player) {
        try {
            ServerGamePacketListenerImpl serverConnection = ((CraftPlayer) player).getHandle().connection;

            Field connectionField = ServerCommonPacketListenerImpl.class.getDeclaredField("connection");
            connectionField.setAccessible(true);

            Connection connection = (Connection) connectionField.get(serverConnection);

            Channel channel = connection.channel;
            if (channel.pipeline().get(PACKET_INJECTOR) == null)
                return;

            channel.pipeline().remove(PACKET_INJECTOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HologramManager getHologramManager() {
        return hologramManager;
    }

}
