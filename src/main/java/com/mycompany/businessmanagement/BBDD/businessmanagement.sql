-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-12-2025 a las 22:24:44
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
-- Base de datos: `businessmanagement`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id` bigint(20) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `codigoPostal` varchar(10) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `provincia` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT 'ES',
  `etiqueta` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`id`, `direccion`, `codigoPostal`, `ciudad`, `provincia`, `pais`, `etiqueta`) VALUES
(1, 'c/ De las pruebas 15', '12345', 'Madrid', 'Madrid', 'España', 'F'),
(2, 'c/ De las pruebas 15', '12345', 'Madrid', 'Madrid', 'España', 'F'),
(3, 'c/ De las pruebas 15', '12345', 'Madrid', 'Madrid', 'España', 'F'),
(4, 'Calle Mayor 10', '28001', 'Madrid', 'Madrid', 'ES', 'F'),
(5, 'Av. Andalucía 22', '29005', 'Málaga', 'Málaga', 'ES', 'E'),
(6, 'C/ Valencia 15', '46001', 'Valencia', 'Valencia', 'ES', 'F'),
(7, 'Gran Vía 100', '28010', 'Madrid', 'Madrid', 'ES', 'O'),
(8, 'C/ Industria 88', '08025', 'Barcelona', 'Barcelona', 'ES', 'F'),
(9, 'diegodiego', '02222', 'diegodiego', 'diegodiego', 'diegodiego', 'c');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `web` varchar(200) DEFAULT NULL,
  `fk_id_direccion` bigint(20) NOT NULL,
  `fk_id_informacion` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id`, `codigo`, `nombre`, `web`, `fk_id_direccion`, `fk_id_informacion`) VALUES
