package me.orangemonkey68.CustomJoinMessageFabric.Mixins;

import me.orangemonkey68.CustomJoinMessageFabric.Config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerManager.class)
public class PlayerJoinMixin {
    @Unique
    private final ThreadLocal<ServerPlayerEntity> cachedPlayer = new ThreadLocal<>();

    @Inject(method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"))
    private void setCachedPlayer(ClientConnection connection, ServerPlayerEntity player, CallbackInfo cli){
        cachedPlayer.set(player);
    }

    @ModifyArg(
            method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V",
            ordinal = 0)
    )
    private Text replaceServerMessage(Text text){
        ServerPlayerEntity player = cachedPlayer.get();
        cachedPlayer.remove();
        String name = player.getName().asString();
        return new LiteralText(AutoConfig.getConfigHolder(ModConfig.class).getConfig().joinMessage.replace("%player%", name)).formatted(Formatting.YELLOW);
    }
}
