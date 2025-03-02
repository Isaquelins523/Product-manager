CREATE TABLE products (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    price_in_cents INTEGER NOT NULL
);