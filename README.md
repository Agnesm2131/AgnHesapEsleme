
![Agn](https://cdn.modrinth.com/data/cached_images/17f1bf40e839c4c357808e39cc7c7844877a365e_0.webp)

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
