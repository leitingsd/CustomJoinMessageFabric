package me.orangemonkey68.CustomJoinMessageFabric.Config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "custom_join_message_fabric")
public class ModConfig implements ConfigData {
    public String joinMessage = "Hi %player%! Welcome to the server!";
}
