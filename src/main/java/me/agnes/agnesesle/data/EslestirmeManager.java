package me.agnes.agnesesle.data;

import me.agnes.agnesesle.AgnesEsle;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class EslestirmeManager {

    private static final Logger logger = AgnesEsle.getInstance().getLogger();

    private static final Map<String, UUID> kodlar = new ConcurrentHashMap<>();
    private static final Map<UUID, String> eslesmeler = new ConcurrentHashMap<>();
    private static final Map<UUID, String> bekleyenEslesmeler = new ConcurrentHashMap<>();
    private static final Map<String, UUID> bekleyenKodlar = new ConcurrentHashMap<>();
    private static final Map<String, Long> kodZamanlari = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> eslesmeZamanlari = new ConcurrentHashMap<>();
    private static final Map<UUID, Boolean> ikiFADurumu = new ConcurrentHashMap<>();
    private static final Map<UUID, Boolean> odulVerildiMap = new ConcurrentHashMap<>();
    private static final Map<UUID, String> kayitliIPler = new ConcurrentHashMap<>();

    public static void init() {
        loadEslesmeler();
<<<<<<< HEAD
        loadIkiFA();
        loadIPler();
        loadOdulVerilenler();

        AgnesEsle.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(AgnesEsle.getInstance(), () -> {
            long now = System.currentTimeMillis();
            long expirationTime = 10 * 60 * 1000;

            kodZamanlari.entrySet().removeIf(entry -> {
                boolean expired = (now - entry.getValue()) > expirationTime;
                if (expired) {
                    String kod = entry.getKey();
                    kodlar.remove(kod);
                    bekleyenKodlar.remove(kod);
                }
                return expired;
            });
        }, 1200L, 1200L);
=======

        AgnesEsle.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(
                AgnesEsle.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        long now = System.currentTimeMillis();
                        long expirationTime = 10 * 60 * 1000;

                        Iterator<Map.Entry<String, Long>> it = kodZamanlari.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, Long> entry = it.next();
                            if ((now - entry.getValue()) > expirationTime) {
                                String kod = entry.getKey();
                                kodlar.remove(kod);
                                bekleyenKodlar.remove(kod);
                                it.remove();
                            }
                        }
                    }
                }, 1200L, 1200L
        );
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
    }

    public static long getEslesmeTarihi(UUID uuid) {
        Long time = eslesmeZamanlari.get(uuid);
        if (time != null) return time;
        return -1L;
    }

<<<<<<< HEAD

    // Kod Üretme İşlevi (Çakışma Kontrolü ile)
    public static String uretKod(UUID uuid) {
        String kod;
        int denemeSayisi = 0;

        do {
            kod = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            denemeSayisi++;
        } while ((kodlar.containsKey(kod) || bekleyenKodlar.containsKey(kod)) && denemeSayisi < 5);
=======
    public static String uretKod(UUID uuid) {
        String kod = null;
        int attempts = 0;
        do {
            kod = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            attempts++;
        } while ((kodlar.containsKey(kod) || bekleyenKodlar.containsKey(kod)) && attempts < 5);
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)

        kodlar.put(kod, uuid);
        bekleyenKodlar.put(kod, uuid);
        kodZamanlari.put(kod, System.currentTimeMillis());
        return kod;
    }

<<<<<<< HEAD
    // Kod Kontrol
=======
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
    public static UUID koduKontrolEt(String kod) {
        if (kod == null) return null;
        return kodlar.get(kod.toUpperCase());
    }

<<<<<<< HEAD
    // Eşleştirme
    public static boolean eslestir(UUID uuid, String discordId) {
        if (discordId == null || uuid == null) return false;
        if (eslesmeler.containsValue(discordId) || bekleyenEslesmeler.containsValue(discordId)) {
            return false;
=======
    public static String getDiscordId(UUID uuid) {
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT discord_id FROM eslestirmeler WHERE uuid=?"
            );
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String discordId = null;
            if (rs.next()) {
                discordId = rs.getString("discord_id");
            }
            rs.close();
            ps.close();
            return discordId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
        }
    }

    public static boolean eslestir(UUID uuid, String discordId) {
        if (uuid == null || discordId == null) return false;
        if (eslesmeler.containsValue(discordId) || bekleyenEslesmeler.containsValue(discordId)) return false;
        bekleyenEslesmeler.put(uuid, discordId);
        return true;
    }

<<<<<<< HEAD
     // Onay Kısmı
