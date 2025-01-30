package net.pottercraft.ollivanders2.item.enchantment;

import net.pottercraft.ollivanders2.Ollivanders2;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.stream.events.Namespace;

/**
 * Port key enchantment
 * <p>
 * Reference: https://harrypotter.fandom.com/wiki/Portkey
 */
public class PORTUS extends Enchantment
{
    /**
     * The teleport destination for this portkey
     */
    double x;
    double y;
    double z;

    /**
     * Constructor
     *
     * @param plugin   a callback to the plugin
     * @param mag      the magnitude of this enchantment
     * @param args     optional arguments for this enchantment
     * @param itemLore the optional lore for this enchantment
     */
    public PORTUS(@NotNull Ollivanders2 plugin, int mag, @Nullable String args, @Nullable String itemLore)
    {
        super(plugin, mag, args, itemLore);
        enchantmentType = ItemEnchantmentType.GEMINIO;

        parseLocation(args);
    }

    /**
     * Parse the location from the args
     */
    private void parseLocation(String args)
    {
        if (args == null)
            return;

        // X, Y, Z should be in the args
        String[] split = args.split(" ");

        double x;
        double y;
        double z;

        if (split.length != 4)
        {
            common.printDebugMessage("Invalid coordinates on Portkey", null, null, false);
            return;
        }

        try
        {
            x = Double.parseDouble(split[1]);
            y = Double.parseDouble(split[2]);
            z = Double.parseDouble(split[3]);
        }
        catch (Exception e)
        {
            common.printDebugMessage("Failed to parse coordinates on Portkey", null, null, false);
            return;
        }

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Don't let portkeys despawn
     *
     * @param event the item despawn event
     */
    @Override
    public void doItemDespawn(@NotNull ItemDespawnEvent event)
    {
        event.setCancelled(true);
    }

    /**
     * Handle item pickup events
     *
     * @param event the item pickup event
     */
    @Override
    public void doItemPickup(@NotNull EntityPickupItemEvent event)
    {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player))
        {
            event.setCancelled(true);
            return;
        }

        if (event.getItem().getItemStack().hasItemMeta()) {
            ItemMeta meta = event.getItem().getItemStack().getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();

            // Create the namespace key for the enchantment args
            NamespacedKey key = NamespacedKey.fromString("ollivanders2:o2enchantment_args");
            String args = container.get(key, PersistentDataType.STRING);
            parseLocation(args);
            // Debug the location
            common.printDebugMessage("Portkey location: " + x + " " + y + " " + z, null, null, false);
            Location location = new Location(entity.getWorld(), x, y, z);

            // teleport the player to the location
            if (location == null)
                location = ((Player) entity).getBedSpawnLocation();

            if (location != null) // location could still be null
                p.addTeleportEvent((Player) entity, location, true);
            else
                common.printDebugMessage("Null location in PORTUS.doItemPickup()", null, null, false);
        }
    }

    /**
     * Handle item drop events
     *
     * @param event the item drop event
     */
    @Override
    public void doItemDrop(@NotNull PlayerDropItemEvent event)
    {
    }

    /**
     * Handle item held events
     *
     * @param event the item drop event
     */
    public void doItemHeld(@NotNull PlayerItemHeldEvent event)
    {
    }
}
