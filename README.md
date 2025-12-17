
=======
![Agn](https://cdn.modrinth.com/data/cached_images/17f1bf40e839c4c357808e39cc7c7844877a365e_0.webp)

![Agn2](https://media.discordapp.net/attachments/1450885204326748181/1450916515590307850/OvqKgCB.png?ex=694446ce&is=6942f54e&hm=d6414bbec32244c0c91c1b007c6f9bf71ff314718569f59cccd0d072188d78fe&=&format=webp&quality=lossless&width=1229&height=819)
![Agn3](https://media.discordapp.net/attachments/1450885204326748181/1450916501400850533/MYzMpz3.png?ex=694446ca&is=6942f54a&hm=f025da0da6e45fd947fe6c280f92b0a89b8f1046fb90452286af6a0d875e6021&=&format=webp&quality=lossless&width=1229&height=819)



## Gereksinimler

- Java 8/17
- Spigot/Paper/Bukkit/Purpur
- LuckPerms


## **ÖZELLİKLER:** (TR)

- **Otomatik Bilgilendirme Sistemi:** Eklenti, Minecraft sunucusunda gerçekleşen belirli olayları otomatik olarak Discord sunucusuna bildirir. Bu sistem sayesinde oyuncu aktiviteleri, eşleşme durumu ve özel bildirimler Discord üzerinden ilgili kanallara gönderilir. Bildirimler, config.yml dosyası aracılığıyla özelleştirilebilir ve kanal bazlı yönlendirme desteklenir.


- **2FA(İki Aşamalı Doğrulama Sistemi):** Hesabınıza farklı bir IP adresinden giriş yapıldığında, güvenliğiniz için Discord üzerinden size bir doğrulama mesajı gönderilir. Bu durumda, dilerseniz giriş yapan oyuncuyu sunucudan atabilir ya da doğrulama mesajını onaylayarak devam etmesine izin verebilirsiniz.

- **Dahili Gömülü Discord Botu:** Eklenti, harici bir bot kurulumuna gerek kalmadan kendi içerisinde çalışan bir entegre Discord botu barındırır.
Bu sayede sistem, herhangi bir 3. parti uygulamaya ihtiyaç duymadan bağımsız olarak çalışabilir.
Bot, Discord API üzerinden:
Mesaj gönderme
Rol atama
Kullanıcı adı güncelleme
Onay sistemi yönetimi
gibi işlemleri doğrudan gerçekleştirebilir.

- **Onaylı Kullanıcı Eşleştirme Sistemi:** Kullanıcıların Minecraft hesaplarını Discord hesaplarıyla bağlamadan önce bir onay sürecinden geçmeleri gerekmektedir. Bu sistem, sunucuda yetkisiz kullanıcı eşleşmelerinin önüne geçmek ve güvenliği artırmak için geliştirilmiştir.

- **6 Adet Dil Desteği:**
   - Çince (Chinese)
   - Türkçe (Turkish)
   - İngilizce (English)
   - Fransızca (French)
   - İspanyolca (Spanish)
   - Almanca (German)

- **Kullanıcı Adı Güncelleme Sistemi:** Başarılı eşleştirme sonrasında, kullanıcının Discord üzerindeki görünen adı otomatik olarak Minecraft kullanıcı adı ile güncellenir.
Bu özellik:
Sunucu içi düzeni artırır
Oyuncuların kimlik takibini kolaylaştırır
Anonim kullanıcı adlarının önüne geçer
Yapılandırma dosyası üzerinden bu özellik aktif/pasif hale getirilebilir.

- **Rol Atama ve Yetkilendirme Sistemi:** Eklenti, oyuncu eşleşmesini tamamladıktan sonra kullanıcının sahip olduğu özel üyelik durumuna göre Discord üzerindeki belirli rolleri otomatik olarak atar.
Rol tanımlamaları config.yml üzerinden yapılır ve şu kurallar desteklenir:
VIP, Premium, Elit gibi özel üyelik sistemleri
Çoklu rol desteği (birden fazla rol aynı anda atanabilir)

###   Kurulum Talimatları

Yayınlanan jar dosyasını plugins/ klasörüne kopyalayın.
Sunucuyu başlatın ve plugins/AgnHesapEsle/ altında config.yml oluşturulmasına izin verin.
Gerekirse config.yml dosyasını düzenleyin.

### Komutlar

- **/hesapeşle eşle**: ➤ Discord botuna gönderilecek olan eşleştirme kodunu görüntüler.
- **/hesapeşle kaldır**: ➤ Mevcut eşleştirme bağlantısını kaldırı
- **/hesapeşle yenile**: ➤ messages.yml dosyasını yeniden yükler.
- **/hesapeşle kodiptal**: ➤ Onay sürecindeki kodunuzu iptal eder.
- **/hesapeşle liste** ➤ Hesabını eşleyen oyuncuların listesini gösterir.
- **/hesapeşle sıfırla OyuncuAdı** ➤ Belirtilen oyuncunun eşleşme bağlantısını sıfırlar.

### PLACEHOLDER
- **%agnesesle_server_toplam_eslesme%**: Sunucudaki, Toplam Eşleşme Sayısını Gösterir
- **%agnesesle_durum%**: Oyuncunun Eşledi mi, Eşlemedi mi Durumunu Gösterir.
- **%agnesesle_discord_id%**: Kullanıcının Discord Id'sini gösterir.
- **%agnesesle_discord_adi%**: Oyuncunun Discord Üzerinde'ki Adını Gösterir.
- **%agnesesle_2fa_durum%**: Oyuncunun 2FA(İki Aşamalı Doğrulama) Durumunu gösterir.


### İzinler

```
agnesesle.admin (Tüm Komutlara Erişim Sağlar.)
```
### Veritabanı
- Veri Tabana Dosya .yml Üzeridir İleri Zamanlarda Geçiş Yapılacaktır.

### Config Yapılandırması:


```
token: "DISCORD_BOT_TOKEN" #Discord Bot Token
log-kanal-id: "log-kanal-id" # LOG KANAL İD
yetkili-rol-id: "yetkili-rol-id" #YETKILI ROL ID
durum-mesajlari: #BOTUN DURUMUNDA YAZACAK YAZILAR
  - "SunucuAdi.net"
  - "HesapEşlemeSistemi"
  - "{aktifkullanici} Oyuncu Aktif"
  - 
oduller: #HESABINI EŞLEDİĞİNDE CALISTIRILCAK KOMUTLAR (ODULLER)
  - "give %player% minecraft:diamond 5"
  - "say Tebrikler %player% hesabını eşleştirdi!"
  - "experience add %player% 100 points"
  - 
discord: 
  bilgilendirme-kanal-id: "" #bilgilendirme mesajının gönderileceği kanal id
  guild-id: "" #Discord Guild İd Giriniz.

# Dil seçimi: tr, en, es, fr, de, zh
lang: tr 

roller:
  vip-rol-id: ""
  vipplus-rol-id: ""
  mvip-rol-id: ""
  mvipplus-rol-id: ""

oyuncular:
  2fa:
    "uuid-string": true

config-version: 1.2
```

  ### Messages.yml Dosyası

  
```
hesap-zaten-eslesmis:
  title: "§cZaten Eşleşmiş"
  subtitle: "§7Bir Discord hesabı ile zaten eşleşmişsin."

kod-lutfen-bekleyin:
  title: "§cBekleyin"
  subtitle: "§7Lütfen önceki kodun süresinin dolmasını bekle."

kod-verildi:
  title: "§aKod Verildi"
  subtitle: "§7Kodunuz: §f%kod%"

eslesme-onayi-beklemiyor:
  title: "§cBekleyen Kod Yok"
  subtitle: "§7Eşleşme onayı bekleyen bir kod yok."

eslesme-onaylanamadi:
  title: "§cBaşarısız"
  subtitle: "§7Eşleşme onaylanamadı."

eslesme-iptal-edilemedi:
  title: "§cİptal Edilemedi"
  subtitle: "§7Bekleyen bir kod bulunamadı veya bir hata oluştu."

eslesme-basariyla-tamamlandi:
  title: "§aBaşarılı"
  subtitle: "§7Hesabınız başarıyla eşleşti!"

eslesme-zaten-bekliyor:
  title: "§cOnay Bekleniyor"
  subtitle: "§7Zaten bir onay bekliyorsun. Lütfen /hesapeşle onayla yaz."

kaldirildi:
  title: "§cEşleşme Kaldırıldı"
  subtitle: "§7Eşleşmeniz kaldırıldı."

yenilendi:
  title: "§aYenilendi"
  subtitle: "§7Mesajlar yeniden yüklendi."

bilinmeyen-komut:
  title: "§cBilinmeyen Komut"
  subtitle: "§7Lütfen geçerli bir komut kullanın."

hesap-esle-kodiptal:
  title: "§cKod İptal Edildi"
  subtitle: "§7Eşleştirme kodunuz iptal edildi."

yetki-yok:
  title: "§cYetki Yok"
  subtitle: "§7Bu komutu kullanmak için yetkin yok."

hatalı-kullanim:
  title: "§cHatalı Kullanım"
  subtitle: "§7Lütfen komutu doğru biçimde kullanın."

oyuncu-bulunamadi:
  title: "§cOyuncu Bulunamadı"
  subtitle: "§7Böyle bir oyuncu bulunamadı."

eslesme-yok:
  title: "§cEşleşme Yok"
  subtitle: "§7Bu oyuncunun eşleşmesi yok."

title-baslik:
  title: "§aEşleşme Başarılı!"
  subtitle: "§7Ödüllerin Teslim Edildi."

title-alt:
  title: "§aHesapEşle"
  subtitle: "§7Minecraft sunucunuzda kaliteli hizmet."

# Genel Mesajlar
only-for-players: "&cBu komut sadece oyuncular tarafından kullanılabilir."
reward-message: "&aTebrikler! Hesabını başarıyla eşleştirdin, ödül kazandın!"

# 2FA Mesajları
2fa-usage-help: "&cLütfen /hesapeşle 2fa aç veya /hesapeşle 2fa kapat yazın."
2fa-already-enabled: "&e2FA zaten açık."
2fa-successfully-enabled: "&a2FA başarıyla açıldı."
2fa-already-disabled: "&e2FA zaten kapalı."
2fa-successfully-disabled: "&c2FA kapatıldı."
2fa-not-linked: "&cHesabın eşleşmemiş olduğu için 2FA özelliğini kullanamazsın."

# Admin Mesajları
no-matches-yet: "&cHenüz hiç eşleşme yok."
list-header: "&6---- [Eşleşme Listesi Sayfa %page%/%maxPage%] ----"
list-entry: "&e%player% &7- Discord ID: &a%discordId%"
list-footer: "&7Sayfa değiştirmek için: /hesapeşle liste <sayfa>"
player-match-reset: "&a%player% isimli oyuncunun eşleşmesi sıfırlandı."

# Kick Mesajları
kick-2fa-ip-changed: |
  &c&lGÜVENLİK UYARISI
  
  &eFarklı bir konumdan giriş yapılıyor!
  &fLütfen onay için Discord hesabını kontrol et.

# ===============================================
# Discord Metinleri (Renk kodları kullanılmaz!)
# ===============================================

# Bot Durumu ve Komut Açıklamaları
discord-bot-status-starting: "Başlatılıyor..."
discord-command-description-esle: "Minecraft hesabını eşleştir"
discord-command-option-code: "Eşleştirme kodun"
discord-command-description-raporla: "Bir oyuncuyu yetkililere bildir"
discord-command-option-player: "Raporlamak istediğin oyuncunun adı"
discord-command-option-reason: "Raporlama sebebin"
discord-command-description-info: "Bir Discord kullanıcısının eşleşmiş olduğu Minecraft hesabını gösterir."
discord-command-option-user: "Bilgisine bakılacak Discord kullanıcısı"
discord-info-reply-no-match: "Bu kullanıcının eşleşmiş bir Minecraft hesabı yok."
discord-info-reply-success: "Bu kullanıcı **%player%** adlı Minecraft hesabıyla eşleşmiş."

# Bilgilendirme Kanalı Embed Mesajı
discord-info-embed-title: "📘 AgnEşle - Hesap Eşleştirme"
discord-info-embed-description: |
  Merhaba! Minecraft hesabını Discord ile eşleştirerek özel ödüller kazanabilirsin!
  
  ➤ Sunucuya gir ve `/hesapeşle eşle` yazarak kodunu al.
  ➤ Aşağıdaki butona tıkla ve kodunu gir.
  ➤ Son olarak Minecraft'ta `/hesapeşle onayla` yazarak işlemi tamamla!
discord-info-embed-footer: "AgnHesapEşle Sistemi"
discord-info-button-label: "🔗 Eşleş"

# Eşleşme Modalı (Açılır Pencere)
discord-modal-title: "Eşleşme Kodunu Gir"
discord-modal-textfield-label: "Eşleştirme Kodu"
discord-modal-textfield-placeholder: "Örn: X7Y2-Z9"

# 2FA Buton Etkileşimleri
discord-2fa-confirm-reply: "✅ Giriş talebi başarıyla onaylandı. Artık sunucuya giriş yapabilirsin."
discord-2fa-confirm-message-edit: "Bu giriş talebi **onaylandı**."
discord-2fa-deny-reply: "❌ Giriş talebi reddedildi. Hesabınız güvende. Şifrenizi değiştirmenizi öneririz."
discord-2fa-deny-message-edit: "Bu giriş talebi **reddedildi**."

# Raporlama Buton Etkileşimleri
discord-button-invalid-id: "Hatalı buton ID!"
discord-button-no-permission: "❌ Bu butonu sadece yetkililer kullanabilir."
discord-button-player-not-found: "Oyuncu bulunamadı!"
discord-button-control-kick-reason: "Yetkili tarafından kontrol için çağrılıyorsun."
discord-button-control-reply: "%player% adlı oyuncu (%discordId%) kontrol için sunucudan atıldı."
discord-button-ban-reason: "Discord üzerinden banlandı."
discord-button-ban-kick-reason: "Discord Üzerinden Yetkili Tarafından Banlandınız."
discord-button-ban-reply: "%player% adlı oyuncu banlandı."

# Slash Komut Cevapları
discord-cooldown-message: "❌ Bu komutu çok sık kullanıyorsun. Lütfen **%timeLeft% saniye** sonra tekrar dene."
discord-invalid-code: "❌ Geçersiz kod."
discord-already-linked-mc: "❌ Bu Minecraft hesabı zaten başka bir hesapla eşleşmiş."
discord-already-linked-discord: "❌ Bu Discord hesabı zaten başka bir Minecraft hesabıyla eşleşmiş veya onay bekliyor."
discord-generic-error: "❌ Eşleştirme başarısız oldu. Lütfen tekrar deneyin."
discord-success: "✅ Kod doğru! Şimdi Minecraft'ta `/hesapeşle onayla` yazarak eşleşmeyi tamamla."
discord-report-channel-not-set: "❌ Rapor kanalı ayarlanmamış, işlem başarısız."
discord-report-channel-not-found: "❌ Rapor kanalı bulunamadı, işlem başarısız."
discord-report-embed-title: "🚨 Yeni Oyuncu Raporu"
discord-report-embed-field-player: "Raporlanan Oyuncu"
discord-report-embed-field-reason: "Raporlama Sebebi"
discord-report-embed-footer: "Raporlayan: %reporter%"
discord-report-success: "✅ Raporun başarıyla yetkililere iletildi."

# 2FA DM Embed Mesajı
discord-2fa-embed-title: "🔒 Güvenlik Uyarısı: Yeni Giriş Talebi"
discord-2fa-embed-description: "**%player%** adlı Minecraft hesabınıza yeni bir konumdan giriş yapılmaya çalışılıyor."
discord-2fa-embed-field-ip: "Giriş Yapılan IP Adresi"
discord-2fa-embed-footer: "Bu işlemi siz yapmadıysanız kesinlikle onaylamayın."
discord-2fa-button-confirm: "Evet, Bu Bendim (Onayla)"
discord-2fa-button-deny: "Hayır, Bu Ben Değildim (Reddet)"

# Ödül Mesajları
odul-not-linked: "&cBu oyuncunun hesabı eşleşmemiş."
odul-already-given: "&eBu oyuncu ödülünü zaten almış."
odul-given-success: "&a%player% adlı oyuncuya ödül başarıyla verildi."
```

### İletişim


<details>
<summary>İletişim</summary>

Discord: **agnes9s_**

</details>



### ENG

## Requirements
- Java 8/17  
- Spigot/Paper/Bukkit/Purpur  
- LuckPerms

### FEATURES: (EN)

- **Automatic Notification System:**  
  The plugin automatically reports specific events happening on the Minecraft server to the Discord server. Through this system, player activities, match status, and special notifications are sent to relevant channels on Discord. Notifications can be customized via the `config.yml` file and support channel-based routing.

- **2FA (Two-Factor Authentication System):**  
  When someone logs into your account from a different IP address, a verification message is sent to you via Discord for security purposes. You can either kick the player from the server or approve the login via the verification message.

- **Built-in Embedded Discord Bot:**  
  The plugin includes a built-in integrated Discord bot, eliminating the need for an external bot setup.  
  This allows the system to work independently without any third-party applications.  
  The bot can directly perform operations such as:
  - Sending messages  
  - Assigning roles  
  - Updating usernames  
  - Managing the verification system

- **Verified User Matching System:**  
  Before users can link their Minecraft accounts to their Discord accounts, they must go through a verification process. This system is designed to prevent unauthorized user matches and enhance security on the server.

- **Support for 6 Languages:**
   - Chinese  
   - Turkish  
   - English  
   - French  
   - Spanish  
   - German

- **Username Update System:**  
  After a successful account match, the user’s visible name on Discord is automatically updated to match their Minecraft username.  
  This feature:
    - Improves in-server order  
    - Simplifies player identification  
    - Prevents anonymous usernames  
  It can be enabled/disabled via the configuration file.

- **Role Assignment and Authorization System:**  
  After matching, the plugin automatically assigns specific roles on Discord based on the player's membership status (e.g., VIP, Premium, Elite).  
  Role definitions are managed via `config.yml` and support:
    - Special membership roles  
    - Multi-role assignment

 ## Installation Instructions

 1. Copy the released `.jar` file into the `plugins/` folder.  
2. Start the server and allow the plugin to generate the `config.yml` under `plugins/AgnHesapEsle/`.  
3. Edit the `config.yml` file if necessary.

## Commands

- **/hesapeşle eşle**: ➤ Displays the match code to be sent to the Discord bot.  
- **/hesapeşle kaldır**: ➤ Removes the current match link.  
- **/hesapeşle yenile**: ➤ Reloads the `messages.yml` file.  
- **/hesapeşle kodiptal**: ➤ Cancels the pending verification code.  
- **/hesapeşle liste** ➤ Displays the list of players who have matched their accounts.  
- **/hesapeşle sıfırla <PlayerName>** ➤ Resets the match link of the specified player.

## PLACEHOLDER
- **%agnesesle_server_toplam_eslesme%**: Displays the total number of matches on the server.
- **%agnesesle_durum%**: Shows whether the player has matched or not.
- **%agnesesle_discord_id%**: Displays the user's Discord ID.
- **%agnesesle_discord_adi%**: Shows the player's name on Discord.
- **%agnesesle_2fa_durum%**: Displays the player's 2FA (Two-Factor Authentication) status.

## Contact

<details>
<summary>Contact</summary>

Discord: **agnes9s_**

</details>



## Permissions
- agnesesle.admin (Grants access to all commands.)

## Database
- Data is stored in `.yml` files. Migration to a different system may be implemented in the future.

## Config.yml

```
token: "DISCORD_BOT_TOKEN" #Discord Bot Token
log-kanal-id: "log-kanal-id" # LOG KANAL İD
yetkili-rol-id: "yetkili-rol-id" #YETKILI ROL ID
durum-mesajlari: #BOTUN DURUMUNDA YAZACAK YAZILAR
  - "SunucuAdi.net"
  - "HesapEşlemeSistemi"
  - "{aktifkullanici} Oyuncu Aktif"
  -
oduller: #HESABINI EŞLEDİĞİNDE CALISTIRILCAK KOMUTLAR (ODULLER)
  - "give %player% minecraft:diamond 5"
  - "say Tebrikler %player% hesabını eşleştirdi!"
  - "experience add %player% 100 points"
  -
discord:
  bilgilendirme-kanal-id: "" #bilgilendirme mesajının gönderileceği kanal id
  guild-id: "" #Discord Guild İd Giriniz.

# Dil seçimi: tr, en, es, fr, de, zh
lang: tr

roller:
  vip-rol-id: ""
  vipplus-rol-id: ""
  mvip-rol-id: ""
  mvipplus-rol-id: ""

oyuncular:
  2fa:
    "uuid-string": true

config-version: 1.2
```

## MESSAGES.YML

```
account-already-matched:
  title: "§cAlready Linked"
  subtitle: "§7You are already linked with a Discord account."

code-please-wait:
  title: "§cPlease Wait"
  subtitle: "§7Please wait for the previous code to expire."

code-provided:
  title: "§aCode Provided"
  subtitle: "§7Your code: §f%code%"

match-confirmation-not-pending:
  title: "§cNo Pending Code"
  subtitle: "§7There is no code awaiting link confirmation."

match-could-not-be-confirmed:
  title: "§cFailed"
  subtitle: "§7The link could not be confirmed."

match-could-not-be-canceled:
  title: "§cCancellation Failed"
  subtitle: "§7No pending code was found or an error occurred."

match-successfully-completed:
  title: "§aSuccess"
  subtitle: "§7Your account has been successfully linked!"

match-already-pending:
  title: "§cConfirmation Pending"
  subtitle: "§7You are already waiting for a confirmation. Please type /accountlink confirm."

unlinked:
  title: "§cLink Removed"
  subtitle: "§7Your link has been removed."

reloaded:
  title: "§aReloaded"
  subtitle: "§7Messages have been reloaded."

unknown-command:
  title: "§cUnknown Command"
  subtitle: "§7Please use a valid command."

account-link-code-canceled:
  title: "§cCode Canceled"
  subtitle: "§7Your linking code has been canceled."

no-permission:
  title: "§cNo Permission"
  subtitle: "§7You do not have permission to use this command."

incorrect-usage:
  title: "§cIncorrect Usage"
  subtitle: "§7Please use the command correctly."

player-not-found:
  title: "§cPlayer Not Found"
  subtitle: "§7No such player was found."

no-match:
  title: "§cNo Link"
  subtitle: "§7This player is not linked."

title-header:
  title: "§aLink Successful!"
  subtitle: "§7Your rewards have been delivered."

title-footer:
  title: "§aAccountLink"
  subtitle: "§7Quality service on your Minecraft server."

# General Messages
only-for-players: "&cThis command can only be used by players."
reward-message: "&aCongratulations! You have successfully linked your account and earned a reward!"

# 2FA Messages
2fa-usage-help: "&cPlease type /accountlink 2fa enable or /accountlink 2fa disable."
2fa-already-enabled: "&e2FA is already enabled."
2fa-successfully-enabled: "&a2FA successfully enabled."
2fa-already-disabled: "&e2FA is already disabled."
2fa-successfully-disabled: "&c2FA disabled."
2fa-not-linked: "&cYou cannot use the 2FA feature because your account is not linked."

# Admin Messages
no-matches-yet: "&cThere are no links yet."
list-header: "&6---- [Link List Page %page%/%maxPage%] ----"
list-entry: "&e%player% &7- Discord ID: &a%discordId%"
list-footer: "&7To change the page: /accountlink list <page>"
player-match-reset: "&aThe link for the player %player% has been reset."

# Kick Messages
kick-2fa-ip-changed: |
  &c&lSECURITY WARNING
  
  &eA login is being attempted from a different location!
  &fPlease check your Discord account for confirmation.

# ===============================================
# Discord Texts (Color codes are not used!)
# ===============================================

# Bot Status and Command Descriptions
discord-bot-status-starting: "Starting..."
discord-command-description-link: "Link your Minecraft account"
discord-command-option-code: "Your linking code"
discord-command-description-report: "Report a player to the staff"
discord-command-option-player: "The name of the player you want to report"
discord-command-option-reason: "The reason for your report"
discord-command-description-info: "Shows the linked Minecraft account of a Discord user."
discord-command-option-user: "The Discord user to look up"
discord-info-reply-no-match: "This user does not have a linked Minecraft account."
discord-info-reply-success: "This user is linked to the Minecraft account **%player%**."

# Information Channel Embed Message
discord-info-embed-title: "📘 AgnLink - Account Linking"
discord-info-embed-description: |
  Hello! You can earn special rewards by linking your Minecraft account with Discord!
  
  ➤ Join the server and type `/accountlink link` to get your code.
  ➤ Click the button below and enter your code.
  ➤ Finally, complete the process by typing `/accountlink confirm` in Minecraft!
discord-info-embed-footer: "AgnAccountLink System"
discord-info-button-label: "🔗 Link"

# Linking Modal (Popup Window)
discord-modal-title: "Enter Your Linking Code"
discord-modal-textfield-label: "Linking Code"
discord-modal-textfield-placeholder: "Ex: X7Y2-Z9"

# 2FA Button Interactions
discord-2fa-confirm-reply: "✅ Login request successfully approved. You can now log into the server."
discord-2fa-confirm-message-edit: "This login request was **approved**."
discord-2fa-deny-reply: "❌ Login request denied. Your account is secure. We recommend changing your password."
discord-2fa-deny-message-edit: "This login request was **denied**."

# Report Button Interactions
discord-button-invalid-id: "Invalid button ID!"
discord-button-no-permission: "❌ Only staff members can use this button."
discord-button-player-not-found: "Player not found!"
discord-button-control-kick-reason: "You are being summoned for a check by a staff member."
discord-button-control-reply: "The player %player% (%discordId%) has been kicked from the server for a check."
discord-button-ban-reason: "Banned via Discord."
discord-button-ban-kick-reason: "You have been banned by a staff member via Discord."
discord-button-ban-reply: "The player %player% has been banned."

# Slash Command Replies
discord-cooldown-message: "❌ You are using this command too frequently. Please try again in **%timeLeft% seconds**."
discord-invalid-code: "❌ Invalid code."
discord-already-linked-mc: "❌ This Minecraft account is already linked to another account."
discord-already-linked-discord: "❌ This Discord account is already linked to another Minecraft account or is pending confirmation."
discord-generic-error: "❌ Linking failed. Please try again."
discord-success: "✅ The code is correct! Now, complete the link by typing `/accountlink confirm` in Minecraft."
discord-report-channel-not-set: "❌ The report channel is not set, the operation failed."
discord-report-channel-not-found: "❌ The report channel could not be found, the operation failed."
discord-report-embed-title: "🚨 New Player Report"
discord-report-embed-field-player: "Reported Player"
discord-report-embed-field-reason: "Reason for Report"
discord-report-embed-footer: "Reported by: %reporter%"
discord-report-success: "✅ Your report has been successfully sent to the staff."

# 2FA DM Embed Message
discord-2fa-embed-title: "🔒 Security Alert: New Login Attempt"
discord-2fa-embed-description: "A login attempt to your Minecraft account **%player%** is being made from a new location."
discord-2fa-embed-field-ip: "Login IP Address"
discord-2fa-embed-footer: "If you did not initiate this action, do not approve it."
discord-2fa-button-confirm: "Yes, It Was Me (Approve)"
discord-2fa-button-deny: "No, It Wasn't Me (Deny)"

# Reward Messages
reward-not-linked: "&cThis player's account is not linked."
reward-already-given: "&eThis player has already received their reward."
reward-given-success: "&aThe reward was successfully given to the player %player%."
```


>>>>>>> 3528b16 (günlük ödül sistemi eklendi, hatalar giderildi)
