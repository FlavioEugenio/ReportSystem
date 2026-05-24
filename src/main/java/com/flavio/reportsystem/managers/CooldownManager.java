package com.flavio.reportsystem.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long>
            cooldowns =
            new HashMap<>();

    // =========================
    // TEM COOLDOWN
    // =========================

    public boolean isInCooldown(
            UUID uuid
    ) {

        if (!cooldowns.containsKey(uuid)) {

            return false;

        }

        long expire =
                cooldowns.get(uuid);

        return System.currentTimeMillis()
                < expire;

    }

    // =========================
    // SET COOLDOWN
    // =========================

    public void setCooldown(
            UUID uuid
    ) {

        cooldowns.put(

                uuid,

                System.currentTimeMillis()
                        + (60 * 1000)

        );

    }

    // =========================
    // TEMPO RESTANTE
    // =========================

    public long getRemaining(
            UUID uuid
    ) {

        if (!cooldowns.containsKey(uuid)) {

            return 0;

        }

        long expire =
                cooldowns.get(uuid);

        long remaining =
                expire
                        - System.currentTimeMillis();

        return remaining / 1000;

    }

}