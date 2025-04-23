-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2025 at 01:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `available` int(2) NOT NULL DEFAULT 2,
  `total` int(2) NOT NULL DEFAULT 2,
  `status` enum('Available','Rented','Reserved') DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`id`, `name`, `available`, `total`, `status`) VALUES
(1, 'Honda Civic', 2, 2, 'Available'),
(2, 'Toyota Vios', 2, 2, 'Available'),
(3, 'Mitsubishi Mirage', 2, 2, 'Available'),
(4, 'Toyota Innova', 2, 2, 'Available'),
(5, 'Toyota Corolla', 2, 2, 'Available'),
(6, 'Mitsubishi Montero', 2, 2, 'Available'),
(7, 'Ford Explorer', 2, 2, 'Available'),
(8, 'Nissan Urvan', 2, 2, 'Available'),
(9, 'Toyota HiAce', 2, 2, 'Available'),
(10, 'Toyota 86', 2, 2, 'Available'),
(11, 'Chevrolet Camaro', 2, 2, 'Available'),
(12, 'Nissan GTR R35', 2, 2, 'Available');

-- --------------------------------------------------------

--
-- Stand-in structure for view `cars_ordered`
-- (See below for the actual view)
--
CREATE TABLE `cars_ordered` (
`id` int(11)
,`name` varchar(255)
,`available` int(2)
,`total` int(2)
,`status` enum('Available','Rented','Reserved')
);

-- --------------------------------------------------------

--
-- Table structure for table `created_account`
--

