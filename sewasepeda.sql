-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Okt 2019 pada 18.01
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sewasepeda`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `Idplgn` int(11) NOT NULL,
  `nmplgn` varchar(255) DEFAULT NULL,
  `almtplgn` varchar(255) DEFAULT NULL,
  `umurplgn` varchar(10) DEFAULT NULL,
  `jkplgn` varchar(10) DEFAULT NULL,
  `nohpplgn` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`Idplgn`, `nmplgn`, `almtplgn`, `umurplgn`, `jkplgn`, `nohpplgn`) VALUES
(11, 'Bunga', 'Jakarta Barat', '21', 'Wanita', '085678453788'),
(16, 'Rizki', 'Jakarta Selatan', '25', 'Pria', '089766765562');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemilik`
--

CREATE TABLE `pemilik` (
  `Idpemilik` int(11) NOT NULL,
  `nmpemilik` varchar(50) DEFAULT NULL,
  `almtpemilik` varchar(50) DEFAULT NULL,
  `jkpemilik` varchar(10) DEFAULT NULL,
  `umurpemilik` varchar(10) DEFAULT NULL,
  `notlpnpemilik` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemilik`
--

INSERT INTO `pemilik` (`Idpemilik`, `nmpemilik`, `almtpemilik`, `jkpemilik`, `umurpemilik`, `notlpnpemilik`) VALUES
(10, 'Panjul', 'Kemang Raya', 'Wanita', '17', '089600001234'),
(12, 'Hamas', 'Tangerang Selatan', 'Pria', '22', '0923963568465'),
(13, 'Jaky', 'Jakarta Pusat', 'Pria', '20', '089873678811'),
(14, 'Bany', 'Jawa Barat', 'Pria', '17', '0897984653345');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sepeda`
--

CREATE TABLE `sepeda` (
  `Idsepeda` int(11) NOT NULL,
  `Idpemilik` int(11) NOT NULL DEFAULT '0',
  `merk` varchar(255) DEFAULT NULL,
  `jenis` varchar(255) DEFAULT NULL,
  `warna` varchar(20) DEFAULT NULL,
  `thnproduksi` varchar(5) DEFAULT NULL,
  `kategori` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sepeda`
--

INSERT INTO `sepeda` (`Idsepeda`, `Idpemilik`, `merk`, `jenis`, `warna`, `thnproduksi`, `kategori`) VALUES
(36, 10, 'United', 'Santai', 'Pink', '2017', 'Dewasa'),
(37, 14, 'Wimcycle', 'Santai', 'Biru', '2017', 'Anak'),
(38, 12, 'Polygon', 'Gunung', 'biru', '2017', 'Dewasa');

-- --------------------------------------------------------

--
-- Struktur dari tabel `trsepeda`
--

CREATE TABLE `trsepeda` (
  `Idtransaksi` int(11) NOT NULL,
  `Idplgn` int(11) DEFAULT NULL,
  `Idsepeda` int(11) DEFAULT NULL,
  `tglsewa` varchar(50) DEFAULT NULL,
  `tglkembali` varchar(50) DEFAULT NULL,
  `hargasewa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `trsepeda`
--

INSERT INTO `trsepeda` (`Idtransaksi`, `Idplgn`, `Idsepeda`, `tglsewa`, `tglkembali`, `hargasewa`) VALUES
(8, 16, 37, '20-12-2018', '30-12-2018', '500000'),
(9, 11, 36, '01-01-2019', '02-02-2019', '400000'),
(10, 11, 37, '30-12-2018', '03-01-2019', '400301'),
(11, 16, 37, '20-12-2018', '22-12-2018', '500000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`Idplgn`);

--
-- Indeks untuk tabel `pemilik`
--
ALTER TABLE `pemilik`
  ADD PRIMARY KEY (`Idpemilik`);

--
-- Indeks untuk tabel `sepeda`
--
ALTER TABLE `sepeda`
  ADD PRIMARY KEY (`Idsepeda`),
  ADD KEY `Idpemilik` (`Idpemilik`);

--
-- Indeks untuk tabel `trsepeda`
--
ALTER TABLE `trsepeda`
  ADD PRIMARY KEY (`Idtransaksi`),
  ADD KEY `Idplgn` (`Idplgn`) USING BTREE,
  ADD KEY `Idsepeda` (`Idsepeda`) USING BTREE;

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `Idplgn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `pemilik`
--
ALTER TABLE `pemilik`
  MODIFY `Idpemilik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT untuk tabel `sepeda`
--
ALTER TABLE `sepeda`
  MODIFY `Idsepeda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT untuk tabel `trsepeda`
--
ALTER TABLE `trsepeda`
  MODIFY `Idtransaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `sepeda`
--
ALTER TABLE `sepeda`
  ADD CONSTRAINT `sepeda_ibfk_1` FOREIGN KEY (`Idpemilik`) REFERENCES `pemilik` (`Idpemilik`);

--
-- Ketidakleluasaan untuk tabel `trsepeda`
--
ALTER TABLE `trsepeda`
  ADD CONSTRAINT `trsepeda_ibfk_1` FOREIGN KEY (`Idsepeda`) REFERENCES `sepeda` (`Idsepeda`),
  ADD CONSTRAINT `trsepeda_ibfk_2` FOREIGN KEY (`Idplgn`) REFERENCES `pelanggan` (`Idplgn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
