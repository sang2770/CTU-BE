CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE farms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    boundary geometry(Polygon, 4326) -- WGS84 Coordinate System
);
