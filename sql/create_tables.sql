DROP TABLE IF EXISTS public.pessoa;

ALTER SYSTEM SET max_connections = 300;

-- Use an extension to enable trigram similarity search and improve LIKE performance
-- https://www.postgresql.org/docs/current/runtime-config-connection.htmlhttps://mazeez.dev/posts/pg-trgm-similarity-search-and-fast-like
CREATE EXTENSION pg_trgm;

ALTER DATABASE postgres SET synchronous_commit=OFF;
-- using 25% of memory as suggested in the docs:
--    https://www.postgresql.org/docs/9.1/runtime-config-resource.html
ALTER SYSTEM SET shared_buffers TO "425MB";

-- debug slow queries, run \d pg_stat_statements
-- docs:
--    https://www.postgresql.org/docs/current/pgstatstatements.html
-- CREATE EXTENSION pg_stat_statements;
-- ALTER SYSTEM SET shared_preload_libraries = 'pg_stat_statements';

CREATE TABLE public.pessoa (
	id uuid NOT NULL PRIMARY KEY,
	apelido varchar(32) NOT NULL UNIQUE,
	nascimento varchar NULL,
	nome varchar(100) NOT NULL,
	stack varchar NULL,
	search varchar NULL,
	CONSTRAINT pessoa_apelido_constraint UNIQUE (apelido)
);

CREATE INDEX pessoa_search_index_idx ON public.pessoa USING gin (search gin_trgm_ops);
--CREATE INDEX pessoa_search_index_idx ON public.pessoa USING gist (search GIST_TRGM_OPS(SIGLEN=64));