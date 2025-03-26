-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-03-2025 a las 09:29:54
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `f1_for_java2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `CodEq` int(11) NOT NULL,
  `NomEq` varchar(100) NOT NULL,
  `CodMot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`CodEq`, `NomEq`, `CodMot`) VALUES
(1, 'McLaren Formula 1 Team', 2),
(2, 'Mercedes-AMG PETRONAS F1 Team', 2),
(3, 'Oracle Red Bull Racing', 6),
(4, 'Atlassian Williams Racing', 2),
(5, 'Aston Martin Aramco F1 Team', 2),
(6, 'Kick Sauber F1 Team', 1),
(7, 'Scuderia Ferrari HP', 1),
(8, 'BWT Alpine F1 Team', 3),
(9, 'Visa Cash App Racing Bulls F1 Team', 6),
(10, 'MoneyGram Haas F1 Team', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grandprix`
--

CREATE TABLE `grandprix` (
  `CodGP` int(11) NOT NULL,
  `NomGP` varchar(100) NOT NULL,
  `FecGp` date NOT NULL,
  `NumGp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grandprix`
--

INSERT INTO `grandprix` (`CodGP`, `NomGP`, `FecGp`, `NumGp`) VALUES
(1, 'Formula 1 Louis Vuitton Australian Grand Prix 2025', '2025-03-16', 1),
(2, 'Formula 1 Heineken Chinese Grand Prix 2025', '2025-03-23', 2),
(3, 'Formula 1 Lenovo Japanese Grand Prix 2025', '2025-04-06', 3),
(4, 'Formula 1 Gulf Air Bahrain Grand Prix 2025', '2025-04-13', 4),
(5, 'Formula 1 stc Saudi Arabian Grand Prix 2025', '2025-04-20', 5),
(6, 'Formula 1 Crypto.com Miami Grand Prix 2025', '2025-05-04', 6),
(7, 'Formula 1 AWS Gran Premio del Made in Italy e dell\'Emilia Romagna 2025', '2025-05-18', 7),
(8, 'Formula 1 TAG Heuer Grand Prix de Monaco 2025', '2025-05-25', 8),
(9, 'Formula 1 Aramco Gran Premio de España 2025', '2025-06-01', 9),
(10, 'Formula 1 Pirelli Grand Prix du Canada 2025', '2025-06-15', 10),
(11, 'Formula 1 MSC Cruises Austrian Grand Prix 2025', '2025-06-29', 11),
(12, 'Formula 1 Qatar Airways British Grand Prix 2025', '2025-07-06', 12),
(13, 'Formula 1 Moët & Chandon Belgian Grand Prix 2025', '2025-07-27', 13),
(14, 'Formula 1 Lenovo Hungarian Grand Prix 2025', '2025-08-03', 14),
(15, 'Formula 1 Heineken Dutch Grand Prix 2025', '2025-08-31', 15),
(16, 'Formula 1 Pirelli Gran Premio d\'Italia 2025', '2025-09-07', 16),
(17, 'Formula 1 Qatar Airways Azerbaijan Grand Prix 2025', '2025-09-21', 17),
(18, 'Formula 1 Singapore Airlines Singapore Grand Prix 2025', '2025-10-05', 18),
(19, 'Formula 1 MSC Cruises United States Grand Prix 2025', '2025-10-19', 19),
(20, 'Formula 1 Gran Premio de la Ciudad de México 2025', '2025-10-26', 20),
(21, 'Formula 1 MSC Cruises Grande Prêmio de São Paulo 2025', '2025-11-09', 21),
(22, 'Formula 1 Heineken Las Vegas Grand Prix 2025', '2025-11-22', 22),
(23, 'Formula 1 Qatar Airways Qatar Grand Prix 2025', '2025-11-30', 23),
(24, 'Formula 1 Etihad Airways Abu Dhabi Grand Prix 2025', '2025-12-07', 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motor`
--

CREATE TABLE `motor` (
  `CodMot` int(11) NOT NULL,
  `NomMot` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `motor`
--

INSERT INTO `motor` (`CodMot`, `NomMot`) VALUES
(1, 'Motor Ferrari'),
(2, 'Motor Mercedes'),
(3, 'Motor Renault'),
(4, 'Motor Audi'),
(5, 'Motor Ford'),
(6, 'Motor Honda'),
(7, 'Motor Cadillac');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pilotos`
--

CREATE TABLE `pilotos` (
  `CodPil` int(11) NOT NULL,
  `NomPil` varchar(100) NOT NULL,
  `Ape1Pil` varchar(100) NOT NULL,
  `CodEq` int(11) NOT NULL,
  `FechaNacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pilotos`
--

INSERT INTO `pilotos` (`CodPil`, `NomPil`, `Ape1Pil`, `CodEq`, `FechaNacimiento`) VALUES
(1, 'Lando', 'Norris', 1, '1999-11-13'),
(2, 'Oscar', 'Piastri', 1, '2001-04-06'),
(3, 'Andrea', 'Kimi Antonelli', 2, '2006-08-25'),
(4, 'George', 'Russell', 2, '1998-02-15'),
(5, 'Max', 'Verstappen', 3, '1997-09-30'),
(6, 'Liam', 'Lawson', 3, '2002-02-11'),
(7, 'Alexander', 'Albon', 4, '1996-03-23'),
(8, 'Carlos', 'Sainz', 4, '1994-09-01'),
(9, 'Lance', 'Stroll', 5, '1998-10-29'),
(10, 'Fernando', 'Alonso', 5, '1981-07-29'),
(11, 'Gabriel', 'Bortoleto', 6, '2004-10-14'),
(12, 'Nico', 'Hülkenberg', 6, '1987-08-19'),
(13, 'Charles', 'Leclerc', 7, '1997-10-16'),
(14, 'Lewis', 'Hamilton', 7, '1985-01-07'),
(15, 'Jack', 'Doohan', 8, '2003-10-20'),
(16, 'Pierre', 'Gasly', 8, '1996-02-07'),
(17, 'Yuki', 'Tsunoda', 9, '2000-05-11'),
(18, 'Isack', 'Hadjar', 9, '2004-09-28'),
(19, 'Esteban', 'Ocon', 10, '1996-09-17'),
(20, 'Oliver', 'Bearman', 10, '2005-05-08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultado`
--

CREATE TABLE `resultado` (
  `pos` int(11) NOT NULL,
  `CodPil` int(11) NOT NULL,
  `CodGP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `resultado`
--

INSERT INTO `resultado` (`pos`, `CodPil`, `CodGP`) VALUES
(1, 1, 1),
(2, 1, 2),
(9, 2, 1),
(1, 2, 2),
(4, 3, 1),
(6, 3, 2),
(3, 4, 1),
(3, 4, 2),
(2, 5, 1),
(4, 5, 2),
(12, 6, 2),
(5, 7, 1),
(7, 7, 2),
(10, 8, 2),
(6, 9, 1),
(9, 9, 2),
(14, 11, 2),
(7, 12, 1),
(15, 12, 2),
(8, 13, 1),
(10, 14, 1),
(13, 15, 2),
(11, 16, 1),
(12, 17, 1),
(16, 17, 2),
(11, 18, 2),
(13, 19, 1),
(5, 19, 2),
(14, 20, 1),
(8, 20, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`CodEq`),
  ADD KEY `CodMot` (`CodMot`);

--
-- Indices de la tabla `grandprix`
--
ALTER TABLE `grandprix`
  ADD PRIMARY KEY (`CodGP`);

--
-- Indices de la tabla `motor`
--
ALTER TABLE `motor`
  ADD PRIMARY KEY (`CodMot`);

--
-- Indices de la tabla `pilotos`
--
ALTER TABLE `pilotos`
  ADD PRIMARY KEY (`CodPil`),
  ADD KEY `CodEq` (`CodEq`);

--
-- Indices de la tabla `resultado`
--
ALTER TABLE `resultado`
  ADD PRIMARY KEY (`CodPil`,`CodGP`),
  ADD KEY `CodGP` (`CodGP`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `CodEq` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `grandprix`
--
ALTER TABLE `grandprix`
  MODIFY `CodGP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `motor`
--
ALTER TABLE `motor`
  MODIFY `CodMot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `pilotos`
--
ALTER TABLE `pilotos`
  MODIFY `CodPil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`CodMot`) REFERENCES `motor` (`CodMot`);

--
-- Filtros para la tabla `pilotos`
--
ALTER TABLE `pilotos`
  ADD CONSTRAINT `pilotos_ibfk_1` FOREIGN KEY (`CodEq`) REFERENCES `equipo` (`CodEq`);

--
-- Filtros para la tabla `resultado`
--
ALTER TABLE `resultado`
  ADD CONSTRAINT `resultado_ibfk_1` FOREIGN KEY (`CodPil`) REFERENCES `pilotos` (`CodPil`),
  ADD CONSTRAINT `resultado_ibfk_2` FOREIGN KEY (`CodGP`) REFERENCES `grandprix` (`CodGP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
