# 🏢 Akıllı Bina Isı Önceliklendirme ve Enerji Optimizasyonu

Bu proje, bir bina içerisindeki odaların ısıtma ihtiyaçlarını **sınırlı enerji koşulları altında** en verimli şekilde karşılamayı amaçlayan bir **akıllı bina ısı yönetimi simülasyonudur**.

Sistem;  
- Odaların **öncelik değerlerini**,  
- **Hedef ve mevcut sıcaklıklarını**,  
- **Enerji maliyetlerini**,  
- **Zamana bağlı ısı kayıplarını**  

dikkate alarak enerji dağıtımı yapar.

Proje, **Greedy algoritmayı ana karar mekanizması** olarak kullanmakta, sonuçları **Dinamik Programlama (DP)** yaklaşımıyla karşılaştırarak analiz etmektedir.

---

## 🎯 Proje Amaçları

- Sınırlı enerji kaynağını **öncelikli odalara yönlendirmek**
- Enerji tüketimini ve maliyeti **minimize etmek**
- Gerçek hayata yakın bir şekilde **ısıtma–soğuma davranışını** simüle etmek
- Greedy algoritmanın etkinliğini **alternatif yöntemlerle karşılaştırmak**

---

## 🧠 Kullanılan Algoritmalar

### 🔹 Greedy Algoritma (Ana Algoritma)

Bu projede **öncelik / enerji ihtiyacı oranına dayalı Greedy algoritma** kullanılmıştır.

Her oda için aşağıdaki skor hesaplanır:

Her simülasyon adımında:
1. Odalar bu skora göre sıralanır
2. En yüksek skora sahip odaya enerji verilir
3. Enerji bitene kadar işlem devam eder

Bu yaklaşım, **Fractional Knapsack problemine benzer** bir greedy stratejidir ve gerçek zamanlı sistemler için uygundur.

---

### 🔹 Dinamik Programlama (Karşılaştırma Amaçlı)

Dinamik Programlama:
- Tüm olası enerji dağıtım kombinasyonlarını dikkate alır
- Teorik olarak **optimal çözümü** bulmayı hedefler

Bu projede DP:
- Greedy algoritmanın başarımını ölçmek
- Toplam enerji, maliyet ve ısınan oda sayısı açısından **karşılaştırma yapmak** amacıyla kullanılmıştır

> ⚠️ DP gerçek zamanlı simülasyonda kullanılmamış, analiz ve değerlendirme amacıyla tercih edilmiştir.

---

## ⏱️ Zaman Tabanlı Simülasyon

- Simülasyon **dakika bazlı** çalışır
- Her dakika:
  - Tüm odalar **dış sıcaklığa bağlı olarak soğur**
  - Enerji varsa, Greedy algoritma ile ısıtma yapılır
- Enerji bittiğinde:
  - Isıtma durur
  - Odalar yalnızca soğuma etkisi altında kalır

---

## ❄️ Isı ve Enerji Modeli

- Hava yoğunluğu ve özgül ısı değerleri kullanılarak enerji hesaplanır
- Dış ortam sıcaklığı modele dahil edilmiştir
- Odaların **minimum**, **konfor** ve **hedef sıcaklık aralıkları** vardır

---

## 💰 Enerji ve Maliyet Hesabı

- Enerji birimi: **kWh**
- Elektrik birim fiyatı tanımlıdır
- Simülasyon boyunca:
  - Toplam harcanan enerji
  - Toplam maliyet
  GUI üzerinden canlı olarak gösterilir

---

## 📊 Greedy vs Normal Karşılaştırması

Proje içerisinde:
- **Greedy algoritma**
- **Normal (eşit enerji dağıtımı) yaklaşımı**

karşılaştırılmıştır.

Karşılaştırma kriterleri:
- Isınan oda sayısı
- Toplam harcanan enerji
- Toplam maliyet
- Toplam öncelik değeri

Sonuçlar grafiklerle görselleştirilmiştir.

---

## 🖥️ Grafik Arayüz (JavaFX)

- Her oda bir **kart (card)** olarak gösterilir
- Renkler:
  - 🟢 Isıtılan oda
  - 🔴 Isıtılmayan / soğuyan oda
- Canlı sıcaklık, enerji ve maliyet takibi
- JSON ile **kaydet / yükle** desteği

---

## 🛠️ Kullanılan Teknolojiler

- **Java**
- **JavaFX**
- **Gson (JSON işlemleri)**
- Greedy Algoritma
- Dinamik Programlama (analiz amaçlı)

---

## 📌 Sonuç

Bu projede:
- Greedy algoritmanın gerçek zamanlı enerji dağıtımı için **uygun ve etkili** olduğu
- Dinamik Programlama’nın teorik olarak daha optimal olsa da **maliyetli** olduğu
- Greedy yaklaşımın pratikte **daha uygulanabilir** olduğu gösterilmiştir

---

## 🚀 Geliştirilebilir Özellikler

- Gerçek hava durumu entegrasyonu
- Makine öğrenmesi ile dinamik öncelik belirleme
- Mobil / web arayüz
- Farklı enerji kaynakları (güneş, batarya vb.)

---

## 👤 Geliştirici

Bu proje bir **bilgisayar mühendisliği algoritmalar dersi** kapsamında geliştirilmiştir.
