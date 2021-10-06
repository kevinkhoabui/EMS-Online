-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 17, 2021 at 05:25 PM
-- Server version: 5.6.50
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee_management_system_major_spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `country_id` int(11) NOT NULL,
  `country_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`country_id`, `country_name`) VALUES
(1, 'India'),
(2, 'USA');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL,
  `department_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`) VALUES
(1, 'IT Department'),
(2, 'Java Developement'),
(3, 'HR Department'),
(4, 'Web Developement');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `employee_sal` varchar(100) NOT NULL,
  `employee_first_name` varchar(100) NOT NULL,
  `employee_middle_name` varchar(100) NOT NULL,
  `employee_last_name` varchar(100) NOT NULL,
  `employee_gender` varchar(100) NOT NULL,
  `employee_address` varchar(100) NOT NULL,
  `employee_village` varchar(100) NOT NULL,
  `employee_state` varchar(100) NOT NULL,
  `employee_country` varchar(100) NOT NULL,
  `employee_landline` varchar(100) NOT NULL,
  `employee_mobile` varchar(100) NOT NULL,
  `employee_email` varchar(100) NOT NULL,
  `employee_status` varchar(255) NOT NULL,
  `employee_department` varchar(255) NOT NULL,
  `employee_dob` varchar(255) NOT NULL,
  `employee_nationalty` varchar(255) NOT NULL,
  `employee_qualification` text NOT NULL,
  `employee_history` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_sal`, `employee_first_name`, `employee_middle_name`, `employee_last_name`, `employee_gender`, `employee_address`, `employee_village`, `employee_state`, `employee_country`, `employee_landline`, `employee_mobile`, `employee_email`, `employee_status`, `employee_department`, `employee_dob`, `employee_nationalty`, `employee_qualification`, `employee_history`) VALUES
