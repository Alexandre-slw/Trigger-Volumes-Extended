package gg.alexandre.extended.util;

import com.hypixel.hytale.builtin.triggervolumes.TriggerVolumesPlugin;
import com.hypixel.hytale.builtin.triggervolumes.effect.TriggerEventType;

import javax.annotation.Nonnull;

public final class EnumReflectionUtil {

    private EnumReflectionUtil() {
    }

    @Nonnull
    public static TriggerEventType registerTriggerEvent(@Nonnull String name) {
        TriggerEventType existing = TriggerEventType.get(name);
        if (existing != null) {
            return existing;
        }
        return TriggerVolumesPlugin.get().registerEventType(name);
    }

}
