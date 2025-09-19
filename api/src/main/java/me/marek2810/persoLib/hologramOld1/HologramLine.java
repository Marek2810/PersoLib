package me.marek2810.persoLib.hologramOld1;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.entity.PersoEntity;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.joml.Vector3f;

import java.util.Map;

public interface HologramLine extends PersoEntity {

    Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
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

    void showTo(Player player);

    void hideFrom(Player player);

    void teleport(Location location);

    public void setTranslation(Vector3f translation);
    public Vector3f getTranslation();

    public void setScale(Vector3f scale);
    public Vector3f getScale();

    public void setBillboard(Display.Billboard billboard);
    public Display.Billboard getBillboard();

    public void setBrightness(org.bukkit.entity.Display.Brightness brightness);
    public org.bukkit.entity.Display.Brightness getBrightness();

    public void setViewRange(float viewRange);
    public float getViewRange();

    public void setShadowRadius(float shadowRadius);
    public float getShadowRadius();

    public void setShadowStrange(float shadowStrange);
    public float getShadowStrange();

}