(1, 1, 'EmpresaPrueba SL', 'https://empresaprueba.com', 1, 1),
(2, 2, 'EmpresaPrueba3 SL', 'https://empresaprueba3.com', 3, 3),
(3, 1, 'DemoSoft S.L.', 'https://www.demosoft.com', 1, 1),
(4, 10, 'diegodiego', 'diegodiego.com', 9, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidad`
--

CREATE TABLE `entidad` (
  `id` bigint(20) NOT NULL,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `fk_id_informacion` bigint(20) NOT NULL,
  `fk_id_direccion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entidad`
--

INSERT INTO `entidad` (`id`, `codigo`, `nombre`, `fk_id_informacion`, `fk_id_direccion`) VALUES
(1, 100, 'Cliente Uno S.A.', 2, 2),
(2, 101, 'Cliente Dos SL', 3, 3),
(3, 200, 'Proveedor Uno', 4, 4),
(4, 201, 'Proveedor Dos', 5, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fabricante`
--

CREATE TABLE `fabricante` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `fabricante`
--

INSERT INTO `fabricante` (`id`, `nombre`) VALUES
(1, 'Fabricante A'),
(2, 'Fabricante B'),
(3, 'Fabricante C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` bigint(20) NOT NULL,
  `fk_id_empresa` bigint(20) NOT NULL,
  `fk_id_cliente` bigint(20) DEFAULT NULL,
  `tipo` varchar(100) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_servicio` date DEFAULT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `base_imponible` decimal(14,2) NOT NULL,
  `iva_total` decimal(14,2) NOT NULL,
  `total_factura` decimal(14,2) NOT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fk_id_empresa`, `fk_id_cliente`, `tipo`, `numero`, `fecha_emision`, `fecha_servicio`, `concepto`, `base_imponible`, `iva_total`, `total_factura`, `estado`, `observaciones`) VALUES
(1, 1, 1, 'V', 'F2025-0001', '2025-01-10', '2025-01-10', 'Venta de productos informáticos', 185.00, 38.85, 223.85, 'PENDIENTE', 'Pago previsto fin de mes'),
(2, 3, 1, 'SERVICIO', '1231', '2005-09-22', '2005-09-22', 'asffs', 123.00, 123.00, 123.00, 'PENDIENTE', 'sdfsd'),
(3, 3, 1, 'SERVICIO', '123123', '2005-09-22', '2005-09-22', 'asdsdf', 7214.88, 0.00, 0.00, 'PENDIENTE', 'sadfsdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturadetalle`
--

CREATE TABLE `facturadetalle` (
  `fk_id_factura` bigint(20) NOT NULL,
  `fk_id_producto` bigint(20) NOT NULL,
  `cantidad` decimal(12,4) NOT NULL,
  `precio_unitario` decimal(12,4) DEFAULT NULL,
  `total_linea` decimal(14,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturadetalle`
--

INSERT INTO `facturadetalle` (`fk_id_factura`, `fk_id_producto`, `cantidad`, `precio_unitario`, `total_linea`) VALUES
(1, 1, 1.0000, 45.0000, 45.00),
(1, 2, 2.0000, 25.0000, 50.00),
(1, 3, 1.0000, 120.0000, 120.00),
(2, 1, 12.0000, 20.0000, 240.00),
(2, 2, 13.0000, 10.0000, 130.00),
(3, 2, 12.0000, 10.0000, 120.00),
(3, 3, 123.0000, 70.0000, 8610.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informacion`
--

CREATE TABLE `informacion` (
  `id` bigint(20) NOT NULL,
  `nif` varchar(20) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `informacion`
--

INSERT INTO `informacion` (`id`, `nif`, `email`, `telefono`) VALUES
(1, '12345678H', 'pruebaEmpresa@gmail.com', '+34612345678'),
(2, '12345679H', 'pruebaEmpresa2@gmail.com', '612345678'),
(3, '12345689H', 'pruebaEmpresa3@gmail.com', '612345679'),
(4, '12345678A', 'empresa@demo.com', '600111222'),
(5, '98765432B', 'cliente1@correo.com', '600222333'),
(6, '45678912C', 'cliente2@correo.com', '600333444'),
(7, '12398745D', 'proveedor1@correo.com', '600444555'),
(8, '78945612E', 'proveedor2@correo.com', '600555666'),
(9, '02576642K', 'diegopg2005@gmail.com', '644909974'),
(10, '12576642K', 'diegopsg2005@gmail.com', '644994442');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientostock`
--

CREATE TABLE `movimientostock` (
  `id` bigint(20) NOT NULL,
  `fk_id_producto` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` char(1) NOT NULL,
  `cantidad` decimal(12,4) NOT NULL,
  `motivo` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientostock`
--

INSERT INTO `movimientostock` (`id`, `fk_id_producto`, `fecha`, `tipo`, `cantidad`, `motivo`) VALUES
(1, 1, '2025-01-05', 'E', 100.0000, 'Reposición almacén'),
(2, 1, '2025-01-10', 'S', 1.0000, 'Venta factura 1'),
(3, 2, '2025-01-03', 'E', 80.0000, 'Compra proveedor'),
(4, 2, '2025-01-10', 'S', 2.0000, 'Venta factura 1'),
(5, 3, '2025-01-08', 'E', 40.0000, 'Stock inicial'),
(6, 3, '2025-01-10', 'S', 1.0000, 'Venta factura 1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(13) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `descripcion_aux` varchar(250) DEFAULT NULL,
  `precio_coste` decimal(12,4) NOT NULL DEFAULT 0.0000,
  `precio_venta` decimal(12,4) NOT NULL DEFAULT 0.0000,
  `referencia_proveedor` varchar(50) DEFAULT NULL,
  `stock` decimal(12,4) NOT NULL DEFAULT 0.0000,
  `fk_id_proveedor` bigint(20) DEFAULT NULL,
  `fk_id_fabricante` bigint(20) DEFAULT NULL,
  `fk_id_tipoIva` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `codigo`, `descripcion`, `descripcion_aux`, `precio_coste`, `precio_venta`, `referencia_proveedor`, `stock`, `fk_id_proveedor`, `fk_id_fabricante`, `fk_id_tipoIva`) VALUES
(1, 'P001', 'Teclado mecánico', 'Switch rojo', 20.0000, 45.0000, 'TK-001', 50.0000, 3, 1, 1),
(2, 'P002', 'Ratón inalámbrico', '16000 DPI', 10.0000, 25.0000, 'RT-002', 70.0000, 3, 2, 1),
(3, 'P003', 'Monitor 24\"', '1080p IPS', 70.0000, 120.0000, 'MN-003', 30.0000, 4, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`) VALUES
(3),
(4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoiva`
--

CREATE TABLE `tipoiva` (
  `id` smallint(6) NOT NULL,
  `concepto` varchar(50) NOT NULL,
  `porcentaje` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipoiva`
--

INSERT INTO `tipoiva` (`id`, `concepto`, `porcentaje`) VALUES
(1, 'General', 21.00),
(2, 'Reducido', 10.00),
(3, 'Superreducido', 4.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_empresa_direccion` (`fk_id_direccion`),
  ADD KEY `fk_empresa_informacion` (`fk_id_informacion`);

--
-- Indices de la tabla `entidad`
--
ALTER TABLE `entidad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_entidad_informacion` (`fk_id_informacion`),
  ADD KEY `fk_entidad_direccion` (`fk_id_direccion`);

--
-- Indices de la tabla `fabricante`
--
ALTER TABLE `fabricante`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_factura_empresa` (`fk_id_empresa`),
  ADD KEY `fk_factura_cliente` (`fk_id_cliente`);

--
-- Indices de la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  ADD PRIMARY KEY (`fk_id_factura`,`fk_id_producto`),
  ADD KEY `fk_detalle_producto` (`fk_id_producto`);

--
-- Indices de la tabla `informacion`
--
ALTER TABLE `informacion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nif` (`nif`);

--
-- Indices de la tabla `movimientostock`
--
ALTER TABLE `movimientostock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_mov_producto` (`fk_id_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_producto_proveedor` (`fk_id_proveedor`),
  ADD KEY `fk_producto_fabricante` (`fk_id_fabricante`),
  ADD KEY `fk_producto_tipoiva` (`fk_id_tipoIva`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipoiva`
--
ALTER TABLE `tipoiva`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `entidad`
--
ALTER TABLE `entidad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `fabricante`
--
ALTER TABLE `fabricante`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `informacion`
--
ALTER TABLE `informacion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `movimientostock`
--
ALTER TABLE `movimientostock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_entidad` FOREIGN KEY (`id`) REFERENCES `entidad` (`id`);

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `fk_empresa_direccion` FOREIGN KEY (`fk_id_direccion`) REFERENCES `direccion` (`id`),
  ADD CONSTRAINT `fk_empresa_informacion` FOREIGN KEY (`fk_id_informacion`) REFERENCES `informacion` (`id`);

--
-- Filtros para la tabla `entidad`
--
ALTER TABLE `entidad`
  ADD CONSTRAINT `fk_entidad_direccion` FOREIGN KEY (`fk_id_direccion`) REFERENCES `direccion` (`id`),
  ADD CONSTRAINT `fk_entidad_informacion` FOREIGN KEY (`fk_id_informacion`) REFERENCES `informacion` (`id`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_cliente` FOREIGN KEY (`fk_id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_factura_empresa` FOREIGN KEY (`fk_id_empresa`) REFERENCES `empresa` (`id`);

--
-- Filtros para la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  ADD CONSTRAINT `fk_detalle_factura` FOREIGN KEY (`fk_id_factura`) REFERENCES `factura` (`id`),
  ADD CONSTRAINT `fk_detalle_producto` FOREIGN KEY (`fk_id_producto`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `movimientostock`
--
ALTER TABLE `movimientostock`
  ADD CONSTRAINT `fk_mov_producto` FOREIGN KEY (`fk_id_producto`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_fabricante` FOREIGN KEY (`fk_id_fabricante`) REFERENCES `fabricante` (`id`),
  ADD CONSTRAINT `fk_producto_proveedor` FOREIGN KEY (`fk_id_proveedor`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `fk_producto_tipoiva` FOREIGN KEY (`fk_id_tipoIva`) REFERENCES `tipoiva` (`id`);

--
-- Filtros para la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `fk_proveedor_entidad` FOREIGN KEY (`id`) REFERENCES `entidad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
