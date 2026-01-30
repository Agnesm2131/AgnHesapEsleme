package me.agnes.agnesesle.util;

import me.agnes.agnesesle.AgnesEsle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SchedulerUtil {

    private static final boolean IS_FOLIA = isFolia();

    public static boolean isFolia() {
        try {
            Bukkit.class.getMethod("getGlobalRegionScheduler");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static void runAsync(Runnable runnable) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getAsyncScheduler").invoke(null);
                Method runNow = scheduler.getClass().getMethod("runNow", Plugin.class, Consumer.class);
                runNow.invoke(scheduler, AgnesEsle.getInstance(), (Consumer<Object>) task -> runnable.run());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(AgnesEsle.getInstance(), runnable);
        }
    }

    public static void runSync(Runnable runnable) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getGlobalRegionScheduler").invoke(null);
                Method execute = scheduler.getClass().getMethod("execute", Plugin.class, Runnable.class);
                execute.invoke(scheduler, AgnesEsle.getInstance(), runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTask(AgnesEsle.getInstance(), runnable);
        }
    }

    public static void runEntitySync(Entity entity, Runnable runnable) {
        if (IS_FOLIA) {
            try {
                Method getScheduler = entity.getClass().getMethod("getScheduler");
                Object scheduler = getScheduler.invoke(entity);
                Method execute = scheduler.getClass().getMethod("execute", Plugin.class, Runnable.class, Runnable.class,
                        long.class);
                execute.invoke(scheduler, AgnesEsle.getInstance(), runnable, null, 0L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTask(AgnesEsle.getInstance(), runnable);
        }
    }

    public static void runLaterAsync(Runnable runnable, long ticks) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getAsyncScheduler").invoke(null);
                Method runDelayed = scheduler.getClass().getMethod("runDelayed", Plugin.class, Consumer.class,
                        long.class, TimeUnit.class);
                runDelayed.invoke(scheduler, AgnesEsle.getInstance(), (Consumer<Object>) task -> runnable.run(),
                        ticks * 50, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskLaterAsynchronously(AgnesEsle.getInstance(), runnable, ticks);
        }
    }

    public static void runLaterSync(Runnable runnable, long ticks) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getGlobalRegionScheduler").invoke(null);
                Method runDelayed = scheduler.getClass().getMethod("runDelayed", Plugin.class, Consumer.class,
                        long.class);
                runDelayed.invoke(scheduler, AgnesEsle.getInstance(), (Consumer<Object>) task -> runnable.run(), ticks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskLater(AgnesEsle.getInstance(), runnable, ticks);
        }
    }

    public static void runTimerAsync(Runnable runnable, long delayTicks, long periodTicks) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getAsyncScheduler").invoke(null);
                Method runAtFixedRate = scheduler.getClass().getMethod("runAtFixedRate", Plugin.class, Consumer.class,
                        long.class, long.class, TimeUnit.class);
                runAtFixedRate.invoke(scheduler, AgnesEsle.getInstance(), (Consumer<Object>) task -> runnable.run(),
                        Math.max(1, delayTicks) * 50, periodTicks * 50, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskTimerAsynchronously(AgnesEsle.getInstance(), runnable, delayTicks,
                    periodTicks);
        }
    }

    public static void runTimerSync(Runnable runnable, long delayTicks, long periodTicks) {
        if (IS_FOLIA) {
            try {
                Object scheduler = Bukkit.class.getMethod("getGlobalRegionScheduler").invoke(null);
                Method runAtFixedRate = scheduler.getClass().getMethod("runAtFixedRate", Plugin.class, Consumer.class,
                        long.class, long.class);
                runAtFixedRate.invoke(scheduler, AgnesEsle.getInstance(), (Consumer<Object>) task -> runnable.run(),
                        Math.max(1, delayTicks), periodTicks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskTimer(AgnesEsle.getInstance(), runnable, delayTicks, periodTicks);
        }
    }
}
