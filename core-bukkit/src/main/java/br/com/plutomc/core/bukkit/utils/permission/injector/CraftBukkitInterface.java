package br.com.plutomc.core.bukkit.utils.permission.injector;

import org.bukkit.Bukkit;

public class CraftBukkitInterface {
   private static final String VERSION;

   public static String getCBClassName(String simpleName) {
      return VERSION == null ? null : "org.bukkit.craftbukkit" + VERSION + simpleName;
   }

   public static Class<?> getCBClass(String name) {
      if (VERSION == null) {
         return null;
      } else {
         try {
            return Class.forName(getCBClassName(name));
         } catch (ClassNotFoundException var2) {
            return null;
         }
      }
   }

   static {
      Class<?> serverClass = Bukkit.getServer().getClass();
      if (!serverClass.getSimpleName().equals("CraftServer")) {
         VERSION = null;
      } else if (serverClass.getName().equals("org.bukkit.craftbukkit.CraftServer")) {
         VERSION = ".";
      } else {
         String name = serverClass.getName();
         name = name.substring("org.bukkit.craftbukkit".length());
         name = name.substring(0, name.length() - "CraftServer".length());
         VERSION = name;
      }
   }
}
