package com.semantyca.djinn.model.stream;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StreamAgenda {
    private final LocalDateTime createdAt;
    private final List<LiveScene> liveScenes;

    public StreamAgenda(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.liveScenes = new ArrayList<>();
    }

    public void addScene(LiveScene entry) {
        this.liveScenes.add(entry);
    }
}
