DROP TABLE IF EXISTS public.pessoa;

CREATE TABLE public.pessoa (
	id uuid NOT NULL,
	apelido varchar(32) NOT NULL,
	nascimento varchar(10) NULL,
	nome varchar(100) NOT NULL,
	stack varchar(255) NULL,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id),
	CONSTRAINT pessoa_apelido_constraint UNIQUE (apelido)
);