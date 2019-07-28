-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2019 at 03:44 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `refed`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `un` varchar(50) COLLATE utf8_bin NOT NULL,
  `pw` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `objectid` int(11) NOT NULL,
  `atext` text COLLATE utf8_bin NOT NULL,
  `ord` int(11) NOT NULL DEFAULT '1',
  `correct` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `objectid`, `atext`, `ord`, `correct`) VALUES
(1, 1, '4:2', 0, 1),
(2, 1, '3:1', 0, 0),
(3, 2, '6/8', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `lang` int(11) NOT NULL DEFAULT '1',
  `file` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `name`, `lang`, `file`) VALUES
(1, 'Math Module', 1, 'modulepics/math.png'),
(2, 'وحدة الرياضيات', 2, 'modulepics/math.png'),
(3, 'Science Module', 1, 'modulepics/science.png');

-- --------------------------------------------------------

--
-- Table structure for table `modvid`
--

CREATE TABLE `modvid` (
  `id` int(11) NOT NULL,
  `modid` int(11) NOT NULL,
  `file` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `object`
--

CREATE TABLE `object` (
  `id` int(11) NOT NULL,
  `topicid` int(11) NOT NULL,
  `qtext` text COLLATE utf8_bin,
  `file` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ord` int(11) NOT NULL DEFAULT '1',
  `type` int(11) NOT NULL DEFAULT '1',
  `qtype` int(11) NOT NULL DEFAULT '1',
  `hint` text COLLATE utf8_bin,
  `hintpic` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `object`
--

INSERT INTO `object` (`id`, `topicid`, `qtext`, `file`, `ord`, `type`, `qtype`, `hint`, `hintpic`) VALUES
(1, 1, 'What is this the equivalent to 2:1?', 'questionpics/h.jpg', 1, 1, 1, 'This is the hint and its very useful', ''),
(2, 1, 'Fill in the blank: The equivelent for 3/4 is ______', 'questionpics/n.jpg', 2, 1, 3, 'This is the hint and its very usefulThis is the hint and its very usefulThis is the hint and its very useful', ''),
(3, 1, '', 'topicvids/m.mp4', 0, 2, 0, '', ''),
(4, 1, 'Check all that apply (testing enter too):\r\nWhat are the proportions that are equal to 5/15?', '', 0, 1, 2, 'This is the hint and its very usefulThis is the hint and its very useful', '');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `modid` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `ord` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`id`, `modid`, `name`, `ord`) VALUES
(1, 1, 'Ratios', 1),
(2, 1, 'Stats', 2);

-- --------------------------------------------------------

--
-- Table structure for table `secvid`
--

CREATE TABLE `secvid` (
  `id` int(11) NOT NULL,
  `secid` int(11) NOT NULL,
  `file` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `ord` int(11) NOT NULL DEFAULT '1',
  `secid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `name`, `ord`, `secid`) VALUES
(1, 'Proportions', 1, 1),
(2, 'Correlations', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `un` varchar(50) COLLATE utf8_bin NOT NULL,
  `pw` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `topicqid` (`objectid`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `modvid`
--
ALTER TABLE `modvid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `modid` (`modid`);

--
-- Indexes for table `object`
--
ALTER TABLE `object`
  ADD PRIMARY KEY (`id`),
  ADD KEY `topicid` (`topicid`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `modid` (`modid`);

--
-- Indexes for table `secvid`
--
ALTER TABLE `secvid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `secid` (`secid`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`),
  ADD KEY `secid` (`secid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `modvid`
--
ALTER TABLE `modvid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `object`
--
ALTER TABLE `object`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `secvid`
--
ALTER TABLE `secvid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`objectid`) REFERENCES `object` (`id`);

--
-- Constraints for table `modvid`
--
ALTER TABLE `modvid`
  ADD CONSTRAINT `modvid_ibfk_1` FOREIGN KEY (`modid`) REFERENCES `module` (`id`);

--
-- Constraints for table `object`
--
ALTER TABLE `object`
  ADD CONSTRAINT `object_ibfk_1` FOREIGN KEY (`topicid`) REFERENCES `topic` (`id`);

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `section_ibfk_1` FOREIGN KEY (`modid`) REFERENCES `module` (`id`);

--
-- Constraints for table `secvid`
--
ALTER TABLE `secvid`
  ADD CONSTRAINT `secvid_ibfk_1` FOREIGN KEY (`secid`) REFERENCES `section` (`id`);

--
-- Constraints for table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`secid`) REFERENCES `section` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
