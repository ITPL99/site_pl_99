-- =============================================================================
-- Test Data Migration for Development/Testing
-- This migration inserts sample data for local development and testing
-- =============================================================================

-- =============================================================================
-- 1. ROLES (ADMIN already exists from baseline)
-- =============================================================================
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('MODERATOR');

-- =============================================================================
-- 2. USERS (password = 'password123' encoded with BCrypt)
-- =============================================================================
INSERT INTO users (username, password, active, active_code, email) 
VALUES ('admin', '$2a$10$N9qoCWz.x6hLsCFZ8iFY5eDzGYqzKqVQd8QFhF3dPbU7X.8tC6qI2', 'ACTIVE', NULL, 'admin@pl99.kg');

INSERT INTO users (username, password, active, active_code, email) 
VALUES ('testuser', '$2a$10$N9qoCWz.x6hLsCFZ8iFY5eDzGYqzKqVQd8QFhF3dPbU7X.8tC6qI2', 'ACTIVE', NULL, 'test@example.com');

INSERT INTO users (username, password, active, active_code, email) 
VALUES ('moderator', '$2a$10$N9qoCWz.x6hLsCFZ8iFY5eDzGYqzKqVQd8QFhF3dPbU7X.8tC6qI2', 'ACTIVE', NULL, 'moderator@pl99.kg');

-- =============================================================================
-- 3. USER_ROLES ASSIGNMENTS
-- =============================================================================
INSERT INTO m2m_users_roles (user_id, role_id) 
VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE role_name = 'ADMIN'));

INSERT INTO m2m_users_roles (user_id, role_id) 
VALUES ((SELECT id FROM users WHERE username = 'testuser'), (SELECT id FROM roles WHERE role_name = 'USER'));

INSERT INTO m2m_users_roles (user_id, role_id) 
VALUES ((SELECT id FROM users WHERE username = 'moderator'), (SELECT id FROM roles WHERE role_name = 'MODERATOR'));

-- =============================================================================
-- 4. IMAGES (file references)
-- =============================================================================
INSERT INTO images (file_name) VALUES ('employee_1.jpg');
INSERT INTO images (file_name) VALUES ('employee_2.jpg');
INSERT INTO images (file_name) VALUES ('master_1.jpg');
INSERT INTO images (file_name) VALUES ('master_2.jpg');
INSERT INTO images (file_name) VALUES ('teacher_1.jpg');
INSERT INTO images (file_name) VALUES ('teacher_2.jpg');
INSERT INTO images (file_name) VALUES ('news_main.jpg');
INSERT INTO images (file_name) VALUES ('news_secondary.jpg');
INSERT INTO images (file_name) VALUES ('course_python.jpg');
INSERT INTO images (file_name) VALUES ('course_design.jpg');
INSERT INTO images (file_name) VALUES ('course_marketing.jpg');
INSERT INTO images (file_name) VALUES ('main_hero.jpg');

-- =============================================================================
-- 5. VIDEOS
-- =============================================================================
INSERT INTO videos (file_name) VALUES ('intro_video.mp4');
INSERT INTO videos (file_name) VALUES ('course_demo.mp4');

-- =============================================================================
-- 6. EMPLOYEES
-- =============================================================================
INSERT INTO employees (full_name, date_berth, image_id, department_ru, department_kg, active, date_employment, date_dismissal)
VALUES ('Иванов Иван Иванович', '1985-05-15', 1, 'Отдел разработки', 'Иштеп чыгуу бөлүмү', 'ACTIVE', '2020-01-15', NULL);

INSERT INTO employees (full_name, date_berth, image_id, department_ru, department_kg, active, date_employment, date_dismissal)
VALUES ('Петрова Мария Сергеевна', '1990-08-22', 2, 'Отдел маркетинга', 'Маркетинг бөлүмү', 'ACTIVE', '2021-03-10', NULL);

-- =============================================================================
-- 7. MASTERS
-- =============================================================================
INSERT INTO masters (full_name, date_berth, profession_ru, profession_kg, image_id, active, date_employment, date_dismissal)
VALUES ('Сидоров Алексей Петрович', '1978-03-12', 'Программист Python', 'Python программист', 3, 'ACTIVE', '2019-06-01', NULL);

INSERT INTO masters (full_name, date_berth, profession_ru, profession_kg, image_id, active, date_employment, date_dismissal)
VALUES ('Ким Айгуль Талгатовна', '1988-11-25', 'UX/UI Дизайнер', 'UX/UI Дизайнер', 4, 'ACTIVE', '2020-09-15', NULL);

-- =============================================================================
-- 8. TEACHERS
-- =============================================================================
INSERT INTO teachers (full_name, date_berth, link_portfolio, image_id, active, date_employment, date_dismissal)
VALUES ('Алиев Бактияр Сатыбалдиевич', '1982-07-08', 'https://portfolio.example.com/aliev', 5, 'ACTIVE', '2018-02-20', NULL);

