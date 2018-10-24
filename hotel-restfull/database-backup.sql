--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.user_ DROP CONSTRAINT fk_user__user_status1;
ALTER TABLE ONLY public.user_ DROP CONSTRAINT fk_user__client1;
ALTER TABLE ONLY public.hotel_reservation DROP CONSTRAINT fk_hotel_reservation_reservation_status1;
ALTER TABLE ONLY public.hotel_reservation DROP CONSTRAINT fk_hotel_reservation_client1;
ALTER TABLE ONLY public.hotel_reservation DROP CONSTRAINT fk_hotel_reservation_bedroom1;
ALTER TABLE ONLY public.bill DROP CONSTRAINT fk_bill_hotel_reservation1;
ALTER TABLE ONLY public.bill DROP CONSTRAINT fk_bill_bill_status1;
ALTER TABLE ONLY public.bedroom DROP CONSTRAINT fk_bedroom_type_bedroom1;
ALTER TABLE ONLY public.bedroom DROP CONSTRAINT fk_bedroom_bedroom_status1;
ALTER TABLE ONLY public.user_status DROP CONSTRAINT user_status_pkey;
ALTER TABLE ONLY public.user_ DROP CONSTRAINT user__pkey;
ALTER TABLE ONLY public.user_ DROP CONSTRAINT user__login_key;
ALTER TABLE ONLY public.user_ DROP CONSTRAINT user__client_key;
ALTER TABLE ONLY public.type_bedroom DROP CONSTRAINT type_bedroom_pkey;
ALTER TABLE ONLY public.reservation_status DROP CONSTRAINT reservation_status_pkey;
ALTER TABLE ONLY public.hotel_reservation DROP CONSTRAINT hotel_reservation_pkey;
ALTER TABLE ONLY public.client DROP CONSTRAINT client_social_security_number_key;
ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
ALTER TABLE ONLY public.client DROP CONSTRAINT client_email_key;
ALTER TABLE ONLY public.bill_status DROP CONSTRAINT bill_status_pkey;
ALTER TABLE ONLY public.bill DROP CONSTRAINT bill_pkey;
ALTER TABLE ONLY public.bill DROP CONSTRAINT bill_hotel_reservation_id_key;
ALTER TABLE ONLY public.bedroom_status DROP CONSTRAINT bedroom_status_pkey;
ALTER TABLE ONLY public.bedroom DROP CONSTRAINT bedroom_pkey;
ALTER TABLE ONLY public.bedroom DROP CONSTRAINT bedroom_number_key;
ALTER TABLE public.user_status ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.user_ ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.type_bedroom ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.reservation_status ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.hotel_reservation ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.client ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.bill_status ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.bill ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.bedroom_status ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.bedroom ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE public.user_status_id_seq;
DROP TABLE public.user_status;
DROP SEQUENCE public.user__id_seq;
DROP TABLE public.user_;
DROP SEQUENCE public.type_bedroom_id_seq;
DROP TABLE public.type_bedroom;
DROP SEQUENCE public.reservation_status_id_seq;
DROP TABLE public.reservation_status;
DROP SEQUENCE public.hotel_reservation_id_seq;
DROP TABLE public.hotel_reservation;
DROP SEQUENCE public.client_id_seq;
DROP TABLE public.client;
DROP SEQUENCE public.bill_status_id_seq;
DROP TABLE public.bill_status;
DROP SEQUENCE public.bill_id_seq;
DROP TABLE public.bill;
DROP SEQUENCE public.bedroom_status_id_seq;
DROP TABLE public.bedroom_status;
DROP SEQUENCE public.bedroom_id_seq;
DROP TABLE public.bedroom;
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bedroom; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE bedroom (
    id integer NOT NULL,
    description character varying(150) NOT NULL,
    floor integer,
    number integer NOT NULL,
    price_daily numeric(10,2) NOT NULL,
    type_bedroom_id integer NOT NULL,
    bedroom_status_id integer NOT NULL
);


ALTER TABLE public.bedroom OWNER TO blossonville;

--
-- Name: bedroom_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE bedroom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bedroom_id_seq OWNER TO blossonville;

