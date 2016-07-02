CREATE TABLE public.chamado
(
   id bigserial NOT NULL, 
   assunto character varying(256) NOT NULL, 
   status character varying(32) NOT NULL, 
   mensagem character varying(2048) NOT NULL, 
   PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;