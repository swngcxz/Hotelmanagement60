-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2024 at 10:40 PM
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
(1001, 'Josh', 'Canillas', '123swngcxz@gmail.com', '123swngcxz', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1002, 'test', 'test', 'test', 'test', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1003, 'user', 'user', 'user', 'user', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'User', 'Active', ''),
(1004, 'first', 'first', 'first', 'first1', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Pending', ''),
(1005, 'second', 'second', 'second', '123123', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1006, 'third', 'third', 'third', 'third', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1007, 'samp', 'samp', 'samp', 'samp', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1008, 'josh', 'josh', 'josh', 'josh', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', ''),
(1009, 'diane', 'raganas', 'diane@gmail.com', 'diane', 'XmCNe+ynUTyrGOajluqJTyxUZjedzB44iMwj1oWupgA=', 'Admin', 'Active', 'src/userimages/maxresdefault.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1010;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
