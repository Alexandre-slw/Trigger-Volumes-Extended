package gg.alexandre.extended.effects;

import com.hypixel.hytale.builtin.triggervolumes.effect.TriggerContext;
import com.hypixel.hytale.builtin.triggervolumes.effect.TriggerEffect;
import com.hypixel.hytale.builtin.triggervolumes.effect.TriggerEventType;
import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;

import javax.annotation.Nonnull;

public class PressInteractionEffect extends TriggerEffect {

    private static final String DEFAULT_HINT = "server.interactionHints.generic";

    @Nonnull
    public static final BuilderCodec<PressInteractionEffect> CODEC = BuilderCodec.builder(
                    PressInteractionEffect.class, PressInteractionEffect::new, BASE_CODEC
            )
            .append(
                    new KeyedCodec<>("Hint", Codec.STRING, false),
                    (e, v) -> e.hint = v,
                    (e) -> e.hint
            ).add()
            .append(
                    new KeyedCodec<>("InteractionEvent", new EnumCodec<>(TriggerEventType.class), false),
                    (e, v) -> e.interactionEvent = v,
                    (e) -> e.interactionEvent
            ).add()
            .append(
                    new KeyedCodec<>("IncludeGroupEffects", Codec.BOOLEAN, false),
                    (e, v) -> e.includeGroupEffects = v,
                    (e) -> e.includeGroupEffects
            ).add()
            .append(
                    new KeyedCodec<>("HitboxPadding", Codec.FLOAT, false),
                    (e, v) -> e.hitboxPadding = Math.max(0.0f, v),
                    (e) -> e.hitboxPadding > 0.0f ? e.hitboxPadding : null
            ).add()
            .build();

    private String hint = DEFAULT_HINT;
    private TriggerEventType interactionEvent = TriggerEventType.ENTER;
    private boolean includeGroupEffects = true;
    private float hitboxPadding = 0.0f;

    @Override
    public void execute(@Nonnull TriggerContext context) {

    }

    @Nonnull
    public String getHint() {
        return hint == null || hint.isBlank() ? DEFAULT_HINT : hint;
    }

    @Nonnull
    public TriggerEventType getInteractionEvent() {
        return interactionEvent == null ? TriggerEventType.ENTER : interactionEvent;
    }

    public boolean shouldIncludeGroupEffects() {
        return includeGroupEffects;
    }

    public float getHitboxPadding() {
        return Math.max(0.0f, hitboxPadding);
    }

}