=======
    public static boolean odulVerildiMi(UUID uuid) {
        if (odulVerildiMap.containsKey(uuid)) return odulVerildiMap.get(uuid);

        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT odul FROM eslestirmeler WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            boolean verildi = false;
            if (rs.next()) {
                verildi = rs.getInt("odul") == 1;
            }
            rs.close();
            ps.close();

            odulVerildiMap.put(uuid, verildi);
            return verildi;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void odulVerildi(UUID uuid) {
        odulVerildiMap.put(uuid, true);

        AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(
                AgnesEsle.getInstance(),
                () -> {
                    try {
                        Connection conn = DatabaseManager.getConnection();
                        PreparedStatement ps = conn.prepareStatement(
                                "UPDATE eslestirmeler SET odul=? WHERE uuid=?"
                        );
                        ps.setInt(1, 1);
                        ps.setString(2, uuid.toString());
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
    public static boolean onaylaEslesme(UUID uuid, String ip) {
        final String discordId = bekleyenEslesmeler.remove(uuid);
        if (discordId == null) return false;

        eslesmeler.put(uuid, discordId);
        eslesmeZamanlari.put(uuid, System.currentTimeMillis());
        kayitliIPler.put(uuid, ip);
        ikiFADurumu.put(uuid, false);

<<<<<<< HEAD
        if (!odulVerilenler.contains(uuid)) {
            odulVerilenler.add(uuid);
            AgnesEsle.getInstance().odulVer(uuid);
            saveOdulVerilenler();
        }

        String roleId = AgnesEsle.getInstance().getMainConfig().verifiedRoleId;
        if (roleId != null && !roleId.isEmpty()) {
            AgnesEsle.getInstance().getDiscordBot().addRoleToMember(discordId, roleId);
        } else {
            AgnesEsle.getInstance().getLogger().warning("Verified role ID config'de ayarlanmamış.");
        }

        kodlar.values().removeIf(u -> u.equals(uuid));
        bekleyenKodlar.values().removeIf(u -> u.equals(uuid));
        kodZamanlari.entrySet().removeIf(entry -> {
            String k = entry.getKey();
            UUID val = bekleyenKodlar.get(k);
            return val != null && val.equals(uuid);
        });

        if (ip != null) {
            kayitliIPler.put(uuid, ip);
        }

        saveEslesmeler();
        saveIPler();

        if (AgnesEsle.getInstance().getMainConfig().logSystem) {
            AgnesEsle.getInstance().getDiscordBot().sendEslestirmeEmbed(uuid, discordId);
        }
=======
        final UUID finalUuid = uuid;
        AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(
                AgnesEsle.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement ps = conn.prepareStatement(
                                    "INSERT OR REPLACE INTO eslestirmeler (uuid, discord_id, iki_fa, ip, odul) VALUES (?, ?, ?, ?, ?)"
                            );
                            ps.setString(1, finalUuid.toString());
                            ps.setString(2, discordId);
                            ps.setInt(3, 0);
                            ps.setString(4, ip);
                            ps.setInt(5, 0);
                            ps.executeUpdate();
                            ps.close();
                        } catch (SQLException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                }
        );
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)

        return true;
    }

    public static boolean beklemeVar(UUID uuid) {
        return bekleyenEslesmeler.containsKey(uuid);
    }

    public static void kaldirEslesme(UUID uuid) {
        eslesmeler.remove(uuid);
        ikiFADurumu.remove(uuid);
        kayitliIPler.remove(uuid);

<<<<<<< HEAD
        saveEslesmeler();
        saveIkiFA();
        saveIPler();

        saveOdulVerilenler();
=======
        final UUID finalUuid = uuid;
        AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(
                AgnesEsle.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement ps = conn.prepareStatement("DELETE FROM eslestirmeler WHERE uuid=?");
                            ps.setString(1, finalUuid.toString());
                            ps.executeUpdate();
                            ps.close();
                        } catch (SQLException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                }
        );
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
    }

    public static Map<UUID, String> getTumEslesmeler() {
        return Collections.unmodifiableMap(eslesmeler);
    }

    public static boolean eslesmeVar(UUID uuid) {
        return eslesmeler.containsKey(uuid);
    }

    public static boolean discordZatenEslesmis(String discordId) {
        return eslesmeler.containsValue(discordId) || bekleyenEslesmeler.containsValue(discordId);
    }

    public static UUID getUUIDByDiscordId(String discordId) {
        for (Map.Entry<UUID, String> entry : eslesmeler.entrySet()) {
            if (entry.getValue().equals(discordId)) return entry.getKey();
        }
        return null;
    }

    public static boolean iptalEt(UUID uuid) {
        String kod = null;
        for (Map.Entry<String, UUID> entry : bekleyenKodlar.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                kod = entry.getKey();
                break;
            }
        }
        if (kod != null) {
            kodlar.remove(kod);
            bekleyenKodlar.remove(kod);
            kodZamanlari.remove(kod);
            return true;
        }
        return false;
    }

    public static boolean isIkiFAOpen(UUID uuid) {
        return ikiFADurumu.getOrDefault(uuid, false);
    }

    public static void setIkiFA(UUID uuid, boolean durum) {
        ikiFADurumu.put(uuid, durum);

        final UUID finalUuid = uuid;
        final int val = durum ? 1 : 0;
        AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(
                AgnesEsle.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE eslestirmeler SET iki_fa=? WHERE uuid=?"
                            );
                            ps.setInt(1, val);
                            ps.setString(2, finalUuid.toString());
                            ps.executeUpdate();
                            ps.close();
                        } catch (SQLException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                }
        );
    }

    public static String getKayitliIP(UUID uuid) {
        return kayitliIPler.get(uuid);
    }

    public static void setKayitliIP(UUID uuid, String ip) {
        kayitliIPler.put(uuid, ip);

        final UUID finalUuid = uuid;
        final String finalIp = ip;
        AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(
                AgnesEsle.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE eslestirmeler SET ip=? WHERE uuid=?"
                            );
                            ps.setString(1, finalIp);
                            ps.setString(2, finalUuid.toString());
                            ps.executeUpdate();
                            ps.close();
                        } catch (SQLException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                }
        );
    }

    public static boolean ipDegisti(UUID uuid, String yeniIP) {
        String eskiIP = kayitliIPler.get(uuid);
        if (eskiIP == null) return false;
        return !eskiIP.equals(yeniIP);
    }

    private static void loadEslesmeler() {
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM eslestirmeler");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                eslesmeler.put(uuid, rs.getString("discord_id"));
                ikiFADurumu.put(uuid, rs.getInt("iki_fa") == 1);
                kayitliIPler.put(uuid, rs.getString("ip"));
                eslesmeZamanlari.put(uuid, System.currentTimeMillis());
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }
<<<<<<< HEAD

    private static void saveIkiFA() {
        Map<String, Boolean> data = new HashMap<>();
        ikiFADurumu.forEach((k,v) -> data.put(k.toString(), v));
        saveData(ikiFAFile, data);
    }

    private static void loadIPler() {
        if (!ipFile.exists()) return;
        try (Reader reader = new FileReader(ipFile)) {
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> data = gson.fromJson(reader, type);
            if (data != null) {
                kayitliIPler.clear();
                data.forEach((k,v) -> kayitliIPler.put(UUID.fromString(k), v));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private static void saveIPler() {
        Map<String, String> data = new HashMap<>();
        kayitliIPler.forEach((k,v) -> data.put(k.toString(), v));
        try (Writer writer = new FileWriter(ipFile)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

     private static <T> void saveData(File file, T data) {
         AgnesEsle.getInstance().getServer().getScheduler().runTaskAsynchronously(AgnesEsle.getInstance(), () -> {
             File tmpFile = new File(file.getParentFile(), file.getName() + ".tmp");

             synchronized (file) {
                 try (Writer writer = new FileWriter(tmpFile)) {
                     gson.toJson(data, writer);
                 } catch (IOException e) {
                     logger.warning("Geçici veri kaydedilemedi: " + e.getMessage());
                     return;
                 }

                 if (file.exists()) {
                     boolean ignored = file.delete();
                 }
                 boolean basarili = tmpFile.renameTo(file);
                 if (!basarili) {
                     logger.warning("Dosya adı değiştirilemedi: " + file.getName());
                 }
             }
         });
     }

    private static <T> T loadData(Type type) {
        if (!EslestirmeManager.dataFile.exists()) {
            return null;
        }
        try (Reader reader = new FileReader(EslestirmeManager.dataFile)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            logger.warning("Veri okunamadı: " + EslestirmeManager.dataFile.getName() + " -> " + e.getMessage());
            return null;
        }
    }
=======
>>>>>>> 9d515ea (SWITCHING TO NEW Database SQLITE)
}
