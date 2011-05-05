SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `Poo2` ;
CREATE SCHEMA IF NOT EXISTS `Poo2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `Poo2` ;

-- -----------------------------------------------------
-- Table `Poo2`.`TipoDocumento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`TipoDocumento` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`TipoDocumento` (
  `idTipoDocumento` INT NOT NULL ,
  `tipoDocumento` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idTipoDocumento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`Ciudad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`Ciudad` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`Ciudad` (
  `idCiudad` INT NOT NULL ,
  `ciudad` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idCiudad`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`Cliente` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`Cliente` (
  `idCliente` INT NOT NULL ,
  `apellido` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `documento` INT NOT NULL ,
  `TipoDocumento_idTipoDocumento` INT NOT NULL ,
  `Ciudad_idCiudad` INT NOT NULL ,
  `cuit` VARCHAR(13) NULL ,
  `direccion` VARCHAR(45) NULL ,
  PRIMARY KEY (`idCliente`) ,
  INDEX `fk_Cliente_TipoDocumento1` (`TipoDocumento_idTipoDocumento` ASC) ,
  INDEX `fk_Cliente_Ciudad1` (`Ciudad_idCiudad` ASC) ,
  CONSTRAINT `fk_Cliente_TipoDocumento1`
    FOREIGN KEY (`TipoDocumento_idTipoDocumento` )
    REFERENCES `Poo2`.`TipoDocumento` (`idTipoDocumento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Ciudad1`
    FOREIGN KEY (`Ciudad_idCiudad` )
    REFERENCES `Poo2`.`Ciudad` (`idCiudad` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`FormaDePago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`FormaDePago` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`FormaDePago` (
  `idFormaDePago` INT NOT NULL ,
  `formaDePago` VARCHAR(45) NOT NULL ,
  `condicion` FLOAT NOT NULL ,
  `descuentoInteres` FLOAT NOT NULL ,
  `cantidadCuotas` INT NOT NULL ,
  PRIMARY KEY (`idFormaDePago`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`TipoFactura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`TipoFactura` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`TipoFactura` (
  `idTipoFactura` INT NOT NULL ,
  `tipoFactura` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idTipoFactura`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`Factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`Factura` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`Factura` (
  `idFactura` INT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `Cliente_idCliente` INT NOT NULL ,
  `FormaDePago_idFormaDePago` INT NOT NULL ,
  `TipoFactura_idTipoFactura` INT NOT NULL ,
  `descuentoInteres` FLOAT NOT NULL ,
  PRIMARY KEY (`idFactura`) ,
  INDEX `fk_Factura_Cliente1` (`Cliente_idCliente` ASC) ,
  INDEX `fk_Factura_FormaDePago1` (`FormaDePago_idFormaDePago` ASC) ,
  INDEX `fk_Factura_TipoFactura1` (`TipoFactura_idTipoFactura` ASC) ,
  CONSTRAINT `fk_Factura_Cliente1`
    FOREIGN KEY (`Cliente_idCliente` )
    REFERENCES `Poo2`.`Cliente` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_FormaDePago1`
    FOREIGN KEY (`FormaDePago_idFormaDePago` )
    REFERENCES `Poo2`.`FormaDePago` (`idFormaDePago` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_TipoFactura1`
    FOREIGN KEY (`TipoFactura_idTipoFactura` )
    REFERENCES `Poo2`.`TipoFactura` (`idTipoFactura` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`Articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`Articulo` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`Articulo` (
  `idArticulo` INT NOT NULL ,
  `codigoArticulo` VARCHAR(45) NOT NULL ,
  `articulo` VARCHAR(45) NOT NULL ,
  `precio` FLOAT NOT NULL ,
  PRIMARY KEY (`idArticulo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Poo2`.`FacturaItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Poo2`.`FacturaItem` ;

CREATE  TABLE IF NOT EXISTS `Poo2`.`FacturaItem` (
  `Articulo_idArticulo` INT NOT NULL ,
  `Factura_idFactura` INT NOT NULL ,
  `cantidad` INT NOT NULL ,
  `precio` FLOAT NOT NULL ,
  PRIMARY KEY (`Articulo_idArticulo`, `Factura_idFactura`) ,
  INDEX `fk_Articulo_has_Factura_Articulo1` (`Articulo_idArticulo` ASC) ,
  INDEX `fk_Articulo_has_Factura_Factura1` (`Factura_idFactura` ASC) ,
  CONSTRAINT `fk_Articulo_has_Factura_Articulo1`
    FOREIGN KEY (`Articulo_idArticulo` )
    REFERENCES `Poo2`.`Articulo` (`idArticulo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Articulo_has_Factura_Factura1`
    FOREIGN KEY (`Factura_idFactura` )
    REFERENCES `Poo2`.`Factura` (`idFactura` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
