-- Raw query, gets all columns
SELECT * FROM Movies AS m 
LEFT JOIN Movie_Genre AS mg
ON m.ID = mg.movie_ID
INNER JOIN Directors AS d
ON m.director_ID = d.ID
INNER JOIN Countries AS c
ON m.country_ID = c.ID;

-- Complete query to get all info. of a Movie
SELECT m.ID, m.title, m.duration, d.name, c.country, g.genre FROM Movies AS m 
LEFT JOIN Movie_Genre AS mg
ON m.ID = mg.movie_ID
INNER JOIN Directors AS d
ON m.director_ID = d.ID
INNER JOIN Countries AS c
ON m.country_ID = c.ID
INNER JOIN Genres AS g
ON mg.genre_ID = g.ID;

-- Suggested by AI
SELECT
    m.*,
    c.country,
    GROUP_CONCAT(DISTINCT g.genre ORDER BY g.genre) AS genres,
    GROUP_CONCAT(DISTINCT d.name ORDER BY d.name) AS directors
FROM movies m

LEFT JOIN movie_country mc ON m.ID = mc.movie_ID
LEFT JOIN countries c ON mc.country_ID = c.ID

LEFT JOIN movie_genre mg ON m.ID = mg.movie_ID
LEFT JOIN genres g ON g.ID = mg.genre_ID

LEFT JOIN movie_director md ON m.ID = md.movie_ID
LEFT JOIN directors d ON d.ID = md.director_ID

GROUP BY m.ID, c.country;