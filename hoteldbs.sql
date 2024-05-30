-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2024 at 08:13 PM
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
-- Database: `hoteldbs`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_archived`
--

CREATE TABLE `tbl_archived` (
  `archID` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `roomID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `check_in` datetime NOT NULL,
  `check_out` datetime NOT NULL,
  `totalPayment` int(11) NOT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_archived`
--

INSERT INTO `tbl_archived` (`archID`, `u_id`, `roomID`, `name`, `contact`, `check_in`, `check_out`, `totalPayment`, `status`) VALUES
(4, 1005, 4003, 'jdfghjknadfg iajdrgnadfg', 93215115532, '2024-05-31 00:00:00', '2024-06-20 00:00:00', 28500, 'Archived'),
(5, 1005, 4004, 'ergergh ehgeherg', 9856562921, '2024-05-31 00:00:00', '2025-05-22 00:00:00', 532500, 'Archived');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_room`
--

CREATE TABLE `tbl_room` (
  `roomID` int(11) NOT NULL,
  `roomNo` int(10) NOT NULL,
  `roomType` varchar(50) NOT NULL,
  `r_price` decimal(10,2) NOT NULL,
  `r_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_room`
--

INSERT INTO `tbl_room` (`roomID`, `roomNo`, `roomType`, `r_price`, `r_status`) VALUES
(2001, 201, 'Regular', 500.00, 'Active'),
(2002, 202, 'Regular', 500.00, 'Active'),
(2003, 203, 'Regular', 500.00, 'Active'),
(2004, 204, 'Regular', 500.00, 'Active'),
(2005, 205, 'Regular', 500.00, 'Active'),
(3001, 301, 'Deluxe', 1000.00, 'Active'),
(3002, 302, 'Deluxe', 1000.00, 'Active'),
(3003, 303, 'Deluxe', 1000.00, 'Active'),
(3004, 304, 'Deluxe', 1000.00, 'Active'),
(3005, 305, 'Deluxe', 1000.00, 'Active'),
(4001, 401, 'VIP', 1500.00, 'Active'),
(4002, 402, 'VIP', 1500.00, 'Booked'),
(4003, 403, 'VIP', 1500.00, 'Active'),
(4004, 404, 'VIP', 1500.00, 'Active'),
(4005, 405, 'VIP', 1500.00, 'Active'),
(5001, 501, 'Penthouse', 5000.00, 'Active'),
(5002, 502, 'Penthouse', 5000.00, 'Active'),
(5003, 503, 'Penthouse', 5000.00, 'Active'),
(5004, 504, 'Penthouse', 5000.00, 'Active'),
(5005, 505, 'Penthouse', 5000.00, 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaction`
--

CREATE TABLE `tbl_transaction` (
  `transID` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `roomID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `check_in` datetime NOT NULL,
  `check_out` date DEFAULT NULL,
  `totalPayment` int(11) DEFAULT NULL,
  `status` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_transaction`
--

INSERT INTO `tbl_transaction` (`transID`, `u_id`, `roomID`, `name`, `contact`, `check_in`, `check_out`, `totalPayment`, `status`) VALUES
(1010, 1003, 2001, 'Diane Raganas', 2147483647, '2024-05-28 18:09:43', '2024-05-30', 152653, 'Active'),
(1011, 1003, 2001, 'Diane Raganass', 63515615, '2024-05-28 18:13:08', '2024-05-31', NULL, 'Active'),
(2012, 1003, 2002, 'Khan Canillas', 1154521, '2024-05-28 19:30:41', '2024-05-31', NULL, 'Active'),
(2031, 1005, 4002, 'jsngnqeigrn qieigjqeijrg', 956212832142, '2024-05-31 00:00:00', '2024-06-27', 39000, 'Active');

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
(1003, 'Josh', 'Canillas', '123swngcxz', '123swngcxz', 'fbAZV0Y4pESsKK2ABOkPHEAtSV82QkGVRA3KRIw/y00=', 'Admin', 'Active', 'null'),
(1004, 'Josh', 'canillas', '123swngcxz1', '123swngcxz1', '8NXUBbqEUiLFdRqRXzAvDEpx9y7BItfwFkpKLVGow+o=', 'Admin', 'Active', 'null'),
(1005, 'Christine', 'Raganas', 'christinedianeraganas@gmail.com', 'diane', '8NXUBbqEUiLFdRqRXzAvDEpx9y7BItfwFkpKLVGow+o=', 'Admin', 'Active', 'src/userimages/d.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_archived`
--
ALTER TABLE `tbl_archived`
  ADD PRIMARY KEY (`archID`),
  ADD KEY `fk_room` (`roomID`),
  ADD KEY `fk_employee` (`u_id`);

--
-- Indexes for table `tbl_room`
--
ALTER TABLE `tbl_room`
  ADD PRIMARY KEY (`roomID`);

--
-- Indexes for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD PRIMARY KEY (`transID`),
  ADD KEY `room_id` (`roomID`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_archived`
--
ALTER TABLE `tbl_archived`
  MODIFY `archID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_room`
--
ALTER TABLE `tbl_room`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5006;

--
-- AUTO_INCREMENT for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  MODIFY `transID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2032;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_archived`
--
ALTER TABLE `tbl_archived`
  ADD CONSTRAINT `fk_employee` FOREIGN KEY (`u_id`) REFERENCES `tbl_user` (`u_id`),
  ADD CONSTRAINT `fk_room` FOREIGN KEY (`roomID`) REFERENCES `tbl_room` (`roomID`);

--
-- Constraints for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD CONSTRAINT `room_id` FOREIGN KEY (`roomID`) REFERENCES `tbl_room` (`roomID`),
  ADD CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_user` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
