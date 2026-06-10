INSERT INTO ai_chat_options (name, model, max_tokens, temperature, default_flag) VALUES
('standard', 'claude-opus-4-8', 1024, 1.0, true),
('creative', 'claude-opus-4-8', 2048, 1.0, false),
('fast',     'claude-haiku-4-5-20251001', 512, 0.5, false);
