package Spell;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.pottercraft.Ollivanders2.Ollivanders2;
import net.pottercraft.Ollivanders2.Spells;

/**
 * Transfigures entity into a parrot.
 *
 * @see MetatrepoSuper
 * @version Ollivanders2
 * @author lownes
 * @author Azami7
 */
public class AVIFORS extends MetatrepoSuper
{

   public AVIFORS (Ollivanders2 plugin, Player player, Spells name, Double rightWand)
   {
      super(plugin, player, name, rightWand);

      animalShape = EntityType.PARROT;
   }
}