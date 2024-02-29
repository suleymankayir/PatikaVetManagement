PGDMP      !                |         	   patikavet    16.1    16.1 G    O           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            P           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Q           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            R           1262    17919 	   patikavet    DATABASE     k   CREATE DATABASE patikavet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE patikavet;
                postgres    false            �            1259    18899    animals    TABLE     �  CREATE TABLE public.animals (
    animal_id bigint NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_color character varying(255) NOT NULL,
    animal_date_of_birth date,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    18898    animals_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.animals_animal_customer_id_seq;
       public          postgres    false    217            S           0    0    animals_animal_customer_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.animals_animal_customer_id_seq OWNED BY public.animals.animal_customer_id;
          public          postgres    false    216            �            1259    18897    animals_animal_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.animals_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    217            T           0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    215            �            1259    18911    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    appointment_animal_id integer NOT NULL,
    appointment_doctor_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    18909 &   appointments_appointment_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_animal_id_seq;
       public          postgres    false    221            U           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_animal_id_seq OWNED BY public.appointments.appointment_animal_id;
          public          postgres    false    219            �            1259    18910 &   appointments_appointment_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_doctor_id_seq;
       public          postgres    false    221            V           0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_doctor_id_seq OWNED BY public.appointments.appointment_doctor_id;
          public          postgres    false    220            �            1259    18908    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    221            W           0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    218            �            1259    18921    availabledates    TABLE     �   CREATE TABLE public.availabledates (
    availabledate_id bigint NOT NULL,
    available_date date NOT NULL,
    doctor_available_date_id integer NOT NULL
);
 "   DROP TABLE public.availabledates;
       public         heap    postgres    false            �            1259    18919 #   availabledates_availabledate_id_seq    SEQUENCE     �   CREATE SEQUENCE public.availabledates_availabledate_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.availabledates_availabledate_id_seq;
       public          postgres    false    224            X           0    0 #   availabledates_availabledate_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.availabledates_availabledate_id_seq OWNED BY public.availabledates.availabledate_id;
          public          postgres    false    222            �            1259    18920 +   availabledates_doctor_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.availabledates_doctor_available_date_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 B   DROP SEQUENCE public.availabledates_doctor_available_date_id_seq;
       public          postgres    false    224            Y           0    0 +   availabledates_doctor_available_date_id_seq    SEQUENCE OWNED BY     {   ALTER SEQUENCE public.availabledates_doctor_available_date_id_seq OWNED BY public.availabledates.doctor_available_date_id;
          public          postgres    false    223            �            1259    18929 	   customers    TABLE     #  CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_email character varying(255),
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    18928    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    226            Z           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    225            �            1259    18938    doctors    TABLE       CREATE TABLE public.doctors (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255),
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    18937    doctors_doctor_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.doctors_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    228            [           0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    227            �            1259    18948    vaccines    TABLE       CREATE TABLE public.vaccines (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255),
    vaccine_protection_finish_date date NOT NULL,
    vaccine_name character varying(255),
    vaccine_protection_start_date date NOT NULL,
    vaccine_animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    18947    vaccines_vaccine_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.vaccines_vaccine_animal_id_seq;
       public          postgres    false    231            \           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.vaccines_vaccine_animal_id_seq OWNED BY public.vaccines.vaccine_animal_id;
          public          postgres    false    230            �            1259    18946    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    231            ]           0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    229            �           2604    18902    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    215    217    217            �           2604    18903    animals animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animals ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animals_animal_customer_id_seq'::regclass);
 I   ALTER TABLE public.animals ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    18914    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    221    218    221            �           2604    18915 "   appointments appointment_animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_animal_id SET DEFAULT nextval('public.appointments_appointment_animal_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_animal_id DROP DEFAULT;
       public          postgres    false    221    219    221            �           2604    18916 "   appointments appointment_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_doctor_id SET DEFAULT nextval('public.appointments_appointment_doctor_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_doctor_id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    18924    availabledates availabledate_id    DEFAULT     �   ALTER TABLE ONLY public.availabledates ALTER COLUMN availabledate_id SET DEFAULT nextval('public.availabledates_availabledate_id_seq'::regclass);
 N   ALTER TABLE public.availabledates ALTER COLUMN availabledate_id DROP DEFAULT;
       public          postgres    false    222    224    224            �           2604    18925 '   availabledates doctor_available_date_id    DEFAULT     �   ALTER TABLE ONLY public.availabledates ALTER COLUMN doctor_available_date_id SET DEFAULT nextval('public.availabledates_doctor_available_date_id_seq'::regclass);
 V   ALTER TABLE public.availabledates ALTER COLUMN doctor_available_date_id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    18932    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    18941    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    228    227    228            �           2604    18951    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    229    231    231            �           2604    18952    vaccines vaccine_animal_id    DEFAULT     �   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_animal_id SET DEFAULT nextval('public.vaccines_vaccine_animal_id_seq'::regclass);
 I   ALTER TABLE public.vaccines ALTER COLUMN vaccine_animal_id DROP DEFAULT;
       public          postgres    false    231    230    231            >          0    18899    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_breed, animal_color, animal_date_of_birth, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    217   1Y       B          0    18911    appointments 
   TABLE DATA           v   COPY public.appointments (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    221   Z       E          0    18921    availabledates 
   TABLE DATA           d   COPY public.availabledates (availabledate_id, available_date, doctor_available_date_id) FROM stdin;
    public          postgres    false    224   ]Z       G          0    18929 	   customers 
   TABLE DATA           �   COPY public.customers (customer_id, customer_address, customer_city, customer_email, customer_name, customer_phone) FROM stdin;
    public          postgres    false    226   �Z       I          0    18938    doctors 
   TABLE DATA           q   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    228   x[       L          0    18948    vaccines 
   TABLE DATA           �   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_protection_finish_date, vaccine_name, vaccine_protection_start_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    231   t\       ^           0    0    animals_animal_customer_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.animals_animal_customer_id_seq', 1, false);
          public          postgres    false    216            _           0    0    animals_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animals_animal_id_seq', 5, true);
          public          postgres    false    215            `           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_animal_id_seq', 1, false);
          public          postgres    false    219            a           0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_doctor_id_seq', 1, false);
          public          postgres    false    220            b           0    0    appointments_appointment_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 6, true);
          public          postgres    false    218            c           0    0 #   availabledates_availabledate_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.availabledates_availabledate_id_seq', 10, true);
          public          postgres    false    222            d           0    0 +   availabledates_doctor_available_date_id_seq    SEQUENCE SET     Z   SELECT pg_catalog.setval('public.availabledates_doctor_available_date_id_seq', 1, false);
          public          postgres    false    223            e           0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 4, true);
          public          postgres    false    225            f           0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 5, true);
          public          postgres    false    227            g           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.vaccines_vaccine_animal_id_seq', 1, false);
          public          postgres    false    230            h           0    0    vaccines_vaccine_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 11, true);
          public          postgres    false    229            �           2606    18907    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    217            �           2606    18918    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    221            �           2606    18927 "   availabledates availabledates_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.availabledates
    ADD CONSTRAINT availabledates_pkey PRIMARY KEY (availabledate_id);
 L   ALTER TABLE ONLY public.availabledates DROP CONSTRAINT availabledates_pkey;
       public            postgres    false    224            �           2606    18936    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    226            �           2606    18945    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    228            �           2606    18956    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    231            �           2606    18967 '   appointments fk27d0xcbajwaeeun2doojsae4    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk27d0xcbajwaeeun2doojsae4 FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctors(doctor_id);
 Q   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk27d0xcbajwaeeun2doojsae4;
       public          postgres    false    228    221    3493            �           2606    18977 $   vaccines fkekhfjmpsduds8nnilqe9b467v    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkekhfjmpsduds8nnilqe9b467v FOREIGN KEY (vaccine_animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkekhfjmpsduds8nnilqe9b467v;
       public          postgres    false    217    3485    231            �           2606    18957 #   animals fknjsvd8kplxqmf48ybxayrx6ru    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru FOREIGN KEY (animal_customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru;
       public          postgres    false    217    226    3491            �           2606    18962 (   appointments fko4t945rb708af9ry6syof7bwt    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fko4t945rb708af9ry6syof7bwt FOREIGN KEY (appointment_animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fko4t945rb708af9ry6syof7bwt;
       public          postgres    false    3485    217    221            �           2606    18972 *   availabledates fkpgmwor95oxcqxcd2a53fcm4cl    FK CONSTRAINT     �   ALTER TABLE ONLY public.availabledates
    ADD CONSTRAINT fkpgmwor95oxcqxcd2a53fcm4cl FOREIGN KEY (doctor_available_date_id) REFERENCES public.doctors(doctor_id);
 T   ALTER TABLE ONLY public.availabledates DROP CONSTRAINT fkpgmwor95oxcqxcd2a53fcm4cl;
       public          postgres    false    228    3493    224            >   �   x�m�K�AEǩU�J*�6���ATx�7���n+R��ո7 ����Ӊ�I.9���T�B2�:2(���m:�bI%��ޟar�n*��@�@$@?�1$KW��ڠ��̹�������y�ߌ�Ոc�N�>�ڰ`����\�̄CݸRm2��~a��{�R��L�W?k����i���v����~[J�?}EP�      B   E   x�u��	�@C�sR��L2�"�b�u��E�����OD�r_f+�d}D���ar��\I^���      E   D   x�M̱� �����~�d�9���/��ht�9���$R�(ea(��}�GI,eb+��<��v ҭE      G   �   x�M�=�0����)z"��Uc0qpuy�4��0��x]��L�����/�Ci������gۡ������5��
�B�!���Ar#cƅL���|��C���6���1� ��pD���*�ukѭ�)�i_*;�m̘�9��L���n��5�ϛ�w���az�<��<��B��,P�      I   �   x���;N�0���)� `��ZѠ����Akَ��-���r��{aG�DJ�bc}����
�Ҟ�������|򷸀�'�Լ,P�,����;_�i+w��&H���7�K,.�R�Qb9G���1���f�Äh;`�<�\���*G���jIC�&,�c�#ǕX�q���"Irg���lHR:��㸴k��T.�%���85�)�D�&T�ƪ���F�I!�?���      L   �   x���M�0FםSx��δU\�� qCb��/��x�^(�������t�Afb.2$�@���&k΋�~�Z۵}��A����Q ^���7��S�j.�S�LZґ�Ç�@�!9�9,|,aŒ��],X���fa��?���R�-��[�9QO��Z���k a��=�}n龬9�$��������E�<A��G�'�n	 ����     