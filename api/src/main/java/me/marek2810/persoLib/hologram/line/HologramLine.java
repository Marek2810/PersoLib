package me.marek2810.persoLib.hologram.line;

import me.marek2810.persoLib.entity.PersoEntity;

public interface HologramLine<T> extends PersoEntity {

    int getDisplayEntityId();

    void setContent(T conntent);

    T getContent();

}
