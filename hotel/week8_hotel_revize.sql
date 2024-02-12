PGDMP  9                    |            hotel    16.1    16.1 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    32873    hotel    DATABASE     �   CREATE DATABASE hotel WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE hotel;
                postgres    false            �            1259    32874    hotel    TABLE     �  CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    mail character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    32879    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    32880    hotel_pansion    TABLE     �   CREATE TABLE public.hotel_pansion (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pansion_type character varying NOT NULL
);
 !   DROP TABLE public.hotel_pansion;
       public         heap    postgres    false            �            1259    32885    hotel_pension_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pansion ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    32886    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    32889    hotel_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    32890    reservation    TABLE     H  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id integer,
    check_in_date date,
    total_price double precision,
    quest_count integer,
    quest_name character varying,
    quest_identy character varying,
    quest_mail character varying,
    quest_phone character varying,
    check_out_date date
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    32895    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    32896    room    TABLE       CREATE TABLE public.room (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    squer_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    32901    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    32902    user    TABLE     s   CREATE TABLE public."user" (
    id bigint NOT NULL,
    user_name text,
    user_pass text,
    user_role text
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    32907    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225                      0    32874    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    215   (                 0    32880    hotel_pansion 
   TABLE DATA           C   COPY public.hotel_pansion (id, hotel_id, pansion_type) FROM stdin;
    public          postgres    false    217   �)                 0    32886    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    219   7*       
          0    32890    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, total_price, quest_count, quest_name, quest_identy, quest_mail, quest_phone, check_out_date) FROM stdin;
    public          postgres    false    221   �*                 0    32896    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, squer_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    223   _+                 0    32902    user 
   TABLE DATA           E   COPY public."user" (id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    225   ,                  0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 51, true);
          public          postgres    false    216                       0    0    hotel_pension_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hotel_pension_id_seq', 19, true);
          public          postgres    false    218                       0    0    hotel_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotel_season_id_seq', 23, true);
          public          postgres    false    220                       0    0    reservation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.reservation_id_seq', 7, true);
          public          postgres    false    222                       0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 17, true);
          public          postgres    false    224                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 22, true);
          public          postgres    false    226            l           2606    32909     hotel_pansion hotel_pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pansion
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pansion DROP CONSTRAINT hotel_pension_pkey;
       public            postgres    false    217            j           2606    32911    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            n           2606    32913    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    219            p           2606    32915    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    221            r           2606    32917    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    223            t           2606    32919    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    225               v  x����N�@�׷Oq�j�N;V�HPbbH܌p���S2�|������\8�E;i���9���H�Y�q�f���K���YX�?&R��<M������Ys;�.\��O1�@�Uj2���Z���ҹ�A�P�3ZS�ZxK1-��4rq����Z��1۟�JK}6��5s�DV��#� �!P��K�e�-M �4/F��ƌ�Z�ɥ���/��E��V��!{]I5��1����!cЮ~����F/B��H��!�!my ���[$);Z��\��Gϕ�aQ��nG0�ɯ�4���b�-�����&�f���t�rxP���L3i�Ԓ�wn���~65|�j�ǫ�}<)V=Ѳ�ڹw������         �   x�]�;
�@@�S�	��ͯE
j��B��	����XZ��{I:I�^�3�
�C��4w׵�L+�[�\���i�P�����K0)��_���TN�#0�5x���!��QJ{�TH�9Ѿ�I�C���''��� �I�2C��\8g         J   x�u���0Cѳ٥�NBv��s�C���8|��X���<�N�J��Vg�����,0�3�V��J�����~�!�      
   �   x�M�Q� ��N�fhK��鋧��,�3JLp\�y17p��4���K����7��N��4ޯ�'{
)��B�5q��s<���*�z%�wj��D�(H�Z'��d�q���u!�[�K���;[�jZP�������l���̠%��!�@y���Wo��C;_�_��Z�N��d�&W��~r�����SL\         �   x�m��
�@�ϳO�'�Mv�m��E񇂶 ��7�]� !a�2��(�Zt�p\��8>! �=�6�DֺY9b�DP��sA�Ȍ�|��H[�d��4���>/�3(���H��U�=�d�/�y��!��̣�_N%Z�q��g[��>��H,%��\w�8��l�A^         5   x�3�LL!ǔ��<.sΤ$r�-�ɯLM�22�L.��(-�L�F���qqq ��     