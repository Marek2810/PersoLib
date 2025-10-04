package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.entity.PersoEntity;
import me.marek2810.persoLib.interaction.InteractionAction;
import me.marek2810.persoLib.interaction.PersoInteraction;

public interface Hologram extends PersoEntity {

    //ID for hologram manager
    String getId();

    int getEntityId();

    PersoInteraction getInteraction();

    void addInteraction(InteractionAction interactionAction);

    void removeInteraction();

}
