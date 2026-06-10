package com.example.agent.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ai_chat_options")
public class AiChatOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(name = "max_tokens", nullable = false)
    private Integer maxTokens;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "default_flag", nullable = false)
    private boolean defaultFlag;

    protected AiChatOptions() {}

    public AiChatOptions(String name, String model, Integer maxTokens, Double temperature, boolean defaultFlag) {
        this.name = name;
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.defaultFlag = defaultFlag;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getModel() { return model; }
    public Integer getMaxTokens() { return maxTokens; }
    public Double getTemperature() { return temperature; }
    public boolean isDefaultFlag() { return defaultFlag; }

    public void setDefaultFlag(boolean defaultFlag) { this.defaultFlag = defaultFlag; }
}
