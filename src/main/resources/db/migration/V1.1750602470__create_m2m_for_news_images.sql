alter table news drop column image_content;

create table m2m_images_news(
    news_id bigint references news(id),
    image_id bigint references images(id)
)