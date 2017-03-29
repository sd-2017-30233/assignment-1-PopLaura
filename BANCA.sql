CREATE DATABASE IF NOT EXISTS bank;
USE `bank` ;

create table if not exists roles
(role_id int unique not null auto_increment primary key,
role_name char(40));

create table if not exists users
(user_id int unique not null auto_increment primary key,
user_name char(40),
email char(40),
passwords char(40),
address char(40),
phone char(40));


create table if not exists user_roles
(user_id int,
role_id int);


create table if not exists clients
(CNP char(40) unique not null primary key,
client_name char(40),
address char(40),
phone char(40));

create table if not exists account
(nr_cont char(40) unique not null primary key,
client_id char(40),
dataa date,
amount int);

create table if not exists tip_operatiune
(id_op int unique not null auto_increment primary key,
op_name char(40));

create table if not exists tranzactie
(id_tranz int unique not null auto_increment primary key,
cont_sursa char(40),
cont_destinatie char(40),
id_op int,
user_id int,
amount float,
dataa date,
timp time,
descriere char(40));

alter table user_roles
add constraint fk_user_roles_roles
foreign key (role_id)
references roles(role_id)
on update cascade
on delete cascade;

alter table user_roles
add constraint fk_user_roles_users
foreign key (user_id)
references users(user_id)
on update cascade
on delete cascade;

alter table account
add constraint fk_account_clients
foreign key (client_id)
references clients(CNP)
on update cascade
on delete cascade;

alter table tranzactie
add constraint fk_tranzactie_account
foreign key (cont_sursa)
references account(nr_cont)
on update cascade
on delete cascade;

alter table tranzactie
add constraint fk_tranzactie_account1
foreign key (cont_destinatie)
references account(nr_cont)
on update cascade
on delete cascade;

alter table tranzactie
add constraint fk_tranzactie_tip_operatiune
foreign key (id_op)
references tip_operatiune(id_op)
on update cascade
on delete cascade;

alter table tranzactie
add constraint fk_tranzactie_users
foreign key (user_id)
references users(user_id)
on update cascade
on delete cascade;

DROP TRIGGER IF EXISTS insert_cont_client;

delimiter //

CREATE TRIGGER insert_cont_client AFTER INSERT ON clients
  FOR EACH ROW BEGIN
    INSERT INTO account (nr_cont, client_id, dataa, amount) VALUES(NEW.CNP * 10001, NEW.CNP, CURRENT_DATE(), 0);
  END; //

delimiter ;


DROP TRIGGER IF EXISTS adauga_tranzactie_creare_cont;

delimiter //

CREATE TRIGGER adauga_tranzactie_creare_cont AFTER INSERT ON account
  FOR EACH ROW BEGIN
    INSERT INTO  tranzactie (cont_sursa, cont_destinatie, id_op, user_id, amount, dataa, timp, descriere) 
    VALUES 
    (NEW.nr_cont, NEW.nr_cont, (SELECT id_op from tip_operatiune WHERE op_name = 'Deschidere cont'), NULL, 0, CURRENT_DATE(), NOW(), 'Deschidere cont');
  END; //

delimiter ;

INSERT INTO users
(user_name, email, passwords, address, phone)
VALUES
('Popa Emiliana', 'emiliana2395@yahoo.com', 'papalapte', 'Turda', '3663654355'),
('Mocan Ioana', 'mioanaelena@yahoo.com', 'papalapte2', 'Turda', '646464446'),
('Pop Laura', 'maria_laura_pop@yahoo.com', 'admin','Strada Scarisoara nr1','0786222079');

INSERT INTO tip_operatiune
(op_name)
VALUES
('Transfer intre conturi'),
('Plati facturi'),
('Deschidere cont'),
('Error: suma insuficienta');

INSERT INTO clients 
(CNP, client_name, address, phone) 
VALUES
('8998698','Popa Ion', 	  'Str. Mare, Dabuleni, Ilfov', '056564'),
('0909879','Popescu Maria',  'Blvd. Primaverii, Bucuresti', '056564'),
('7658656','Andreescu Victor', 'Blvd. Iuliu Maniu, Bucuresti', '056564'),
('7868765','Predescu Andreea', 'Str. Virtutii, Baia Mare','056564'),
('876r656','Ionescu Mihaela', 'Str. Visinilor, Bucuresti', '056564'),
('5445435','Dinu Cornel', 'Calea Victoriei, Bucuresti', '056564'),
('6564554','Vodafone S.A',   'Bucuresti', '056564'),
('5765654','Romtelecon S.A',   'Bucuresti', '056564'); 




INSERT INTO roles 
(role_name)
VALUES
('admin'),
('not admin');



