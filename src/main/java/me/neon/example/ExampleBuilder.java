package me.neon.example;

import me.geek.cos.api.Cosmetic;
import me.geek.cos.api.CosmeticConfig;
import me.geek.cos.api.SourceComponent;
import me.geek.cos.api.carrier.CarrierAbstractEntity;
import me.geek.cos.api.carrier.CarrierBuilderProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * GeekCosmetics-Example
 * me.neon.example
 *
 * @author 老廖
 * @since 2023/12/14 15:47
 */
public class ExampleBuilder extends CarrierBuilderProvider {

    @Override
    public String getDisplayName() {
        return "副手饰品";
    }

    @Override
    public Plugin getPlugin() {
        return GeekCosmeticsExample.plugin;
    }

    @Override
    public String getProviderType() {
        return "OFF";
    }

    @Override
    public CarrierAbstractEntity parseCarrier(Player player, Cosmetic cosmetic, CosmeticConfig cosmeticConfig) {
        ItemStack itemStack = parseItemSource(parseColor(cosmetic.getColor()), cosmeticConfig);
        return new ExampleCarrier(cosmeticConfig, player, new SourceComponent(cosmeticConfig.getNode(), cosmeticConfig.getProviderType(), cosmetic.getColor(), itemStack));
    }
}