(1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(1001, '1', 'Amit', 'Kumar', 'Singh', 'Male', 'A : 42/6', 'Ghaziabad', '5', '1', 'Uttar Pradesh', '09876543212', 'kaushal@gmail.com', '2', '1', '2021-07-07', 'Indian', '', ''),
(1002, '2', 'Kaushal', 'Kishore', 'Jaiswal', 'Male', 'A : 42/6', 'Ghaziabad', 'sdfgsdfg', '1', '89273458', '09876543212', 'kaushal.rahuljaiswal@gmail.com', '1', '3', '2021-08-31', 'Indian', 'qwrqwer', 'qwreqwer'),
(1003, '1', 'Sumit', 'Kumar', 'Aggarwal', 'Male', 'A : 42/6', 'Ghaziabad', '1', '1', 'Uttar Pradesh', '09876543212', 'kishore@gmail.com', '2', '1', '12 January, 1988', 'Indian', '', ''),
(1014, '1', 'Anuj', 'Kumar', 'Dubej', 'Male', 'A : 42/6 Sector 62', 'Bhabua (Kaimur)', '1', '1', '9876543212', '9876543212', 'anuj@gmail.com', '1', '1', '12 January 1985', 'Indian', '', ''),
(1024, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(1025, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(1026, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(1027, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(112);

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `leave_id` int(11) NOT NULL,
  `leave_employee_id` varchar(10) NOT NULL,
  `leave_reason` varchar(300) NOT NULL,
  `leave_description` varchar(300) NOT NULL,
  `leave_from_date` varchar(300) NOT NULL,
  `leave_to_date` varchar(300) NOT NULL,
  `leave_status` varchar(255) NOT NULL DEFAULT '0',
  `leave_total_days` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`leave_id`, `leave_employee_id`, `leave_reason`, `leave_description`, `leave_from_date`, `leave_to_date`, `leave_status`, `leave_total_days`) VALUES
(110, '1002', 'Going to Home', 'Going to home', '2021-09-01', '2021-09-03', 'Pending', '3'),
(111, '1002', 'sdf', 'df', '2021-09-10', '2021-09-13', 'Pending', '4');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `login_employee_id` varchar(255) NOT NULL,
  `login_email` varchar(255) NOT NULL,
  `login_password` varchar(255) NOT NULL,
  `login_level_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `login_employee_id`, `login_email`, `login_password`, `login_level_id`) VALUES
(1, '1001', 'admin', 'test', '1'),
(2, '1002', 'employee', 'test', '2'),
(37, '36', 'hh', 'hh', '2'),
(39, '38', 'hh', 'hh', '1'),
(41, '40', 'hh', 'hh', '2'),
(43, '42', 'hh', 'hh', '2'),
(45, '44', 'hh', 'hh', '1'),
(47, '46', 'hh', 'hh', '1'),
(49, '48', 'jj', 'jj', '1'),
(51, '50', 'hh', 'hh', '1'),
(53, '52', 'hh', 'hh', '2'),
(55, '54', 'hh', 'hh', '1'),
(57, '56', 'hh', 'hh', '2'),
(59, '58', 'hhh', 'hh', '1'),
(61, '60', 'admin1', 'test', '1'),
(100, '99', 'uu', 'uu', '2'),
(102, '101', 'jjj', 'jj', '1'),
(104, '103', 'xx', 'xx', '1');

-- --------------------------------------------------------

--
-- Table structure for table `month`
--

CREATE TABLE `month` (
  `month_id` int(11) NOT NULL,
  `month_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `month`
--

INSERT INTO `month` (`month_id`, `month_name`) VALUES
(1, 'January'),
(2, 'February'),
(3, 'March'),
(4, 'April'),
(5, 'May'),
(6, 'June'),
(7, 'July'),
(8, 'August'),
(9, 'September'),
(10, 'October'),
(11, 'November'),
(12, 'December');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roles_id` int(11) NOT NULL,
  `roles_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`roles_id`, `roles_name`) VALUES
(1, 'Admin'),
(2, 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `salary_id` int(11) NOT NULL,
  `salary_employee_id` varchar(255) NOT NULL,
  `salary_month` varchar(255) NOT NULL,
  `salary_working_days` varchar(255) NOT NULL,
  `salary_basic` varchar(255) NOT NULL,
  `salary_hra` varchar(255) NOT NULL,
  `salary_mediclaim` varchar(255) NOT NULL,
  `salary_ta` varchar(255) NOT NULL,
  `salary_da` varchar(255) NOT NULL,
  `salary_reimbursement` varchar(255) NOT NULL,
  `salary_ca` varchar(255) NOT NULL,
  `salary_others` varchar(255) NOT NULL,
  `salary_dpf` varchar(255) NOT NULL,
  `salary_dtax` varchar(255) NOT NULL,
  `salary_desc` text NOT NULL,
  `salary_total` varchar(255) NOT NULL,
  `salary_dedc` varchar(255) NOT NULL,
  `salary_slip` longblob,
  `salary_slip_filename` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`salary_id`, `salary_employee_id`, `salary_month`, `salary_working_days`, `salary_basic`, `salary_hra`, `salary_mediclaim`, `salary_ta`, `salary_da`, `salary_reimbursement`, `salary_ca`, `salary_others`, `salary_dpf`, `salary_dtax`, `salary_desc`, `salary_total`, `salary_dedc`, `salary_slip`, `salary_slip_filename`) VALUES
(91, '1003', '8', '30', '1000', '1000', '1000', '1', '1', '1', '1', '1', '1', '1', '1', '4000', '1', NULL, '1630263488_303881675-Vehicle-Showroom-Management-System-Project-Report-in-PHP-and-MySQL (1).docx'),
(92, '1002', '5', '30', '3500', '4500', '3', '3', '3', '3', '3', '3', '3', '3', '3', '7500', '3', NULL, '1630263915_0584082100172_.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `saluation`
--

CREATE TABLE `saluation` (
  `saluation_id` int(11) NOT NULL,
  `saluation_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saluation`
--

INSERT INTO `saluation` (`saluation_id`, `saluation_name`) VALUES
(1, 'Mr.'),
(2, 'Mrs.');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `state_id` int(11) NOT NULL,
  `state_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`state_id`, `state_name`) VALUES
(1, 'Maharastra'),
(2, 'Gujrat'),
(3, 'Bihar'),
(4, 'Uttar Pradesh'),
(5, 'Delhi'),
(6, 'Haryana');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`country_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`leave_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`);

--
-- Indexes for table `month`
--
ALTER TABLE `month`
  ADD PRIMARY KEY (`month_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roles_id`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`salary_id`);

--
-- Indexes for table `saluation`
--
ALTER TABLE `saluation`
  ADD PRIMARY KEY (`saluation_id`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`state_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `country_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1028;

--
-- AUTO_INCREMENT for table `leaves`
--
ALTER TABLE `leaves`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `month`
--
ALTER TABLE `month`
  MODIFY `month_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `roles_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE `salary`
  MODIFY `salary_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `saluation`
--
ALTER TABLE `saluation`
  MODIFY `saluation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `state_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
