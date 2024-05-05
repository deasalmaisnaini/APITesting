# Proyek Pengujian API

Selamat datang di Proyek Pengujian API kami! Proyek ini difokuskan pada pengujian website DummyAPI menggunakan Java 11, Maven sebagai alat pengelolaan proyek, dan TestNG untuk memberikan laporan dan menjalankan pengujian.

## Daftar Isi
- [Tentang Proyek](#tentang-proyek)
- [Memulai](#memulai)
  - [Persyaratan](#persyaratan)
  - [Instalasi](#instalasi)
  - [Menjalankan Pengujian](#menjalankan-pengujian)
- [Kontributor](#kontributor)
- [Alat dan Teknologi](#alat-dan-teknologi)
- [Kontak](#kontak)

## Tentang Proyek

Proyek kami bertujuan untuk melakukan pengujian terhadap website Dummy API. Kami menggunakan Java 11 untuk menulis skrip otomasi pengujian, Maven untuk mengelola dependensi dan membangun proyek, serta TestNG sebagai kerangka pengujian untuk menjalankan pengujian terhadap berbagai endpoint API.

### Struktur Proyek
- `src/test/java`: Berisi kelas-kelas pengujian yang ditulis dengan TestNG untuk pengujian API.
- `pom.xml`: Berkas konfigurasi proyek Maven.

## Memulai

### Persyaratan
Sebelum memulai, pastikan Anda telah menginstal hal-hal berikut:
- Java JDK 11: [Unduh Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Maven: [Unduh Maven](https://maven.apache.org/download.cgi)
- Memiliki app-id yang diperoleh dari Web DummyAPI

### Instalasi
1. Clone repositori ini.
2. Buka proyek menggunakan IDE.

### Menjalankan Pengujian
Untuk menjalankan pengujian terhadap Dummy API, ikuti langkah-langkah berikut:

1. Buka terminal atau command prompt, dan navigasi ke direktori proyek.
2. Ubah app-id pada file test menggunakan app-id yang diperoleh dari web DummyAPI
3. Jalankan perintah Maven berikut untuk menjalankan semua kasus pengujian: `mvn test`
4. Setelah pengujian berhasil dieksekusi, Anda dapat melihat laporan pengujian di direktori `target/surefire-reports`.

### Menjalankan Pengujian dengan Rest Assured

Pengujian API menggunakan Rest Assured melibatkan tiga konsep utama:
- `given`: Untuk menyiapkan permintaan dengan mengatur header, parameter, dan body.
- `when`: Untuk menentukan aksi pada permintaan, seperti GET, POST, PUT, atau DELETE.
- `then`: Untuk memeriksa respons yang diterima dari permintaan, seperti status code dan isi body.

Contoh skrip pengujian dengan Rest Assured:

```java
given()
    .header("Content-Type", "application/json")
    .body("{ \"key\": \"value\" }")
.when()
    .post("/api/endpoint")
.then()
    .statusCode(200)
    .body("status", equalTo("success"));

## Laporan Pengujian

### Create User
Terdapat 54 test case pada operasi pembuatan pengguna (create user), dimana 8 test case mengalami kegagalan.

### Update User
Terdapat 29 test case pada operasi pembaruan pengguna (update user), dimana 16 test case mengalami kegagalan.

### Get User & Delete User
Terdapat 6 test case pada operasi pengambilan pengguna (get user) dan penghapusan pengguna (delete user), dimana keseluruhan test case berhasil dilalui.

## Kontributor

Proyek ini dikembangkan dan dikelola oleh kontributor berikut:
- [Dea Salma Isnaini](https://github.com/deasalmaisnaini)
- [Falia Davina Gustaman](https://github.com/faliadavina)
- [Syifa Khairina](https://github.com/syifakhairina)

## Alat dan Teknologi

Alat yang digunakan dalam proyek ini meliputi:

- [![Java 11](https://img.shields.io/badge/Java-11-red)](https://www.java.com/)
- [![Maven](https://img.shields.io/badge/Maven-Latest-blue)](https://maven.apache.org/)
- [![TestNG](https://img.shields.io/badge/TestNG-Latest-green)](https://testng.org/)
