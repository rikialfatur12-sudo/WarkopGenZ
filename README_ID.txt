WARKOP GEN Z ANDROID - PROYEK SUMBER

Ini adalah proyek Android Studio (source project), bukan APK yang sudah ditandatangani.
Cara membuat APK:
1. Buka folder WarkopGenZ_Android di Android Studio.
2. Tunggu Gradle Sync selesai (perlu internet untuk mengunduh Android Gradle Plugin).
3. Pilih Build > Build Bundle(s) / APK(s) > Build APK(s).
4. APK debug akan berada di app/build/outputs/apk/debug/app-debug.apk.

Fitur pembungkus:
- Membuka https://warkopsimulator.pages.dev/
- WebView mengaktifkan JavaScript, DOM Storage, database, dan cookies.
- Menu aplikasi menampilkan kredit @fathr.
- Donasi membuka https://sociabuzz.com/fiwvyp2.
- Lapor bug/saran membuka Instagram @fathr.
- Tidak ada tombol "Pasang Game di HP" di menu aplikasi ini.

BATASAN PENTING:
- Game tetap dimuat dari website pihak ketiga. Perubahan ini tidak mengubah kode website game.
- Auto-save hanya bisa berjalan jika website game sendiri menyimpan progres (misalnya ke localStorage/akun).
  WebView mengaktifkan DOM Storage agar penyimpanan lokal yang didukung situs dapat bekerja.
- Login Google/Cloud Save tidak dapat ditambahkan ke game dari pembungkus ini. Website game harus menyediakan autentikasi Google dan backend/cloud save. OAuth Google sering tidak mengizinkan login dari WebView tertanam; implementasi resmi memerlukan alur OAuth yang didukung situs.
- Menu developer adalah menu aplikasi Android, bukan menu pengaturan di dalam halaman game.
