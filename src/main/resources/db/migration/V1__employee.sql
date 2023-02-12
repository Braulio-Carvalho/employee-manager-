CREATE TABLE employee (
  id UUID NOT NULL,
  name varchar(255) NOT NULL,
  cpf varchar(255) NOT NULL UNIQUE,
  date_of_birth timestamp NOT NULL,
  phone varchar(255) NOT NULL UNIQUE,
  address varchar(255) NOT NULL,
  remuneration numeric NOT NULL,
  PRIMARY KEY (id)
);
