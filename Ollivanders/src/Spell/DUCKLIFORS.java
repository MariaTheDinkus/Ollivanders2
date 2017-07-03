package Spell;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.pottercraft.Ollivanders2.Ollivanders2;
import net.pottercraft.Ollivanders2.Spells;

/**
 * Transfigures entity into a chicken.
 *
 * @see MetatrepoSuper
 * @version Ollivanders2
 * @author Azami7
 */
public class DUCKLIFORS extends MetatrepoSuper
{

   public DUCKLIFORS (Ollivanders2 plugin, Player player, Spells name, Double rightWand)
   {
      super(plugin, player, name, rightWand);

      animalShape = EntityType.CHICKEN;
   }
}