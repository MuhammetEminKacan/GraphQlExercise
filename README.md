## 🚀 SpaceX Launch Tracker (GraphQL)
SpaceX Launch Tracker, SpaceX’in GraphQL API’si kullanılarak geliştirilmiş bir mobil uygulamadır.
Uygulama, geçmiş SpaceX görevlerini ve görev detaylarını listeleyerek kullanıcıya kapsamlı bir uzay keşfi deneyimi sunar.

## 🚀 Proje Hakkında
Bu uygulama, GraphQL teknolojisini kullanarak veri çekme ve yönetme üzerine geliştirilmiştir.
- SpaceX’in GraphQL API’si kullanılmıştır.
- Geçmiş uzay görevleri listelenir.
- Her görev için detaylı bilgiler sunulur.
- SpaceX şirketi hakkında genel bilgilere erişilebilir.

Bu proje ile hedefim:
- GraphQL kullanımını öğrenmek ve uygulamak.
- Modern Android mimarileri ile veri yönetimini geliştirmek.
- API tüketimi konusunda farklı yaklaşımlar (REST vs GraphQL) deneyimlemek.

## 🧩 Özellikler
- 🚀 Geçmiş SpaceX görevlerini listeleme.
- 📄 Görev detaylarını görüntüleme.
- 🏢 SpaceX şirket bilgilerini görüntüleme.
- 🖼️ Görev görsellerini listeleme (Coil).
- ⚡ Hızlı ve optimize veri çekme (GraphQL).
- 🎯 Tek sorguda ihtiyaç duyulan veriyi alma.

## 🛠️ Kullanılan Teknolojiler
- Kotlin.
- Jetpack Compose.
- Material 3.
- Clean Architecture.
- MVI (Model–View–Intent).
- Coroutines.
- Ktor (GraphQL client işlemleri).
- OkHttp Logging Interceptor.
- Kotlin Serialization.
- Dagger Hilt.
- Coil.
- Navigation Compose (Navigation 3).
- Material Icons.

## 🏗️ Mimari Yapı
Proje, modern Android geliştirme prensiplerine uygun olarak tasarlanmıştır.
- MVI Architecture.
UI state yönetimi tek yönlü veri akışı ile sağlanır.
- Clean Architecture.
Katmanlar bağımsız, test edilebilir ve sürdürülebilir yapıdadır.

Katmanlar:
- Presentation.
UI ve state yönetimi.
- Domain.
İş kuralları ve use-case’ler.
- Data.
GraphQL sorguları ve veri yönetimi.

## 🌐 Veri Kaynağı
Uygulama, SpaceX tarafından sağlanan GraphQL API üzerinden veri almaktadır.
- Tek endpoint üzerinden esnek veri çekimi.
- İhtiyaç duyulan alanların seçilerek optimize veri kullanımı.