--
-- Name: bedroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE bedroom_id_seq OWNED BY bedroom.id;


--
-- Name: bedroom_status; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE bedroom_status (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.bedroom_status OWNER TO blossonville;

--
-- Name: bedroom_status_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE bedroom_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bedroom_status_id_seq OWNER TO blossonville;

--
-- Name: bedroom_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE bedroom_status_id_seq OWNED BY bedroom_status.id;


--
-- Name: bill; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE bill (
    id integer NOT NULL,
    bill_status_id integer NOT NULL,
    hotel_reservation_id integer NOT NULL
);


ALTER TABLE public.bill OWNER TO blossonville;

--
-- Name: bill_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE bill_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bill_id_seq OWNER TO blossonville;

--
-- Name: bill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE bill_id_seq OWNED BY bill.id;


--
-- Name: bill_status; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE bill_status (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.bill_status OWNER TO blossonville;

--
-- Name: bill_status_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE bill_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bill_status_id_seq OWNER TO blossonville;

--
-- Name: bill_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE bill_status_id_seq OWNED BY bill_status.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE client (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    create_date timestamp without time zone NOT NULL,
    email character varying(50) NOT NULL,
    gender character varying(1) NOT NULL,
    social_security_number character varying(12) NOT NULL
);


ALTER TABLE public.client OWNER TO blossonville;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO blossonville;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- Name: hotel_reservation; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE hotel_reservation (
    id integer NOT NULL,
    entry_date date NOT NULL,
    exit_date date,
    price_daily numeric(10,2) NOT NULL,
    bedroom_id integer NOT NULL,
    reservation_status_id integer NOT NULL,
    client_id integer NOT NULL
);


ALTER TABLE public.hotel_reservation OWNER TO blossonville;

--
-- Name: hotel_reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE hotel_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hotel_reservation_id_seq OWNER TO blossonville;

--
-- Name: hotel_reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE hotel_reservation_id_seq OWNED BY hotel_reservation.id;


--
-- Name: reservation_status; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE reservation_status (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.reservation_status OWNER TO blossonville;

--
-- Name: reservation_status_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE reservation_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_status_id_seq OWNER TO blossonville;

--
-- Name: reservation_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE reservation_status_id_seq OWNED BY reservation_status.id;


--
-- Name: type_bedroom; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE type_bedroom (
    id integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.type_bedroom OWNER TO blossonville;

--
-- Name: type_bedroom_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE type_bedroom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_bedroom_id_seq OWNER TO blossonville;

--
-- Name: type_bedroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE type_bedroom_id_seq OWNED BY type_bedroom.id;


--
-- Name: user_; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE user_ (
    id integer NOT NULL,
    login character varying(45) NOT NULL,
    password character varying(100) NOT NULL,
    client_id integer NOT NULL,
    user_status_id integer NOT NULL,
    type_user integer DEFAULT 2 NOT NULL
);


ALTER TABLE public.user_ OWNER TO blossonville;

--
-- Name: user__id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE user__id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user__id_seq OWNER TO blossonville;

--
-- Name: user__id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE user__id_seq OWNED BY user_.id;


--
-- Name: user_status; Type: TABLE; Schema: public; Owner: blossonville; Tablespace: 
--

CREATE TABLE user_status (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.user_status OWNER TO blossonville;

--
-- Name: user_status_id_seq; Type: SEQUENCE; Schema: public; Owner: blossonville
--

CREATE SEQUENCE user_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_status_id_seq OWNER TO blossonville;

--
-- Name: user_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: blossonville
--

ALTER SEQUENCE user_status_id_seq OWNED BY user_status.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bedroom ALTER COLUMN id SET DEFAULT nextval('bedroom_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bedroom_status ALTER COLUMN id SET DEFAULT nextval('bedroom_status_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bill ALTER COLUMN id SET DEFAULT nextval('bill_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bill_status ALTER COLUMN id SET DEFAULT nextval('bill_status_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY hotel_reservation ALTER COLUMN id SET DEFAULT nextval('hotel_reservation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY reservation_status ALTER COLUMN id SET DEFAULT nextval('reservation_status_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY type_bedroom ALTER COLUMN id SET DEFAULT nextval('type_bedroom_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY user_ ALTER COLUMN id SET DEFAULT nextval('user__id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY user_status ALTER COLUMN id SET DEFAULT nextval('user_status_id_seq'::regclass);


--
-- Data for Name: bedroom; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY bedroom (id, description, floor, number, price_daily, type_bedroom_id, bedroom_status_id) FROM stdin;
2	BEDROOM ACCESSIBLE	1	200	250.00	6	1
5	NICE VIEW	2	102	300.00	5	1
7	CHILDREN	2	110	230.00	3	1
6	 MY PILLOW	2	103	200.00	2	1
8	OUR PILLOW	2	109	20.00	3	1
4	HAPPY FAMILY	2	21	250.00	4	1
\.


--
-- Name: bedroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('bedroom_id_seq', 8, true);


--
-- Data for Name: bedroom_status; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY bedroom_status (id, code, name) FROM stdin;
1	0	VACANT
2	1	BUSY
3	9	REFORM
\.


--
-- Name: bedroom_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('bedroom_status_id_seq', 3, true);


--
-- Data for Name: bill; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY bill (id, bill_status_id, hotel_reservation_id) FROM stdin;
43	1	5
42	2	1
50	3	8
\.


--
-- Name: bill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('bill_id_seq', 50, true);


--
-- Data for Name: bill_status; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY bill_status (id, code, name) FROM stdin;
1	1	PAY
2	9	CANCELLED
3	0	OPEN
\.


--
-- Name: bill_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('bill_status_id_seq', 3, true);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY client (id, name, create_date, email, gender, social_security_number) FROM stdin;
1	JOÃO PAULO	2016-01-01 00:00:00	JP.ALENCAR@HOTMAIL.COM	M	14190842
4	THALLYS	2016-01-01 00:00:00	jp.allenk@gmail.com	F	43411412
5	YANNE	2016-09-07 13:52:04.445	yanne_alencar@hotmail.com	F	5374855
6	Yuri	2016-09-12 00:31:03.279	yuri_alencar@hotmail.com	M	1241412
2	MARCOS OLIVEIVA	2016-01-01 00:00:00	marcos.ads.ti@gmail.com	F	234823984
\.


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('client_id_seq', 6, true);


--
-- Data for Name: hotel_reservation; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY hotel_reservation (id, entry_date, exit_date, price_daily, bedroom_id, reservation_status_id, client_id) FROM stdin;
3	2016-08-10	2016-09-16	90.00	2	1	1
5	2016-08-07	2016-09-12	90.00	5	1	1
7	2016-08-16	2016-09-18	90.00	6	1	1
8	2016-08-17	2016-08-25	90.00	8	1	1
1	2016-08-05	2016-08-15	90.00	4	3	1
\.


--
-- Name: hotel_reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('hotel_reservation_id_seq', 8, true);


--
-- Data for Name: reservation_status; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY reservation_status (id, code, name) FROM stdin;
1	1	CONFIRMED
2	9	CANCELLED
3	5	IN PROGRESS
4	0	CLOSED
\.


--
-- Name: reservation_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('reservation_status_id_seq', 4, true);


--
-- Data for Name: type_bedroom; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY type_bedroom (id, name) FROM stdin;
2	CLASSIC BED KING
3	CLASSIC BED TWIN
4	TOP BED TWIN
5	TOP BED KING
6	WHEELCHAIR ACCESS
\.


--
-- Name: type_bedroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('type_bedroom_id_seq', 6, true);


--
-- Data for Name: user_; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY user_ (id, login, password, client_id, user_status_id, type_user) FROM stdin;
13	Yanne	1966e694bad90686516f99cdf432800fdca39290	5	1	2
14	thallys	1966e694bad90686516f99cdf432800fdca39290	4	1	2
1	marc	7a5df5ffa0dec2228d90b8d0a0f1b0767b748b0a41314c123075b8289e4e053f	1	1	1
9	jp	7a5df5ffa0dec2228d90b8d0a0f1b0767b748b0a41314c123075b8289e4e053f	2	1	2
15	yuri	7a5df5ffa0dec2228d90b8d0a0f1b0767b748b0a41314c123075b8289e4e053f	6	1	2
\.


--
-- Name: user__id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('user__id_seq', 15, true);


--
-- Data for Name: user_status; Type: TABLE DATA; Schema: public; Owner: blossonville
--

COPY user_status (id, code, name) FROM stdin;
1	1	ACTIVE
2	0	INACTIVE
\.


--
-- Name: user_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: blossonville
--

SELECT pg_catalog.setval('user_status_id_seq', 2, true);


--
-- Name: bedroom_number_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bedroom
    ADD CONSTRAINT bedroom_number_key UNIQUE (number);


--
-- Name: bedroom_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bedroom
    ADD CONSTRAINT bedroom_pkey PRIMARY KEY (id);


--
-- Name: bedroom_status_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bedroom_status
    ADD CONSTRAINT bedroom_status_pkey PRIMARY KEY (id);


--
-- Name: bill_hotel_reservation_id_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT bill_hotel_reservation_id_key UNIQUE (hotel_reservation_id);


--
-- Name: bill_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- Name: bill_status_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY bill_status
    ADD CONSTRAINT bill_status_pkey PRIMARY KEY (id);


--
-- Name: client_email_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_email_key UNIQUE (email);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: client_social_security_number_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_social_security_number_key UNIQUE (social_security_number);


--
-- Name: hotel_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY hotel_reservation
    ADD CONSTRAINT hotel_reservation_pkey PRIMARY KEY (id);


--
-- Name: reservation_status_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY reservation_status
    ADD CONSTRAINT reservation_status_pkey PRIMARY KEY (id);


--
-- Name: type_bedroom_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY type_bedroom
    ADD CONSTRAINT type_bedroom_pkey PRIMARY KEY (id);


--
-- Name: user__client_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY user_
    ADD CONSTRAINT user__client_key UNIQUE (client_id);


--
-- Name: user__login_key; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY user_
    ADD CONSTRAINT user__login_key UNIQUE (login);


--
-- Name: user__pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY user_
    ADD CONSTRAINT user__pkey PRIMARY KEY (id);


--
-- Name: user_status_pkey; Type: CONSTRAINT; Schema: public; Owner: blossonville; Tablespace: 
--

ALTER TABLE ONLY user_status
    ADD CONSTRAINT user_status_pkey PRIMARY KEY (id);


--
-- Name: fk_bedroom_bedroom_status1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bedroom
    ADD CONSTRAINT fk_bedroom_bedroom_status1 FOREIGN KEY (bedroom_status_id) REFERENCES bedroom_status(id);


--
-- Name: fk_bedroom_type_bedroom1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bedroom
    ADD CONSTRAINT fk_bedroom_type_bedroom1 FOREIGN KEY (type_bedroom_id) REFERENCES type_bedroom(id);


--
-- Name: fk_bill_bill_status1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT fk_bill_bill_status1 FOREIGN KEY (bill_status_id) REFERENCES bill_status(id);


--
-- Name: fk_bill_hotel_reservation1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY bill
    ADD CONSTRAINT fk_bill_hotel_reservation1 FOREIGN KEY (hotel_reservation_id) REFERENCES hotel_reservation(id);


--
-- Name: fk_hotel_reservation_bedroom1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY hotel_reservation
    ADD CONSTRAINT fk_hotel_reservation_bedroom1 FOREIGN KEY (bedroom_id) REFERENCES bedroom(id);


--
-- Name: fk_hotel_reservation_client1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY hotel_reservation
    ADD CONSTRAINT fk_hotel_reservation_client1 FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: fk_hotel_reservation_reservation_status1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY hotel_reservation
    ADD CONSTRAINT fk_hotel_reservation_reservation_status1 FOREIGN KEY (reservation_status_id) REFERENCES reservation_status(id);


--
-- Name: fk_user__client1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY user_
    ADD CONSTRAINT fk_user__client1 FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: fk_user__user_status1; Type: FK CONSTRAINT; Schema: public; Owner: blossonville
--

ALTER TABLE ONLY user_
    ADD CONSTRAINT fk_user__user_status1 FOREIGN KEY (user_status_id) REFERENCES user_status(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

