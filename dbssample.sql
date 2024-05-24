-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2024 at 09:25 AM
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
  `c_balance` int(50) NOT NULL,
  `c_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_cost`
--

INSERT INTO `tbl_cost` (`id`, `Name`, `ContactNo`, `RoomType`, `RoomNo`, `Check_In`, `Check_Out`, `c_balance`, `c_status`) VALUES
(1, 'Josh Canillas', '09123456798', 'Regular', 203, '2024-05-22 03:16:48', '2024-05-24', 500, 'Active'),
(11, 'mnbjkenrb', '21843213', 'Deluxe', 303, '2024-05-24 00:27:42', NULL, 1000, 'Active'),
(12, 'bfefgsv', '214325423', 'VIP', 402, '2024-05-24 00:29:07', NULL, 1500, 'Active');

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
(3002, 302, 'Deluxe', 1000.00, 'Archived'),
(3003, 303, 'Deluxe', 1000.00, 'Booked'),
(3004, 304, 'Deluxe', 1000.00, 'Archived'),
(3005, 305, 'Deluxe', 1000.00, 'Archived'),
(4001, 401, 'VIP', 1500.00, 'Archived'),
(4002, 402, 'VIP', 1500.00, 'Booked'),
(4003, 404, 'VIP', 1500.00, 'Archived'),
(4005, 405, 'VIP', 1500.00, 'Unclean'),
(5010, 510, 'Penthouse', 5000.00, 'Archived'),
(5011, 511, 'Penthouse', 5000.00, 'Archived'),
(5012, 512, 'Penthouse', 5001.00, 'under Maintaintance');

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
(1009, 'Diane', 'Raganas', 'dianeraganas@gmail.com', 'diane', 'omkq6v5p1uSEzN1bCvdUtb380MF+dNOmfR110DpAPA4=', 'Admin', 'Active', 'src/userimages/images.png'),
(1010, 'Josh', 'Canillas', '123swngcxz@gmail.com', '123swngcxz', '8NXUBbqEUiLFdRqRXzAvDEpx9y7BItfwFkpKLVGow+o=', 'User', 'Active', 'null'),
(1011, 'Khan', 'Canillas', 'canillaskhan@gmail.com', 'khan123', 'yV/gq9UI10+GHTPbVB8ceDIEjGmvnTJeuwMRaBLtR4I=', 'User', 'Active', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_cost`
--
ALTER TABLE `tbl_cost`
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT for table `tbl_cost`
--
ALTER TABLE `tbl_cost`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
-- Constraints for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  ADD CONSTRAINT `roomId` FOREIGN KEY (`roomId`) REFERENCES `tbl_rooms` (`roomID`),
  ADD CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_user` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
