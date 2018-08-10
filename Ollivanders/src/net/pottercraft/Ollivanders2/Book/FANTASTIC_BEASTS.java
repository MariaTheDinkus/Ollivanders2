package net.pottercraft.Ollivanders2.Book;

import net.pottercraft.Ollivanders2.O2MagicBranch;
import net.pottercraft.Ollivanders2.Ollivanders2;

/**
 * Fantastic Beasts and Where to Find Them
 *
 * @author Azami7
 */
public class FANTASTIC_BEASTS extends Book
{
   public FANTASTIC_BEASTS (Ollivanders2 plugin)
   {
      super(plugin);

      shortTitle = "Fantastic Beasts";
      title = "Fantastic Beasts and Where to Find Them";
      author = "Newt Scamander";
      branch = O2MagicBranch.CARE_OF_MAGICAL_CREATURES;
   }
}
