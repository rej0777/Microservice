package userservice.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;


//@Service
public class DataSetupService implements CommandLineRunner {

	@Value("classpah:init.sql")
	private Resource initsql;
	

	String query = """
			
			CREATE TABLE users(
id bigint auto_increment PRIMARY KEY,
name varchar(50),
balance int
);

CREATE TABLE user_transaction(
id bigint auto_increment PRIMARY KEY,
user_id bigint,
amount int,
transaction_date timestamp,
foreign key (user_id) references users(id) on delete cascade
);
insert into users
			(name, balance)
			values ('user1', 1000), ('user2', 5000), ('user3', 500);
			""";
	
	
	
	@Autowired
	private R2dbcEntityTemplate entityTemplate;
	
	@Override
	public void run(String... args) throws Exception {

	//	String query = StreamUtils.copyToString(( initsql()), StandardCharsets.UTF_8);
		System.out.println(query);
		this.entityTemplate
		.getDatabaseClient()
		.sql(query)
		.then()
		.subscribe();
		
	}

}

/*
Dla Postgres
	CREATE TABLE users(
id  SERIAL PRIMARY KEY,
name varchar(50),
balance int
);

CREATE TABLE user_transaction(
id SERIAL PRIMARY KEY,
user_id bigint,
amount int,
transaction_date timestamp,
foreign key (user_id) references users(id) on delete cascade
);



insert into users
			(name, balance)
			values ('user1', 1000), ('user2', 5000), ('user3', 500);
*/