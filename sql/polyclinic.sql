DROP DATABASE IF EXISTS `polyclinic`;
CREATE DATABASE `polyclinic`; 
USE `polyclinic`;

CREATE TABLE `polyclinic`.`medical_specialties` (
  `id_speciality` INT NOT NULL AUTO_INCREMENT,
  `name_speciality` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_speciality`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;  
  
  INSERT INTO `medical_specialties` (`name_speciality`)
VALUES
("Аллерголог"),
("Гастроэнтеролог"),
("Гинеколог"),
("Дерматолог"),
("Диетолог"),
("Иммунолог"),
("Кардиолог"),
("Оториноларинголог"),
("Психиатр"),
("Пульмонолог"),
("Терапевт"),
("Уролог");
    
  CREATE TABLE `polyclinic`.`rooms` (
  `id_room` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `state` ENUM ('FREE', 'BUSY') NULL DEFAULT 'FREE',
  PRIMARY KEY (`id_room`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin; 

  CREATE TABLE `polyclinic`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `category` ENUM ('ADMINISTRATOR', 'PATIENT', 'DOCTOR') NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;
  
  CREATE TABLE `polyclinic`.`patients` (
  `id_patient` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id_patient`), INDEX `user_patient_idx` (`user_id` ASC),
  CONSTRAINT `user_patient` FOREIGN KEY (`user_id`) REFERENCES `polyclinic`.`users` (`id_user`) ON DELETE CASCADE) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;


CREATE TABLE `polyclinic`.`administrators` (
  `id_administrator` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_administrator`), INDEX `user_administrator_idx` (`user_id` ASC), CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`) REFERENCES `polyclinic`.`users` (`id_user`) ON DELETE CASCADE) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;
  
 CREATE TABLE `polyclinic`.`doctors` (
  `id_doctor` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `room` INT NOT NULL,
  `speciality` INT NOT NULL,
  PRIMARY KEY (`id_doctor`), INDEX `user_idx` (`user_id` ASC), INDEX `room_idx` (`room` ASC), INDEX `specialties_idx` (`speciality` ASC),
  CONSTRAINT `user_doctor` FOREIGN KEY (`user_id`) REFERENCES `polyclinic`.`users` (`id_user`) ON DELETE CASCADE,
  CONSTRAINT `room` FOREIGN KEY (`room`) REFERENCES `polyclinic`.`rooms` (`id_room`) ON DELETE CASCADE,
  CONSTRAINT `specialties` FOREIGN KEY (`speciality`) REFERENCES `polyclinic`.`medical_specialties` (`id_speciality`) ON DELETE CASCADE) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;
    
CREATE TABLE `polyclinic`.`schedule` (
  `id_schedule` INT NOT NULL AUTO_INCREMENT,
  `doctor_id` INT NOT NULL,
  `date` DATE NULL,
  `duration_reception` INT NOT NULL,
  `time_start` TIME NOT NULL,
  `time_end` TIME NOT NULL,
  PRIMARY KEY (`id_schedule`), INDEX `doctor_idx` (`doctor_id` ASC),
  CONSTRAINT `doctor` FOREIGN KEY (`doctor_id`) REFERENCES `polyclinic`.`doctors` (`id_doctor`) ON DELETE CASCADE
    ON UPDATE CASCADE) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;
    
    CREATE TABLE `polyclinic`.`appointment` (
  `id_appointment` INT NOT NULL AUTO_INCREMENT,
  `scheduleId` INT NOT NULL,
  `state` ENUM ('FREE', 'BUSY') NULL DEFAULT 'FREE',
  `time_start` TIME NOT NULL,
  `time_end` TIME NOT NULL,
  PRIMARY KEY (`id_appointment`), INDEX `schedule_idx` (`scheduleId` ASC),
  CONSTRAINT `schedule` 
  FOREIGN KEY (`scheduleId`) REFERENCES `polyclinic`.`schedule` (`id_schedule`) ON DELETE CASCADE)
    ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;
 
 CREATE TABLE `polyclinic`.`tickets` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NULL,
  `appointment_id` INT NOT NULL, 
  PRIMARY KEY (`id_ticket`), INDEX `patient_ticket_idx` (`patient_id` ASC),
  CONSTRAINT `patient_ticket` FOREIGN KEY (`patient_id`) REFERENCES `polyclinic`.`patients` (`id_patient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointment_ticket` FOREIGN KEY (`appointment_id`) REFERENCES `polyclinic`.`appointment` (`id_appointment`) ON DELETE CASCADE ON UPDATE CASCADE)
  ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_bin;