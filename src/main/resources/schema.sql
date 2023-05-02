create table person
(
    `id`   int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255) NOT NULL
);

create table location
(
    `id`          int AUTO_INCREMENT PRIMARY KEY,
    `reference_id` int    NOT NULL,
    `latitude`    double not null,
    `longitude`   double not null
);
