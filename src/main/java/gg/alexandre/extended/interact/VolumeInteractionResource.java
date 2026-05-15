package gg.alexandre.extended.interact;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Resource;
import com.hypixel.hytale.component.ResourceType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class VolumeInteractionResource implements Resource<EntityStore> {

    private static ResourceType<EntityStore, VolumeInteractionResource> resourceType;

    private final Map<String, Ref<EntityStore>> entitiesByVolumeId = new HashMap<>();

    public static void setResourceType(@Nonnull ResourceType<EntityStore, VolumeInteractionResource> type) {
        resourceType = type;
    }

    @Nonnull
    public static ResourceType<EntityStore, VolumeInteractionResource> getResourceType() {
        if (resourceType == null) {
            throw new IllegalStateException("VolumeInteractionResource was not registered yet");
        }
        return resourceType;
    }

    @Nullable
    public Ref<EntityStore> get(@Nonnull String volumeId) {
        Ref<EntityStore> ref = entitiesByVolumeId.get(volumeId);
        if (ref == null) {
            return null;
        }
        if (ref.isValid()) {
            return ref;
        }
        entitiesByVolumeId.remove(volumeId);
        return null;
    }

    public void put(@Nonnull String volumeId, @Nonnull Ref<EntityStore> ref) {
        entitiesByVolumeId.put(volumeId, ref);
    }

    public void remove(@Nonnull String volumeId) {
        entitiesByVolumeId.remove(volumeId);
    }

    public void removeMissing(@Nonnull Set<String> volumes, @Nonnull Consumer<Ref<EntityStore>> remover) {
        Iterator<Map.Entry<String, Ref<EntityStore>>> iterator = entitiesByVolumeId.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Ref<EntityStore>> entry = iterator.next();
            if (volumes.contains(entry.getKey())) {
                continue;
            }
            Ref<EntityStore> ref = entry.getValue();
            if (ref != null && ref.isValid()) {
                remover.accept(ref);
            }
            iterator.remove();
        }
    }

    @Override
    public Resource<EntityStore> clone() {
        return new VolumeInteractionResource();
    }

}
