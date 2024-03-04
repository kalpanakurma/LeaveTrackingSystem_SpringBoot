-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 20, 2024 at 11:37 AM
-- Server version: 8.0.36-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
--

-- --------------------------------------------------------

--
-- Table structure for table `leave_application`
--

CREATE TABLE `leave_application` (
  `id` bigint NOT NULL,
  `end_date` date DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('APPROVED','REJECTED','PENDING') DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `manager_comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


  ALTER TABLE leave_application
ADD COLUMN leavetype VARCHAR(255);


--
-- Dumping data for table `leave_application`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `personal_email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mobile_no` bigint NOT NULL,
  `date_of_joining` date DEFAULT NULL,
  `manager_id` bigint DEFAULT NULL,
  `role` enum('EMPLOYEE','MANAGER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `leave_application`
--
ALTER TABLE `leave_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKluc5o41p06fg90rscfa04390u` (`employee_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `leave_application`
--
ALTER TABLE `leave_application`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `leave_application`
--
ALTER TABLE `leave_application`
  ADD CONSTRAINT `FKluc5o41p06fg90rscfa04390u` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`);
  
  -- creating table for maxleaves 
 CREATE TABLE `maxleaves` (
  `id` bigint NOT NULL,
  `leave_type` varchar(255) NOT NULL,
  `max_leaves` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 
 
 ALTER TABLE `maxleaves`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;
  
  
  
  
  -- view for employee wise leaves summary
  
   CREATE or replace VIEW leave_summary AS
SELECT
    ROW_NUMBER() OVER () AS serial_no,
    u.name,
     -- (SELECT SUM(max_leaves) FROM settings) AS total_max_leaves,
    SUM(CASE WHEN l.status = 'APPROVED' THEN 1 ELSE 0 END) AS leaves_approved,
    SUM(CASE WHEN l.status = 'PENDING' THEN 1 ELSE 0 END) AS leaves_pending,
    SUM(CASE WHEN l.status = 'REJECTED' THEN 1 ELSE 0 END) AS leaves_rejected,
    (SELECT SUM(max_leaves) FROM maxleaves) - SUM(CASE WHEN l.status = 'APPROVED' THEN 1 ELSE 0 END) - SUM(CASE WHEN l.status = 'PENDING' THEN 1 ELSE 0 END) - SUM(CASE WHEN l.status = 'REJECTED' THEN 1 ELSE 0 END) AS leaves_remaining
FROM
    `leave_application` l
JOIN
    user u ON l.employee_id = u.id
GROUP BY
    u.id;
  
  
  -- procedure to get datewise leaves
  
  DELIMITER $$
CREATE PROCEDURE leave_summary_filtered(IN fromDate DATE, IN toDate DATE)
BEGIN
    SELECT
        ROW_NUMBER() OVER () AS serial_no,
        u.name,
        SUM(CASE WHEN l.status = 'APPROVED' THEN 1 ELSE 0 END) AS leaves_approved,
        SUM(CASE WHEN l.status = 'PENDING' THEN 1 ELSE 0 END) AS leaves_pending,
        SUM(CASE WHEN l.status = 'REJECTED' THEN 1 ELSE 0 END) AS leaves_rejected
    FROM
        `leave_application` l
    JOIN
        user u ON l.employee_id = u.id
    WHERE
        (l.start_date BETWEEN fromDate AND toDate) AND (l.end_date <= toDate)
    GROUP BY
        u.id;
END $$
  
  
  --CALL leave_summary_filtered('2024-02-01', '2024-03-01');
  
  
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
