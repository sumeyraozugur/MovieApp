# 🎬 MovieApp - Modern Android & MVI Architecture

**MovieApp**, güncel Android geliştirme pratikleriyle oluşturulmuş, film keşfetmeye odaklanan modern bir uygulamadır. Proje; **ölçeklenebilirlik** için **Multi-module**, **tutarlı state yönetimi** için **MVI (Model-View-Intent)** ve modern arayüz geliştirme için **Jetpack Compose** kullanır.

---

## 🚀 Proje Özellikleri

- ✅ Jetpack Compose ile modern UI
- ✅ MVI Architecture: State yönetimi için Unidirectional Data Flow (UDF).
- ✅ Multi-module: Özellik ve sorumluluk bazlı ayrıştırılmış mimari.
- ✅ Pagination: LazyColumn state takibi ile akıcı veri yükleme (Infinite Scroll).
- ✅ Coroutines & Flow: Asenkron veri akışı ve reaktif state güncellemeleri.
- ✅ Unit Test
- ✅ Maestro ile E2E test otomasyonu
- ✅ Semantics + testTag ile kararlı UI testleri


---

## 🏗 Mimari Yaklaşım

### 1) MVI (Model - View - Intent)

Uygulama, **Unidirectional Data Flow (UDF)** prensibini benimser.

Kullanıcı etkileşimleri (**Intent/Event**), merkezi bir **State** üretir ve UI sadece bu state'i render eder.

- **State**: UI'ın tek doğruluk kaynağıdır.
- **Event (Intent)**: Kullanıcı aksiyonları (Örn: `OnMovieClicked`, `OnRefresh`).
- **Effect**: Tek seferlik yan etkiler (Örn: navigasyon, toast).

---

## 🧩 Multi-module Yapısı

Proje, sorumlulukların ayrılması (**Separation of Concerns**) için özellik bazlı modüllere ayrılmıştır:

- `:core`  
  Tema yönetimi, ortak UI bileşenleri ve network konfigürasyonu

- `:domain`  
  Pure Kotlin business logic, modeller ve UseCase’ler

- `:data`  
  Repository implementasyonları ve API servisleri

- `:feature:main`  
  Film listeleme ekranı

- `:feature:detail`  
  Film detay ekranı ve mesaj gösterme fonksiyonelliği

---

## 🧪 Test Stratejisi (Maestro E2E)

Projenin güçlü yanlarından biri, kullanıcı senaryolarını uçtan uca otomatize eden **Maestro** testleridir.

Maestro, Jetpack Compose'un **Semantics** yapısını kullanarak kararlı ve hızlı testler sunar.

### ✅ Neden Semantics & testTag?

Compose projelerinde geleneksel yöntemlerle eleman bulmak zor olabilir. Bu projede:

- `testTagsAsResourceId = true` kullanılarak  
  Compose etiketleri (**testTag**) standart Android **ID**’lerine dönüştürülmüştür.

- `id: movie_item_0`
- `id: movie_item_1`
- `id: upcoming_slider`


## Screenshots

<p align="center">
  <img width="350" height="670" alt="Main Screen" src="https://github.com/user-attachments/assets/57be9ed6-1ad6-4018-bb76-edf8196402de" />
  <img width="350" height="670" alt="Detail Screen"  src="https://github.com/user-attachments/assets/85e85d4e-1a12-4ecd-abc4-ae5d6c73bda9"/>
</p>


