CREATE ROLE root WITH PASSWORD 'root' LOGIN SUPERUSER;
CREATE DATABASE chat WITH OWNER = root;
GRANT ALL PRIVILEGES ON DATABASE chat TO root;

CREATE TABLE public.user (
    id serial PRIMARY KEY NOT NULL,
    login varchar,
    password varchar
);

CREATE TABLE public.chatroom (
    id serial PRIMARY KEY NOT NULL,
    name varchar,
    owner_id bigint,
    CONSTRAINT fk_user1 FOREIGN KEY(owner_id) REFERENCES public.user(id)
);

CREATE TABLE public.user_chatroom (
    user_id bigint NOT NULL,
    chatroom_id bigint NOT NULL,
    CONSTRAINT fk_user2 FOREIGN KEY(user_id) REFERENCES public.user(id),
    CONSTRAINT fk_chatroom1 FOREIGN KEY(chatroom_id) REFERENCES public.chatroom(id)
);

CREATE TABLE public.message (
    id serial  PRIMARY KEY NOT NULL,
    author_id bigint,
    room_id bigint,
    text text,
    date timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user3 FOREIGN KEY(author_id) REFERENCES public.user(id),
    CONSTRAINT fk_chatroom2 FOREIGN KEY(room_id) REFERENCES public.chatroom(id)
);
