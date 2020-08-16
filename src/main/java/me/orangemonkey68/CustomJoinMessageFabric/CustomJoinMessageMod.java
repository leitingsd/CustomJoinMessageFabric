package me.orangemonkey68.CustomJoinMessageFabric;

import me.orangemonkey68.CustomJoinMessageFabric.Config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import java.io.*;
import java.net.URISyntaxException;

public class CustomJoinMessageMod implements ModInitializer {

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

    }

}
