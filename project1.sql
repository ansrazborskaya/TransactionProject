create database Project1;
use Project1;

create table bank (
	id int primary key auto_increment,
    title varchar(50) not null unique,
	comission double (2, 1)
    );
    
    create table accounts (
	id int primary key auto_increment,
    amount double (15,2) not null,
	bank_id int,
    foreign key (bank_id) references bank (id)
    );
    
   

      create table transactions (
	id int primary key auto_increment,
    date_time datetime not null,
	account_from_id int,
    account_to_id int,
    amount double (15,2) not null,
    status varchar(50) not null,
    foreign key (account_from_id) references accounts (id),
    foreign key (account_to_id) references accounts (id)
    );
    
    insert into bank (title, comission) values 
		("Priorbank", 2.1),
        ("VTB", 4.0),
        ("BSB", 3.5);
        
	insert into accounts (amount, bank_id) values
		(5000.00, 1),
        (0.00, 2),
        (750.00, 3),
        (10.70, 1),
        (85.88, 1),
        (1035.11, 2),
        (120.00, 2),
        (0.00, 3);
        
	insert into transactions (date_time, account_from_id, account_to_id,  amount, status) values
		('2020-02-23 10:22:22', 7, 8, 5.00, "DONE"),
        ('2020-02-23 09:37:22', 2, 1, 200.00, "FAILED"),
        ('2020-01-31 12:32:21', 3, 5, 15.00, "DONE"),
        ('2020-01-30 18:00:01', 6, 7, 20.50, "DONE"),
        ('2020-01-30 17:21:02', 5, 4, 60.00, "DONE");
        
        
        
	
        
        select * from bank;
        select * from accounts;
        select * from transactions;
        
select t.date_time, t.account_from_id as Sender, t.account_to_id as Receiver, a.amount as Balance, t.amount as SumOfTransaction, b.title, b.comission
	from transactions t join accounts a 
    on (a.id = t.account_from_id)
	join bank b on (b.id = a.bank_id);
    
    select a.amount, b.comission from accounts a join bank b on (b.id = a.bank_id)  where a.id = 1;
    
    update accounts set amount = '4500.00' where id = '1';
        