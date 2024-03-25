CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(100) NOT NULL
);

CREATE TABLE habits (
    id SERIAL PRIMARY KEY,
    habit_name VARCHAR(100) NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE,
    habit_progress INTEGER,
    habit_description TEXT,
    user_id INTEGER REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_user_id ON habits(user_id);

CREATE TABLE habit_logs (
    id SERIAL PRIMARY KEY,
    habit_id INTEGER REFERENCES habits(id),
    log_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_habit_id FOREIGN KEY (habit_id) REFERENCES habits(id) ON DELETE CASCADE
);

CREATE TABLE habit_history (
    id SERIAL PRIMARY KEY,
    habit_id INTEGER REFERENCES habits(id),
    execution_date DATE,
    CONSTRAINT fk_habit_id_history FOREIGN KEY (habit_id) REFERENCES habits(id) ON DELETE CASCADE
);