CREATE TABLE `created_account` (
  `ID` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `created_account`
--

INSERT INTO `created_account` (`ID`, `username`, `password`) VALUES
(7, 'admin', 'admin'),
(8, 'Maron', 'admin123'),
(9, 'MaronFajardo', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `login_user`
--

CREATE TABLE `login_user` (
  `ID` int(11) NOT NULL,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login_user`
--

INSERT INTO `login_user` (`ID`, `username`, `password`) VALUES
(12, 'admin', 'admin'),
(13, 'Maron', 'admin123'),
(14, 'MaronFajardo', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `ID` int(11) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `contact_number` varchar(15) NOT NULL,
  `address` varchar(50) NOT NULL,
  `car_model` varchar(50) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `payment_mode` varchar(50) DEFAULT NULL,
  `status` enum('Ongoing','Completed','Cancelled','Reserved') NOT NULL DEFAULT 'Ongoing',
  `staff_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`ID`, `customer_name`, `contact_number`, `address`, `car_model`, `start_date`, `end_date`, `payment_mode`, `status`, `staff_name`) VALUES
(4, 'Maron Fajardo', '09751234977', '2274, chyrsanthemum', 'Honda Civic', '2025-04-17', '2025-04-20', 'Cash Payment', 'Cancelled', 'admin'),
(5, 'Maron Fajardo', '09751234977', '2274, chyrsanthemum', 'Toyota Vios', '2025-04-17', '2025-04-20', 'Cash Payment', 'Cancelled', 'admin'),
(6, 'maron', '123', '123 ccaca', 'Toyota Vios', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(7, ' Maron Fajardo', '123124', '123123', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(8, ' admin', 'admin123123', '123 admin test', 'Mitsubishi Mirage', '2025-04-17', '2025-04-17', 'Online Payment', 'Cancelled', 'Maron'),
(9, 'ad', 'awd', 'wadawd', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(10, 'ad', 'wad', 'wad', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(11, 'awdwa', 'dwa', 'dwadwa', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(12, 'awda', 'wdawda', 'wdaw', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(13, ' Maron', 'awdwa', 'awdwa', 'Toyota Vios', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', 'Maron'),
(14, 'awdawd', 'awdaw', 'dwadwa', 'Toyota Vios', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(15, ' awd', 'wadaw', 'dwadwa', 'Mitsubishi Mirage', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(16, ' awd', 'wadaw', 'dwadwa', 'Mitsubishi Mirage', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(17, ' adw', 'dwa', 'dwa', 'Toyota Innova', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(18, ' adw', 'dwa', 'dwa', 'Toyota Innova', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(19, 'adawdwadwa', 'awdaw', 'dwa', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(20, ' Maron ', '12454', '123 asdadwa', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', ''),
(21, 'aw', 'da', 'wdwa', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', 'Maron'),
(22, ' Maron Fajardo', '123466789', '2274 chyrsanthemum sa puso mo', 'Honda Civic', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', 'Maron'),
(23, 'adwa ', 'daw', 'dawd', 'Toyota Vios', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', 'Maron'),
(24, ' Maron Fajardo', '09751234977', '2274 chrysanthemum sa puso mo lang bebekho', 'Toyota Vios', '2025-04-17', '2025-04-17', 'Cash Payment', 'Cancelled', 'Maron'),
(25, 'adawawdwa', 'dwad', 'wadwa', 'Ford Explorer', '2025-04-17', '2025-04-17', 'Cash Payment', 'Ongoing', ''),
(26, 'awd', 'wadwad', 'wadwa', 'Ford Explorer', '2025-04-17', '2025-04-17', 'Cash Payment', 'Ongoing', ''),
(27, 'awdwa', 'wadwa', 'dawdwa', 'Ford Explorer', '2025-04-17', '2025-04-17', 'Cash Payment', 'Ongoing', ''),
(28, 'Maron Fajardo', '09751234977', '2274, chrysanthemum', 'Honda Civic', '2025-04-18', '2025-04-18', 'Cash Payment', 'Cancelled', 'Maron'),
(29, 'Rodolfo', '1223345', '123 sa puso mo st.', 'Nissan GTR R35', '2025-04-18', '2025-04-18', 'Cash Payment', 'Cancelled', 'MaronFajardo'),
(30, 'awd', 'awdaw', 'dawda', 'Nissan GTR R35', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', 'MaronFajardo'),
(31, 'awd', 'awdwad', 'wadwa', 'Nissan GTR R35', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', 'MaronFajardo'),
(32, 'ad', 'wadwa', 'dwad', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', 'MaronFajardo'),
(33, 'awd', 'wadwa', 'dawdaw', 'Toyota Vios', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(34, 'awd', 'wadwa', 'dawdaw', 'Toyota Vios', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(35, 'awd', 'wadwa', 'dawdaw', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(36, 'awd', 'wadwa', 'dawdaw', 'Toyota Vios', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(37, 'dawd', 'awdaw', 'dawdaw', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(38, 'dawd', 'awdaw', 'dawdaw', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(39, 'daw', 'dawd', 'awdwa', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(40, 'daw', 'dawd', 'awdwa', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(42, 'ad', 'wadwad', 'wadwa', 'Chevrolet Camaro', '2025-04-19', '2025-04-19', 'Cash Payment', 'Ongoing', ''),
(43, 'ad', 'wadwad', 'wadwa', 'Chevrolet Camaro', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(44, 'awd', 'wadawd', 'awdawdwa', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', 'admin'),
(45, 'awd', 'wadw', 'adwadwa', 'Honda Civic', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', 'admin'),
(46, 'wadwa', 'dwa', 'dwadwa', 'Toyota Vios', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(47, 'daw', 'dawd', 'awdwa', 'Toyota Vios', '2025-04-19', '2025-04-19', 'Cash Payment', 'Cancelled', ''),
(48, 'awd', 'wadaw', 'dawdaw', 'Nissan Urvan', '2025-04-19', '2025-04-19', 'Cash Payment', 'Ongoing', ''),
(49, 'awd', 'awdaw', 'dwadaw', 'Ford Explorer', '2025-04-19', '2025-04-19', 'Cash Payment', 'Ongoing', ''),
(50, 'awd', 'wad', 'wadwa', 'Ford Explorer', '2025-04-19', '2025-04-19', 'Cash Payment', 'Ongoing', '');

-- --------------------------------------------------------

--
-- Table structure for table `rental_logs`
--

CREATE TABLE `rental_logs` (
  `ID` int(11) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `car_model` varchar(100) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `payment_mode` varchar(50) NOT NULL,
  `status` enum('Ongoing','Completed','Cancelled','Reserved') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure for view `cars_ordered`
--
DROP TABLE IF EXISTS `cars_ordered`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cars_ordered`  AS SELECT `cars`.`id` AS `id`, `cars`.`name` AS `name`, `cars`.`available` AS `available`, `cars`.`total` AS `total`, `cars`.`status` AS `status` FROM `cars` ORDER BY `cars`.`id` ASC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `created_account`
--
ALTER TABLE `created_account`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `login_user`
--
ALTER TABLE `login_user`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `rental_logs`
--
ALTER TABLE `rental_logs`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `created_account`
--
ALTER TABLE `created_account`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `login_user`
--
ALTER TABLE `login_user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `rental_logs`
--
ALTER TABLE `rental_logs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
