package net.omni.pet;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.OcelotWatcher;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class DisguiseHook {

    private final OmniPetPlugin plugin;
    private boolean hooked = false;

    public DisguiseHook(OmniPetPlugin plugin) {
        this.plugin = plugin;
    }

    public void hook() {
        if (Bukkit.getPluginManager().isPluginEnabled("LibsDisguises")) {
            hooked = true;
            plugin.sendConsole("&aSuccessfully hooked into LibsDisguises.");
        } else {
            hooked = false;
            plugin.sendConsole("&aCould not hook into LibsDisguises. LibsDisguises not found.");
        }
    }

    public void disguise(Player player) {
        Validate.notNull(player, "Player not found.");

        MobDisguise disguise = new MobDisguise(DisguiseType.OCELOT);
        disguise.setHearSelfDisguise(true);
        disguise.setModifyBoundingBox(true);
        disguise.setHideArmorFromSelf(true);
        disguise.setVelocitySent(true);
        disguise.setEntity(player);

        OcelotWatcher ocelotWatcher = (OcelotWatcher) disguise.getWatcher();
        ocelotWatcher.setTamed(true);
        ocelotWatcher.setOwner(player.getUniqueId());
        ocelotWatcher.setCustomNameVisible(true);
        ocelotWatcher.setCustomName(player.getName());
        ocelotWatcher.setAddEntityAnimations(true);

        disguise.setWatcher(ocelotWatcher);

        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (maxHealth != null)
            maxHealth.setBaseValue(ocelotWatcher.getMaxHealth());

        DisguiseAPI.disguiseToAll(player, disguise);
    }

    public void unDisguise(Player player) {
        DisguiseAPI.undisguiseToAll(player);
    }

    public boolean isHooked() {
        return hooked;
    }
}
