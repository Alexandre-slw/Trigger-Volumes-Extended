package gg.alexandre.extended.interact;

import com.hypixel.hytale.builtin.triggervolumes.effect.TriggerEventType;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class VolumeInteractionComponent implements Component<EntityStore> {

    private static ComponentType<EntityStore, VolumeInteractionComponent> componentType;

    private final String volumeId;
    private final TriggerEventType eventType;
    private final boolean includeGroupEffects;

    public static void setComponentType(@Nonnull ComponentType<EntityStore, VolumeInteractionComponent> type) {
        componentType = type;
    }

    @Nonnull
    public static ComponentType<EntityStore, VolumeInteractionComponent> getComponentType() {
        if (componentType == null) {
            throw new IllegalStateException("VolumeInteractionComponent was not registered yet");
        }
        return componentType;
    }

    public VolumeInteractionComponent() {
        this("", TriggerEventType.ENTER, true);
    }

    public VolumeInteractionComponent(
            @Nonnull String volumeId,
            @Nonnull TriggerEventType eventType,
            boolean includeGroupEffects
    ) {
        this.volumeId = volumeId;
        this.eventType = eventType;
        this.includeGroupEffects = includeGroupEffects;
    }

    @Nonnull
    public String getVolumeId() {
        return volumeId;
    }

    @Nonnull
    public TriggerEventType getEventType() {
        return eventType == null ? TriggerEventType.ENTER : eventType;
    }

    public boolean shouldIncludeGroupEffects() {
        return includeGroupEffects;
    }

    @Override
    public Component<EntityStore> clone() {
        return new VolumeInteractionComponent(volumeId, getEventType(), includeGroupEffects);
    }

}
