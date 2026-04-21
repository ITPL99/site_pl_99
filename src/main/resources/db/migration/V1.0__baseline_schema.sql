-- =============================================================================
-- Baseline Schema Migration
-- Consolidated migration combining all previous migrations
-- Timestamp: 2025-04-21
-- =============================================================================

-- =============================================================================
-- 1. CORE TABLES
-- =============================================================================

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active VARCHAR(50),
    active_code VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Roles table
CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

-- Many-to-many: users <-> roles
CREATE TABLE IF NOT EXISTS m2m_users_roles (
    user_id BIGINT NOT NULL REFERENCES users(id),
    role_id BIGINT NOT NULL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

-- =============================================================================
-- 2. MEDIA TABLES
-- =============================================================================

-- Images table
CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(500) UNIQUE
);

-- Videos table
CREATE TABLE IF NOT EXISTS videos (
    id BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(500) UNIQUE
);

-- =============================================================================
-- 3. EMPLOYEE/PERSONNEL TABLES
-- =============================================================================

-- Employees table
CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    date_berth DATE NOT NULL DEFAULT CURRENT_DATE,
    image_id BIGINT UNIQUE REFERENCES images(id),
    department_ru VARCHAR(255) NOT NULL,
    department_kg VARCHAR(255) NOT NULL,
    active VARCHAR(50),
    date_dismissal DATE,
    date_employment DATE
);

-- Masters table
CREATE TABLE IF NOT EXISTS masters (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    date_berth DATE NOT NULL DEFAULT CURRENT_DATE,
    profession_ru VARCHAR(255),
    profession_kg VARCHAR(255),
    image_id BIGINT UNIQUE REFERENCES images(id),
    active VARCHAR(50),
    date_dismissal DATE,
    date_employment DATE
);

-- Teachers table
CREATE TABLE IF NOT EXISTS teachers (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    date_berth DATE NOT NULL DEFAULT CURRENT_DATE,
    link_portfolio VARCHAR(500),
    image_id BIGINT UNIQUE REFERENCES images(id),
    active VARCHAR(50),
    date_dismissal DATE,
    date_employment DATE
);

-- =============================================================================
-- 4. CONTENT TABLES
-- =============================================================================

-- News table
CREATE TABLE IF NOT EXISTS news (
    id BIGSERIAL PRIMARY KEY,
    title_ru VARCHAR(255),
    title_kg VARCHAR(255),
    subtitle_ru VARCHAR(255),
    subtitle_kg VARCHAR(255),
    description_ru TEXT,
    description_kg TEXT,
    date_create TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    image_small BIGINT UNIQUE REFERENCES images(id),
    image_full BIGINT UNIQUE REFERENCES images(id),
    video_content BIGINT UNIQUE REFERENCES videos(id),
    active VARCHAR(50)
);

-- Many-to-many: news <-> images (content images)
CREATE TABLE IF NOT EXISTS m2m_images_news (
    news_id BIGINT NOT NULL REFERENCES news(id),
    image_id BIGINT NOT NULL REFERENCES images(id),
    PRIMARY KEY (news_id, image_id)
);

-- Course table
CREATE TABLE IF NOT EXISTS course (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50),
    title_ru VARCHAR(255) NOT NULL,
    title_kg VARCHAR(255) NOT NULL,
    description_ru TEXT NOT NULL,
    description_kg TEXT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    date_start DATE,
    date_end DATE,
    image_id BIGINT REFERENCES images(id),
    active VARCHAR(50) DEFAULT 'NEW'
);

-- Main menu table
CREATE TABLE IF NOT EXISTS main_menu (
    id BIGSERIAL PRIMARY KEY,
    title_ru VARCHAR(255) NOT NULL,
    title_kg VARCHAR(255) NOT NULL,
    subtitle_ru VARCHAR(255) NOT NULL,
    subtitle_kg VARCHAR(255) NOT NULL,
    amount_students BIGINT DEFAULT 0,
    amount_graduated BIGINT DEFAULT 0,
    amount_partners BIGINT DEFAULT 0,
    image_id BIGINT REFERENCES images(id)
);

-- =============================================================================
-- 5. SERVICE TABLES
-- =============================================================================

-- Mails table (contact form submissions)
CREATE TABLE IF NOT EXISTS mails (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    email_to VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'CONSIDER',
    date_created DATE DEFAULT CURRENT_DATE
);

-- =============================================================================
-- 6. SEED DATA
-- =============================================================================

-- Insert default admin role
INSERT INTO roles (role_name) VALUES ('ADMIN');

-- =============================================================================
-- END OF MIGRATION
-- =============================================================================
