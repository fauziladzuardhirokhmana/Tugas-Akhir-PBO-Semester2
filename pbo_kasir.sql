-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 22 Bulan Mei 2020 pada 00.15
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo_kasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `ID_Barang` varchar(15) NOT NULL,
  `Nama_Barang` char(50) NOT NULL,
  `Harga_Satuan` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`ID_Barang`, `Nama_Barang`, `Harga_Satuan`) VALUES
('ATK_1', 'buku tulis', 3000),
('ATK_2', 'pulpen', 2000),
('ATK_3', 'pensil', 2000),
('ATK_4', 'penghapus', 500),
('ATK_5', 'tip ex', 4000),
('ATK_6', 'penyerut', 1000),
('ATK_7', 'kertas HVS', 250),
('ATK_8', 'kertas polio', 500),
('ATK_9', 'penggaris', 2500),
('DR_1', 'aqua gelas', 500),
('DR_2', 'aqua botol', 3500),
('DR_3', 'pocari sweat', 5000),
('SNC_1', 'snack kecil', 500),
('SNC_2', 'snack medium', 1000),
('SNC_3', 'snack besar', 2000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login_info`
--

CREATE TABLE `login_info` (
  `ID_Login` int(2) NOT NULL,
  `Nama_Admin` char(50) NOT NULL,
  `No_Telepon` varchar(15) NOT NULL,
  `Email` text NOT NULL,
  `Alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login_info`
--

INSERT INTO `login_info` (`ID_Login`, `Nama_Admin`, `No_Telepon`, `Email`, `Alamat`) VALUES
(1, 'Fauzi Ladzuardhi Rokhmana', '0831-3556-0460', 'fauzilrokhmana@gmail.com', 'Jl. Holis Cibuntu Tengah 3 RT 04 RW 10 no.10');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login_username`
--

CREATE TABLE `login_username` (
  `ID_Login` int(2) NOT NULL,
  `Username` varchar(15) NOT NULL,
  `Password` text NOT NULL,
  `Level` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login_username`
--

INSERT INTO `login_username` (`ID_Login`, `Username`, `Password`, `Level`) VALUES
(1, 'Admin1', '2ec10e4f7cd2159e7ea65d2454f68287ecf81251', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_detail`
--

CREATE TABLE `transaksi_detail` (
  `ID_Transaksi` int(11) DEFAULT NULL,
  `ID_Barang` varchar(15) DEFAULT NULL,
  `Kuantitas` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`ID_Transaksi`, `ID_Barang`, `Kuantitas`) VALUES
(1, 'ATK_1', 3),
(1, 'SNC_1', 5),
(1, 'DR_1', 2),
(2, 'ATK_7', 2),
(2, 'SNC_3', 3),
(2, 'DR_3', 1),
(3, 'DR_2', 1),
(3, 'ATK_5', 3),
(3, 'SNC_2', 4);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `transaksi_per_barang`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `transaksi_per_barang` (
`ID_Transaksi` int(11)
,`ID_Barang` varchar(15)
,`Kuantitas` int(3)
,`Total_Harga` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `transaksi_total`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `transaksi_total` (
`ID_Transaksi` int(11)
,`Total_Bayar` decimal(42,0)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_utama`
--

CREATE TABLE `transaksi_utama` (
  `ID_Transaksi` int(11) NOT NULL,
  `ID_Login` int(2) NOT NULL,
  `Tanggal` date NOT NULL,
  `Waktu` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi_utama`
--

INSERT INTO `transaksi_utama` (`ID_Transaksi`, `ID_Login`, `Tanggal`, `Waktu`) VALUES
(1, 1, '2020-05-01', '12:15:06'),
(2, 1, '2020-05-02', '15:16:28'),
(3, 1, '2020-05-03', '13:11:03');

-- --------------------------------------------------------

--
-- Struktur untuk view `transaksi_per_barang`
--
DROP TABLE IF EXISTS `transaksi_per_barang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `transaksi_per_barang`  AS  select `transaksi_utama`.`ID_Transaksi` AS `ID_Transaksi`,`barang`.`ID_Barang` AS `ID_Barang`,`transaksi_detail`.`Kuantitas` AS `Kuantitas`,`transaksi_detail`.`Kuantitas` * `barang`.`Harga_Satuan` AS `Total_Harga` from ((`transaksi_utama` join `transaksi_detail`) join `barang`) where `transaksi_utama`.`ID_Transaksi` = `transaksi_detail`.`ID_Transaksi` and `transaksi_detail`.`ID_Barang` = `barang`.`ID_Barang` order by `transaksi_utama`.`ID_Transaksi` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `transaksi_total`
--
DROP TABLE IF EXISTS `transaksi_total`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `transaksi_total`  AS  select `transaksi_per_barang`.`ID_Transaksi` AS `ID_Transaksi`,sum(if(`transaksi_per_barang`.`ID_Barang` = `barang`.`ID_Barang`,`transaksi_per_barang`.`Total_Harga`,0)) AS `Total_Bayar` from (`transaksi_per_barang` join `barang`) where `transaksi_per_barang`.`ID_Barang` = `barang`.`ID_Barang` group by `transaksi_per_barang`.`ID_Transaksi` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`ID_Barang`);

--
-- Indeks untuk tabel `login_info`
--
ALTER TABLE `login_info`
  ADD PRIMARY KEY (`ID_Login`);

--
-- Indeks untuk tabel `login_username`
--
ALTER TABLE `login_username`
  ADD PRIMARY KEY (`ID_Login`);

--
-- Indeks untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD KEY `ID_Transaksi` (`ID_Transaksi`),
  ADD KEY `ID_Barang` (`ID_Barang`);

--
-- Indeks untuk tabel `transaksi_utama`
--
ALTER TABLE `transaksi_utama`
  ADD PRIMARY KEY (`ID_Transaksi`),
  ADD KEY `ID_Login` (`ID_Login`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `login_username`
--
ALTER TABLE `login_username`
  MODIFY `ID_Login` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `transaksi_utama`
--
ALTER TABLE `transaksi_utama`
  MODIFY `ID_Transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `login_info`
--
ALTER TABLE `login_info`
  ADD CONSTRAINT `login_info_ibfk_1` FOREIGN KEY (`ID_Login`) REFERENCES `login_username` (`ID_Login`);

--
-- Ketidakleluasaan untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD CONSTRAINT `transaksi_detail_ibfk_1` FOREIGN KEY (`ID_Transaksi`) REFERENCES `transaksi_utama` (`ID_Transaksi`),
  ADD CONSTRAINT `transaksi_detail_ibfk_2` FOREIGN KEY (`ID_Transaksi`) REFERENCES `transaksi_utama` (`ID_Transaksi`),
  ADD CONSTRAINT `transaksi_detail_ibfk_3` FOREIGN KEY (`ID_Barang`) REFERENCES `barang` (`ID_Barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
