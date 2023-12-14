package me.neon.example;

import me.geek.cos.api.CosmeticConfig;
import me.geek.cos.api.SourceComponent;
import me.geek.cos.api.carrier.CarrierAbstractEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * GeekCosmetics-Example
 * me.neon.example
 *
 * @author 老廖
 * @since 2023/12/14 15:48
 */
public class ExampleCarrier extends CarrierAbstractEntity {
    private final Player player;

    private final SourceComponent sourceComponent;

    private int npcID;

    private boolean isNpc;

    public ExampleCarrier(CosmeticConfig config, Player player, SourceComponent sourceComponent) {
        super(config);
        this.player = player;
        this.sourceComponent = sourceComponent;
    }

    @Override
    public Player getOwnerPlayer() {
        return player;
    }

    @Override
    public SourceComponent getSourceComponent() {
        return sourceComponent;
    }

    @Override
    public void deleteByNPC() {
        this.removeByPlayer(this.player);
    }

    @Override
    public void eval(List<? extends Player> list, Location location) {}
    @Override
    public void evalByNpc(List<? extends Player> list, Location location) {}

    @Override
    public int getMountEntityID() {
        return 0;
    }
    @Override
    public boolean isMount() {
        return false;
    }

    @Override
    public CarrierAbstractEntity spawn(Player player, Location location) {
        isNpc = false;
        this.setCustomEquip(player, this.player.getEntityId(), EquipmentSlot.OFF_HAND, sourceComponent.getItemStack());
        return this;
    }

    @Override
    public void spawnByNPC(int i, Location location) {
        npcID = i;
        isNpc = true;
        this.setCustomEquip(player, npcID, EquipmentSlot.OFF_HAND, sourceComponent.getItemStack());
    }

    @Override
    public void removeByPlayer(Player view) {
        if (getViewPlayer().remove(view.getName())) {
            ItemStack itemStack = player.getInventory().getItemInOffHand();
            int id;
            if (isNpc) {
                id = npcID;
            } else id = player.getEntityId();

            if (itemStack.getType() != Material.AIR) {
                setCustomEquip(view, id, EquipmentSlot.OFF_HAND, new ItemStack(Material.AIR));
            } else {
                setCustomEquip(view, id, EquipmentSlot.OFF_HAND, itemStack);
            }
        }
    }
}
