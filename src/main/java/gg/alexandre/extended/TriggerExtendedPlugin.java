package gg.alexandre.extended;

import com.hypixel.hytale.builtin.triggervolumes.TriggerVolumesPlugin;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.RootInteraction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import gg.alexandre.extended.commands.RunTriggerVolumeCommand;
import gg.alexandre.extended.effects.CommandEffect;
import gg.alexandre.extended.effects.DestroyOtherVolumeEffect;
import gg.alexandre.extended.effects.PressInteractionEffect;
import gg.alexandre.extended.interact.VolumeInteraction;
import gg.alexandre.extended.interact.VolumeInteractionComponent;
import gg.alexandre.extended.interact.VolumeInteractionResource;
import gg.alexandre.extended.interact.VolumeInteractionSystem;

import javax.annotation.Nonnull;
import java.util.List;

public class TriggerExtendedPlugin extends JavaPlugin {

    public TriggerExtendedPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        setupCommands();
        setupEffects();
        setupInteraction();
    }

    private void setupCommands() {
        getCommandRegistry().registerCommand(new RunTriggerVolumeCommand());
    }

    private void setupEffects() {
        TriggerVolumesPlugin.get().registerEffectType("Command", CommandEffect.class, CommandEffect.CODEC);
        TriggerVolumesPlugin.get().registerEffectType(
                "DestroyOtherVolume", DestroyOtherVolumeEffect.class, DestroyOtherVolumeEffect.CODEC
        );
        TriggerVolumesPlugin.get().registerEffectType(
                "PressInteraction", PressInteractionEffect.class, PressInteractionEffect.CODEC
        );
    }

    private void setupInteraction() {
        this.getCodecRegistry(Interaction.CODEC).register(
                "VolumeInteraction", VolumeInteraction.class, VolumeInteraction.CODEC
        );
        Interaction.getAssetStore().loadAssets(
                "TriggerVolumesExtended:TriggerVolumesExtended",
                List.of(new VolumeInteraction(VolumeInteraction.INTERACTION_ID))
        );
        RootInteraction.getAssetStore().loadAssets(
                "TriggerVolumesExtended:TriggerVolumesExtended",
                List.of(VolumeInteraction.DEFAULT_ROOT)
        );

        VolumeInteractionComponent.setComponentType(
                this.getEntityStoreRegistry().registerComponent(
                        VolumeInteractionComponent.class, VolumeInteractionComponent::new
                )
        );
        VolumeInteractionResource.setResourceType(
                this.getEntityStoreRegistry().registerResource(
                        VolumeInteractionResource.class, VolumeInteractionResource::new
                )
        );
        this.getEntityStoreRegistry().registerSystem(new VolumeInteractionSystem());
    }

}
