package me.marek2810.persoLib.hologram.setting;

import me.marek2810.persoLib.entity.EntitySetting;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.joml.Vector3f;

import java.util.Map;

public final class HologramSetting {

    public static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Global.TRANSLATION, new Vector3f(0, 0, 0)),
            Map.entry(HologramSetting.Global.SCALE, new Vector3f(1, 1, 1)),
//            Map.entry(HologramSetting.BILLBOARD, Display.Billboard.FIXED),
            Map.entry(HologramSetting.Global.BILLBOARD, Display.Billboard.CENTER),
            Map.entry(HologramSetting.Global.BRIGHTNESS, -1),
            Map.entry(HologramSetting.Global.VIEW_RANGE, 1f),
            Map.entry(HologramSetting.Global.SHADOW_RADIUS, 0f),
            Map.entry(HologramSetting.Global.SHADOW_STRANGE, 0f),
            Map.entry(HologramSetting.Global.WIDTH, 0f),
            Map.entry(HologramSetting.Global.HEIGHT, 0f),
            Map.entry(HologramSetting.Global.GLOW_COLOR, -1)
    );
    public static final Map<EntitySetting<?>, Object> TEXT_DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Text.TEXT, ""),
            Map.entry(HologramSetting.Text.LINE_WIDTH, 200),
            Map.entry(HologramSetting.Text.BACKGROUND_COLOR, 1073741824),
            Map.entry(HologramSetting.Text.TEXT_OPACITY, (byte) -1),
            Map.entry(HologramSetting.Text.FORMAT, (byte) 0)
    );
    public static final Map<EntitySetting<?>, Object> ITEM_DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Item.ITEM_STACK, new ItemStack(Material.GRASS_BLOCK)),
            Map.entry(HologramSetting.Item.DISPLAY_TRANSFORM, ItemDisplay.ItemDisplayTransform.NONE)
    );
    public static final Map<EntitySetting<?>, Object> BLOCK_DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.BLock.BLOCK_STATE, 0)
    );

    private HologramSetting() {
    }

    public static class Global {

        // All holograms (Display entity)
        public static final EntitySetting<Vector3f> TRANSLATION = new EntitySetting<>(Vector3f.class, 11);
        public static final EntitySetting<Vector3f> SCALE = new EntitySetting<>(Vector3f.class, 12);
        public static final EntitySetting<Display.Billboard> BILLBOARD = new EntitySetting<>(Display.Billboard.class, 15);
        public static final EntitySetting<Display.Brightness> BRIGHTNESS = new EntitySetting<>(Display.Brightness.class, 16);
        public static final EntitySetting<Float> VIEW_RANGE = new EntitySetting<>(Float.class, 17);
        public static final EntitySetting<Float> SHADOW_RADIUS = new EntitySetting<>(Float.class, 18);
        public static final EntitySetting<Float> SHADOW_STRANGE = new EntitySetting<>(Float.class, 19);
        public static final EntitySetting<Float> WIDTH = new EntitySetting<>(Float.class, 20);
        public static final EntitySetting<Float> HEIGHT = new EntitySetting<>(Float.class, 21);
        public static final EntitySetting<Integer> GLOW_COLOR = new EntitySetting<>(Integer.class, 22);
    }

    public static class BLock {
        // Block-specific (BlockDisplay entity)
        public static final EntitySetting<BlockState> BLOCK_STATE = new EntitySetting<>(BlockState.class, 23);
    }

    public static class Item {
        // Item-specific  (ItemDisplay entity)
        public static final EntitySetting<ItemStack> ITEM_STACK = new EntitySetting<>(ItemStack.class, 23);
        public static final EntitySetting<ItemDisplay.ItemDisplayTransform> DISPLAY_TRANSFORM = new EntitySetting<>(ItemDisplay.ItemDisplayTransform.class, 24);
    }

    public static class Text {
        // Text-specific  (TextDisplay entity)
        public static final EntitySetting<String> TEXT = new EntitySetting<>(String.class, 23);
        public static final EntitySetting<Integer> LINE_WIDTH = new EntitySetting<>(Integer.class, 24);
        public static final EntitySetting<Integer> BACKGROUND_COLOR = new EntitySetting<>(Integer.class, 25);
        public static final EntitySetting<Byte> TEXT_OPACITY = new EntitySetting<>(Byte.class, 26);
        public static final EntitySetting<Byte> FORMAT = new EntitySetting<>(Byte.class, 27);

    }

    public static class Interaction {
        // Text-specific  (TextDisplay entity)
        public static final EntitySetting<Float> WIDTH = new EntitySetting<>(Float.class, 8);
        public static final EntitySetting<Float> HEIGHT = new EntitySetting<>(Float.class, 9);
        public static final EntitySetting<Boolean> RESPONSIVE  = new EntitySetting<>(Boolean.class, 10);

    }

}