INSERT INTO user_roles
(user_id, role_id)
VALUES
(1, 2),
(2, 2),
(3, 1);

DROP PROCEDURE IF EXISTS TRANSFER_INTRE_CONTURI;

DELIMITER //

CREATE PROCEDURE 
   TRANSFER_INTRE_CONTURI(cont_sursa_cod char(40), cont_destinatie_cod char(40),  valoare_transfer float, detaliu_transfer char(40), user_id int)   
	
  BEGIN
    START TRANSACTION; 
    	    SET @ID_S = NULL, @ID_D = NULL, @VAL = NULL, @ID_TIP1 = NULL, @ID_TIP2 = NULL, @ID_USER=user_id;
    	    SELECT @ID_S := nr_cont FROM account WHERE nr_cont = cont_sursa_cod;
    	    SELECT @ID_D := nr_cont FROM account WHERE nr_cont = cont_destinatie_cod;
    	    SELECT @VAL := amount FROM account WHERE nr_cont = @ID_S;
    	    SELECT @ID_TIP1 := 1; 
            #FROM tip_operatiune WHERE op_name = 'Transfer intre conturi';
    	    SELECT @ID_TIP2 := 4; 
            #FROM tip_operatiune WHERE op_name = 'Error: suma insuficienta';

    	    IF (@VAL < valoare_transfer) THEN 	
		    BEGIN
			INSERT INTO 
                tranzactie (cont_sursa, cont_destinatie, id_op, user_id, amount, dataa, timp, descriere)
			    VALUES
			    (@ID_S, @ID_D, @ID_TIP2, @ID_USER, 0, CURRENT_DATE(), NOW(),detaliu_transfer);
		    	COMMIT;
		    END;
	    ELSEIF (@ID_S IS NOT NULL AND @ID_D IS NOT NULL) THEN 	
		    BEGIN
			INSERT INTO tranzactie (cont_sursa, cont_destinatie, id_op, user_id, amount, dataa, timp, descriere)
			    VALUES
			    (@ID_S, @ID_D, @ID_TIP1,@ID_USER, valoare_transfer,CURRENT_DATE(), NOW(),detaliu_transfer);
			    
			UPDATE account SET amount = amount - valoare_transfer WHERE nr_cont = @ID_S;
			
   			UPDATE account SET amount = amount + valoare_transfer WHERE nr_cont = @ID_D;
   			
			COMMIT;
		    END;

	   ELSE ROLLBACK;
    	   END IF;
  END //
DELIMITER ;

DROP PROCEDURE IF EXISTS ADD_USER;
DELIMITER //
CREATE PROCEDURE ADD_USER(nume char(40), email char(40), pass char(40), address char(40), phone char(40), admin char(40))
BEGIN 
    IF(admin="admin") THEN
    BEGIN
    INSERT INTO users (user_name, email, passwords, address, phone)
		VALUES
        (nume, email, pass, address, phone);
	INSERT INTO user_roles (user_id, role_id)
    VALUES
        ((SELECT user_id FROM users WHERE user_name=nume), 1);
        COMMIT;
        END;
	ELSEIF(admin="not admin") THEN
	BEGIN
    INSERT INTO users (user_name, email, passwords, address, phone)
		VALUES
        (nume, email, pass, address, phone);
	INSERT INTO user_roles (user_id, role_id)
    VALUES
        ((SELECT user_id FROM users WHERE user_name=nume), 2);
        COMMIT;
        END;
	ELSE ROLLBACK;
		END IF;
  END //
DELIMITER ;

DROP PROCEDURE IF EXISTS UPDATE_USER;
DELIMITER //
CREATE PROCEDURE UPDATE_USER(user_id int, nume char(40), email char(40), pass char(40), address char(40), phone char(40), admin char(40))
BEGIN 
	UPDATE users 
    SET user_name=nume, email=email, passwords=pass, address=address, phone=phone
    WHERE users.user_id=user_id;
    IF(admin="admin") THEN
    BEGIN
		UPDATE user_roles 
		SET role_id=1
		WHERE user_roles.user_id=user_id;
        COMMIT;
        END;
	ELSEIF(admin="not admin") THEN
	BEGIN
    UPDATE user_roles 
		SET role_id=2
		WHERE user_roles.user_id=user_id;
        COMMIT;
        END;
	ELSE ROLLBACK;
		END IF;
    
  END //
DELIMITER ;
#call TRANSFER_INTRE_CONTURI('54459795435', '57662305654',10, 'Proba', 1);
#call UPDATE_USER(1,'Popa Emiliana','emiliana2395@yahoo.com','papalapte','Turda','3663654355','not admin');