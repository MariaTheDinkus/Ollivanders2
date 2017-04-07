package Spell;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.pottercraft.Ollivanders2.Ollivanders2;
import net.pottercraft.Ollivanders2.SpellProjectile;
import net.pottercraft.Ollivanders2.Spells;

/**
 * Causes blindness in a radius larger than fumos
 *
 * @author lownes
 */
public class FUMOS_DUO extends SpellProjectile implements Spell
{

   public FUMOS_DUO (Ollivanders2 plugin, Player player, Spells name,
                     Double rightWand)
   {
      super(plugin, player, name, rightWand);
      moveEffect = Effect.SMOKE;
      moveEffectData = 4;
   }

   @Override
   public void checkEffect ()
   {
      move();
      if (getBlock().getType() != Material.AIR && getBlock().getType() != Material.FIRE && getBlock().getType() != Material.WATER && getBlock().getType() != Material.STATIONARY_WATER)
      {
         for (LivingEntity live : getLivingEntities(usesModifier))
         {
            PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, (int) (usesModifier * 20 * 2), 0);
            live.addPotionEffect(blind);
         }
      }
   }

}
