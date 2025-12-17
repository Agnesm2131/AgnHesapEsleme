package me.agnes.agnesesle.discord;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import me.agnes.agnesesle.util.MessageUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import java.io.File;
import java.util.*;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.agnes.agnesesle.AgnesEsle;
import me.agnes.agnesesle.data.EslestirmeManager;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import java.awt.Color;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscordBot extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("kod_gir_modal")) {
            String kod = Objects.requireNonNull(event.getValue("kod")).getAsString();
            UUID uuid = EslestirmeManager.koduKontrolEt(kod);

            if (uuid == null) {
                event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-modal-cevap.gecersiz-kod"))).setEphemeral(true).queue();
                return;
            }

            if (EslestirmeManager.discordZatenEslesmis(event.getUser().getId())) {
                event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-modal-cevap.discord-zaten-esli"))).setEphemeral(true).queue();
                return;
            }

            boolean basarili = EslestirmeManager.eslestir(uuid, event.getUser().getId());

            if (basarili) {
                event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-modal-cevap.basarili-yonlendirme"))).setEphemeral(true).queue();
            } else {
                event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-modal-cevap.basarisiz"))).setEphemeral(true).queue();
            }
        }
    }

    private static final long ESLE_COOLDOWN_SECONDS = 60;
    private static final long REPORT_COOLDOWN_SECONDS = 300;

    private final Logger logger;
    private final String token;
    private JDA jda;

    private final Cache<String, Long> esleCooldowns;
    private final Cache<String, Long> reportCooldowns;

    private String parsePlaceholders(String mesaj) {
        int aktifKullanici = Bukkit.getOnlinePlayers().size();
        return mesaj.replace("{aktifkullanici}", String.valueOf(aktifKullanici));
    }

    public DiscordBot(String token) {
        this.logger = AgnesEsle.getInstance().getLogger();
        this.token = token;
        this.esleCooldowns = Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
        this.reportCooldowns = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();

    }

    public void start() {
        try {
            jda = JDABuilder.createDefault(token)
                    .setActivity(net.dv8tion.jda.api.entities.Activity.playing(MessageUtil.getMessage("discord-bot-status-starting")))
                    .addEventListeners(this)
                    .build()
                    .awaitReady();

            jda.upsertCommand("eşle", "Bir hesap eşleştirme kodu girin")
                    .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "kod", "Eşleştirme kodu", true)
                    .queue();
            jda.upsertCommand("raporla", "Bir oyuncuyu raporlayın")
                    .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "oyuncu", "Raporlanacak oyuncu adı", true)
                    .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "sebep", "Rapor sebebi", true)
                    .queue();
            jda.upsertCommand("bilgi", "Bir kullanıcı hakkında bilgi al")
                    .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.USER, "kullanıcı", "Bilgi alınacak kullanıcı", true)
                    .queue();

            final List<String> durumlar = AgnesEsle.getInstance().getMainConfig().statusMessages;
            new org.bukkit.scheduler.BukkitRunnable() {
                @Override
                public void run() {
                    if (jda == null || jda.getStatus() != JDA.Status.CONNECTED) return;
                    if (durumlar == null || durumlar.isEmpty()) return;

                    int index = (int) ((System.currentTimeMillis() / 5000) % durumlar.size());
                    String mesaj = durumlar.get(index);
                    String finalMesaj = parsePlaceholders(mesaj);
                    net.dv8tion.jda.api.entities.Activity activity = net.dv8tion.jda.api.entities.Activity.playing(finalMesaj);
                    jda.getPresence().setActivity(activity);
                }
            }.runTaskTimer(AgnesEsle.getInstance(), 0L, 100L);

            String infoMessageStatus = AgnesEsle.getInstance().getMainConfig().informationMessage;
            if ("gönderilmedi".equalsIgnoreCase(infoMessageStatus)) {
                String kanalId = AgnesEsle.getInstance().getMainConfig().informationChannelId;
                if (kanalId == null || kanalId.isEmpty()) {
                    System.out.println("AgnHesapEşle: Bilgilendirme kanalı ID'si ayarlanmamış.");
                    return;
                }

                TextChannel kanal = jda.getTextChannelById(kanalId);
                if (kanal != null) {
                    Guild guild = kanal.getGuild();
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(MessageUtil.getMessage("information-message.title"));
                    embed.setDescription(MessageUtil.getMessage("information-message.description"));
                    embed.setColor(new Color(0x2F3136));
                    embed.setThumbnail(guild.getIconUrl());

                    kanal.sendMessageEmbeds(embed.build())
                            .setActionRow(
                                    Button.secondary("hesap_durumu", "🔗 Hesap Durumu"),
                                    Button.secondary("eslestir", "✔ Eşleştir"),
                                    Button.danger("eslesmeyi_kaldir", "❌ Eşleşmeyi Kaldır"),
                                    Button.secondary("odul-kontrol", "🎁 Ödüllerini Kontrol Et!")
                            )
                            .queue();

                    AgnesEsle.getInstance().getMainConfig().informationMessage = "gönderildi";
                    AgnesEsle.getInstance().getConfigManager().save(AgnesEsle.getInstance().getMainConfig(), "config.yml");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("deprecation")
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String id = event.getComponentId();

        switch (id) {
            case "hesap_durumu":
                handleHesapDurumu(event);
                break;
            case "eslestir":
                Modal modal = Modal.create("kod_gir_modal", "Eşleştirme Kodu Gir")
                        .addActionRow(
                                TextInput.create("kod", "Eşleştirme Kodu", TextInputStyle.SHORT)
                                        .setPlaceholder("Kodunuzu buraya girin")
                                        .setRequired(true)
                                        .build()
                        )
                        .build();
                event.replyModal(modal).queue();
                break;
            case "eslesmeyi_kaldir":
                handleEslesmeyiKaldir(event);
                break;
            case "odul-kontrol":
                String discordId = event.getUser().getId();
                UUID playerUUID = EslestirmeManager.getUUIDByDiscordId(discordId);

                if (playerUUID == null) {
                    event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-odul-butonu.hesap-bulunamadi")))
                            .setEphemeral(true)
                            .queue();
                    break;
                }
                event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-odul-butonu.kontrol-ediliyor")))
                        .setEphemeral(true)
                        .queue(hook -> {
                            AgnesEsle.getInstance().handleRewardCheck(playerUUID, hook);
                        });
                break;
        }



        if (id.startsWith("2fa_confirm_")) {
            try {
                String[] parts = id.split("_", 4);

                if (parts.length < 4) {
                    event.reply("Buton ID'si hatalı: " + id).setEphemeral(true).queue();
                    return;
                }

                UUID playerUUID = UUID.fromString(parts[2]);
                String newIP = parts[3];

                EslestirmeManager.setKayitliIP(playerUUID, newIP);

                event.reply(MessageUtil.getMessage("discord-2fa-confirm-reply")).setEphemeral(true).queue();
                event.getMessage().editMessage(MessageUtil.getMessage("discord-2fa-confirm-message-edit")).setComponents().queue();

            } catch (IllegalArgumentException uuidEx) {
                event.reply("Geçersiz UUID formatı: `" + id + "`").setEphemeral(true).queue();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "2FA onayı sırasında bir hata oluştu:", e);
                event.reply("2FA onayı sırasında bilinmeyen bir hata oluştu.").setEphemeral(true).queue();
            }
            return;
        }

        if (id.startsWith("2fa_deny_")) {
            try {
                event.reply(MessageUtil.getMessage("discord-2fa-deny-reply")).setEphemeral(true).queue();
                event.getMessage().editMessage(MessageUtil.getMessage("discord-2fa-deny-message-edit")).setComponents().queue();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "2FA reddi sırasında bir hata oluştu:", e);
            }
            return;
        }

        if (id.startsWith("report_kontrol_")) {
            try {
                String[] parts = id.split("_");
                if (parts.length < 4) {
                    event.reply(MessageUtil.getMessage("discord-button-invalid-id")).setEphemeral(true).queue();
                    return;
                }
                String raporlananDiscordId = parts[2];
                String uuidStr = parts[3];
                String yetkiliRolId = AgnesEsle.getInstance().getMainConfig().adminRoleId;

                if (yetkiliRolId == null || Objects.requireNonNull(event.getMember()).getRoles().stream().noneMatch(role -> role.getId().equals(yetkiliRolId))) {
                    event.reply(MessageUtil.getMessage("discord-button-no-permission")).setEphemeral(true).queue();
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(uuidStr));
                if (!target.hasPlayedBefore()) {
                    event.reply(MessageUtil.getMessage("discord-button-player-not-found")).setEphemeral(true).queue();
                    return;
                }

                Player player = Bukkit.getPlayer(target.getUniqueId());
                if (player != null) {
                    player.kickPlayer(MessageUtil.getMessage("discord-button-control-kick-reason"));
                }

                Map<String, String> vars = new HashMap<>();
                vars.put("player", target.getName());
                vars.put("discordId", raporlananDiscordId);
                event.reply(MessageUtil.getMessage("discord-button-control-reply", vars)).setEphemeral(true).queue();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Rapor kontrolü sırasında bir hata oluştu:", e);
            }
            return;
        }

        if (id.startsWith("report_ban_")) {
            try {
                String[] parts = id.split("_");
                if (parts.length < 4) {
                    event.reply(MessageUtil.getMessage("discord-button-invalid-id")).setEphemeral(true).queue();
                    return;
                }
                String uuidStr = parts[3];
                String yetkiliRolId = AgnesEsle.getInstance().getMainConfig().adminRoleId;

                if (yetkiliRolId == null || Objects.requireNonNull(event.getMember()).getRoles().stream().noneMatch(role -> role.getId().equals(yetkiliRolId))) {
                    event.reply(MessageUtil.getMessage("discord-button-no-permission")).setEphemeral(true).queue();
                    return;
                }
                OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(uuidStr));
                if (!target.hasPlayedBefore()) {
                    event.reply(MessageUtil.getMessage("discord-button-player-not-found")).setEphemeral(true).queue();
                    return;
                }

                Bukkit.getScheduler().runTask(AgnesEsle.getInstance(), () -> {
                    Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(Objects.requireNonNull(target.getName()), MessageUtil.getMessage("discord-button-ban-reason"), null, event.getUser().getAsTag());
                    Player player = Bukkit.getPlayer(target.getUniqueId());
                    if (player != null) {
                        player.kickPlayer(MessageUtil.getMessage("discord-button-ban-kick-reason"));
                    }
                });

                Map<String, String> vars = new HashMap<>();
                vars.put("player", target.getName());
                event.reply(MessageUtil.getMessage("discord-button-ban-reply", vars)).setEphemeral(true).queue();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Rapor ban işlemi sırasında bir hata oluştu:", e);
            }
        }
    }





    @SuppressWarnings("deprecation")
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        try {
            if (event.getName().equals("eşle")) {
                String userId = event.getUser().getId();
                if (isUserOnCooldown(userId, esleCooldowns, ESLE_COOLDOWN_SECONDS, event)) {
                    return;
                }

                String kod = Objects.requireNonNull(event.getOption("kod")).getAsString().toUpperCase();

                event.deferReply(true).queue();

                Bukkit.getScheduler().runTaskAsynchronously(AgnesEsle.getInstance(), () -> {
                    UUID uuid = EslestirmeManager.koduKontrolEt(kod);
                    if (uuid == null) {
                        event.getHook().sendMessage(MessageUtil.stripColors(MessageUtil.getMessage("discord-invalid-code"))).queue();
                        return;
                    }

                    if (EslestirmeManager.eslesmeVar(uuid)) {
                        event.getHook().sendMessage(MessageUtil.getMessage("discord-already-linked-mc")).queue();
                        return;
                    }

                    if (EslestirmeManager.discordZatenEslesmis(event.getUser().getId())) {
                        event.getHook().sendMessage(MessageUtil.getMessage("discord-already-linked-discord")).queue();
                        return;
                    }

                    boolean basarili = EslestirmeManager.eslestir(uuid, event.getUser().getId());
                    if (!basarili) {
                        event.getHook().sendMessage(MessageUtil.getMessage("discord-generic-error")).queue();
                        return;
                    }

                    setUserCooldown(userId, esleCooldowns);
                    sendEslestirmeEmbed(uuid, event.getUser().getId());

                    event.getHook().sendMessage(MessageUtil.getMessage("discord-success")).queue();
                });
            }
            else if (event.getName().equals("raporla")) {
                String userId = event.getUser().getId();

                if (isUserOnCooldown(userId, reportCooldowns, REPORT_COOLDOWN_SECONDS, event)) {
                    return;
                }

                String raporlananOyuncu = Objects.requireNonNull(event.getOption("oyuncu")).getAsString();
                String sebep = Objects.requireNonNull(event.getOption("sebep")).getAsString();
                String raporlayanKullanici = Objects.requireNonNull(event.getUser()).getAsTag();

                String logKanalId = AgnesEsle.getInstance().getMainConfig().logChannelId;
                if (logKanalId == null || logKanalId.isEmpty()) {
                    event.reply(MessageUtil.getMessage("discord-report-channel-not-set")).setEphemeral(true).queue();
                    return;
                }

                TextChannel logKanali = jda.getTextChannelById(logKanalId);
                if (logKanali == null) {
                    event.reply(MessageUtil.getMessage("discord-report-channel-not-found")).setEphemeral(true).queue();
                    return;
                }

                Map<String, String> reportVars = new HashMap<>();
                reportVars.put("reporter", raporlayanKullanici);
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle(MessageUtil.getMessage("discord-report-embed-title"))
                        .setColor(Color.RED)
                        .addField(MessageUtil.getMessage("discord-report-embed-field-player"), raporlananOyuncu, false)
                        .addField(MessageUtil.getMessage("discord-report-embed-field-reason"), sebep, false)
                        .setFooter(MessageUtil.getMessage("discord-report-embed-footer", reportVars));

                logKanali.sendMessageEmbeds(embed.build()).queue();

                setUserCooldown(userId, reportCooldowns);
                event.reply(MessageUtil.getMessage("discord-report-success")).setEphemeral(true).queue();
            }
            else if (event.getName().equals("bilgi")) {
                net.dv8tion.jda.api.entities.User targetUser = Objects.requireNonNull(event.getOption("kullanıcı")).getAsUser();
                String discordId = targetUser.getId();

                UUID playerUUID = EslestirmeManager.getUUIDByDiscordId(discordId);

                if (playerUUID == null) {
                    event.reply(MessageUtil.getMessage("discord-info-reply-no-match")).setEphemeral(true).queue();
                } else {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
                    Map<String, String> vars = new HashMap<>();
                    vars.put("player", player.getName() != null ? player.getName() : "Bilinmiyor");
                    event.reply(MessageUtil.getMessage("discord-info-reply-success", vars)).setEphemeral(true).queue();
                }
            }
        } catch (Exception e) {
            logger.severe("Slash komutu işlenirken bir hata oluştu: " + event.getName());
            logger.severe(e.getMessage());
        }
    }


    public void changeNickname(String discordId, String newNickname) {
        String guildId = AgnesEsle.getInstance().getMainConfig().guildId;
        if (guildId == null || guildId.isEmpty()) {
            System.out.println("AgnHesapEşle: Guild ID ayarlanmamış.");
            return;
        }

        Guild guild = jda.getGuildById(guildId);
        if (guild == null) return;

        guild.retrieveMemberById(discordId).queue(member -> {
            guild.modifyNickname(member, newNickname).queue();
        }, error -> {
            logger.warning("Kullanıcı bulunamadı veya nickname değiştirilemedi: " + error.getMessage());
        });
    }









    private void sendPrivateMessage(String discordId, String message) {
        User user = jda.getUserById(discordId);
        if (user != null) {
            user.openPrivateChannel().queue(channel -> {
                channel.sendMessage(message).queue();
            });
        }
    }


    public void addRoleToMember(String discordId, String roleId) {
        String guildId = AgnesEsle.getInstance().getMainConfig().guildId;
        if (guildId == null || guildId.isEmpty()) {
            System.out.println("AgnHesapEşle: Guild ID ayarlanmamış.");
            return;
        }
        Guild guild = jda.getGuildById(guildId);
        if (guild == null) {
            System.out.println("Guild bulunamadı: " + guildId);
            return;
        }

        guild.retrieveMemberById(discordId).queue(member -> {
            Role role = guild.getRoleById(roleId);
            if (role == null) {
                System.out.println("Rol bulunamadı: " + roleId);
                return;
            }

            System.out.println("Rol bulunuyor, ekleniyor: " + role.getName());
            guild.addRoleToMember(member, role).queue(
                    success -> System.out.println("Rol başarıyla verildi: " + role.getName()),
                    error -> System.out.println("Rol verilirken hata: " + error.getMessage())
            );
        }, error -> System.out.println("Üye bulunamadı veya hata oluştu: " + error.getMessage()));
    }




    public void send2FAConfirmationMessage(UUID playerUUID, String playerName, String newIpAddress) {
        String discordId = EslestirmeManager.getDiscordId(playerUUID);
        if (discordId == null) {
            logger.warning("2FA mesajı gönderilemedi: " + playerUUID + " için Discord ID bulunamadı.");
            return;
        }
        if (jda == null) return;

        jda.retrieveUserById(discordId).queue(user -> {
            if (user == null) return;

            Map<String, String> vars = new HashMap<>();
            vars.put("player", playerName);
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(MessageUtil.getMessage("discord-2fa-embed-title"))
                    .setDescription(MessageUtil.getMessage("discord-2fa-embed-description", vars))
                    .addField(MessageUtil.getMessage("discord-2fa-embed-field-ip"), "||" + newIpAddress + "||", false)
                    .setColor(Color.ORANGE)
                    .setFooter(MessageUtil.getMessage("discord-2fa-embed-footer"));

            String confirmId = "2fa_confirm_" + playerUUID.toString() + "_" + newIpAddress;
            String denyId = "2fa_deny_" + playerUUID;

            Button confirmButton = Button.success(confirmId, MessageUtil.getMessage("discord-2fa-button-confirm"));
            Button denyButton = Button.danger(denyId, MessageUtil.getMessage("discord-2fa-button-deny"));

            user.openPrivateChannel().queue(channel -> {
                channel.sendMessageEmbeds(embed.build()).setActionRow(confirmButton, denyButton).queue();
            });
        }, throwable -> {
            logger.warning("2FA onayı için " + discordId + " ID'li kullanıcıya DM gönderilemedi.");
        });
    }
    private boolean isUserOnCooldown(String userId, Cache<String, Long> cooldowns, long cooldownTimeSeconds, net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent event) {
        Long lastUsed = cooldowns.getIfPresent(userId);

        if (lastUsed != null) {
            long secondsSinceLastUse = (System.currentTimeMillis() - lastUsed) / 1000;

            if (secondsSinceLastUse < cooldownTimeSeconds) {
                long timeLeft = cooldownTimeSeconds - secondsSinceLastUse;

                Map<String, String> vars = new HashMap<>();
                vars.put("timeLeft", String.valueOf(timeLeft));
                event.reply(MessageUtil.getMessage("discord-cooldown-message", vars)).setEphemeral(true).queue();

                return true;
            }
        }

        return false;
    }

    private void setUserCooldown(String userId, Cache<String, Long> cooldowns) {
        cooldowns.put(userId, System.currentTimeMillis());
    }

    public void shutdown() {
        if (jda != null) jda.shutdownNow();
    }

    public JDA getJda() {
        return jda;
    }

    private void handleHesapDurumu(ButtonInteractionEvent event) {
        String discordId = event.getUser().getId();
        UUID uuid = EslestirmeManager.getUUIDByDiscordId(discordId);

        if (uuid == null) {
            event.reply(MessageUtil.stripColors(
                    MessageUtil.getMessage("discord-hesap-durumu.eslesmemis")
            )).setEphemeral(true).queue();
            return;
        }

        String playerName = Bukkit.getOfflinePlayer(uuid).getName();
        boolean is2FA = EslestirmeManager.isIkiFAOpen(uuid);

        long eslesmeMillis = EslestirmeManager.getEslesmeTarihi(uuid);
        long days = 0;
        if (eslesmeMillis > 0) {
            days = (System.currentTimeMillis() - eslesmeMillis) / (1000L * 60 * 60 * 24);
        }

        Map<String, String> vars = new HashMap<>();
        vars.put("days", String.valueOf(days));

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.embed-baslik")))
                .setColor(Color.CYAN)
                .addField(
                        MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.field-oyuncu")),
                        playerName != null ? playerName : "Bilinmiyor",
                        false
                )
                .addField(
                        MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.field-2fa")),
                        is2FA
                                ? MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.acik"))
                                : MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.kapali")),
                        false
                )
                .addField(
                        MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.field-tarih")),
                        MessageUtil.stripColors(
                                MessageUtil.getMessage("discord-hesap-durumu.field-tarih-deger", vars)
                        ),
                        false
                )
                .setFooter(MessageUtil.stripColors(MessageUtil.getMessage("discord-hesap-durumu.footer")));

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }




    private void handleEslesmeyiKaldir(ButtonInteractionEvent event) {
        String discordId = event.getUser().getId();
        UUID uuid = EslestirmeManager.getUUIDByDiscordId(discordId);

        if (uuid == null) {
            event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-kaldir-butonu.eslesmemis"))).setEphemeral(true).queue();
            return;
        }

        EslestirmeManager.kaldirEslesme(uuid);

        event.reply(MessageUtil.stripColors(MessageUtil.getMessage("discord-kaldir-butonu.basarili"))).setEphemeral(true).queue();
    }



    public void sendEslestirmeEmbed(UUID playerUUID, String discordId) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        String playerName = player.getName() != null ? player.getName() : "Bilinmiyor";

        String logChannelId = AgnesEsle.getInstance().getMainConfig().logChannelId;
        if (logChannelId == null || logChannelId.isEmpty()) {
            logger.warning("Log kanalı ID'si ayarlanmamış.");
            return;
        }

        TextChannel logChannel = jda.getTextChannelById(logChannelId);
        if (logChannel == null) {
            logger.warning("Log kanalı bulunamadı.");
            return;
        }

        String avatarUrl = "https://minotar.net/helm/" + playerName + "/100.png";

        Map<String, String> vars = new HashMap<>();
        vars.put("playerName", playerName);

        String title = MessageUtil.getMessage("log-message-embed.title");
        String playerFieldName = MessageUtil.getMessage("log-message-embed.player-field-name");
        String discordFieldName = MessageUtil.getMessage("log-message-embed.discord-field-name");
        String dateFieldName = MessageUtil.getMessage("log-message-embed.date-field-name");
        String securityFieldName = MessageUtil.getMessage("log-message-embed.security-field-name");
        String footer = MessageUtil.getMessage("log-message-embed.footer");

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(title)
                .setColor(Color.GREEN)
                .setThumbnail(avatarUrl)
                .addField(playerFieldName, playerName, true)
                .addField(discordFieldName, "<@" + discordId + ">", true)
                .addField(dateFieldName, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(java.time.LocalDateTime.now()), false)
                .addField(securityFieldName, EslestirmeManager.isIkiFAOpen(playerUUID) ? "2FA Aktif ✅" : "2FA Kapalı ❌", false)
                .setFooter(footer);

        logChannel.sendMessageEmbeds(embed.build()).queue();
    }
}