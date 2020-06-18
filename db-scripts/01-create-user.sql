create user 'ecommerce'@'localhost' identified by 'ecommerce';

grant all privileges on *.* to 'ecommerce'@'localhost';

alter user 'ecommerce'@'localhost' identified with caching_sha2_password by 'ecommerce';