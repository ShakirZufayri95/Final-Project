-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 12, 2019 at 04:13 PM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id9115371_shakiros_data`
--

-- --------------------------------------------------------

--
-- Table structure for table `CART`
--

CREATE TABLE `CART` (
  `FOODID` varchar(10) NOT NULL,
  `USERID` varchar(40) NOT NULL,
  `QUANTITY` varchar(10) NOT NULL,
  `PRICE` varchar(10) NOT NULL,
  `FOODNAME` varchar(30) NOT NULL,
  `STATUS` varchar(20) NOT NULL,
  `RESTID` varchar(10) NOT NULL,
  `ORDERID` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CART`
--

INSERT INTO `CART` (`FOODID`, `USERID`, `QUANTITY`, `PRICE`, `FOODNAME`, `STATUS`, `RESTID`, `ORDERID`) VALUES
('1', '0123456789', '1', '17.00', 'PEAKO 1/64 Vielside Fortune 7', 'not paid', '2', '12062019-MRlzblW'),
('1', '0123456789', '3', '17.00', 'PEAKO 1/64 Vielside Fortune 7', 'not paid', '2', '12062019-MRlzblW'),
('1', '012345678', '2', '17.00', 'PEAKO 1/64 Vielside Fortune 7', 'not paid', '2', '12062019-5QsOn8a');

-- --------------------------------------------------------

--
-- Table structure for table `ORDERED`
--

CREATE TABLE `ORDERED` (
  `ORDERID` varchar(20) NOT NULL,
  `USERID` varchar(10) NOT NULL,
  `TOTAL` varchar(10) NOT NULL,
  `DATE` timestamp(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ORDERED`
--

INSERT INTO `ORDERED` (`ORDERID`, `USERID`, `TOTAL`, `DATE`) VALUES
('08052019-qxgbUmp', '0194702493', '15.5', '2019-05-08 15:22:51.247162'),
('08052019-Oi6XCFF', '0194702493', '37.3', '2019-05-08 16:05:18.157234');

-- --------------------------------------------------------

--
-- Table structure for table `PRODUCTS`
--

CREATE TABLE `PRODUCTS` (
  `FOODID` int(5) NOT NULL,
  `FOODNAME` varchar(100) NOT NULL,
  `FOODPRICE` varchar(10) NOT NULL,
  `QUANTITY` varchar(5) NOT NULL,
  `RESTID` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PRODUCTS`
--

INSERT INTO `PRODUCTS` (`FOODID`, `FOODNAME`, `FOODPRICE`, `QUANTITY`, `RESTID`) VALUES
(1, 'PEAKO 1/64 Vielside Fortune 7', '17.00', '11', '2'),
(2, 'ALMOSTREAL 1/18 McLaren P1 GTR', '92.00', '5', '3'),
(3, 'Ottomobile 1/18 Mitsubishi Lancer Evo 8 MR FQ-400', '37.00', '9', '2'),
(4, 'AUTOart 1/18 JAGUAR LIGHTWEIGHT E-TYPE', '48.00', '7', '3'),
(5, 'AUTOart 1/18 FORD SHELBY GT-350R', '80.00', '9', '3'),
(6, 'AUTOart 1/18 BUGATTI CHIRON 2017', '89.00', '8', '2'),
(7, 'AUTOart 1/18 MITSUBISHI LANCER EVO IX RALLIART', '70.00', '2', '2'),
(8, 'AUTOart 1/12 McLAREN P1', '98.00', '14', '3'),
(9, 'AUTOart 1/18 Nissan Wangan Midnight “Akuma no Z”', '65.00', '7', '2'),
(10, 'Sportswear Ringer Size S', '20.00', '20', '4'),
(11, 'Sport-T 3Q Size L', '25.00', '17', '4'),
(12, 'Vintage Mickey Mouse Size M', '18.00', '7', '5'),
(13, 'Vintage Monterey California Size S', '35.00', '6', '5'),
(14, 'LEVI\'S 550 Relaxed Fit W29 L29', '45.00', '14', '5'),
(15, 'JERZEES Vintage 80s Mickey Mouse Size S', '40.00', '6', '4'),
(16, 'JERZEES Vintage NEW KIDS ON THE BLOCK Size S', '15.00', '5', '1'),
(17, 'Mickey Mouse Bucket Hat', '58.00', '8', '1'),
(18, 'Vintage STP Cap', '80.00', '9', '1'),
(19, 'Vintage 76 Cap', '40.00', '13', '1'),
(20, 'LUCKY STRIKE Leather Jacket', '89.00', '12', '4');

-- --------------------------------------------------------

--
-- Table structure for table `SHOP`
--

CREATE TABLE `SHOP` (
  `RESTID` int(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `LOCATION` varchar(20) NOT NULL,
  `LATITUDE` varchar(30) NOT NULL,
  `LONGITUDE` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SHOP`
--

INSERT INTO `SHOP` (`RESTID`, `NAME`, `PHONE`, `ADDRESS`, `LOCATION`, `LATITUDE`, `LONGITUDE`) VALUES
(1, 'E Bundle Vintage Shop', '0136113671', '658, Exit 87, 28000 Temerloh', 'Pahang', '6.433105', '100.436619'),
(2, 'Akids Diecast', '0122543868', '18-1 Jalan Bayan, Taman Bukit Katil, 75460 Bukit Katil', 'Melaka', '6.447736', '100.524096'),
(3, 'I Hobby House', '062862820', 'F2-09 / 2nd Floor, Melaka Megamall', 'Melaka', '6.435130', '100.430111'),
(4, 'Heaven Bundle', '0193493424', '88, Jalan Badminton 13/29, Tadisma Business Park, 40100 Shah Alam', 'Selangor', '6.477651', '100.509161'),
(5, 'Triple A Bundle', '0192887019', '69 G Jalan Wangsa Delima 5 , Pusat Bandar Wangsa Maju 53300 Kuala Lumpur ', 'Kuala Lumpur', '6.493133', '100.478162');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `EMAIL` varchar(100) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `LOCATION` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `LOCATION`) VALUES
('slumberjer@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '0194702493', 'Ahmad Hanis', 'Changlun'),
('test@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '0123456789', 'test', 'Kedah'),
('test2@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '0122334455', 'test2', 'Perlis'),
('haikal@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '0123456789', 'Haikal', 'Pahang'),
('john@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '012345678', 'John', 'Kedah'),
('johnny@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '0123456789', 'Johnny', 'Kelantan'),
('tom@gmail.com', 'da39a3ee5e6b4b0d3255bfef95601890afd80709', '0123456789', 'tom', 'Perak'),
('tim@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', 'Tim', 'Kelantan'),
('case@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '012456789', 'Case', 'Kelantan'),
('shakir@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '012345678', 'Shakir', 'Perlis');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `PRODUCTS`
--
ALTER TABLE `PRODUCTS`
  ADD PRIMARY KEY (`FOODID`);

--
-- Indexes for table `SHOP`
--
ALTER TABLE `SHOP`
  ADD PRIMARY KEY (`RESTID`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`EMAIL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `PRODUCTS`
--
ALTER TABLE `PRODUCTS`
  MODIFY `FOODID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `SHOP`
--
ALTER TABLE `SHOP`
  MODIFY `RESTID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
