-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2024 at 04:34 AM
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
-- Database: `dbssample`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cio`
--

CREATE TABLE `tbl_cio` (
  `cio_id` int(11) NOT NULL,
  `c_Id` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_cio`
--

INSERT INTO `tbl_cio` (`cio_id`, `c_Id`, `roomId`, `check_in`, `check_out`) VALUES
(1111, 1011, 2003, '2024-05-21', '2024-05-23');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cost`
--

CREATE TABLE `tbl_cost` (
  `id` int(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `ContactNo` varchar(50) NOT NULL,
  `RoomType` varchar(20) NOT NULL,
  `RoomNo` int(15) NOT NULL,
  `Check_In` datetime NOT NULL DEFAULT current_timestamp(),
  `Check_Out` date DEFAULT NULL,
  `c_balance` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_cost`
--

INSERT INTO `tbl_cost` (`id`, `Name`, `ContactNo`, `RoomType`, `RoomNo`, `Check_In`, `Check_Out`, `c_balance`) VALUES
(1, 'Josh Canillas', '09123456798', 'Regular', 203, '2024-05-22 03:16:48', '2024-05-24', 500),
(4, 'eqweqw', '1231214', 'VIP', 401, '2024-05-23 04:31:27', NULL, 1500),
(7, 'sud', '1232131', 'VIP', 404, '2024-05-23 04:35:05', NULL, 1500);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `c_id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `c_email` varchar(50) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_customer`
--

INSERT INTO `tbl_customer` (`c_id`, `Name`, `Address`, `c_email`, `room_id`) VALUES
(1011, 'Christine Raganas', 'Tunghaan, Minglanilla, Cebu', 'christineraganas@gmail.com', 2001);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reservations`
--

CREATE TABLE `tbl_reservations` (
  `rsrvtnId` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rooms`
--

CREATE TABLE `tbl_rooms` (
  `roomID` int(11) NOT NULL,
  `roomNo` int(11) NOT NULL,
  `roomType` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `r_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_rooms`
--

INSERT INTO `tbl_rooms` (`roomID`, `roomNo`, `roomType`, `price`, `r_status`) VALUES
(2001, 201, 'Regular', 500.00, 'Archived'),
(2002, 202, 'Regular', 500.00, 'Active'),
(2003, 203, 'Regular', 500.00, 'Booked'),
(2004, 204, 'Regular', 500.00, 'Unclean'),
(2050, 205, 'Regular', 500.00, 'Booked'),
(3001, 301, 'Deluxe', 1000.00, 'Unclean'),
(3002, 302, 'Deluxe', 1000.00, 'Active'),
(3003, 303, 'Deluxe', 1000.00, 'Active'),
(3004, 304, 'Deluxe', 1000.00, 'Active'),
(3005, 305, 'Deluxe', 1000.00, 'Archived'),
(4001, 401, 'VIP', 1500.00, 'Booked'),
(4002, 402, 'VIP', 1500.00, 'Active'),
(4003, 404, 'VIP', 1500.00, 'Booked'),
(4005, 405, 'VIP', 1500.00, 'Unclean'),
(5010, 510, 'Penthouse', 5000.00, 'Active'),
(5011, 511, 'Penthouse', 5000.00, 'Archived'),
(5012, 512, 'Penthouse', 5000.00, 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `u_id` int(20) NOT NULL,
  `u_fname` varchar(50) NOT NULL,
  `u_lname` varchar(50) NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `u_password` varchar(150) NOT NULL,
  `u_type` varchar(50) NOT NULL,
  `u_status` varchar(50) NOT NULL,
  `u_image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_username`, `u_password`, `u_type`, `u_status`, `u_image`) VALUES
(1009, 'Diane', 'Ragans', 'dianeraganas@gmail.com', 'diane', 'omkq6v5p1uSEzN1bCvdUtb380MF+dNOmfR110DpAPA4=', 'Admin', 'Active', ''),
(1010, 'Josh', 'Canillas', '123swngcxz@gmail.com', '123swngcxz', '8NXUBbqEUiLFdRqRXzAvDEpx9y7BItfwFkpKLVGow+o=', 'User', 'Active', 'null'),
(1011, 'Khan', 'Canillas', 'canillaskhan@gmail.com', 'khan123', 'yV/gq9UI10+GHTPbVB8ceDIEjGmvnTJeuwMRaBLtR4I=', 'User', 'Active', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_cio`
--
ALTER TABLE `tbl_cio`
  ADD PRIMARY KEY (`cio_id`),
  ADD KEY `room_id` (`roomId`),
  ADD KEY `c_id` (`c_Id`);

--
-- Indexes for table `tbl_cost`
--
ALTER TABLE `tbl_cost`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  ADD PRIMARY KEY (`rsrvtnId`),
  ADD KEY `u_id` (`u_id`),
  ADD KEY `roomId` (`roomId`);

--
-- Indexes for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  ADD PRIMARY KEY (`roomID`),
  ADD UNIQUE KEY `roomNo` (`roomNo`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_cio`
--
ALTER TABLE `tbl_cio`
  MODIFY `cio_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1112;

--
-- AUTO_INCREMENT for table `tbl_cost`
--
ALTER TABLE `tbl_cost`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1012;

--
-- AUTO_INCREMENT for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  MODIFY `rsrvtnId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_rooms`
--
ALTER TABLE `tbl_rooms`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5015;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1021;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_cio`
--
ALTER TABLE `tbl_cio`
  ADD CONSTRAINT `c_id` FOREIGN KEY (`c_Id`) REFERENCES `tbl_customer` (`c_id`),
  ADD CONSTRAINT `room_id` FOREIGN KEY (`roomId`) REFERENCES `tbl_rooms` (`roomID`);

--
-- Constraints for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  ADD CONSTRAINT `roomId` FOREIGN KEY (`roomId`) REFERENCES `tbl_rooms` (`roomID`),
  ADD CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_user` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
