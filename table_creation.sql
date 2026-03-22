-- MYSQL specific syntax
USE movies;

CREATE TABLE Genres (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	genre NVARCHAR(50)
);

CREATE TABLE Directors (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	name NVARCHAR(100)
);

CREATE TABLE Countries(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	country NVARCHAR(50)
);

CREATE TABLE Movies (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	title NVARCHAR(100),
	duration INT, -- in minutes
    release_date INT,
    poster_URL VARCHAR(2048)
);

CREATE TABLE Movie_Genre (
	movie_ID INT,
	genre_ID INT,
	FOREIGN KEY (movie_ID) REFERENCES Movies(ID),
	FOREIGN KEY (genre_ID) REFERENCES Genres(ID)
);

CREATE TABLE Movie_Director (
	movie_ID INT,
	director_ID INT,
	FOREIGN KEY (movie_ID) REFERENCES Movies(ID),
	FOREIGN KEY (director_ID) REFERENCES Directors(ID)
);

CREATE TABLE Movie_Country (
    movie_ID INT,
    country_ID INT,
    FOREIGN KEY (movie_ID) REFERENCES Movies(ID),
    FOREIGN KEY (country_ID) REFERENCES Countries(ID)
);
