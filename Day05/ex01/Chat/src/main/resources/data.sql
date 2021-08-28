INSERT INTO public.user (login, password) VALUES
('cat', 'passcat'), ('dog', 'passdpg'), ('bird', 'passbird'), ('fish', 'passfish'), ('rabbit', 'passrabbit');

INSERT INTO public.chatroom (name, owner_id) VALUES
('chat1', 1), ('chat2', 2), ('chat3', 3), ('chat4', 2), ('chat5', 5);

INSERT INTO public.user_chatroom (user_id, chatroom_id) VALUES
(1, 1), (5, 1), (1, 2), (2, 2), (3, 2), (2, 3), (3, 3), (4, 3), (1, 4), (2, 4), (4, 4), (1, 5), (2, 5), (3, 5), (4, 5), (5, 5);

INSERT INTO public.message (author_id, room_id, text) VALUES
(1, 5, 'Hello everyone!'),
(2, 5, 'Nice to meet you all!'),
(3, 2, 'How are you, my friends?'),
(2, 2, 'I am fine, thanks, and you?'),
(3, 2, 'I am also fine');