INSERT INTO teachers (full_name, date_berth, link_portfolio, image_id, active, date_employment, date_dismissal)
VALUES ('Бекова Светлана Игоревна', '1992-04-18', 'https://portfolio.example.com/bekova', 6, 'ACTIVE', '2022-01-10', NULL);

-- =============================================================================
-- 9. NEWS
-- =============================================================================
INSERT INTO news (title_ru, title_kg, subtitle_ru, subtitle_kg, description_ru, description_kg, image_small, image_full, video_content, active)
VALUES (
    'Открытие нового курса по Python',
    'Python боюнча жаңы курс ачылды',
    'Начни карьеру программиста',
    'Программист карьерасын башта',
    'Полный курс обучения Python от основ до продвинутого уровня. Практические задания, менторство.',
    'Python боюнча толук окуу курсу: негиздерден илгерилеген деңгээлге чейин. Практикалык тапшырмалар, ментордук.',
    7, 7, 1, 'ACTIVE'
);

INSERT INTO news (title_ru, title_kg, subtitle_ru, subtitle_kg, description_ru, description_kg, image_small, image_full, video_content, active)
VALUES (
    'Набор студентов на 2026 год',
    '2026-жылга студенттерди кабыл алуу',
    'Присоединяйся к PL99',
    'PL99''га кошул',
    'Открыт набор на все направления обучения. Скидки при ранней регистрации.',
    'Бардык багыттар боюнча окууга катталуу ачык. Эрте катталууда арзандатуу.',
    8, 8, NULL, 'ACTIVE'
);

-- =============================================================================
-- 10. COURSES
-- =============================================================================
INSERT INTO course (type, title_ru, title_kg, description_ru, description_kg, price, date_start, date_end, image_id, active)
VALUES ('PROGRAMMING', 'Python для начинающих', 'Python баштапкылар үчүн', 
    'Базовый курс по Python. Переменные, циклы, функции, ООП.',
    'Python боюнча базалык курс. Өзгөрмөлөр, циклдер, функциялар, ООП.',
    15000.00, '2026-05-01', '2026-07-01', 9, 'ACTIVE');

INSERT INTO course (type, title_ru, title_kg, description_ru, description_kg, price, date_start, date_end, image_id, active)
VALUES ('DESIGN', 'UI/UX Дизайн', 'UI/UX Дизайн',
    'Проектирование интерфейсов, Figma, прототипирование, пользовательские исследования.',
    'Интерфейстерди долбоорлоо, Figma, прототиптөө, колдонуучуларды изилдөө.',
    20000.00, '2026-06-01', '2026-09-01', 10, 'ACTIVE');

INSERT INTO course (type, title_ru, title_kg, description_ru, description_kg, price, date_start, date_end, image_id, active)
VALUES ('MARKETING', 'Digital Marketing', 'Санарип Маркетинг',
    'SMM, SEO, контекстная реклама, аналитика, стратегия.',
    'SMM, SEO, контексттук жарнама, аналитика, стратегия.',
    18000.00, '2026-05-15', '2026-08-15', 11, 'NEW');

-- =============================================================================
-- 11. MAIN_MENU (Hero section data)
-- =============================================================================
INSERT INTO main_menu (title_ru, title_kg, subtitle_ru, subtitle_kg, amount_students, amount_graduated, amount_partners, image_id)
VALUES (
    'Образовательный центр PL99',
    'PL99 билим берүү борбору',
    'Современное образование для вашего будущего',
    'Сиздин келечегиңиз үчүн заманбап билим берүү',
    1250, 850, 45, 12
);

-- =============================================================================
-- 12. MAILS (Sample contact form submissions)
-- =============================================================================
INSERT INTO mails (title, content, email_to, status, date_created)
VALUES ('Вопрос о курсе Python', 'Здравствуйте! Хочу узнать расписание занятий по курсу Python для начинающих.', 'info@pl99.kg', 'CONSIDER', CURRENT_DATE);

INSERT INTO mails (title, content, email_to, status, date_created)
VALUES ('Запись на консультацию', 'Прошу записать меня на консультацию по выбору курса. Тел: 0555 123456', 'manager@pl99.kg', 'ANSWERED', CURRENT_DATE - 2);

INSERT INTO mails (title, content, email_to, status, date_created)
VALUES ('Сотрудничество', 'Наша компания заинтересована в корпоративном обучении сотрудников. Свяжитесь для обсуждения.', 'ceo@pl99.kg', 'CONSIDER', CURRENT_DATE - 1);

-- =============================================================================
-- END OF TEST DATA MIGRATION
-- =============================================================================
